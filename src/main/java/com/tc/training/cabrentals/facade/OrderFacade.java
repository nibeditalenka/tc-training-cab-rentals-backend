package com.tc.training.cabrentals.facade;

import com.tc.training.cabrentals.dto.OrderInput;
import com.tc.training.cabrentals.dto.OrderOutput;

public interface OrderFacade {
  OrderOutput placeOrder( OrderInput input );
}
