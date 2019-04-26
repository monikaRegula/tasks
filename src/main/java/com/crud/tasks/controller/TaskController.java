package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")//to informuje Springa że może otrzymywać żądania z zewn
//serwerów znajdującyh się pod innymi domenami i ma na to zezwalać
@RestController // to zmienia zwykła klasę POJO na kontroler
//ogólem mówi Springowi,żeby utworzył w kontekście aplikacji Controller wraz z ResposeBody
// Odpowiedź controllera defaultowa jest jako JSON
@RequestMapping("v1/task")//generuje adres dla API;
//na górze przed klasa określa adres pod którym dany Controller będzie przyjmował żądania
//przed metodami w klasie okresli adresy i typy żądań, na ktore metody Controllera bd reagować
public class TaskController {
    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;


    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getALLTasks());

    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(@RequestParam Long taskId) {
        service.deleteTask(taskId);
    }

//    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
//    public TaskDto updateTask(Long id, String name, String desc) {
//        TaskDto taskDto = new TaskDto(id, name, desc);
//        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
//    }

    @RequestMapping(method = RequestMethod.PUT,value="updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}
