package com.suninjjang.oasis.global.util;

import com.suninjjang.oasis.domain.user.domain.entity.User;

public interface UserUtils {
    User getCurrentUser();
    User getUserById(String id);
}
