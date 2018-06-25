package com.pmonteiro.dropwizard.resources;

import com.pmonteiro.dropwizard.App;
import com.pmonteiro.dropwizard.AppConfiguration;
import com.pmonteiro.dropwizard.api.TaskApi;
import com.pmonteiro.dropwizard.core.Task;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.restassured.response.Response;
import org.junit.ClassRule;
import org.junit.Test;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static javax.ws.rs.core.Response.Status.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class TasksResourceTest {

    @ClassRule
    public static DropwizardAppRule<AppConfiguration>
            rule = new DropwizardAppRule<>(App.class,  resourceFilePath("test-config.yml"));

    @Test
    public void getTasks_whenTasksExist_ShouldReturnTasks() {
        Task task = insertTask(new TaskApi("some-description"));
        when()
                .get("/tasks")
        .then()
                .statusCode(OK.getStatusCode())
                    .body("tasks[0].description", is(task.getDescription()))
                    .body("tasks[0].id", is(task.getId().toString()))
                    .body("tasks[0]._links.self.href", is("/tasks/" + task.getId()));
    }

    @Test
    public void getTask_whenTaskDoesNotExist_ShouldReturn404() {
        when()
                .get("/task/1")
        .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    public void getTask_whenIdExists_ShouldReturnTask() {
        Task task = insertTask(new TaskApi("description-2"));
        when()
                .get("/tasks/" + task.getId())
        .then()
                .statusCode(OK.getStatusCode())
                .body("description", is(task.getDescription()))
                .body("id", is(task.getId().toString()))
                .body("_links.self.href", is("/tasks/" + task.getId()));
    }

    @Test
    public void create() {
        TaskApi task = new TaskApi("description");
        given()
                .accept(JSON)
                .contentType(JSON)
                .body(task)
        .when()
                .post("/tasks")
        .then()
                .statusCode(CREATED.getStatusCode())
                .body("description", is(task.getDescription()))
                .body("id", is(notNullValue()))
                .body("_links.self.href", is(notNullValue()));
    }

    @Test
    public void update_whenTaskDoesNotExist_ShouldReturn404() {
        Task task = new Task("description");
        given()
                .accept(JSON)
                .contentType(JSON)
                .body(task)
        .when()
                .put("/tasks/1")
        .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    @Test
    public void update_whenTaskExists_ShouldUpdateTask() {
        Task task = insertTask(new TaskApi("description"));
        TaskApi taskApi = new TaskApi(task.getDescription());
        given()
                .accept(JSON)
                .contentType(JSON)
                .body(taskApi)
        .when()
                .put("/tasks/" + task.getId())
        .then()
                .statusCode(OK.getStatusCode());
    }

    @Test
    public void delete_whenTaskExists_ShouldDeleteAndReturn204() {
        Task task = insertTask(new TaskApi("description"));
        given()
                .accept(JSON)
                .contentType(JSON)
        .when()
                .delete("/tasks/" + task.getId())
        .then()
                .statusCode(NO_CONTENT.getStatusCode());
    }

    @Test
    public void delete_whenTaskDoesNotExist_ShouldReturn404() {
        given()
                .accept(JSON)
                .contentType(JSON)
        .when()
                .delete("/tasks/1")
        .then()
                .statusCode(NOT_FOUND.getStatusCode());
    }

    private Task insertTask(TaskApi task) {
        Response response =
            given()
                    .accept(JSON)
                    .contentType(JSON)
                    .body(task)
            .when()
                    .post("/tasks");

        return response.as(Task.class);
    }
}