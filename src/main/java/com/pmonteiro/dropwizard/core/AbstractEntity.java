package com.pmonteiro.dropwizard.core;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    @ApiModelProperty(example = "3d7a2dc5-e8b3-48c2-8e3d-b1ed7882a082")
    private UUID id;

    public UUID getId() {
        return id;
    }
}