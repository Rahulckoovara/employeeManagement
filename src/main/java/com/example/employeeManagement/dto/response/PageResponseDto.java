package com.example.employeeManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@JsonPropertyOrder({ "content", "pageNumber", "pageSize", "totalElements", "totalPages", "last" })
public class PageResponseDto<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
