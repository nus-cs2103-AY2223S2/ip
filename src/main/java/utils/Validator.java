package utils;


@FunctionalInterface
public interface Validator {
    boolean validate(String value);
}
