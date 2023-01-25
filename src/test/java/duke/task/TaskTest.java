package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void isDoneTest(){
        Task t = new Task("do work", "T", "X");
<<<<<<< .merge_file_GhrlOw
        assertEquals(true, t.checkisdonestr());
=======
        assertEquals(true, t.checkIsDoneStr());
>>>>>>> .merge_file_Df5mSr
    }
}
