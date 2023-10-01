package com.poly.service;

public interface SessionService {
    public Object get(String key);
    public void set(String key, Object value);
    public void clear(String key);
}
