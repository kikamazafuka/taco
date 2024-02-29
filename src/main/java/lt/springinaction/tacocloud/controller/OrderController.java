package lt.springinaction.tacocloud.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lt.springinaction.tacocloud.repository.OrderRepository;
import lt.springinaction.tacocloud.tacos.OrderProps;
import lt.springinaction.tacocloud.tacos.TacoOrder;
import lt.springinaction.tacocloud.tacos.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    private OrderProps orderProps;


    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderProps orderProps, OrderRepository orderRepository) {
        this.orderProps = orderProps;
        this.orderRepository = orderRepository;
    }



    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus, @AuthenticationPrincipal UserAccount user){
        if (errors.hasErrors()){
            return "orderForm";
        }
        log.info("Order submitted: {}", order);
        order.setUserAccount(user);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal UserAccount user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserAccountOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

}
