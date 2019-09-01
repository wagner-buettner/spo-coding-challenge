package wagner.spo.challenge.optimizer.service.impl;

import org.springframework.stereotype.Service;
import wagner.spo.challenge.optimizer.dto.RoomDTO;
import wagner.spo.challenge.optimizer.model.Room;
import wagner.spo.challenge.optimizer.service.CleanerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CleanerServiceImpl implements CleanerService {

    @Override
    public List<Room> calculateCleaners(RoomDTO roomDTO) {
        return roomDTO.getRooms()
                .stream()
                .map(room -> calculateCleaner(room, roomDTO.getSenior(), roomDTO.getJunior()))
                .collect(Collectors.toList());
    }

    @Override
    public Room calculateCleaner(final int roomCapacity, final int seniorCapacity, final int juniorCapacity) {

        if (seniorCapacity <= 0) {
            throw new RuntimeException("Senior must be greater than 0");
        }

        if (juniorCapacity <= 0) {
            throw new RuntimeException("Junior must be greater than 0");
        }

        if (roomCapacity <= 0) {
            throw new RuntimeException("Room must be greater than 0");
        }

        if (roomCapacity >= 100) {
            throw new RuntimeException("Room must be less than 100");
        }

        // always starts with one senior
        int cleanerCapacity = roomCapacity - seniorCapacity;
        int totalSeniors = 1;
        int totalJuniors = 0;

        while (cleanerCapacity <= roomCapacity && cleanerCapacity > 0) {
            if ((cleanerCapacity - seniorCapacity) > seniorCapacity) {
                totalSeniors++;
                cleanerCapacity -= seniorCapacity;
                // total mod junior = 0
            } else if (cleanerCapacity % juniorCapacity == 0) {
                totalJuniors = totalJuniors + (cleanerCapacity / juniorCapacity);
                cleanerCapacity = cleanerCapacity - ((cleanerCapacity / juniorCapacity) * juniorCapacity);

                // total is less than senior and greater than junior
            } else if (cleanerCapacity <= seniorCapacity && cleanerCapacity > juniorCapacity) {
                totalSeniors++;
                cleanerCapacity -= seniorCapacity;

                // total - junior > junior
            } else if ((cleanerCapacity - juniorCapacity) > juniorCapacity) {
                totalSeniors++;
                cleanerCapacity -= seniorCapacity;

            } else {
                totalJuniors++;
                cleanerCapacity -= juniorCapacity;
            }
        }

        return new Room(roomCapacity, totalSeniors, totalJuniors);
    }
}
