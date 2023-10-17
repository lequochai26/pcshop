package gdu.pm05.group1.pcshop.model;

import java.util.List;

public interface IDBHandler {
    void save(Object... objects);
    void update(Object... objects);
    void remove(Object... objects);
    <T> T get(Class<T> objClass, Object target);
    <T> List<T> getAll();
}
