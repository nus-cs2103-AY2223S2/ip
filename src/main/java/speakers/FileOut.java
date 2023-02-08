package speakers;

import types.ISpeaker;

/**
 * A class to print into file. Not implemented yet.
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
