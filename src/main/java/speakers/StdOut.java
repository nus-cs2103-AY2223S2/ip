package speakers;

import types.ISpeaker;

/**
 * Prints output to stdout.
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
