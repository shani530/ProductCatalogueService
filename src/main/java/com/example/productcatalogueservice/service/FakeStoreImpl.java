package com.example.productcatalogueservice.service;

import com.example.productcatalogueservice.dtos.FakeStoreProductDto;
import com.example.productcatalogueservice.dtos.ProductDto;
import com.example.productcatalogueservice.models.Category;
import com.example.productcatalogueservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class FakeStoreImpl implements ProductService{
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
/*    @Autowired
    RestTemplate restTemplate;*/
    public <T> ResponseEntity<T> putForEntity(String url, HttpMethod httpMethod , @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto[]> fakeStoreDtoResponseEntity =
                    restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreDtoResponseEntity.getBody()){
            productList.add(convertFakeStoreDtoToProduct(fakeStoreProductDto));
        }
            return productList;
    }


    public Product replaceProduct(Long id ,Product product ){
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                putForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProductDto, FakeStoreProductDto.class, id);
        FakeStoreProductDto  fakeStoreProductDtoResp = responseEntity.getBody();
        Product replacedProduct = convertFakeStoreDtoToProduct(fakeStoreProductDtoResp);
        return replacedProduct;
    }


        @Override
        public Product getProductById(Long id) {
            RestTemplate restTemplate =  restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
                    restTemplate.getForEntity("http://fakestoreapi.com/products/{id}",
                            FakeStoreProductDto.class,id);

            if(fakeStoreProductDto.getBody() != null &&
                    fakeStoreProductDto.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
                return convertFakeStoreDtoToProduct(fakeStoreProductDto.getBody());
            }
            return null;
    }

    @Override
    public void deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}", id);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        restTemplate.put("https://fakestoreapi.com/products/{id}", fakeStoreProductDto, id);
    }

    @Override
    public Product createProduct(Product product) throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = convertProductToFakeStoreDto(product);
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        if(responseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            return convertFakeStoreDtoToProduct(responseEntity.getBody());
        }
        // Handle the case when the response is not successful
        // You can throw an exception or return null based on your requirements
        // For example, you can throw a custom exception:
        // throw new CustomException("Failed to create product");
        // Or you can return null:
        // throw new RuntimeException("Failed to create product");
        // Or you can return null:


        throw new Exception("Failed to create product");
    }


    private FakeStoreProductDto convertProductToFakeStoreDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return fakeStoreProductDto;
    }
    private Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
