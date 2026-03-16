package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "suppliers")
@Data
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String gstNo;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pinCode;
}
