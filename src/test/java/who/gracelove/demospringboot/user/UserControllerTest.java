package who.gracelove.demospringboot.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void hello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Test
    void createUser_JSON() throws Exception {
        String userJson = "{\"username\":\"grace\",\"password\":\"1234\"}";
        mockMvc.perform(post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("grace"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    void createUser_XML() throws Exception {
        String userJson = "{\"username\":\"grace\",\"password\":\"1234\"}";
        mockMvc.perform(post("/users/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_XML)
                    .content(userJson))
                .andExpect(status().isOk())
                .andExpect(xpath("/User/username").string("grace"))
                .andExpect(xpath("/User/password").string("1234"));
    }
}
