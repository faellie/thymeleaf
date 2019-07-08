package com.myropcb.pcb.controller;


import com.myropcb.pcb.core.OrderService;
import com.myropcb.pcb.model.CustomOrder;
import com.myropcb.pcb.model.WorkOrder;
import com.myropcb.pcb.model.WorkOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("form", orderService.getOrder());
        return "customOrder";
        //return "redirect:/books/all";
    }


    @PostMapping(value = "/process")
    public String process(@ModelAttribute CustomOrder form, Model model) {

        ArrayList<WorkOrder> lworkOrders  = orderService.processOrder(form);
        model.addAttribute("workOrderDto", new WorkOrderDto(lworkOrders));
        return "workOrder";
    }



    /*@GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        BooksCreationDto booksForm = new BooksCreationDto();

        for (int i = 1; i <= 3; i++) {
            booksForm.addBook(new Book());
        }

        model.addAttribute("form", booksForm);

        return "createBooksForm";
    }

    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Book> books = new ArrayList<>();
        orderService.findAll()
            .iterator()
            .forEachRemaining(books::add);

        model.addAttribute("form", new BooksCreationDto(books));

        return "editBooksForm";
    }

    @PostMapping(value = "/save")
    public String saveBooks(@ModelAttribute BooksCreationDto form, Model model) {
        orderService.saveAll(form.getBooks());

        model.addAttribute("books", orderService.findAll());

        return "redirect:/books/all";
    }*/
}
