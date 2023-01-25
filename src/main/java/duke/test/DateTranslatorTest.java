package duke.test;

import duke.utilities.DateTranslator;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

/**
 * The type Date translator test.
 */
class DateTranslatorTest {


    /**
     * Test 1
     * Identify date format 1.
     */
    @Test
    void identifyDateFormat1() {
        assertTrue(DateTranslator.is_date("return book /by 2/12/2019 1800"));
        assertTrue(DateTranslator.is_date("return book /by 2/12/2019"));
        assertTrue(DateTranslator.is_date("return book /by 2019/2/12"));

    }

    /**
     * Test 2
     * Identify date format 2.
     */
    @Test
    void identifyDateFormat2() {
        assertTrue(DateTranslator.is_date("return book /by 2019-11-12"));
        assertTrue(DateTranslator.is_date("return book /by 2019-11-12 1800"));
        assertFalse(DateTranslator.is_date("return book /by 2019-11"));
    }

    /**
     * Test 3
     * Identify wrong format.
     */
    @Test
    void identifyWrongFormat() {
        assertFalse(DateTranslator.is_date("return book /by dance"));
        assertFalse(DateTranslator.is_date("return book /by 2019"));
        assertFalse(DateTranslator.is_date("return book /by "));
    }
}