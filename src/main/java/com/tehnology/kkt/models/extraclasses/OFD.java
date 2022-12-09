package com.tehnology.kkt.models.extraclasses;

import com.tehnology.kkt.models.Product;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OFD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contact;
    private String pass;

    private LocalDateTime dateOfCreated;
    private int days;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

}
