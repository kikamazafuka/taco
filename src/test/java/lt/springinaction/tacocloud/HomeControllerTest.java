package lt.springinaction.tacocloud;

import static org.hamcrest.core.StringContains.containsString;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import lt.springinaction.tacocloud.controller.OrderController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(OrderController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testHomePage() throws Exception{

        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"))
                .andExpect(content().string(containsString("Start page")));
    }
}
