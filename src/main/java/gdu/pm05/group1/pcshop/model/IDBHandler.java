package gdu.pm05.group1.pcshop.model;

import java.util.List;

public interface IDBHandler {
    void save(Object... objects);
    void remove(Object... objects);
    <T> T get(Class<T> objClass, T target);
    <T> List<T> getAll(Class<T> objClass);
}
