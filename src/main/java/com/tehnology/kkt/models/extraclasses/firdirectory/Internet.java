package com.tehnology.kkt.models.extraclasses.firdirectory;

import com.tehnology.kkt.models.Product;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Internet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "Internet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
