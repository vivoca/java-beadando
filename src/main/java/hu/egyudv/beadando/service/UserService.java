package hu.egyudv.beadando.service;


import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.repository.db.entity.User;

public interface UserService extends GeneralService<UserData>{

    UserData convertToUserData(User user);
}
