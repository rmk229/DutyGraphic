package kz.ermek.DutyGraphicProject.services;

import kz.ermek.DutyGraphicProject.models.Duty;
import kz.ermek.DutyGraphicProject.models.Orderly;
import kz.ermek.DutyGraphicProject.repositories.DutyRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DutyService {
    private final DutyRepository dutyRepository;

    @Autowired
    public DutyService(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    public List<Duty> findAll() {
        return dutyRepository.findAll();
    }

    public Duty findOne(int id) {
        Optional<Duty> foundDuty = dutyRepository.findById(id);
        return foundDuty.orElse(null);
    }

    @Transactional
    public void save(Duty duty) {
        dutyRepository.save(duty);
    }

    @Transactional
    public void update(int id, Duty updatedDuty) {
        updatedDuty.setDutyId(id);
        dutyRepository.save(updatedDuty);
    }

    @Transactional
    public void delete(int id) {
        dutyRepository.deleteById(id);
    }

    public Optional<Duty> getDutyByName(String name) {
        return dutyRepository.findByDutyName(name);
    }

    public List<Orderly> getOrderliesByDutyId(int id) {
        Optional<Duty> duty = dutyRepository.findById(id);

        if (duty.isPresent()) {
            Hibernate.initialize(duty.get().getOrderlies());

            duty.get().getOrderlies().forEach(orderly -> {
                long diff = Math.abs(orderly.getTakenAt().getTime() -
                        new Date().getTime());
                if (diff > 864000000){
                    orderly.setExpired(true);
                }
            });
            return duty.get().getOrderlies();
        } else {
            return Collections.emptyList();
        }
    }
}