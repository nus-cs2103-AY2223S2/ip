import java.util.List;
import java.util.Scanner;

public class UiHandler {
    private static final Scanner sc = new Scanner(System.in);
    
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final String separator = "____________________________________________________________";
    
    public static void start() {
        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println("Duke: ");
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);
    }
    
    public static boolean run(List<Task> tasks) {
        boolean isRunning = true;
        System.out.println("You: ");
        String command = sc.nextLine();
        System.out.println(separator);
        System.out.println("Duke: ");
        
        String response = "";
        if (command.equals("bye")) {
            response = CommandHandler.endDuke();
            isRunning = false;
        } else if (command.equals("list")) {
            response = CommandHandler.showTasks(tasks);
        } else if (command.startsWith("mark")) {
            response = CommandHandler.markTask(command, tasks);
        } else if (command.startsWith("unmark")) {
            response = CommandHandler.unmarkTask(command, tasks);
        } else if (command.startsWith("todo")) {
            response = CommandHandler.addTodo(command, tasks);
        } else if (command.startsWith("deadline")) {
            response = CommandHandler.addDeadline(command, tasks);
        } else if (command.startsWith("event")) {
            response = CommandHandler.addEvent(command, tasks);
        } else if (command.startsWith("delete")) {
            response = CommandHandler.deleteEvent(command, tasks);
        } else {
            response = CommandHandler.noMatch();
        }
        System.out.print(response);
        System.out.println(separator);
        return isRunning;
    }
    
    
}
