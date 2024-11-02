package com.datican.siweslogbook.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name= "undergraduates")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long matricNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long phoneNumber;
    private String email;
    private String residentialAddress;
    private String password;
    private String faculty;
    private String department;
    private String companyName;
    private String companyLocation;
    private String companyCustomerCare;
    private String supervisorId;
    private String supervisorName;
    private String supervisorPhone;



}
