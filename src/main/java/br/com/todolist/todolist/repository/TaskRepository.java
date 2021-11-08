package br.com.todolist.todolist.repository;

import java.util.List;

import br.com.todolist.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByProjectId(Long projectId);
}
