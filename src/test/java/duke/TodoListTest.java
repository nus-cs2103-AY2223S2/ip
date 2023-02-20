package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoListTest {
    @Test
    public void addToDoTask(){
        try {
            TodoList newTodoList = new TodoList();
            newTodoList.add("todo", "read book");
            assertEquals(newTodoList.getNumberOfTasks(), 1);
        } catch (DukeExceptions ex) {
            System.out.println(ex.getErrorMessage());
        }
    }
}
