package com.example.gccoffee.DTO;

import com.example.gccoffee.entity.OderStatus;
import com.example.gccoffee.entity.OrderItem;
import com.example.gccoffee.entity.Orders;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class OrderDTO {


    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;
    @NotBlank(message = "우편 번호는 필수 입력 값입니다.")
    private String postCode;
    private List<OrderItemDTO> orderItemList=new ArrayList<>();

    public OrderDTO(String email, String address, String postCode, List<OrderItemDTO> orderItemList) {
        this.email = email;
        this.address = address;
        this.postCode = postCode;
        this.orderItemList = orderItemList;
    }

    public Orders toEntity(){
       return Orders.builder()
                .email(email)
                .address(address)
                .postcode(postCode)
                .oderStatus(OderStatus.ACCEPTED)
                .build();

    }
}
