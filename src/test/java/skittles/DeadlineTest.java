package skittles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void DeadlineToStringTest(){
        assertEquals(String.format("[D][] %s\n","Eat chocolate by tomorrow"),new Deadline("Eat chocolate", "tomorrow"));
    }
}
