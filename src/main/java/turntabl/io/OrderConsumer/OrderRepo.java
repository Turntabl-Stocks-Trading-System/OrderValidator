package turntabl.io.OrderConsumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import turntabl.io.OrderConsumer.bindings.GetOrderRequest;
import turntabl.io.OrderConsumer.bindings.GetOrderResponse;


@Service
public class OrderRepo extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    private WebServiceTemplate webServiceTemplate;

    public GetOrderResponse showOrder(GetOrderRequest request){
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        return (GetOrderResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8083/ws", request);
    }

    private static final Logger log = LoggerFactory.getLogger(OrderRepo.class);

    public GetOrderResponse getOrder(String id) {

        GetOrderRequest request = new GetOrderRequest();
        request.setId(id);


        log.info("Requesting location for " + id);

        GetOrderResponse response =  (GetOrderResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8083/ws", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetOrderRequest"));

        return response;
    }



}
