package utils;

import dukeexception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DateTimeUtilsTest {
    @Test
    public void dateFormatterSuccessTest() {
        try {
            assertEquals(DateTimeUtils.dateFormatter("2022-08-01"), "01 Aug 2022");
            assertEquals(DateTimeUtils.dateFormatter("2023-10-12"),"12 Oct 2023");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void dateFormatterFailTest() {
        assertThrows(DukeException.class, () -> DateTimeUtils.dateFormatter("fail"));
    }
}
