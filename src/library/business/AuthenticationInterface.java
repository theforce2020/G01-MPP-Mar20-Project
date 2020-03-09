package library.business;

import library.exceptions.LoginException;

public interface AuthenticationInterface {

    void login(String username, String password) throws LoginException;
}