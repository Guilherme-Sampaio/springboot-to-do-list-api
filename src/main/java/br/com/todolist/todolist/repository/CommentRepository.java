package br.com.todolist.todolist.repository;

import java.util.List;

import br.com.todolist.todolist.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByTaskIdOrderById(Long taskId);
}
