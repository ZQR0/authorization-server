package com.testing.auth_server.dao.utils;

import com.testing.auth_server.dao.entity.AbstractEntity;


/**
 * Специальный интерфейс для имплементации паттерна Builder для сущностей базы данных
 * */
public interface Builder<T extends AbstractEntity> {
    T build();
}
