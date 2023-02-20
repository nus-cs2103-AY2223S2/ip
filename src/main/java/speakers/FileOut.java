package speakers;

import types.ISpeaker;

/**
 * Prints output into som file. Not implemented.
 */
public class FileOut implements ISpeaker {
    /**
     * {@inheritDoc}
     */
    @Override
    public void speak(String s) {
        throw new java.lang.UnsupportedOperationException("This is just for show!");
    }
}
