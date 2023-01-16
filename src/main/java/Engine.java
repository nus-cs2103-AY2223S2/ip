import java.util.Scanner;

public class Engine {
    private final Scanner scanner;
    private final TaskList taskList;

    private enum Command {
        ERROR,
        EXIT,
        ECHO,
        LIST,
        ADD
    }

    Engine() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    private String getOutput(Command command, String text) {
        switch(command) {
            case EXIT:
                return "Bye...Why do you even need me?\n";
            case ECHO:
                return text + '\n';
            case LIST:
                return this.taskList.toString();
            case ADD:
                this.taskList.addTask(text);
                return "added-> "  + text + '\n';
            default:
                return "Case not accounted for, review code\n";
                // for debugging
        }
    }

    private Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.EXIT;
        }
        if (command.equals("list")) {
            return Command.LIST;
        }
        return Command.ADD; 
    }

    public boolean run() {
        String command = this.scanner.nextLine();
        Command com = this.parseCommand(command);
        System.out.println("\nD: " + this.getOutput(com, command));
        if (com.equals(Command.EXIT)) {
            return false;
        }
        return true;
    } 
}
