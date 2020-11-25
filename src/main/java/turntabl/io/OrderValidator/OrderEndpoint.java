package turntabl.io.OrderValidator;


import io.spring.guides.gs_producing_web_service.GetOrderRequest;
import io.spring.guides.gs_producing_web_service.GetOrderResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import redis.clients.jedis.Jedis;
import turntabl.io.OrderValidator.module.ClientOrder;


@Endpoint
public class OrderEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

//    private final OrderRepository orderRepository;
//
//    @Autowired
//    public OrderEndpoint(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrder(@RequestPayload GetOrderRequest request) {
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(response.getOrder());

        Jedis jedis = new Jedis("localhost");

        ClientOrder clientOrder = new ClientOrder(
                request.getId(),
                request.getProduct(),
                request.getPrice() ,
                request.getQuantity(),
                request.getSide()
        );

        String order = Utility.convertToString(clientOrder);
        System.out.println(order);

//        jedis.set(request.getId(),order);
        jedis.lpush("makeorder",order);

        return response;
    }
}