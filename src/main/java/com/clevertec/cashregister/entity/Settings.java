package com.clevertec.cashregister.entity;

import com.clevertec.cashregister.entity.parent.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Settings extends BaseEntity {

    @Column(name = "skey", unique = true, nullable = false)
    private String key;

    private String value;
}
