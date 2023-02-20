package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import dukeexception.DukeException;


public class DateTimeTest {
    @Test
    public void getDateTimeTest() {
        try {
            assertEquals(DateTime.dateFormatter("2018-08-05 1800"), "Aug 05 2018 18:00");
            assertEquals(DateTime.dateFormatter("2022-02-02 0937"), "Feb 02 2022 09:37");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
