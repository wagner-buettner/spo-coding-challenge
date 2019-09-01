package wagner.spo.challenge.optimizer.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wagner.spo.challenge.optimizer.dto.RoomDTO;
import wagner.spo.challenge.optimizer.model.Room;
import wagner.spo.challenge.optimizer.service.impl.CleanerServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "SPO Optimizer - Cleaner", tags = {"Cleaner"})
@RequestMapping("/api/cleaners")
public class CleanerResource {

    private final CleanerServiceImpl cleanerService;

    @Autowired
    public CleanerResource(CleanerServiceImpl cleanerService) {
        this.cleanerService = cleanerService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Post Structures to Optimize", nickname = "postStructures")
    public ResponseEntity<List<Room>> cleaners(@Valid @RequestBody final RoomDTO roomDTO) {
        return ResponseEntity.ok(cleanerService.calculateCleaners(roomDTO));
    }
}
