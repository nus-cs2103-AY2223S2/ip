package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {
    // test setup
    ToDos task1 = new ToDos("Do Homework");
    ToDos task2 = new ToDos("Do CS2103T IP");

    @Test
    public void toString_ValidTask1_StringReturned(){
        assertEquals("[T][0] Do Homework", task1.toString());
    }

    @Test
    public void toString_ValidTask2_StringReturned(){
        assertEquals("[T][0] Do CS2103T IP", task2.toString());
    }
}

