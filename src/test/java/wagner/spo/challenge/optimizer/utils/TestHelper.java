package wagner.spo.challenge.optimizer.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Integer> generateRandomNumbers(Integer x) {
        Random random = new Random();
        return Arrays.stream(IntStream.generate(() -> random.nextInt(100)).limit(x).toArray()).boxed().collect(Collectors.toList());
    }
}
