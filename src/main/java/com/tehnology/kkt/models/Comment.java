package com.tehnology.kkt.models;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String text;
    private String user;

    @Column(updatable = false, columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = new Date();
    }

}
