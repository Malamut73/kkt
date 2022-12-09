package com.tehnology.kkt.models.extraclasses;

import com.tehnology.kkt.models.Product;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LK {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String factoryNumber;
    private String contact;
    private String pass;
    private int codeAdministrator;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
