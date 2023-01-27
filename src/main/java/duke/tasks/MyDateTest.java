package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class MyDateTest {

    private static final String VALID_DATE_1 = "01/01/2001";
    private static final String VALID_DATE_2 = "02/01/2001";
    private static final String VALID_DATE_3 = "03/01/2001";

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern(("dd/MM/yyyy"));

    private static final String INVALID_DATE = "13/13/2011";
    private static final MyDate VALID_MY_DATE = new MyDate(VALID_DATE_1);
    private static final LocalDate SOME_DATE_1 = LocalDate.parse(VALID_DATE_1, FORMAT);
    private static final LocalDate SOME_DATE_2 = LocalDate.parse(VALID_DATE_2, FORMAT);
    private static final LocalDate SOME_DATE_3 = LocalDate.parse(VALID_DATE_3, FORMAT);

    @Test
    public void isValidDate() {
        assertTrue(MyDate.isValidDate(VALID_DATE_1));
        assertFalse(MyDate.isValidDate(INVALID_DATE));
    }

    @Test
    public void isEqual() {
        assertTrue(VALID_MY_DATE.isEqual(SOME_DATE_1));
        assertFalse(VALID_MY_DATE.isEqual(SOME_DATE_2));
    }

    @Test
    public void isBetween() {
        assertTrue(VALID_MY_DATE.isBetween(SOME_DATE_1, SOME_DATE_2));
        assertFalse(VALID_MY_DATE.isBetween(SOME_DATE_2, SOME_DATE_3));
    }


}
