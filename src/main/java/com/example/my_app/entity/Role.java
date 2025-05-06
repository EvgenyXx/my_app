package com.example.my_app.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles",indexes= {
        @Index(name = "idx_role_id", columnList = "id"),
        @Index(name = "idx_role_name", columnList = "role_name")
}

)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name",unique = true)
    @EqualsAndHashCode.Include
    private String roleName;


    @Column(name = "create_at",nullable = false)
    private LocalDateTime createAt;


    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToMany(mappedBy = "roles")
    private List<User>users = new ArrayList<>();
}









