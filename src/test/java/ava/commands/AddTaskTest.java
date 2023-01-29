package ava.commands;

import ava.Ava;
import ava.exceptions.AvaException;
import ava.Storage;
import ava.TaskList;
import ava.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddTaskTest {
    @Test
    public void addTodoTest(){
        try {
            String[] parsedInput = new String[]{"read book"};
            Ava.TASK_TYPE tt = Ava.TASK_TYPE.TODO;
            TaskList tasks = new TaskList();
            Storage s = new Storage();
            AddTask command = new AddTask(parsedInput, tt);
            Todo newTodo = new Todo(parsedInput[0]);
            boolean running = command.run(tasks, s);
            assertEquals(tasks.formatTasks(""),"1. " + newTodo.getRepresentation()+"\n");
        }catch (AvaException e){
            fail();
        }
    }
}
