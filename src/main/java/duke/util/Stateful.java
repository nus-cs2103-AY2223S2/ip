package duke.util;

/**
 * Wrapper class for command functions to return String outputs *and* side-effects
 * (e.g. modifications to execution flow)
 *
 * @param output String array of outputs
 * @param state  (Modified) State of program
 * @see State
 */
public record Stateful(String[] output, State state) {}