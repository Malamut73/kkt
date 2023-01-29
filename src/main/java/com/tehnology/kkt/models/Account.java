package com.tehnology.kkt.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> incomes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> outgoes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> featureIncomes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Operation> featureOutgoes = new HashSet<>();

    public void addIncome(BigDecimal amount){
        this.amount = this.amount.add(amount);
    }

    public void addOutgo(BigDecimal amount){
        this.amount = this.amount.subtract(amount);

    }

//    @PrePersist
//    private void init(){
//         BigDecimal bigDecimal = new BigDecimal(0);
//        this.amount = this.amount.add(bigDecimal);
//    }
}
