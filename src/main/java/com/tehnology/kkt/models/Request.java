package com.tehnology.kkt.models;


import com.tehnology.kkt.models.catalog.Topic;
import com.tehnology.kkt.models.enums.Etsp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request { //заявка

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfCreated;
    private String comment;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Etsp etsp; //электронно цифровая подпись

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Topic topic; //тема

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEnd;

    @PrePersist
    private void init(){
        dateOfCreated = new Date();
    }

}
