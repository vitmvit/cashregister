package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "discount_card")
public class Card extends BaseEntity {

    private int percent;
}
