package wagner.spo.challenge.optimizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Room {
    private int capacity;
    private int senior;
    private int junior;
}
