package com.tonson.eng.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, length = 4)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    //@JsonIgnore เมื่อ register สำเร็จ response จะไม่ส่ง password กลับไป หรือทำ mapper ก็ได้เหมือนกัน
    @Column(nullable = false, unique = true)
    private String password;

    @Column
    private String role;

    @Column
    private String phone_number;
}
