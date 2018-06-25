package com.pmonteiro.dropwizard.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.pmonteiro.dropwizard.api.TaskApi;
import com.pmonteiro.dropwizard.core.Task;
import com.pmonteiro.dropwizard.db.TaskDAO;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.*;

@Api("/")
@Path("tasks")
@Produces(APPLICATION_JSON)
public class TasksResource {

    private TaskDAO dao;

    @Inject
    public TasksResource(TaskDAO taskDAO) {
        this.dao = taskDAO;
    }

    @GET
    @Timed
    @ApiOperation(
            value = "Get all the tasks",
            notes = "Returns all the tasks save on the database")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "", responseContainer = "List", response = Task.class)})
    public Response getTasks() {
        Optional<List> tasks = dao.find(Task.class)
                .map(this::getTasksWithHypermediaLinks);

        return ok().entity(ImmutableMap.of("tasks", tasks)).build();
    }

    @GET
    @Path("/{taskId}")
    @Timed
    @ApiOperation(
            value = "Get task by id",
            notes = "Returns task by Id. If it does not exist it will return a HTTP 404")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "", response = Task.class),
        @ApiResponse(code = 404, message = "Not Found")})
    public Response getTask(@ApiParam(value = "taskId", example = "9781337a-a4f6-4ee9-b7b2-2c001d8d457d") @PathParam("taskId") UUID id) {
        return dao.findById(id)
                .map(task -> ok().entity(task.asEmbedded()).build())
                .orElse(status(NOT_FOUND.getStatusCode()).build());
    }

    @POST
    @Timed
    @Consumes(APPLICATION_JSON)
    @ApiOperation(
            value = "Creates a new task",
            notes = "Creates a new task. Task descriptions are not unique.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = TaskApi.class)})
    public Response create(@ApiParam(value = "payload", required = true) TaskApi taskApi) {
        Task task = new Task(taskApi);
        dao.persist(task);
        return status(CREATED).entity(task.asEmbedded()).build();
    }

    @PUT
    @Path("{taskId}")
    @Timed
    @Consumes(APPLICATION_JSON)
    @Transactional
    @ApiOperation(
            value = "Updates task by id",
            notes = "Updates a task description if available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Updated"),
            @ApiResponse(code = 404, message = "Not Found")})
    public Response update(@ApiParam(value = "taskId", example = "9781337a-a4f6-4ee9-b7b2-2c001d8d457d") @PathParam("taskId") UUID id,
                           @ApiParam(value = "payload", required = true) TaskApi payload) {
        return dao.findById(id)
                .map(task -> {
                    task.setDescription(payload.getDescription());
                    return ok().entity(task.asEmbedded()).build();
                })
                .orElse(status(NOT_FOUND.getStatusCode()).build());
    }

    @DELETE
    @Path("/{taskId}")
    @Timed
    @Consumes(APPLICATION_JSON)
    @Transactional
    @ApiOperation(
            value = "Deletes task by id",
            notes = "Deletes a if available in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found")})

    public Response delete(@ApiParam(value = "taskId", example = "9781337a-a4f6-4ee9-b7b2-2c001d8d457d") @PathParam("taskId") UUID id) {
        return dao.findById(id)
                .map(task -> {
                    dao.remove(task);
                    return noContent().build();
                })
                .orElse(status(NOT_FOUND.getStatusCode()).build());
    }

    private List getTasksWithHypermediaLinks(List<Task> list) {
        return list.stream()
                .map(task -> task.asEmbedded())
                .collect(Collectors.toList());
    }
}
