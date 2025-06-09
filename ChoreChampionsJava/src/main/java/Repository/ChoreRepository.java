package Repository;
import Models.Chore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChoreRepository extends JpaRepository<Chore, Long> {

    // This method is automatically provided by JpaRepository:
    // List<Chore> findAll() - gets all tasks/chores from database

    // Additional useful methods you might want:

    // Get all tasks ordered by points (highest first - most rewarding chores)
    List<Chore> findAllByOrderByPointsDesc();

    // Get all tasks ordered by time (shortest first - quick chores)
    List<Chore> findAllByOrderByTimeAsc();

    // Get tasks by specific criteria
    List<Chore> findByPointsGreaterThanEqual(Integer minPoints);
    List<Chore> findByTimeLessThanEqual(Integer maxTime);

    // Find tasks by name (useful for searching)
    List<Chore> findByNameContainingIgnoreCase(String name);
}