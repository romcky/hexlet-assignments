package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    @Test
    public void testCreate() throws Exception {
        var data = new HashMap<>();
        data.put("title", "Task Title");
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request).andExpect(status().isCreated());
        assertThat(taskRepository.findByTitle("Task Title").isPresent()).isTrue();
    }

    @Test
    public void testShow() throws Exception {
        var task = Instancio.of(Task.class)
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        var dbTask = taskRepository.save(task);
        var request = get("/tasks/" + dbTask.getId());
        var result = mockMvc.perform(request).andExpect(status().isOk()).andReturn();
        var resultString = result.getResponse().getContentAsString();
        assertThat(om.writeValueAsString(dbTask)).isEqualTo(resultString);
    }

    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.of(Task.class)
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        var dbTask = taskRepository.save(task);
        var data = new HashMap<>();
        data.put("title", "Task Title");
        var request = put("/tasks/" + dbTask.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));
        mockMvc.perform(request).andExpect(status().isOk());
        assertThat(taskRepository.findById(dbTask.getId()).get().getTitle()).isEqualTo("Task Title");
    }

    @Test
    public void testDelete() throws Exception {
        var task = Instancio.of(Task.class)
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
        var dbTask = taskRepository.save(task);
        var request = delete("/tasks/" + dbTask.getId());
        mockMvc.perform(request).andExpect(status().isOk());
        assertThat(taskRepository.findById(dbTask.getId()).isPresent()).isFalse();
    }

    // END
}
