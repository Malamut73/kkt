package com.tehnology.kkt.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTransaction;

    private BigDecimal amount;
    private String aimTransaction;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Expenses expenses;


//    @PrePersist
//    private void init(){
//        dateTransaction = new Date();
//    }


}
