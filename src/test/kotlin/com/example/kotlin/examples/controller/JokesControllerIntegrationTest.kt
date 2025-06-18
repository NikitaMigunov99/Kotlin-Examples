package com.example.kotlin.examples.controller

import com.example.kotlin.examples.model.dto.JokeRequestDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Sql("classpath:test-data.sql")
class JokesControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `get joke`() {
        mockMvc.perform(get("/joke"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.type").value("general"))
            .andExpect(jsonPath("$.setup").value("Why did the burglar hang his mugshot on the wall?"))
            .andExpect(jsonPath("$.punchline").value("To prove that he was framed!"))
    }

    @Test
    fun `get joke by id`() {
        mockMvc.perform(get("/joke/{id}", 3))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(3))
            .andExpect(jsonPath("$.type").value("general"))
            .andExpect(jsonPath("$.setup").value("What did the Dorito farmer say to the other Dorito farmer?"))
            .andExpect(jsonPath("$.punchline").value("Cool Ranch!"))
    }

    @Test
    fun `wrong joke id`() {
        mockMvc.perform(get("/joke/{id}", 100)).andExpect(status().isNotFound)
    }

    @Test
    fun `save joke`() {
        val objectMapper = ObjectMapper()
        val request = JokeRequestDTO(type = "general", setup = "some setup", punchline = "some punchline")

        mockMvc.perform(post("/save/joke")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk)

        mockMvc.perform(get("/jokes"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(11))

        mockMvc.perform(get("/jokes/count"))
            .andExpect(status().isOk)
            .andExpect(content().string("Jokes count is 11"))
    }

    @Test
    fun `save joke from remote`() {
        mockMvc.perform(post("/save/joke/remote/75")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)

        mockMvc.perform(get("/jokes"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(11))

        mockMvc.perform(get("/jokes/count"))
            .andExpect(status().isOk)
            .andExpect(content().string("Jokes count is 11"))
    }
}