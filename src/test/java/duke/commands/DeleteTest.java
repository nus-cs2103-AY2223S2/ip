package duke.commands;

import duke.TaskList;
import duke.commands.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteTest {

    @Test
    void executeTest() {
        TaskList toDoList = new TaskList();
        toDoList.add(new ToDo("test"));
        assertEquals(toDoList.size(), 1);
        new Delete("", 0).execute(toDoList);
        assertEquals(toDoList.size(), 0);
    }
}