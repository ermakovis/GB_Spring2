package ru.geekbrains.happy.market.soap;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.happy.market.services.ProductService;

@Endpoint
@RequiredArgsConstructor
public class ItemEndpoint {
        private static final String NAMESPACE_URI = "http://www.geekbrains.ru/happy/ws/items";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllItemsRequest")
    @ResponsePayload
    public GetAllItemsResponse getAllItems(@RequestPayload GetAllItemsRequest request) {
        GetAllItemsResponse rs = new GetAllItemsResponse();
        productService.findAllForSoap().forEach((rs.getProducts()::add));
        return rs;
    }
}
