package command;

import duke.ui.Ui;
import duke.commands.ToDoCommand;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoCommandTest {
    TaskList tasks;
    Ui ui;
    Storage storage;

    @BeforeEach
    public void init() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage("./data/");
    }

    @Test
    public void shouldAddTodo() {
        ToDoCommand demoToDoCommand = new ToDoCommand("Demo");
        demoToDoCommand.execute(this.tasks, this.ui, this.storage);
        Task actual = this.tasks.showTask(0);
        ToDo expected = new ToDo("Demo");
        assertEquals(expected, actual);
    }
}
