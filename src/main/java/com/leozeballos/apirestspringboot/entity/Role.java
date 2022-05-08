package com.leozeballos.apirestspringboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String name;

}
