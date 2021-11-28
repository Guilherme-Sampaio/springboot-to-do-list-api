package br.com.todolist.todolist.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.todolist.todolist.model.Task;
import br.com.todolist.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public List<Task> findTodayTasks(Long projectId) {
    Date today = new Date();

    if (projectId != null) {
      return repository.findByProjectAndDateInterval(projectId, today, today);
    }
    return repository.findTaskWithoutProjectByDateInterval(today, today);
  }

  public List<Task> findWeekTasks(Long projectId) {
    Date initialDate = addDays(new Date(), 1);
    Date finalDate = addDays(new Date(), 7);

    if (projectId != null) {
      return repository.findByProjectAndDateInterval(projectId, initialDate, finalDate);
    }
    return repository.findTaskWithoutProjectByDateInterval(initialDate, finalDate);
  }

  public List<Task> findMoreThanOneWeekTasks(Long projectId) {
    Date initialDate = addDays(new Date(), 8);
    Date finalDate = addDays(new Date(), 365000);

    if (projectId != null) {
      return repository.findByProjectAndDateInterval(projectId, initialDate, finalDate);
    }
    return repository.findTaskWithoutProjectByDateInterval(initialDate, finalDate);
  }

  private Date addDays (Date date, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, days);

    return calendar.getTime();
  }

}
