package com.tehnology.kkt.models;

import com.tehnology.kkt.models.User;
import lombok.*;

import javax.persistence.*;

@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
