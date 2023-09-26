package com.ecommerce.project.controller;

import com.ecommerce.project.dto.checkout.CheckOutItemDto;
import com.ecommerce.project.dto.checkout.StripeResponse;
import com.ecommerce.project.service.AuthenticationService;
import com.ecommerce.project.service.OrderService;
import com.stripe.model.checkout.Session;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private OrderService orderService;

    // stripe session checkout api

    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckOutItemDto> checkoutItemDtoList)
            throws StripeException {
        Session session = (Session) orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }
}

