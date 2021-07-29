package com.pss.clientservice.service.dto;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "contractSeq", sequenceName = "contract_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @OneToOne
    private Employee employee;

    @OneToOne
    private ContractType contractType;

    @OneToOne
    private ServiceLevelAgreement serviceLevelAgreement;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;


}
