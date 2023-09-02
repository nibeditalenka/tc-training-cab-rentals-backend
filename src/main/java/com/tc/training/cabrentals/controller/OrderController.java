package com.tc.training.cabrentals.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;
import com.tc.training.cabrentals.dto.PageOutput;
import com.tc.training.cabrentals.enums.OrderStatus;
import com.tc.training.cabrentals.facade.OrderFacade;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping( "/orders" )
@RequiredArgsConstructor
public class OrderController {
  private final OrderFacade orderFacade;

  @PostMapping
  public OrderOutput placeOrder( @RequestBody @Valid OrderInput input ) {
    return orderFacade.placeOrder( input );
  }

  @GetMapping
  public PageOutput<OrderOutput> getAll( @RequestParam( required = false, defaultValue = "0" ) Integer pageNumber,
      @RequestParam( required = false, defaultValue = "25" ) Integer pageSize,
      @RequestParam( required = false, defaultValue = "orderedDate" ) String sortBy,
      @RequestParam( required = false, defaultValue = "DESC" ) Sort.Direction sortDirection,
      @RequestParam( required = false ) LocalDate orderedDate, @RequestParam( required = false ) UUID centerId,
      @RequestParam( required = false ) UUID userId, @RequestParam( required = false ) UUID carId,
      @RequestParam( required = false ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime startDateTime,
      @RequestParam( required = false ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dropDateTime,
      @RequestParam( required = false ) OrderStatus orderStatus ) {
    return orderFacade.getAllFiltered( pageNumber, pageSize, sortBy, sortDirection, orderedDate, centerId, userId,
        carId, startDateTime, dropDateTime, orderStatus );
  }

  @GetMapping( "/{id}" )
  public OrderOutput getOrderById( @PathVariable UUID id ) {
    return orderFacade.getById( id );
  }

  @PutMapping( "/{id}" )
  public OrderOutput updateOrderById( @PathVariable UUID id, @RequestBody OrderInput input ) {
    return orderFacade.updateOrder( id, input );
  }

  @DeleteMapping( "/{id}" )
  public void deleteOrderById( @PathVariable UUID id ) {
    orderFacade.deleteOrder( id );
  }

  @PatchMapping( "/{id}" )
  public OrderOutput updateOrderStatus( @PathVariable UUID id, @RequestParam OrderStatus status ) {
    return orderFacade.updateStatus( id, status );
  }
}
