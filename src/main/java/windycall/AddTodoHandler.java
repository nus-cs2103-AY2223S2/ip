package windycall;

public class AddTodoHandler extends AddTaskHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static Task handleAddTodo(String message) throws WindyCallException {
        if (message.length() == 4 || message.substring(4).trim().isEmpty()) {
            throw new WindyCallException("☹" +
                    "" +
                    " OOPS!!! The description of a todo cannot be empty!");
        }
        String description = message.substring(5);
        System.out.println("Got it. I've added this Todo task:");
        return new Todo(description, false);
    }
}
