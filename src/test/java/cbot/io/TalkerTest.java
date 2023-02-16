package cbot.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import cbot.command.BadInputException;
import cbot.command.PoorInputException;

public class TalkerTest {
    @Test
    public void testSayHi() {
        String cbotLogo = "  _____  _            _\n"
                + " /  ___]| |   ^-^   _| |_\n"
                + "|  / :D | |___  ___[_ + _]\n"
                + "|  \\___ | / . \\/ . \\ | |\n"
                + " \\_____]|_,_*_/\\_*_/ |_/\n\n";

        String hi = cbotLogo + "How can I help you today?";

        assertEquals(Talker.sayHi(), hi);
    }

    @Test
    public void testSayBye() {
        String bye = " ~ See you again!";
        assertEquals(Talker.sayBye(), bye);
    }

    @Test
    public void testWarn() {
        PoorInputException e = new PoorInputException("testWarn");
        assertEquals(Talker.warn(e),
                "!! testWarn");
    }

    @Test
    public void testWarnBad() {
        BadInputException e = new BadInputException("testWarnBad");
        assertEquals(Talker.warnBad(e),
                "!! <Error> testWarnBad");
    }

    @Test
    public void testWarnTime() {
        String warning = "!! Sorry, I don't know how to interpret that datetime."
                + "Try something in the form:\n"
                + "yyyy-MM-dd HH:mm";
        assertEquals(Talker.warnTime(), warning);
    }

    @Test
    public void testSay() {
        String sayStr = "abcdef";
        assertEquals(Talker.say(sayStr), " ~ abcdef");
    }

    @Test
    public void testPrintMany() {
        assertEquals(
                Talker.printMany(new ArrayList<>(Arrays.asList("a", "b", "c"))),
                "a\nb\nc");
    }
}
