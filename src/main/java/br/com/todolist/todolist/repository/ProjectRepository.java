package br.com.todolist.todolist.repository;

import java.util.List;

import br.com.todolist.todolist.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findAllByOrderById();
}
