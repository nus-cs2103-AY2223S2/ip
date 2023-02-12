package wessy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ParserTest {
    @Test
    public void getCmd() {
        assertEquals(Parser.getCmd("bye"), CmdType.BYE);
        assertNull(Parser.getCmd("listkjcbjdfvb"));
        assertEquals(Parser.getCmd("event     "), CmdType.EVENT);
        assertEquals(Parser.getCmd("todo jsvdjnv"), CmdType.TODO);
        assertNull(Parser.getCmd("dEaDlIne bjb"));
    }

    @Test
    public void parseDateTime() {
        assertEquals(Parser.parseDateTime(" 2023/3/2  "), LocalDateTime.parse("2023-03-02T12:34:56"));
        assertEquals(Parser.parseDateTime("10-3-2022 1700    "), LocalDateTime.parse("2022-03-10T17:00:00"));
        assertEquals(Parser.parseDateTime("2023-11-5 13:45"), LocalDateTime.parse("2023-11-05T13:45:00"));
        assertEquals(Parser.parseDateTime("  3/12/2022 04.25"), LocalDateTime.parse("2022-12-03T04:25:00"));
    }

    @Test
    public void removeSpacePadding() {
        assertEquals(Parser.removeSpacePadding("FEGW"), "FEGW");
        assertEquals(Parser.removeSpacePadding("A BCD"), "A BCD");
        assertEquals(Parser.removeSpacePadding("    fkjv efb d jhbdjkdf          "), "fkjv efb d jhbdjkdf");
    }
}
