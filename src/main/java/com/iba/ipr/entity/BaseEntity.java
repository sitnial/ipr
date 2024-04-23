package com.iba.ipr.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import com.opencsv.bean.CsvBindByName;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@Getter
public class BaseEntity {
    @Id
    @Column(name = "id")
    @NotNull
    @CsvBindByName(column = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}