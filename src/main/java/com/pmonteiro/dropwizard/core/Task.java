package com.pmonteiro.dropwizard.core;

import com.pmonteiro.dropwizard.api.TaskApi;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
@ApiModel(value="Task Entity", description = "A description of a task")
public class Task extends AbstractEntity {

    @Basic
    @ApiModelProperty(example = "Lorem Ipsum, porro quisquam est qui dolorem ipsum quia dolo")
    private String description;

    public Task() {}

    public Task(String description) {
        this.description = description;
    }

    public Task(TaskApi task) {
        this.description = task.getDescription();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
