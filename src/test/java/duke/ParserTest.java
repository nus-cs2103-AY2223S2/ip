package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testGreet() {
        String expected = "Welcome to Manchester United.";
        String actual = Parser.INITIAL_GREETING;
        assertEquals(expected, actual);
    }

    @Test
    public void testExit() {
        String expected = "SUIII, Bye";
        String actual = Parser.FINAL_GREETING;
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkCommand() {
        String expected = "SUI, I have marked this task from the training room: ";
        String actual = Parser.MARK_COMMAND;
        assertEquals(expected, actual);
    }

    @Test
    public void testUnmarkCommand() {
        String expected = "SUI, I have unmarked this task from the training room: ";
        String actual = Parser.UNMARK_COMMAND;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteCommand() {
        String expected = "Tasks successfully deleted. SUI.";
        String actual = Parser.DELETE_COMMAND;
        assertEquals(expected, actual);
    }

    @Test
    public void testFindCommand() {
        String expected = "Here are matching tasks in your list";
        String actual = Parser.FIND_COMMAND;
        assertEquals(expected, actual);
    }

    @Test
    public void testIllegalCommand() {
        String expected = "This command is incomplete or illegal";
        String actual = Parser.ILLEGAL_COMMAND;
        assertEquals(expected, actual);
    }

    @Test
    public void testShowTasksCommand() {
        String expected = "list";
        String actual = Parser.SHOW_TASKS;
        assertEquals(expected, actual);
    }

    @Test
    public void testTerminateCommand() {
        String expected = "exit";
        String actual = Parser.TERMINATE;
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkKey() {
        String expected = "mark";
        String actual = Parser.MARK;
        assertEquals(expected, actual);
    }

    @Test
    public void testUnmarkKey() {
        String expected = "unmark";
        String actual = Parser.UNMARK;
        assertEquals(expected, actual);
    }

    @Test
    public void testTodoCommand() {
        String expected = "todo";
        String actual = Parser.TODO;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeadlineCommand() {
        String expected = "deadline";
        String actual = Parser.DEADLINE;
        assertEquals(expected, actual);
    }

    @Test
    public void testEventCommand() {
        String expected = "event";
        String actual = Parser.EVENT;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteKey() {
        String expected = "delete";
        String actual = Parser.DELETE;
        assertEquals(expected, actual);
    }

    @Test
    public void testFindKey() {
        String expected = "find";
        String actual = Parser.FIND;
        assertEquals(expected, actual);
    }

    @Test
    public void testRecurCommand() {
        String expected = "recur";
        String actual = Parser.RECUR;
        assertEquals(expected, actual);
    }

    @Test
    public void testDecrementCommand() {
        int expected = 1;
        int actual = Parser.DECREMENT;
        assertEquals(expected, actual);
    }

    @Test
    public void testMarkSymbol() {
        String expected = "X";
        String actual = Parser.MARK_SYMBOL;
        assertEquals(expected, actual);
    }

    @Test
    public void testTodoSymbol() {
        String expected = "T";
        String actual = Parser.TODO_SYMBOL;
        assertEquals(expected, actual);
    }

    @Test
    public void testDeadlineSymbol() {
        String expected = "D";
        String actual = Parser.DEADLINE_SYMBOL;
        assertEquals(expected, actual);
    }

    @Test
    public void testEventSymbol() {
        String expected = "E";
        String actual = Parser.EVENT_SYMBOL;
        assertEquals(expected, actual);
    }

    @Test
    public void testMark() {
        int taskPosition = 0;
        TaskList<Task> tasks = new TaskList<>();
        tasks = tasks.add(new Task("Task1"));
        tasks = tasks.add(new Task("Task2"));

        TaskList<Task> markedTasks = Parser.mark(taskPosition, tasks);
        Task task = markedTasks.get(taskPosition);

        String expected = "SUI, I have marked this task from the training room: [X] Task1";
        String actual = Parser.MARK_COMMAND + task;
        assertEquals(expected, actual);
    }

    @Test
    public void testUnmark() {
        int taskPosition = 0;
        TaskList<Task> tasks = new TaskList<>();
        tasks = tasks.add(new Task("Task1"));
        tasks = tasks.add(new Task("Task2"));

        TaskList<Task> markedTasks = Parser.mark(taskPosition, tasks);
        TaskList<Task> unmarkedTasks = Parser.unmark(taskPosition, markedTasks);
        Task task = unmarkedTasks.get(taskPosition);

        String expected = "SUI, I have unmarked this task from the training room: [ ] Task1";
        String actual = Parser.UNMARK_COMMAND + task;
        assertEquals(expected, actual);
    }
}
