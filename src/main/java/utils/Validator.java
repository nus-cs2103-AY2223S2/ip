package utils;

/**
 * An interface for performing the validation action.
 */
@FunctionalInterface
public interface Validator {
    boolean validate(String value);
}
