package wagner.spo.challenge.optimizer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wagner.spo.challenge.optimizer.error.RestExceptionHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CleanerApplication.class)
public class CleanerApplicationTests {

	protected MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	protected void initMockMvc(Object controller) {
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
				.setControllerAdvice(RestExceptionHandler.class)
				.build();
	}
}
