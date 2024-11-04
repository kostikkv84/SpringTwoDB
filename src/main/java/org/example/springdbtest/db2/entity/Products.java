package org.example.springdbtest.db2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products", schema = "public")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    private String description;

    private Integer customer_id;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customers customer; */

    private Double price;

    private Integer count;

}
