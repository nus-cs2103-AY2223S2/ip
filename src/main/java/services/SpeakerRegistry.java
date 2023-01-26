package services;

import types.ISpeaker;

import java.util.ArrayList;

public final class SpeakerRegistry {
    private final ArrayList<ISpeaker> speakerRegistry = new ArrayList<>(1);

    public void registerSpeaker(ISpeaker s) {
        speakerRegistry.add(s);
    }

    public void broadcast(String str) {
        for (ISpeaker s : speakerRegistry) {
            s.speak(str);
        }
    }
}
