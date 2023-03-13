package kz.ermek.DutyGraphicProject.services;

import kz.ermek.DutyGraphicProject.models.Duty;
import kz.ermek.DutyGraphicProject.models.Orderly;
import kz.ermek.DutyGraphicProject.repositories.OrderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderlyService {
    private final OrderlyRepository orderlyRepository;

    @Autowired
    public OrderlyService(OrderlyRepository orderlyRepository) {
        this.orderlyRepository = orderlyRepository;
    }

    public List<Orderly> findAll() {
        return orderlyRepository.findAll();
    }

    public Orderly findOne(int id) {
        Optional<Orderly> foundOrderly = orderlyRepository.findById(id);
        return foundOrderly.orElse(null);
    }

    public Optional<Orderly> searchByName(String name) {
        return orderlyRepository.findByOrderlyName(name);
    }

    @Transactional
    public void save(Orderly orderly) {
        orderlyRepository.save(orderly);
    }

    @Transactional
    public void update(int id, Orderly updatedOrderly) {
        Orderly orderlyToBeUpdated = orderlyRepository.findByOrderlyId(id).get();

        updatedOrderly.setOrderlyId(id);
        updatedOrderly.setDuty(orderlyToBeUpdated.getDuty());

        orderlyRepository.save(updatedOrderly);
    }

    @Transactional
    public void delete(int id) {
        orderlyRepository.deleteById(id);
    }

    public Duty getOrdDuty(int id) {
        return orderlyRepository.findById(id).map(Orderly::getDuty).orElse(null);
    }

    @Transactional
    public void release(int id) {
        orderlyRepository.findByOrderlyId(id).ifPresent(
                orderly -> {
                    orderly.setDuty(null);
                    orderly.setTakenAt(null);
                });
    }

    @Transactional
    public void assign(int id, Duty selectedDuty) {
        orderlyRepository.findByOrderlyId(id).ifPresent(
                orderly -> {
                    orderly.setDuty(selectedDuty);
                    orderly.setTakenAt(new Date());
                }
        );
    }
}
