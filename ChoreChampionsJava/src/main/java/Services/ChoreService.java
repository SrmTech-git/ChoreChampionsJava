package Services;
import Models.Chore;
import Repository.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChoreService {

    @Autowired
    private ChoreRepository choreRepository;

    // Get all chores
    public List<Chore> getAllChores() {
        return choreRepository.findAll();
    }

    // Get chores sorted by points (highest first - most rewarding)
    public List<Chore> getChoresByHighestPoints() {
        return choreRepository.findAllByOrderByPointsDesc();
    }

    // Get chores sorted by time (shortest first - quickest to complete)
    public List<Chore> getChoresByShortestTime() {
        return choreRepository.findAllByOrderByTimeAsc();
    }

    // Get a specific chore by ID
    public Optional<Chore> getChoreById(Long id) {
        return choreRepository.findById(id);
    }

    // Business logic: Calculate points per time ratio (efficiency)
    public Double getChoreEfficiency(Long choreId) {
        Optional<Chore> chore = choreRepository.findById(choreId);

        if (chore.isPresent()) {
            Chore selectedChore = chore.get();

            if (selectedChore.getTime() == 0) {
                return 0.0; // Avoid division by zero
            }

            return (double) selectedChore.getPoints() / selectedChore.getTime();
        }

        return 0.0; // Chore doesn't exist
    }

    // Business logic: Get chores that give at least a certain number of points
    public List<Chore> getHighValueChores(Integer minPoints) {
        return choreRepository.findByPointsGreaterThanEqual(minPoints);
    }

    // Business logic: Get quick chores (under a certain time limit)
    public List<Chore> getQuickChores(Integer maxTime) {
        return choreRepository.findByTimeLessThanEqual(maxTime);
    }

    // Business logic: Search chores by name
    public List<Chore> searchChoresByName(String name) {
        return choreRepository.findByNameContainingIgnoreCase(name);
    }

}