package com.spring.professional.exam.tutorial.module03.question23.jta.tx.db.employees.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
@ToString
public class Employee {
    @Id
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String email;
    @Column(name = "phonenumber")
    private String phoneNumber;
    @Column(name = "hiredate")
    private Date hireDate;
    private float salary;

    @SuppressWarnings("unused")
    public Employee() {
    }
}
