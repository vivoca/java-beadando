package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.repository.db.UserRepositoryDb;
import hu.egyudv.beadando.repository.db.entity.User;
import hu.egyudv.beadando.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository = new UserRepositoryDb();

    @Override
    public List<UserData> all() {
        List<User> userList = userRepository.all();
        List<UserData> resultList = new ArrayList<>();
        for (User object : userList) {
            resultList.add(convertToUserData(object));
        }
        return resultList;
    }

    @Override
    public UserData save(UserData object) {
        User user = convertToUser(object);
        user = userRepository.save(user);
        return convertToUserData(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public UserData get(long id) {
        User user = userRepository.get(id);
        return convertToUserData(user);
    }

    @Override
    public UserData convertToUserData(User user) {
        UserData userData = new UserData();
        userData.setId(user.getId());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setBirthDate(user.getBirthDate());
        userData.setMobile(user.getMobile());
        return userData;
    }

    private User convertToUser(UserData userData) {
        User user = new User();
        user.setId(userData.getId());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setBirthDate(userData.getBirthDate());
        user.setMobile(userData.getMobile());
        return user;
    }
}
