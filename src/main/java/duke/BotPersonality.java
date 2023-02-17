package duke;

import java.util.Random;

/**
 * The BotPersonality class represents bot personality data.
 */
public class BotPersonality {
    private static final String[] FLAVOUR_TEXTS = {
        "There is no difference in who started to study first; the one who achieves accomplishment is first.",
        "Relax and calm your mind. Forget about yourself and follow your opponent's movement.",
        "Do not fight with the strength, absorb it, and it flows, use it."
    };

    public String getRandomFlavourText() {
        return FLAVOUR_TEXTS[new Random().nextInt(FLAVOUR_TEXTS.length)];
    }
}
