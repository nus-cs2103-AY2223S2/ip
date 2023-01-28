package duke.task;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasklistTest {
    @Test
    public void parseTest(){
        TaskList list = new TaskList(
               new ArrayList<>()
        );
        list.addTodo("Event watch movie /from 2022-12-12 18:00 /to " +
                "2022-12-12 20:00");
        assertEquals("[T][ ] watch movie /from 2022-12-12 18:00 /to 2022-12-12 20:00",
                list.getList().get(0).toString());
    }

    @Test
    public void invalidTask() {
        TaskList list = new TaskList(
                new ArrayList<>()
        );
        list.addTodo("todo");
        assertEquals(list.getList().size(), 0);
    }

}
