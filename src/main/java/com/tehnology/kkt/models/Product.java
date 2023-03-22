package com.tehnology.kkt.models;

import com.tehnology.kkt.models.enums.*;

import javax.persistence.*;

import lombok.*;

import java.util.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String number;
    private String taxation;
    private String vat;

    @Enumerated(EnumType.STRING)
    private Excise excise;

    @Enumerated(EnumType.STRING)
    private Condit condit;

    @Enumerated(EnumType.STRING)
    private Egais egais;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Request> request = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @ElementCollection(targetClass=String.class)
    private Set<String> productMark;

    @ElementCollection(targetClass=String.class)
    private Set<String> typeOfActivities;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "simCard_id")
    private SimCard simCard;

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
