package com.codeformas.miranda_server.services;

import java.util.HashMap;

public interface ICrudService<T> {
    HashMap list(T t);
    HashMap getById(T t);
    HashMap saveUpdate(T t);
    HashMap delete(T t);
}
