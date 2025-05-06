package com.example.my_app.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards",indexes = {
        @Index(name = "idx_card_id",columnList = "id")
})
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "card_number",length = 16,unique = true,nullable = false)
    private String cardNumber;

    @Column(name = "expiration_number",nullable = false)
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;


    @Column(name = "create_at", nullable = false,insertable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;


}
