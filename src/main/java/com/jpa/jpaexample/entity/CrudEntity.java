package com.jpa.jpaexample.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "sample_member")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrudEntity {

    @Id
    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private int age;
}
