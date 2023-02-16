package duke;

import java.util.Random;

public class BotPersonality {
    private final String[] FLAVOUR_TEXTS = {
        "\"There is no difference in who started to study first; the one who achieves accomplishment is first.\" ~ Yip Man",
        "\"Relax and calm your mind. Forget about yourself and follow your opponent's movement.\" ~ Yip Man",
        "\"Do not fight with the strength, absorb it, and it flows, use it.\" ~ Yip Man"
    };

    public String getRandomFlavourText() {
        return FLAVOUR_TEXTS[new Random().nextInt(FLAVOUR_TEXTS.length)];
    }
}