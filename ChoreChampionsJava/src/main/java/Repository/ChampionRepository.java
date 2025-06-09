package Repository;
import Models.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, Long> {

    // This method is automatically provided by JpaRepository:
    // List<Champion> findAll() - gets all champions from database

    // Additional useful methods you might want:

    // Find champion by name (useful for lookups)
    Optional<Champion> findByName(String name);
    Optional<Champion> findByNameIgnoreCase(String name);

    // Get all champions ordered by name (alphabetical)
    List<Champion> findAllByOrderByNameAsc();

    // Find champions by partial name match (useful for searching)
    List<Champion> findByNameContainingIgnoreCase(String name);

    // Find champions that have images
    List<Champion> findByImageIsNotNull();

    // Find champions by partial description match
    List<Champion> findByDescriptionContainingIgnoreCase(String description);
}