package com.example.gccoffee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Entity
@Getter
@Transactional
@ToString // 양방향 일 시 제외 시켜줘야함 안그러면 무한 루프에 빠질 가능성이 높다.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // Date를 등록, 수정 일시 자동 반영 중요!!
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordersId;
    private String email;
    private String address;
    private String postcode;
    private OderStatus oderStatus;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;
    //OneToMany 양방향 관계 지정
    //이점 - order를 입력했을 때 orderItem 한번에 가져 올 수 있다
}
