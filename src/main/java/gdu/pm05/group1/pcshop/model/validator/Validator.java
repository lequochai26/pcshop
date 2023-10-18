package gdu.pm05.group1.pcshop.model.validator;

import java.util.Map;

public interface Validator {
    void validate(Map<String, Object> validationResult, Object... params);
}
