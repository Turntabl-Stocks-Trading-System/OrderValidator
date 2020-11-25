package turntabl.io.OrderConsumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import turntabl.io.OrderConsumer.OrderRepo;
import turntabl.io.OrderConsumer.bindings.GetOrderRequest;
import turntabl.io.OrderConsumer.bindings.GetOrderResponse;


@RestController
public class OrderController {
    @Autowired
    OrderRepo order;

    @PostMapping("/order")
    public GetOrderResponse showDetails(@RequestBody GetOrderRequest getOrderRequest){
        return order.showOrder(getOrderRequest);
    }
}
