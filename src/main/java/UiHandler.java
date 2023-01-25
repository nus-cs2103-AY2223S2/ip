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
    private CommandHandler commandHandler;
    UiHandler() {
        this.commandHandler = new CommandHandler();
    }
    public void start() {
        System.out.println(logo + "\n");
        System.out.println(separator);
        System.out.println("Duke: ");
        System.out.println("Hello! I'm DUKE!\nWhat can I do for you?");
        System.out.println(separator);
    }
    
    public boolean run(List<Task> tasks) {
        boolean isRunning = true;
        System.out.println("You: ");
        String command = sc.nextLine();
        System.out.println(separator);
        System.out.println("Duke: ");
        
        String response = "";
        if (command.equals("bye")) {
            response = this.commandHandler.endDuke();
            isRunning = false;
        } else if (command.equals("list")) {
            response = this.commandHandler.showTasks(tasks);
        } else if (command.startsWith("mark")) {
            response = this.commandHandler.markTask(command, tasks);
        } else if (command.startsWith("unmark")) {
            response = this.commandHandler.unmarkTask(command, tasks);
        } else if (command.startsWith("todo")) {
            response = this.commandHandler.addTodo(command, tasks);
        } else if (command.startsWith("deadline")) {
            response = this.commandHandler.addDeadline(command, tasks);
        } else if (command.startsWith("event")) {
            response = this.commandHandler.addEvent(command, tasks);
        } else if (command.startsWith("delete")) {
            response = this.commandHandler.deleteEvent(command, tasks);
        } else {
            response = this.commandHandler.noMatch();
        }
        System.out.print(response);
        System.out.println(separator);
        return isRunning;
    }
    
    
}
