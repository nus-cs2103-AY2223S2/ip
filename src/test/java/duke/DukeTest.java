package duke;

import duke.command.Echo;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DukeTest {
    @Test
    public void deadlineTest(){
        Locale.setDefault(Locale.ENGLISH);
        Deadline deadline = new Deadline("test1","15/02/2023 1600", true);
        assertEquals("[D][X] test1 (by: 2023-Feb-15 16:00)", deadline.toString());
    }

    @Test
    public void todoTest(){
        Locale.setDefault(Locale.ENGLISH);
        Todo todo = new Todo("test2", false);
        assertEquals("[T][ ] test2", todo.toString());
    }

    @Test
    public void echoTest(){
        Locale.setDefault(Locale.ENGLISH);
        Echo echo = new Echo("helloworld");
        ByteArrayOutputStream functionPrint = new ByteArrayOutputStream();
        System.setOut(new PrintStream(functionPrint));
        echo.execute(null);
        assertEquals("helloworld\n".trim(), functionPrint.toString().trim());
    }

}