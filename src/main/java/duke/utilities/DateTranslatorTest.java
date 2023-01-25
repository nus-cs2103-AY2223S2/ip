package duke.utilities;

import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.*;

class DateTranslatorTest {


    @Test void identify_dateFormat1 (){
        assertTrue(DateTranslator.is_date("return book /by 2/12/2019 1800"));
        assertTrue(DateTranslator.is_date("return book /by 2/12/2019"));
        assertTrue(DateTranslator.is_date("return book /by 2019/2/12"));

    }

    @Test void identify_dateFormat2() {
        assertTrue(DateTranslator.is_date("return book /by 2019-11-12"));
        assertTrue(DateTranslator.is_date("return book /by 2019-11-12 1800"));
        assertFalse(DateTranslator.is_date("return book /by 2019-11"));
    }

    @Test void identify_wrongFormat() {
        assertFalse(DateTranslator.is_date("return book /by dance"));
        assertFalse(DateTranslator.is_date("return book /by 2019"));
        assertFalse(DateTranslator.is_date("return book /by "));
    }
}