package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping(method = RequestMethod.GET, value= "getTasks")
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getALLTasks());

    }

    @RequestMapping(method = RequestMethod.GET, value= "getTask")
    public TaskDto getTask(Long taskId){
        return new TaskDto(1L,"test title","test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value= "deleteTask")
    public void deleteTask(Long taskId){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(Long oldId,String newTitle, String newContent){
        return new TaskDto(1L,"Edited test title","Test content");
    }

    @RequestMapping(method = RequestMethod.POST, value= "createTask")
    public void createTask(Long id,String title, String content){

    }
}
