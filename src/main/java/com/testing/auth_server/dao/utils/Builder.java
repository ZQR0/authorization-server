package com.testing.auth_server.dao.utils;

import com.testing.auth_server.dao.entity.AbstractEntity;

public interface Builder<T extends AbstractEntity> {
    T build();
}
