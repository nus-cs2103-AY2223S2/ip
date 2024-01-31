package types;

/**
 * Abstracts output layer.
 */
public interface ISpeaker {
    /**
     * Pushes given string to the output.
     * @param s String to say.
     */
    void speak(String s);
}
