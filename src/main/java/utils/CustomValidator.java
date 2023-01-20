package utils;

/**
 * A custom validator class that does validation.
 */
public class CustomValidator {
    public static boolean validate(String string, Validator validator) {
        return validator.validate(string);
    }
}
