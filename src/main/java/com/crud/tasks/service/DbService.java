package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {
    @Autowired//wstrzykiwanie klasy
    private TaskRepository repository;

    public List<Task> getALLTasks() {
        return repository.findAll();
    }
}
