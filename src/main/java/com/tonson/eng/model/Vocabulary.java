package com.tonson.eng.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Vocabulary {

    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String eng;

    @Column(nullable = false)
    private String thai;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String pronunciation;
}
