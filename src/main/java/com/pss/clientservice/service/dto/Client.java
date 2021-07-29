package com.pss.clientservice.service.dto;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "clientSeq", sequenceName = "client_seq", schema = "premier_service_solutions", allocationSize = 1)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String eMail;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "client_type")
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @OneToMany
    private List<Contract> contract;

    @OneToMany
    private List<Call> calls;
}
