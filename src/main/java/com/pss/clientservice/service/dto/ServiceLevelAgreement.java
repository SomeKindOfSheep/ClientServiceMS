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
@Table(name="service_level_agreement")
public class ServiceLevelAgreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "slaSeq", sequenceName = "sla_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @Column(name = "full_document_name")
    private String fullDocumentName;

    @Column(name = "document_location")
    private String documentLocation;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;
}
