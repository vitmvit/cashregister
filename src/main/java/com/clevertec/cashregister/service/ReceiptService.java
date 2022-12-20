package com.clevertec.cashregister.service;

import com.clevertec.cashregister.dto.ReceiptDto;
import com.clevertec.cashregister.dto.page.PageContentDto;

public interface ReceiptService {

    ReceiptDto findById(Long id);

    PageContentDto<ReceiptDto> findAll(int pageNumber, int countElements);

    ReceiptDto create(Long cardNumber, Long[] barcodeArray);
}
