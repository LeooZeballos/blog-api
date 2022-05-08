package com.leozeballos.apirestspringboot.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entry", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Entry {

    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="content", nullable = false)
    private String content;


}
