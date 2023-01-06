package com.tehnology.kkt.models.extraclasses.firdirectory;

import com.tehnology.kkt.models.Request;
import lombok.*;

import javax.persistence.*;

@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
