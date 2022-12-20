package com.clevertec.cashregister.dto.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PageContentDto<E> implements Serializable {

    private PageDto page;
    private List<E> content;
}
