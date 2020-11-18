package turntabl.io.OrderConsumer.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import turntabl.io.OrderConsumer.ClientRepo;
import turntabl.io.OrderConsumer.bindings.GetClientRequest;
import turntabl.io.OrderConsumer.bindings.GetClientResponse;


@RestController
public class ClientController {
    @Autowired
    ClientRepo client;

    @PostMapping("/getClient")
    public GetClientResponse showDetails(@RequestBody GetClientRequest getClientRequest){
        return client.showClient(getClientRequest);
    }
}
