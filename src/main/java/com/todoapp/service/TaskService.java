package com.todoapp.service;

import com.todoapp.entity.Task;
import com.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task){
        System.out.println("Task has been created");
        taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, String title, String status){
        System.out.println("Task has been updated");
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Id: "+id+" does not exist"));

        existingTask.setTitle(title);
        existingTask.setStatus(status);
        return taskRepository.save(existingTask);
    }

    public String deleteTask(Long id){
        System.out.println("Task has been deleted");
        taskRepository.deleteById(id);
        return "Task has been deleted with id:"+id;
    }

    public void updateStatus(Long id, String status){
        System.out.println("Status has been updated");
        Task getTask = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("ID:"+id+" not found!"));
        getTask.setStatus(status);
        taskRepository.save(getTask);
    }
}
