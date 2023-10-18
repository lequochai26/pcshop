package gdu.pm05.group1.pcshop.model.validator;

public interface Validator<T> {
    void validate(T target, Object... params);
}
