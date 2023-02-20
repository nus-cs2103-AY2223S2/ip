package duke.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleanUtilsTest {
    @Test
    public void isBooleanString_trueString_true() {
        String input = "true";

        Assertions.assertTrue(BooleanUtils.isBooleanStr(input));
    }

    @Test
    public void isBooleanString_falseString_true() {
        String input = "false";

        Assertions.assertTrue(BooleanUtils.isBooleanStr(input));
    }

    @Test
    public void isBooleanString_nonBooleanString_false() {
        String input = "0";

        Assertions.assertFalse(BooleanUtils.isBooleanStr(input));
    }
}
