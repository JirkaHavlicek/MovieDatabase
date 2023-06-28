package movie.database.service;

import movie.database.dto.UserDTO;
import movie.database.entity.UserEntity;
import movie.database.entity.repository.UserRepository;
import movie.database.service.exceptions.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO create(UserDTO model) {
        try {
            UserEntity entity = new UserEntity();
            entity.setEmail(model.getEmail());
            entity.setPassword(passwordEncoder.encode(model.getPassword()));

            entity = userRepository.save(entity);

            UserDTO dto = new UserDTO();
            dto.setUserId(entity.getUserId());
            dto.setEmail(entity.getEmail());
            return dto;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }
}
