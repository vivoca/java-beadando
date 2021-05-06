package hu.egyudv.beadando.model;

import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> all() {
        return userRepository.all();
    }

    @Override
    public User save(User object) {
        return userRepository.save(object);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }

    @Override
    public User get(String id) {
        return userRepository.get(id);
    }
}
