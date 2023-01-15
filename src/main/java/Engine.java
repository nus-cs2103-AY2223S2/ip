import java.util.Scanner;

public class Engine {
    private final Scanner scanner;

    private enum Command {
        ERROR,
        EXIT,
        ECHO
    }

    Engine() {
        this.scanner = new Scanner(System.in);
    }

    private String getOutput(Command command, String text) {
        switch(command) {
            case EXIT:
                return "Bye...Why do you even need me?\n";
            case ECHO:
                return text + '\n';
            default:
                return "Case not accounted for, review code\n";
                // for debugging
        }
    }

    private Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.EXIT;
        }
        return Command.ECHO;
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
