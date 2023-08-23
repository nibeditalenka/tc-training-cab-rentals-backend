package com.tc.training.cabrentals.dto;

import java.util.List;

public class PageOutput<T> {
  private List<T> data;
  private boolean isFirstPage;
  private boolean isLastPage;
  private Integer pageNumber;
  private Integer pageSize;
  private Integer totalPages;
  private Long totalElements;
}
