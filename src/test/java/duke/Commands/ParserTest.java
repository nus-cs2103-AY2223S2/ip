package duke.Commands;

import duke.Commands.Tasks.Task;
import duke.DukeException.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    @Test
    void taskNameTest() {
        Parser toDo = new Parser("todo qwerty");
        Parser deadline = new Parser("deadline qwerty /by 2023-01-01");
        Parser event = new Parser("event qwerty /from 2023-01-02 /to 2023-01-03");
        Parser mark = new Parser("mark 2");
        Parser find = new Parser("find go home");

        try {
            Command toDoCommand = toDo.process();
            Command deadlineCommand= deadline.process();
            Command eventCommand = event.process();
            Command markCommand = mark.process();
            Command findCommand = find.process();
            assertTrue(toDoCommand instanceof Add);
            assertTrue(deadlineCommand instanceof Add);
            assertTrue(eventCommand instanceof Add);
            assertTrue(markCommand instanceof Mark);
            assertTrue(findCommand instanceof Find);
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}