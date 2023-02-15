package elise;
import elise.internal.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void test1() {
        MaybeDate actual = Parser.parseDate("01-12-2019");
        assertEquals(actual.toString(), "Dec 01 2019 23:59");
    }

    @Test
    public void test2() {
        MaybeDate actual = Parser.parseDate("02-08-2000 2210");
        assertEquals(actual.toString(), "Aug 02 2000 22:10");
    }
}
