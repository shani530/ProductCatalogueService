package com.example.productcatalogueservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    @Id
    private Long id;
    private Date createdAt;
    private  Date lastUpdatedAt;
    private State state;

}
