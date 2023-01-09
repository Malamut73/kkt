package com.tehnology.kkt.models;

import com.tehnology.kkt.models.enums.*;
import com.tehnology.kkt.models.extraclasses.*;

import javax.persistence.*;

import com.tehnology.kkt.models.extraclasses.firdirectory.Internet;
import com.tehnology.kkt.models.extraclasses.firdirectory.Taxation;
import com.tehnology.kkt.models.extraclasses.firdirectory.TypeOfActivity;
import lombok.*;

import java.util.*;


@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private String number;


    @Enumerated(EnumType.STRING)
    private VAT vat;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "internet_id")
    private Internet internet;

    @Enumerated(EnumType.STRING)
    private Excise excise;

    @Enumerated(EnumType.STRING)
    private Egais egais;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_of_activity_id")
    private TypeOfActivity typeOfActivity;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "taxation_id")
    private Taxation taxation;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "description_id")
    private Description description;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Request> request = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fn_id")
     private FN fn;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lk_id")
    private LK lk;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ofd_id")
    private OFD ofd;


}
