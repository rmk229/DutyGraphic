package kz.ermek.DutyGraphicProject.repositories;

import kz.ermek.DutyGraphicProject.models.Orderly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderlyRepository extends JpaRepository<Orderly, Integer> {
    Optional<Orderly> findByOrderlyId(int id);
    Optional<Orderly> findByOrderlyName(String name);
}
