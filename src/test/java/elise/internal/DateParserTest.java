package elise.internal;

import elise.MaybeDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateParserTest {
    @Test
    public void test1() {
        MaybeDate actual = DateParser.parseDate("01-12-2019");
        assertEquals(actual.toString(), "Dec 01 2019 23:59");
    }

    @Test
    public void test2() {
        MaybeDate actual = DateParser.parseDate("02-08-2000 2210");
        assertEquals(actual.toString(), "Aug 02 2000 22:10");
    }

    @Test
    public void test3() {
        MaybeDate actual = DateParser.parseDate("02/05/2030");
        assertEquals(actual.toString(), "May 02 2030 23:59");
    }
}