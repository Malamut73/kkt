package com.tehnology.kkt.repositories;

import com.tehnology.kkt.models.User;
import com.tehnology.kkt.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskDAO extends JpaRepository<Task, Long> {
    List<Task> findTaskByUser(User user);
}
