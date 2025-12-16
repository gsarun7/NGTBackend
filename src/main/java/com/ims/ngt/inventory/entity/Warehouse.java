package com.ims.ngt.inventory.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "warehouses")
@Data
public class Warehouse {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
}
