package com.example.my_app.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_user_id", columnList = "id"),
        @Index(name = "idx_numberPhone", columnList = "number_phone"),
        @Index(name = "idx_email", columnList = "email")

})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone", length = 11, unique = true)
    private String numberPhone;


    @Column(name = "firstname")
    private String firstname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @Transient
    public int getAge(){
        return Period.between(birthDate,LocalDate.now()).getYears();
    }



    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Card>cards;


}
