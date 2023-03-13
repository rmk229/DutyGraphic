package kz.ermek.DutyGraphicProject.services;

import kz.ermek.DutyGraphicProject.models.Users;
import kz.ermek.DutyGraphicProject.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Users findOne(int id) {
        Optional<Users> foundUser = usersRepository.findById(id);
        return foundUser.orElse(null);
    }
}
