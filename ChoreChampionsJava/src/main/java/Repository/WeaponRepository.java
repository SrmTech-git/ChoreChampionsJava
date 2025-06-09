package Repository;

import Models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    // This method is automatically provided by JpaRepository:
        // List<Weapon> findAll() - gets all items from database

    // custom methods if needed:

    // Get all items ordered by cost (useful for shop display)
    List<Weapon> findAllByOrderByCostAsc();

    // Get all items ordered by damage (useful for battle selection)
    List<Weapon> findAllByOrderByDamageDesc();

    // Get items by specific criteria if needed later
    List<Weapon> findByCostLessThanEqual(Integer maxCost);
}
