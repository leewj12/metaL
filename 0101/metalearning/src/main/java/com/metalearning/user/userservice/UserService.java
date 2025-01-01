package com.metalearning.user.userservice;

import com.metalearning.user.userdto.UserSignUpDTO;

public interface UserService {

    boolean usersave(UserSignUpDTO userSignUpDTO);
}
