package com.tehnology.kkt.models;


import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.firdirectory.Topic;
import com.tehnology.kkt.models.extraclasses.firdirectory.Etsp;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Etsp etsp; //электронно цифровая подпись

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Topic topic; //тема

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime dateOfEnd;



    @PrePersist
    private void init(){
        dateOfCreated = new Date();
    }

}
