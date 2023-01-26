package speakers;

import types.ISpeaker;

public class FileOut implements ISpeaker {
    @Override
    public void speak(String s) {
        throw new java.lang.UnsupportedOperationException("This is just for show!");
    }
}
