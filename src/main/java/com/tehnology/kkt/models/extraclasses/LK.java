package com.tehnology.kkt.models.extraclasses;

import com.tehnology.kkt.models.Product;
import javax.persistence.*;

import lombok.*;

@Entity
//@Data
@Getter
@Setter
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

}
