package wagner.spo.challenge.optimizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    @Size(min = 1, max = 100)
    private List<Integer> rooms;

    @Min(1)
    @Max(100)
    private int senior;

    @Min(1)
    @Max(100)
    private int junior;
}
