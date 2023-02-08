package services;

import java.util.ArrayList;

import types.ISpeaker;

/**
 * A singleton class to hold all possible output locations.
 */
public final class SpeakerRegistry {
    private final ArrayList<ISpeaker> speakerRegistry = new ArrayList<>(1);

    /**
     * Add a new speaker.
     * @param s Speaker to add.
     */
    public void registerSpeaker(ISpeaker s) {
        speakerRegistry.add(s);
    }

    /**
     * Push string to all speakers.
     * @param str String to say.
     */
    public void broadcast(String str) {
        speakerRegistry.forEach(s -> s.speak(str));
    }
}
