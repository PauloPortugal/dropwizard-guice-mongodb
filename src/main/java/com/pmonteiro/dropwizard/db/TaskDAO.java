package com.pmonteiro.dropwizard.db;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import com.pmonteiro.dropwizard.core.Task;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class TaskDAO extends BaseDAO {

    @Inject
    public TaskDAO(final Provider<EntityManager> entityManager) {
        super(entityManager);
    }

    public Optional<Task> findById(final UUID id) {
        return findById(Task.class, id);
    }
}