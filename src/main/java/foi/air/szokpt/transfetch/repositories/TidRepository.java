package foi.air.szokpt.transfetch.repositories;

import foi.air.szokpt.transfetch.entities.Tid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TidRepository extends JpaRepository<Tid, String> {
}
