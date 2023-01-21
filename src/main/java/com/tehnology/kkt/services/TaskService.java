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
}
