package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void listToStringNormalString() {
        assertEquals("Hello",
                Util.listToString(List.of('H', 'e', 'l', 'l', 'o')));
    }

    @Test
    public void listToStringEmptyList() {
        assertEquals("", Util.listToString(List.of()));
    }

    @Test
    public void cleanupEmptyString() {
        assertEquals("", Util.cleanup(""));
    }

    @Test
    public void cleanupStringWithSpaceBothEnds() {
        String output = Util.cleanup("  rawr  ");
        assertEquals("rawr", output);
    }

    @Test
    void cleanupStringWithSpaceFront() {
        String output = Util.cleanup("  test");
        assertEquals("test", output);
    }

    @Test
    public void cleanupStringWithSpaceBack() {
        String output = Util.cleanup("test   ");
        assertEquals("test", output);
    }

    @Test
    public void splitWsNormalString() {
        List<String> actual = Util.splitWhitespace("My name is Ginloy.");
        List<String> expected = List.of("My", "name", "is", "Ginloy.");
        assertEquals(expected, actual);
    }

    @Test
    public void splitWsEmptyString() {
        List<String> actual = Util.splitWhitespace("");
        List<String> expected = List.of();
        assertEquals(expected, actual);
    }

    @Test
    public void splitWsNoWs() {
        List<String> actual = Util.splitWhitespace("test.");
        List<String> expected = List.of("test.");
        assertEquals(expected, actual);
    }

    @Test
    public void splitWsWsAtEdges() {
        List<String> actual = Util.splitWhitespace("  test  ");
        List<String> expected = List.of("test");
        assertEquals(expected, actual);
    }
}
