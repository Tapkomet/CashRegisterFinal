package ua.training.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.service.exception.LoginException;
import ua.training.model.service.exception.WrongEmailException;
import ua.training.model.service.exception.WrongPasswordException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> getAllUsers() throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public Optional<User> login(String email, String pass) throws LoginException {
        Optional<User> result;
        try (UserDao userDao = daoFactory.createUserDao()) {
            result = userDao.findByEmail(email);
        }
        if (result.isPresent()) {
            if (result.get().getPassword().equals(pass)) {
                return result;
            }
            logger.info("Wrong password : {} for email : {}", pass, email);
            throw new WrongPasswordException("Password does not match.");
        }
        logger.info("Wrong email : {}", email);
        throw new WrongEmailException("User with email " + email + " is not found.");
    }

    public void register(String surname, String email, String pass) throws SQLException {
        UserDao userDao = daoFactory.createUserDao();
            userDao.register(surname, email, pass);
    }

    public void update(User user) throws SQLException {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        }
    }
}
