package com.myropcb.pcb.controller;


import com.myropcb.pcb.core.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")

public class APIController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/hello")
    public String customOrder(Model model) {
        return "hello";
    }


}
