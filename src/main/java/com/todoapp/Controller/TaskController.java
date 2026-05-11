package com.todoapp.Controller;

import com.todoapp.entity.Task;
import com.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:5173/")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/task")
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/task")
    public void createTasks(@RequestBody Task task){
        taskService.createTask(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId,
                                     @RequestBody Task task){
        System.out.println("Title:"+task.getTitle());
        Task updatedTask = taskService.updateTask(taskId, task.getTitle(), task.getStatus());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable(value = "id") Long taskId){
        return taskService.deleteTask(taskId);
    }

    @PatchMapping("/task/{id}/{status}")
    public String updateStatus(@PathVariable(value = "id") Long taskid, @PathVariable String status){
        taskService.updateStatus(taskid, status);
        return "Task Id:"+taskid+" updated with status: "+status;
    }
}
