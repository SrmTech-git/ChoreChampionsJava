package Services;
import Models.Champion;
import Repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChampionService {

    @Autowired
    private ChampionRepository championRepository;

    // Get all champions
    public List<Champion> getAllChampions() {
        return championRepository.findAll();
    }

    // Get champions sorted alphabetically
    public List<Champion> getChampionsAlphabetically() {
        return championRepository.findAllByOrderByNameAsc();
    }

    // Get a specific champion by ID
    public Optional<Champion> getChampionById(Long id) {
        return championRepository.findById(id);
    }

    // Get a specific champion by name (exact match)
    public Optional<Champion> getChampionByName(String name) {
        return championRepository.findByNameIgnoreCase(name);
    }

    // Business logic: Search champions by name (partial match)
    public List<Champion> searchChampionsByName(String name) {
        return championRepository.findByNameContainingIgnoreCase(name);
    }

    // Business logic: Search champions by description content
    public List<Champion> searchChampionsByDescription(String keyword) {
        return championRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

    // Business logic: Get only champions that have images
    public List<Champion> getChampionsWithImages() {
        return championRepository.findByImageIsNotNull();
    }

    // Business logic: Check if champion exists by name
    public boolean championExistsByName(String name) {
        return championRepository.findByNameIgnoreCase(name).isPresent();
    }

    // Business logic: Get champion count
    public long getChampionCount() {
        return championRepository.count();
    }

    // Business logic: Get random champion for battles/encounters
    public Optional<Champion> getRandomChampion() {
        List<Champion> allChampions = championRepository.findAll();

        if (allChampions.isEmpty()) {
            return Optional.empty();
        }

        int randomIndex = (int) (Math.random() * allChampions.size());
        return Optional.of(allChampions.get(randomIndex));
    }

    // Business logic: Search across both name and description
    public List<Champion> searchChampions(String searchTerm) {
        // Get champions that match either name or description
        List<Champion> nameMatches = championRepository.findByNameContainingIgnoreCase(searchTerm);
        List<Champion> descriptionMatches = championRepository.findByDescriptionContainingIgnoreCase(searchTerm);

        // Combine and remove duplicates
        return nameMatches.stream()
                .distinct()
                .toList();
    }


}
