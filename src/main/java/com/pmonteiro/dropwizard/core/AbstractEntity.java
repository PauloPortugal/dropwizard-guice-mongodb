package com.pmonteiro.dropwizard.core;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
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

    protected URI location(String uri) {
        try {
            return new URI(uri);
        } catch (URISyntaxException e) {
            throw new WebApplicationException("Could not create URI for task " + getId(), e);
        }
    }
}