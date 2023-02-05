package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test 
    public void stringTest() {
        Deadline temp = new Deadline("sleep" , "2023/01/01 1800");
        assertEquals("[D] [ ] sleep (by: 01/Jan/2023 1800Hrs)", temp.toString());

    }
}