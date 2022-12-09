package com.tehnology.kkt.models;


import com.tehnology.kkt.models.extraclasses.Comment;
import com.tehnology.kkt.models.extraclasses.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request { //заявка

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateOfCreated;

    @OneToOne(mappedBy = "request", cascade = CascadeType.REMOVE)
    private Topic topic; //тема


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User client;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime dateOfEnd;



    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

}
