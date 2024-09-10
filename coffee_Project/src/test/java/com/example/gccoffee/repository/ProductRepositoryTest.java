package com.example.gccoffee.repository;


import com.example.gccoffee.entity.Category;
import com.example.gccoffee.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() {
        Product product = Product.builder()
                .productId(3L)
                .productName("상품명")
                .category(Category.COFFEE_BEAN_PACKAGE)
                .price(2000L)
                .description("앗싸라비아 콜롬비아")
                .createdAt(LocalDateTime.now())
                .build();

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct);
    }

    @Test
    public void testFindById(){
        Long productId = 2L;
        Optional<Product> foundMember = productRepository.findById(productId);
        assertNotNull(foundMember);
        assertEquals(productId, foundMember.get().getProductId());

        log.info("foundMember: "+ foundMember);
        log.info("productId: "+ productId);

    }


    @Test   //UPDATE 테스트 - 트랜잭션 O
    @Transactional
    @Commit
    public void testUpdateTransactional(){  //회원 수정 테스트
        Long productId = 2L;

        //user1 사용자를 데이터베이스에서 조회하여
        Optional<Product> foundMember = productRepository.findById(productId);



//        foundMember.get().changeDescription("앗싸 호랑나비");
//        foundMember.get().changePrice(80L);


        assertEquals("앗싸 호랑나비", foundMember.get().getDescription());//변경된 이메일이 bbb@bbb.com과 일치하는지 확인
    }

    @Test   //DELETE 테스트 - 트랜잭션 O
    @Transactional
    @Commit
    public void testDelete() {
        Long productId = 3L;
        productRepository.deleteById(3L);

        Optional<Product> foundMember = productRepository.findById(productId);
        assertTrue( foundMember.isEmpty() );
    }

}