package wagner.spo.challenge.optimizer.resource;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wagner.spo.challenge.optimizer.CleanerApplicationTests;
import wagner.spo.challenge.optimizer.dto.RoomDTO;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static wagner.spo.challenge.optimizer.utils.TestHelper.asJsonString;
import static wagner.spo.challenge.optimizer.utils.TestHelper.generateRandomNumbers;

public class CleanerResourceTest extends CleanerApplicationTests {

    @Autowired
    private CleanerResource cleanerResource;

    @Before
    public void setup() {
        initMockMvc(cleanerResource);
    }

    @Test
    public void calculateCleaners_ValidDto_Sucess() throws Exception {
        RoomDTO dto = new RoomDTO(Arrays.asList(35, 21, 17, 28), 10, 6);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/cleaners")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        final String expected = "[{\"capacity\": 35,\"senior\": 3,\"junior\": 1},{\"capacity\": 21,\"senior\": 1,\"junior\": 2}" +
                ",{\"capacity\": 17,\"senior\": 2,\"junior\": 0},{\"capacity\": 28,\"senior\": 1,\"junior\": 3}]";

        JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void calculateCleaners_InvalidDto_MoreThan100Rooms_Error() throws Exception {
        RoomDTO dto = new RoomDTO(generateRandomNumbers(101), 10, 6);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cleaners")
            .content(asJsonString(dto))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError())
            .andExpect(jsonPath("$.subErrors", hasSize(1)))
            .andExpect(jsonPath("$.subErrors[0].message").value("size must be between 1 and 100"));
    }

    @Test
    public void calculateCleaners_InvalidDto_SeniorZero_Error() throws Exception {
        RoomDTO dto = new RoomDTO(generateRandomNumbers(10), 0, 6);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cleaners")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.subErrors", hasSize(1)))
                .andExpect(jsonPath("$.subErrors[0].message").value("must be greater than or equal to 1"));
    }

    @Test
    public void calculateCleaners_InvalidDto_JuniorZero_Error() throws Exception {
        RoomDTO dto = new RoomDTO(generateRandomNumbers(10), 10, 0);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cleaners")
                .content(asJsonString(dto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.subErrors", hasSize(1)))
                .andExpect(jsonPath("$.subErrors[0].message").value("must be greater than or equal to 1"));
    }
}
