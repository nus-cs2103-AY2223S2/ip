package speakers;

import types.ISpeaker;

/**
 * A class to print to terminal.
 */
public class StdOut implements ISpeaker {
    /**
     * {@inheritDoc}
     */
    @Override
    public void speak(String s) {
        System.out.print(s);
    }
}
