package turntabl.io.OrderValidator;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import io.spring.guides.gs_producing_web_service.Client;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ClientRepository {
    private static final Map<Integer, Client> clientRepo = new HashMap<>();

    @PostConstruct
    public void initData() {
        Client client1 = new Client();
        client1.setId(1);
        client1.setPrice(10.5);
        client1.setQuantity(20);
        client1.setSide("Buy");
        client1.setTicker("IBM");

        Client client2 = new Client();
        client2.setId(2);
        client2.setPrice(20.5);
        client2.setQuantity(10);
        client2.setSide("Sell");
        client2.setTicker("GOOGL");

        Client client3 = new Client();
        client3.setId(3);
        client3.setPrice(22.5);
        client3.setQuantity(5);
        client3.setSide("Buy");
        client3.setTicker("IBM");

        clientRepo.put(client1.getId(),client1);
        clientRepo.put(client2.getId(),client2);
        clientRepo.put(client3.getId(),client3);
    }

    public Client findClient(int id) {
        Assert.notNull(id, "The country's name must not be null");
        return clientRepo.get(id);
    }
}
