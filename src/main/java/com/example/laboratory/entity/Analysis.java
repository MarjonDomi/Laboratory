package com.example.laboratory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Analysis {

    public enum AnalysisType {
        BLOOD,
        COVID,
        LIVER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private AnalysisType analysisType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfAnalysis;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Users user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SubCategory> subCategoryList;
}
