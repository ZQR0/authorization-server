package com.testing.auth_server.mapper;

public interface Mapper<FROM, TO> {
    TO map(FROM entity);
}
