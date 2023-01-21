package com.tehnology.kkt.models;

import com.tehnology.kkt.models.Request;
import com.tehnology.kkt.models.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String text;

}
