package br.com.todolist.todolist.repository;

import java.util.Date;
import java.util.List;

import br.com.todolist.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findByProjectIdOrderById(Long projectId);

  List<Task> findByProjectIsNullOrderById();

  @Query(value = "select id, title, done, project_id, selected_date from tasks "
               + "where project_id = :projectId "
               + "and selected_date between :firstDate and :secondDate", nativeQuery = true)
  List<Task> findByProjectAndDateInterval(@Param("projectId") Long projectId, @Param("firstDate") Date firstDate,
      @Param("secondDate") Date secondDate);

  @Query(value = "select id, title, done, project_id, selected_date from tasks "
      + "where project_id is null "
      + "and selected_date between :firstDate and :secondDate", nativeQuery = true)
  List<Task> findTaskWithoutProjectByDateInterval(@Param("firstDate") Date firstDate,
      @Param("secondDate") Date secondDate);

  @Query(value = "select id, title, done, project_id, selected_date from tasks "
      + "where project_id = :projectId "
      + "and (selected_date < :todayDate "
      + "or selected_date is null)", nativeQuery = true)
  List<Task> findTaskWithExpiredOrNullDate(@Param("projectId") Long projectId, @Param("todayDate") Date todayDate);

  @Query(value = "select id, title, done, project_id, selected_date from tasks "
      + "where project_id is null "
      + "and (selected_date < :todayDate "
      + "or selected_date is null)", nativeQuery = true)
  List<Task> findTaskWithExpiredOrNullDateWithoutProject(@Param("todayDate") Date todayDate);

}
