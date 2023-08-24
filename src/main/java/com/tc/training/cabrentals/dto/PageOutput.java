package com.tc.training.cabrentals.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageOutput<T> {
  private List<T> content;
  private boolean isFirst;
  private boolean isLast;
  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPages;
  private Long totalElements;
}
