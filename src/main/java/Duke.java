import java.util.*;
public class Duke {
    // Constants
    final static String PARTITION = "*******************************************";
    final static String EXIT_COMMAND = "bye";
    final static String LIST_COMMAND = "list";
    final static String DELETE_COMMAND = "delete";
    final static String MARK_COMMAND = "mark";
    final static String UNMARK_COMMAND = "unmark";
    final static String TODO_COMMAND = "todo";
    final static String EVENT_COMMAND = "event";
    final static String DEADLINE_COMMAND = "deadline";

    // Fields
    private static List<Task> current_list = new ArrayList<>();

    // Methods
    private static void checkInput(String[] current_input_array) throws EmptyDescriptionException {
        if (current_input_array.length < 2) {
            throw new EmptyDescriptionException("");
        }
    }
    private static void handleExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < current_list.size(); i++) {
            Task current_task = current_list.get(i);
            System.out.println(String.format("%d.%s", i + 1, current_task.toString()));
        }
    }
    
    private static void handleDelete(String[] current_input_array) throws EmptyDescriptionException {
        checkInput(current_input_array);
        int task_number = Integer.parseInt(current_input_array[1]);
        Task current_task = current_list.get(task_number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + current_task.toString());
        current_list.remove(task_number - 1);
        System.out.println(String.format("Now you have %d tasks in the list.", current_list.size()));
    }

    private static void handleMark(String[] current_input_array) throws EmptyDescriptionException{
        checkInput(current_input_array);
        int task_number = Integer.parseInt(current_input_array[1]);
        Task current_task = current_list.get(task_number - 1);
        current_task.markAsDone();
        System.out.println("  Nice! I've marked this task as done:");
        System.out.println(current_task.toString());
    }

    private static void handleUnmark(String[] current_input_array) throws EmptyDescriptionException{
        checkInput(current_input_array);
        int task_number = Integer.parseInt(current_input_array[1]);
        Task current_task = current_list.get(task_number - 1);
        current_task.markAsUndone();
        System.out.println("  OK, I've marked this task as not done yet:");
        System.out.println(current_task.toString());
    }

    private static void handleTodo(String[] current_input_array) throws EmptyDescriptionException{
        checkInput(current_input_array);
        Task current_task = new Todo(current_input_array[1]);
        current_list.add(current_task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + current_task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", current_list.size()));
    }

    private static void handleEvent(String[] current_input_array) throws EmptyDescriptionException{
        checkInput(current_input_array);
        current_input_array = current_input_array[1].split(" /from ", 2);
        String description = current_input_array[0];
        current_input_array = current_input_array[1].split(" /to ", 2);
        String from = current_input_array[0];
        String to = current_input_array[1];
        Task current_task = new Event(description, from, to);
        current_list.add(current_task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + current_task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", current_list.size()));
    }

    private static void handleDeadline(String[] current_input_array) throws EmptyDescriptionException{
        checkInput(current_input_array);
        current_input_array = current_input_array[1].split(" /by ", 2);
        String description = current_input_array[0];
        String by = current_input_array[1];
        Task current_task = new Deadline(description, by);
        current_list.add(current_task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + current_task.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", current_list.size()));
    }
    public static void main(String[] args) {

        Scanner user_input = new Scanner(System.in);

        System.out.println("Hello! I'm Anton's Bot");
        System.out.println("What can I do for you?");

        while (true){
            try {
                // Handling Input
                String current_input = user_input.nextLine();
                System.out.println(PARTITION);
                String[] current_input_array = current_input.split(" ", 2);
                String input_command = current_input_array[0];

                // Partition in UI

                // Handling Various Commnds
                if (input_command.equals(EXIT_COMMAND)) {
                    handleExit();
                    break;
                } else if (input_command.equals(LIST_COMMAND)) {
                    handleList();
                } else if (input_command.equals(DELETE_COMMAND)) {
                    handleDelete(current_input_array);
                } else if (input_command.equals(MARK_COMMAND)) {
                    handleMark(current_input_array);
                } else if (input_command.equals(UNMARK_COMMAND)) {
                    handleUnmark(current_input_array);
                } else if (input_command.equals(TODO_COMMAND)) {
                    handleTodo(current_input_array);
                } else if (input_command.equals(EVENT_COMMAND)) {
                    handleEvent(current_input_array);
                } else if (input_command.equals(DEADLINE_COMMAND)) {
                    handleDeadline(current_input_array);
                } else {
                    throw new InvalidCommandException("");
                }
                System.out.println(PARTITION);
            } catch (Exception e) {
                // handle exception
                System.out.println(e.getMessage());
                System.out.println(PARTITION);
            }
            
        }
        user_input.close();
    }
}
