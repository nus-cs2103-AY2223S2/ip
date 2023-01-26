package speakers;

import types.ISpeaker;

public class StdOut implements ISpeaker {
    @Override
    public void speak(String s) {
        System.out.print(s);
    }
}
