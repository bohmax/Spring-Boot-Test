package com.Commerciale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "P_Commerciale")
public class Commerciale {

    @Id
    @SequenceGenerator(name = "p_comm_sequence", sequenceName = "p_comm_sequence", allocationSize = 1 /* Increment value */)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "p_comm_sequence")
    private Long id;
    @Column(name = "suffix")
    private Integer suffix;
    @Column(name = "val", columnDefinition = "varchar(63)")
    private String val;
    @Column(name = "data", columnDefinition = "varchar(63)")
    private String data;

}
