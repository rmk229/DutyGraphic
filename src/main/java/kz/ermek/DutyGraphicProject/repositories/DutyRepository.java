package kz.ermek.DutyGraphicProject.repositories;

import kz.ermek.DutyGraphicProject.models.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DutyRepository extends JpaRepository<Duty, Integer> {
    List<Duty> findByDutyId(int id);
    Optional<Duty> findByDutyName(String name);
}
