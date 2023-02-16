package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.DukeException;
import org.junit.jupiter.api.Test;

public class TodoTest {
    private final String description = "Hello World";
    private final Todo task = new Todo(description);

    @Test
    public void toSaveData() {
        // A unit test for Todo#toSaveData
        // test setup
        String delimiter = " | ";
        String saveData = "T"
                + delimiter + " "
                + delimiter + description;

        // automatically verify the response
        assertEquals(task.toSaveData(delimiter),
                saveData);
    }

    @Test
    public void generate() throws DukeException {
        // A unit test for Todo#generate
        // test setup
        Todo task = Todo.generate("todo " + description);

        // automatically verify the response
        assertEquals(task.getDescription(),
                this.task.getDescription());
        assertEquals(task.getStatusIcon(),
                this.task.getStatusIcon());
    }
}
