package com.phoneshop.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {

    private List<?> lists;
    private PaginationDTO paginationDTO;

    public PageDTO(Page<?> page){
        this.lists = page.getContent();
        this.paginationDTO = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getSize())
                .pageNumber(page.getNumber()+1)
                .totalPages(page.getTotalPages())
                .totalElement(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }

}
