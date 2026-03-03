package cat.itacademy.s04.t01.userapi.controllerTest;

import cat.itacademy.s04.t01.userapi.controller.UserController;
import cat.itacademy.s04.t01.userapi.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        UserController.users.clear();
    }

    @Test
    void getUsers_returnEmptyListInitially() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void createUser_returnsUserWithId() throws Exception {
        User user = new User();
        user.setName(("Anna"));
        user.setEmail("anna@mail.com");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Anna"));
    }

    @Test
    void getUserById_returnsCorrectUser() throws Exception {
        User user = new User();
        user.setId(java.util.UUID.randomUUID());
        user.setName("Marc");
        user.setEmail("marc@mail.com");
        UserController.users.add(user);

        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marc"))
                .andExpect(jsonPath("$.email").value("marc@mail.com"));
    }

    @Test
    void getUserById_returnNotFoundIfMissing() throws Exception {
        mockMvc.perform(get("/users/" + java.util.UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUsers_withNameParam_returnFilteredUsers() throws Exception {
        User u1 = new User();
        User u2 = new User();
        u1.setId(java.util.UUID.randomUUID());
        u1.setName("Maria");
        u1.setEmail("maria@mail.com");
        u2.setId(java.util.UUID.randomUUID());
        u2.setName("Carlos");
        u2.setEmail("carlos@mail.com");
        UserController.users.add(u1);
        UserController.users.add(u2);


        mockMvc.perform(get("/users").param("name", "maria"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Maria"));
    }


}
