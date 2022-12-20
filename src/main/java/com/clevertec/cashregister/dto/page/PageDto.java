package com.clevertec.cashregister.dto.page;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageDto implements Serializable {

    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
    private long offset;
    private boolean hasPrevious;
}
