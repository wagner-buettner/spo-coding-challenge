package wagner.spo.challenge.optimizer.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wagner.spo.challenge.optimizer.CleanerApplicationTests;
import wagner.spo.challenge.optimizer.dto.RoomDTO;
import wagner.spo.challenge.optimizer.error.exception.CleanerException;
import wagner.spo.challenge.optimizer.model.Room;
import wagner.spo.challenge.optimizer.service.impl.CleanerServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static wagner.spo.challenge.optimizer.utils.TestHelper.generateRandomNumbers;

public class CleanerServiceTest extends CleanerApplicationTests {

    @Autowired
    private CleanerServiceImpl cleanerService;

    @Test
    public void assert_senior_equals_one() {
        Room process = cleanerService.calculateCleaner(28, 10, 6);
        assertEquals(1, process.getSenior());
    }

    @Test
    public void assert_junior_equals_three() {
        Room process = cleanerService.calculateCleaner(28, 10, 6);
        assertEquals(3, process.getJunior());
    }

    @Test(expected = CleanerException.class)
    public void test_senior_0_error() {
        RoomDTO roomDTO = new RoomDTO(generateRandomNumbers(1), 0, 1);
        List<Room> process = cleanerService.calculateCleaners(roomDTO);
    }

}
