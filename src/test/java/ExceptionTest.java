// package ;

import DukeHelpfulCode.Exceptions.DukeException;
import DukeHelpfulCode.Exceptions.EmptyTaskListException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Utilities.*;


public class ExceptionTest {
    @Test
    public void noSuchTaskExceptionTest(){
        TaskList tl = new TaskList();
        Exception e = assertThrows(NoSuchTaskException.class, () -> {
            tl.delete(1);
        });
        assertEquals("sorry, but I can't find this Task.",e.getMessage());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4,4);
    }
}
