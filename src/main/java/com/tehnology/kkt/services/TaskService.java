package com.tehnology.kkt.services;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Task;
import com.tehnology.kkt.repositories.TaskDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskDAO taskDAO;

    public List<Task> findTaskByUser(User user) {
        return taskDAO.findTaskByUser(user);
    }

    public void save(Task task) {
        taskDAO.save(task);
    }

    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    public List<Task> findAllByOrderByDateOfReminderDesc() {
        return taskDAO.findAllByOrderByDateOfReminderAsc();
    }

    public List<Task> findByTasksIsNull() {
        return taskDAO.findAllByProductIsNull();
    }

    public Task saveRedirect(Task task) {
        Task newTask = taskDAO.saveAndFlush(task);
        System.out.println(task.getId());
        return newTask;
    }

    public List<Task> findAllByActiveOrderByDateOfReminderDesc(String value) {
        switch (value){
            case "true":
                return taskDAO.findAllByActiveOrderByDateOfReminderDesc(true);
            case "false":
                return taskDAO.findAllByActiveOrderByDateOfReminderDesc(false);
            default:
                return taskDAO.findAllByOrderByDateOfReminderDesc();
        }
    }
}
