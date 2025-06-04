package com.phoneshop.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PaginationDTO {

    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private Long totalElement;
    private int numberOfElements;


    private boolean last;
    private boolean first;
    private boolean empty;


}
