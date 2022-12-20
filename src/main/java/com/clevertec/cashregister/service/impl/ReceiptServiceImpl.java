package com.clevertec.cashregister.service.impl;

import com.clevertec.cashregister.converter.ItemConverter;
import com.clevertec.cashregister.converter.ReceiptConverter;
import com.clevertec.cashregister.dto.ReceiptDto;
import com.clevertec.cashregister.dto.page.PageContentDto;
import com.clevertec.cashregister.dto.page.PageDto;
import com.clevertec.cashregister.entity.*;
import com.clevertec.cashregister.repository.*;
import com.clevertec.cashregister.service.ReceiptService;
import com.clevertec.cashregister.util.ReceiptSettingsUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final CardRepository cardRepository;
    private final ProductRepository productRepository;
    private final ReceiptRepository receiptRepository;
    private final ReceiptHeaderRepository receiptHeaderRepository;
    private final ReceiptItemRepository receiptItemRepository;
    private final ReceiptFooterRepository receiptFooterRepository;

    private final ReceiptConverter receiptConverter;
    private final ItemConverter itemConverter;

    @Override
    public ReceiptDto findById(Long id) {
        return receiptConverter.convert(
                receiptRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Receipt not found by id: " + id)
                )
        );
    }

    @Override
    public PageContentDto<ReceiptDto> findAll(int pageNumber, int pageSize) {
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<Receipt> receiptPage = receiptRepository.findAll(pageable);

        PageDto pageDto = PageDto
                .builder()
                .totalElements(receiptPage.getTotalElements())
                .totalPages(receiptPage.getTotalPages())
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .offset(pageable.getOffset())
                .hasPrevious(pageable.hasPrevious())
                .build();

        List<ReceiptDto> receiptDtoList = receiptPage.getContent().isEmpty()
                ? Collections.emptyList()
                : receiptPage.getContent().stream().map(receiptConverter::convert).toList();

        PageContentDto<ReceiptDto> pageContentDto = new PageContentDto<>();
        pageContentDto.setPage(pageDto);
        pageContentDto.setContent(receiptDtoList);
        return pageContentDto;
    }

    @Override
//    @Transactional
    public ReceiptDto create(Long cardNumber, Long[] barcodeArray) {
        if (barcodeArray == null || barcodeArray.length == 0) {
            throw new ValidationException("Barcode list is empty");
        }

        Card card = cardNumber == null
                ? new Card(0)
                : cardRepository.findById(cardNumber).orElse(new Card(0));

        // create receipt
        Receipt receipt = receiptRepository.save(new Receipt());

        // create header
        ReceiptHeader receiptHeader = receiptHeaderRepository.save(ReceiptHeader.builder().dateTime(new Date()).build());

        // create body
        Map<Long, Integer> uniqAndCount = Arrays
                .stream(barcodeArray)
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        List<ReceiptItem> receiptItemList = new ArrayList<>(uniqAndCount.size());
        for (Map.Entry<Long, Integer> entry : uniqAndCount.entrySet()) {
            Product product = productRepository.findById(entry.getKey()).orElseThrow(
                    () -> new EntityNotFoundException("Product not found by barcode: " + entry.getKey())
            );
            ReceiptItem receiptItem = itemConverter.convert(product);
            receiptItem.setQuantity(entry.getValue());
            BigDecimal fullPrice = receiptItem.getPrice().multiply(BigDecimal.valueOf(receiptItem.getQuantity()));
            if (entry.getValue() > 5 && receiptItem.getAction()) {
                BigDecimal discount = fullPrice.multiply(BigDecimal.valueOf((double) card.getPercent() / 100));
                receiptItem.setDiscount(discount.setScale(2, RoundingMode.HALF_UP));
                receiptItem.setTotal(fullPrice.subtract(discount).setScale(2, RoundingMode.HALF_UP));
            } else {
                receiptItem.setDiscount(BigDecimal.ZERO);
                receiptItem.setTotal(fullPrice.setScale(2, RoundingMode.HALF_UP));
            }
            receiptItem.setAction(product.getAction());
            receiptItem.setReceipt(receipt);
            receiptItem = receiptItemRepository.save(receiptItem);
            receiptItemList.add(receiptItem);
        }

        // save footer
        ReceiptFooter receiptFooter = ReceiptFooter
                .builder()
                .discount(receiptItemList.stream().map(ReceiptItem::getDiscount).toList().stream().reduce(BigDecimal.ZERO, BigDecimal::add))
                .total(receiptItemList.stream().map(ReceiptItem::getTotal).toList().stream().reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();
        receiptFooter.setTaxTotal(receiptFooter.getTotal().subtract(receiptFooter.getDiscount()));
        receiptFooterRepository.save(receiptFooter);

        // save receipt
        receipt.setHeader(receiptHeader);
        receipt.setItems(receiptItemList);
        receipt.setFooter(receiptFooter);

        // return result
        ReceiptDto receiptDto = receiptConverter.convert(receiptRepository.save(receipt));
        receiptDto.getHeader().setTitle(ReceiptSettingsUtils.getTitle());
        receiptDto.getHeader().setStore(ReceiptSettingsUtils.getStore());
        receiptDto.getHeader().setAddress(ReceiptSettingsUtils.getAddress());
        receiptDto.getHeader().setPhone(ReceiptSettingsUtils.getPhone());
        return receiptDto;
    }
}
