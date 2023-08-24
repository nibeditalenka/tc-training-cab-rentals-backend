package com.tc.training.cabrentals.utils;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.tc.training.cabrentals.dto.PageOutput;

public class AppUtils {
  private static final ModelMapper modelMapper = new ModelMapper();

  public static <T, R> PageOutput<R> convertPageToPageOutput( Page<T> page, Class<R> clazz ) {
    PageOutput<R> output = new PageOutput<>();
    output.setPageSize( page.getSize() );
    output.setPageNumber( page.getNumber() );
    output.setTotalPages( page.getTotalPages() );
    output.setTotalElements( page.getTotalElements() );
    output.setFirst( page.isFirst() );
    output.setLast( page.isLast() );
    output.setContent( page.map( t -> modelMapper.map( t, clazz ) ).stream().toList() );
    return output;
  }
}
