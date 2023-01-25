package com.example.ponto.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Is Empty")
    private String name;
    private String function;
    @Builder.Default
    private Boolean blocked = false;
    @Builder.Default
    private String nextLog = "1st period - entrance";
    @OneToMany
    Set<LogTime> logTimes;
    @Column(unique = true)
    private Long code;
    @Builder.Default
    private Integer alerts=0;
}
