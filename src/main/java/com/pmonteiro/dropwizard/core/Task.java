package com.pmonteiro.dropwizard.core;

import com.pmonteiro.dropwizard.api.TaskApi;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.eclipse.persistence.nosql.annotations.NoSql;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NoSql(dataFormat= DataFormatType.MAPPED)
@Table(name = "tasks")
@SequenceGenerator(name = "tasks_id_seq", sequenceName = "tasks_id_seq", allocationSize = 1)
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
}
