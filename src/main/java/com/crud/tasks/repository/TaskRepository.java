package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task,Long> {
    @SuppressWarnings("unchecked")
    @Override
    List<Task> findAll();
    @SuppressWarnings("unchecked")
    @Override
    Task save (Task task);
    @SuppressWarnings("unchecked")
    @Override
    Optional<Task> findById(Long id);
    @SuppressWarnings("unchecked")
    @Override
    void deleteById(Long id);
}
