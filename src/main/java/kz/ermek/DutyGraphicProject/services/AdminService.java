package kz.ermek.DutyGraphicProject.services;

import kz.ermek.DutyGraphicProject.models.Users;
import kz.ermek.DutyGraphicProject.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final UsersRepository usersRepository;

    @Autowired
    public AdminService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doAdminStaff() {
        System.out.println("Only admin here!");
    }
}
