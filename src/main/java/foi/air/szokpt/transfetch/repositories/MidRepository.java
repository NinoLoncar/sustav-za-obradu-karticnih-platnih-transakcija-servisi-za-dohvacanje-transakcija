package foi.air.szokpt.transfetch.repositories;

import foi.air.szokpt.transfetch.entities.Mid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidRepository extends JpaRepository<Mid, String> {
}
