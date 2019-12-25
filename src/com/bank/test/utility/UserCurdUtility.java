package com.bank.test.utility;

import com.bank.test.model.AppMessage;
import com.bank.test.model.User;

public interface UserCurdUtility {
    AppMessage saveUserDetails(User user);

    User getUserDetails(Long accountNumber);

    void deleteduser(User user);
}
