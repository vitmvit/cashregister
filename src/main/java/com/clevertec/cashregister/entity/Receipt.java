package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Receipt extends BaseEntity {

    @OneToOne
    private ReceiptHeader header;

    @OneToMany(mappedBy = "receipt")
    private List<ReceiptItem> items;

    @OneToOne
    private ReceiptFooter footer;
}
