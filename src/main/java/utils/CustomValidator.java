package utils;

public class CustomValidator {
    public static boolean validate(String string, Validator validator) {
        return validator.validate(string);
    }
}
