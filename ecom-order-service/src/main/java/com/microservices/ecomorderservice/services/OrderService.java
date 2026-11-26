package com.microservices.ecomorderservice.services;

import com.microservices.ecomorderservice.dto.Inventory;
import com.microservices.ecomorderservice.exception.MyCustomException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
@SuppressWarnings("removal")
public class OrderService {

    private final RestTemplate restTemplate;
    private final RestClient restClient;

    public OrderService(RestTemplate restTemplate, RestClient restClient) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }

    public String placeOrder(String productId) {

        ResponseEntity<Inventory> entity = restClient.get()
                .uri("http://localhost:8082/inventory55/{productId}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new MyCustomException(response.getStatusCode(), response.getHeaders());
                })
                .toEntity(Inventory.class);

        Inventory inventory = entity.getBody();
        if (inventory == null || inventory.getQuantity() <= 0) {
            return "OUT OF STOCK";
        }

        updateInventory(inventory);
        return "ORDER PLACED";

    }

    private void updateInventory(Inventory inventory){

        inventory.setQuantity(inventory.getQuantity() - 1);

        restClient.put()
                .uri("http://localhost:8082/inventory")
                .body(inventory)
                .retrieve()
                .toBodilessEntity();
    }
}
