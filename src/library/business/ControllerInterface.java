package library.business;

import library.exceptions.LoginException;

import java.util.List;

public interface ControllerInterface {

    void login(String id, String password) throws LoginException;

    List<String> allMemberIds();

    List<String> allBookIds();
}