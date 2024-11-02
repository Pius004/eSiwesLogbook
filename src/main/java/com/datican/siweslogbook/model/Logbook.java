package com.datican.siweslogbook.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Logbook {


        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricNumber;
    private String date;
    private String activities;
    private String challenges;
    private String solutions;
}
