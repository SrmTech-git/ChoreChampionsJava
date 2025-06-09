package Services;
import Models.Weapon;
import Repository.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WeaponService {

    @Autowired
    private WeaponRepository weaponRepository;

    // Get all weapons for the shop/catalog
    public List<Weapon> getAllWeapons() {
        return weaponRepository.findAll();
    }

    // Get weapons sorted by cost (for shop display)
    public List<Weapon> getWeaponsSortedByCost() {
        return weaponRepository.findAllByOrderByCostAsc();
    }

    // Get a specific weapon by ID
    public Optional<Weapon> getWeaponById(Long id) {
        return weaponRepository.findById(id);
    }

    // Business logic: Check if user can afford a weapon
    public boolean canUserAffordWeapon(Long weaponId, Integer userTime) {
        Optional<Weapon> weapon = weaponRepository.findById(weaponId);

        if (weapon.isPresent()) {
            return userTime >= weapon.get().getCost();
        }

        return false; // Weapon doesn't exist
    }



    // Get affordable weapons for a user
    public List<Weapon> getAffordableWeapons(Integer userTime) {
        return weaponRepository.findByCostLessThanEqual(userTime);
    }
}