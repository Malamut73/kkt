package com.tehnology.kkt.models;

import com.tehnology.kkt.models.extraclasses.FN;
import com.tehnology.kkt.models.extraclasses.LK;
import com.tehnology.kkt.models.extraclasses.Maintenance;
import com.tehnology.kkt.models.extraclasses.OFD;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String name;
     private String address;



    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private Set<Request> request = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product")
    private List<Maintenance> maintenance = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
     private FN fn;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
     private LK lk;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
     private OFD ofd;


}
