package wagner.spo.challenge.optimizer.service;

import wagner.spo.challenge.optimizer.dto.RoomDTO;
import wagner.spo.challenge.optimizer.model.Room;

import java.util.List;

public interface CleanerService {

    List<Room> calculateCleaners(RoomDTO roomDTO);

    Room calculateCleaner(final int roomCapacity, final int seniorCapacity, final int juniorCapacity);
}
