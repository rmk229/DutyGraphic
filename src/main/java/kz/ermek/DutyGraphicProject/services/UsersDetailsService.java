package kz.ermek.DutyGraphicProject.services;

import kz.ermek.DutyGraphicProject.models.Users;
import kz.ermek.DutyGraphicProject.repositories.UsersRepository;
import kz.ermek.DutyGraphicProject.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> users = usersRepository.findByUsername(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new UsersDetails(users.get());
    }

}
