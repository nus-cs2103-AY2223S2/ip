package duke.utilities;


import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.DoubleAccumulator;

class DateTranslatorTest {
    @Test void identify_date(String raw_input){
        DateTranslator dateTranslator = new DateTranslator(raw_input);
    }
}