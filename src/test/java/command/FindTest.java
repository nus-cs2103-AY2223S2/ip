package command;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {
    @Test
    public void testParserNormal() {
        String actual = Find.parser().parse("find book").match(
                pr -> ((Find) pr.first()).keywords.toString(),
                error -> "oops");
        String expected = Set.of("book").toString();
        assertEquals(expected, actual);
    }
}
