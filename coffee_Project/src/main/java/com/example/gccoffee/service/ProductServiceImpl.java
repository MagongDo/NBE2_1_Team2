package com.example.gccoffee.service;

import com.example.gccoffee.DTO.ProductDTO;
import com.example.gccoffee.Exception.ProductException;
import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import com.example.gccoffee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
private final ProductRepository productRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAlls();
    }

    @Override
    public Product registerProduct(Product product) {
        Product products=Product.builder().productName(product.getProductName())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
        log.info(product.getProductName());
        log.info(product.getCategory());
        log.info(products);
        return productRepository.save(products);
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product read(Long productId){
        Optional<Product> product=productRepository.findById(productId);
        Product foundproduct=product.orElseThrow(ProductException.NOT_FOUND::get);
        return foundproduct;
    }
    @Override
    public void remove(Long productId) {
        Optional<Product> foundProduct=productRepository.findById(productId); //삭제하려는 상품의 번호를 데이터베이스에서 조회해서
        Product product=foundProduct.orElseThrow(ProductException.NOT_FOUND::get); //해당 상품이 없는 경우 Product NOT FOUND 예외 발생 시키기

        try {
            productRepository.delete(product);
        }catch (Exception e){
            log.error("--- "+e.getMessage());
            throw ProductException.NOT_REMOVED.get(); // 상품 삭제 실패 예외 발생
        }
    }

    @Override
    public ProductDTO modify(ProductDTO productDTO) {
        Optional<Product> foundProduct=productRepository.findById(productDTO.getProductId()); //수정하려는 상품을 데이터베이스에서 조회해서
        Product product=foundProduct.orElseThrow(ProductException.NOT_FOUND::get); //해당 상품이 없는 경우 Product NOT FOUND 예외 발생 시키기

        try {
            //상품 이름, 가격, 설명 수정
            product.changeProductName(productDTO.getProductName());
            product.changePrice(productDTO.getPrice());
            product.changeDescription(productDTO.getDescription());

            return new ProductDTO(product);//변경된 상품을 반환
        }catch (Exception e){
            log.error("--- "+e.getMessage());
            throw ProductException.NOT_MODIFIED.get();
        }
    }

}
