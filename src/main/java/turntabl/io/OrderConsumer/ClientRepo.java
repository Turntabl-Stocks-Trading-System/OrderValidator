package turntabl.io.OrderConsumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceOperations;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import turntabl.io.OrderConsumer.bindings.GetClientRequest;
import turntabl.io.OrderConsumer.bindings.GetClientResponse;


@Service
public class ClientRepo extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    private WebServiceTemplate webServiceTemplate;

    public GetClientResponse showClient(GetClientRequest request){
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        return (GetClientResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8080/ws", request);
    }

    private static final Logger log = LoggerFactory.getLogger(ClientRepo.class);

    public GetClientResponse getClient(int id) {

        GetClientRequest request = new GetClientRequest();
        request.setId(id);

        log.info("Requesting location for " + id);

        GetClientResponse response =  (GetClientResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

        return response;
    }



}
