package com.pmonteiro.dropwizard.core;

import io.swagger.annotations.ApiModelProperty;
import org.eclipse.persistence.nosql.annotations.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(name = "_id")
    @ApiModelProperty(example = "1")
    private Long id;

    public Long getId() {
        return id;
    }
}