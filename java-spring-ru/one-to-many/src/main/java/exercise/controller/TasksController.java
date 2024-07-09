package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskMapper taskMapper;

    @GetMapping(path = "")
    public List<TaskDTO> index() {
        return taskRepository.findAll()
                .stream()
                .map(task -> taskMapper.map(task))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@RequestBody TaskCreateDTO dto) {
        User assignee = userRepository.findById(dto.getAssigneeId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + dto.getAssigneeId() + " not found")
        );
        Task task = taskMapper.map(dto);
        assignee.addTask(task);
        return taskMapper.map(taskRepository.save(task));
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @RequestBody TaskUpdateDTO updateDTO) {
        User newAssignee = userRepository.findById(updateDTO.getAssigneeId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + updateDTO.getAssigneeId() + " not found")
        );
        Task task = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task with id " + id + " not found")
        );
        //task.getAssignee().removeTask(task);
        newAssignee.addTask(task);
        //task.getAssignee().removeTask(task);
        //assignee.addTask(task);
        //taskMapper.update(updateDTO, task);
        task.setTitle(updateDTO.getTitle());
        task.setDescription(updateDTO.getDescription());
        return taskMapper.map(taskRepository.save(task));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task == null) {
            return;
        }
        task.getAssignee().removeTask(task);
        taskRepository.deleteById(id);
    }
    // END
}
