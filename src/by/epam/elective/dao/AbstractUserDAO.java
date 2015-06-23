package by.epam.elective.dao;

import by.epam.elective.entity.User;
import by.epam.elective.exception.TechnicalException;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public abstract class AbstractUserDAO extends AbstractDAO {

    public abstract boolean addUser(User user) throws TechnicalException;

    public abstract User checkUser(String login, String password) throws TechnicalException;

    public abstract ArrayList<User> findUsersByRole(int role) throws TechnicalException;

    public abstract User findUserById(int id) throws TechnicalException;
}
