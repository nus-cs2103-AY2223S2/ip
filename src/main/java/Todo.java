import Exceptions.EmptyTodoException;

import java.util.ArrayList;

public class Todo extends Task{
    protected String by;

    public Todo(String description) {
        super(description);
    }

    public static void createTodo(ArrayList<Task> taskList, String desc) {
        Format.line();
        System.out.println("Got it. I've added this task:");
        Todo todo = new Todo(desc);
        taskList.add(todo);
        Format.indent("" + todo);
    }

    public static void runTodo(ArrayList<Task> taskList, String description) {
        try {
            if (description.length() == 0) {
                throw new EmptyTodoException("");
            }
            Todo.createTodo(taskList, description);
            Format.checkList(taskList);
        } catch (EmptyTodoException e) {
            Format.line();
            System.out.println(e.getMessage());
            Format.line();
        } catch (ArrayIndexOutOfBoundsException e) {
            Format.line();
            System.out.println("Hey! The description of a todo cannot be empty!");
            Format.line();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
