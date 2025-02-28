package vignesh.service;

import vignesh.model.User;

public interface UserService {
    String registerUser(String name, String email, String password, String confirmPassword);
    User loginUser(String email, String password);
}
