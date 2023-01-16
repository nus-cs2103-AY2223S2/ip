import java.util.Scanner;
import java.util.stream.IntStream;

public class Engine {
    private final Scanner scanner;
    private final TaskList taskList;

    private enum Command {
        EXIT,
        ECHO,
        LIST,
        ADD,
        MARK,
        UNMARK,

        // errors
        ERROR
    }

    Engine() {
        this.scanner = new Scanner(System.in);
        this.taskList = new TaskList();
    }

    private String getOutput(Command command, String args) {
        switch(command) {
            case ERROR:
                return args;
            case EXIT:
                return "Bye...Why do you even need me?\n";
            case ECHO:
                return args + '\n';
            case LIST:
                return this.taskList.toString();
            case ADD:
                this.taskList.addTask(new Task(args));
                return "added-> "  + args + '\n';
            case MARK:
                
            default:
                return "Case not accounted for, review code\n";
                // for debugging
        }
    }

    private Pair<Command, String> parseCommand(String line) {
        int startIndex = IntStream.range(0, line.length())
                .filter(i -> line.charAt(i) != ' ')
                .findFirst()
                .orElse(-1);
        
        if (startIndex == -1)
            return new Pair<>(Command.ERROR, "no input detected\n");

        String[] lst = line.split(" ", 2);
        String command = lst[0];
        if (command.equals("bye")) {
            return new Pair<>(Command.EXIT, "");
        }
        if (command.equals("list")) {
            return new Pair<>(Command.LIST, "");
        }
        if (command.equals("mark")) {
            return new Pair<>(Command.MARK, lst[1]);
        }
        if (command.equals("unmark")) {
            return new Pair<>(Command.UNMARK, lst[1]);
        }
        return new Pair<>(Command.ADD, ""); 
    }

    public boolean run() {
        String command = this.scanner.nextLine();
        Pair<Command, String> pr = this.parseCommand(command.toLowerCase());
        System.out.println("\nD: " + this.getOutput(pr.first(), pr.second()));
        if (pr.first().equals(Command.EXIT)) {
            return false;
        }
        return true;
    } 
}
