package com.tehnology.kkt.models;

import javax.persistence.*;


import com.tehnology.kkt.models.catalog.Operator;
import com.tehnology.kkt.models.catalog.Tariff;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OFD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contact;
    private String pass;
    private String operator;
    private int days;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayEnd;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "ofd")
    private Product product;




}
