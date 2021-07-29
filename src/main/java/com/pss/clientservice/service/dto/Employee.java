package com.pss.clientservice.service.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "employeeSeq", sequenceName = "employee_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated;

    @Column(name = "date_removed")
    @Nullable
    private LocalDate dateRemoved;
}
