package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ReceiptHeader extends BaseEntity {

    private Date dateTime;
}
