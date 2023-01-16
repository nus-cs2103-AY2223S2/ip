import java.util.Scanner;

public class Engine {
    private final Scanner scanner;
    private final TaskList taskList;

    private enum Command {
        EXIT,
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
            case LIST:
                return this.taskList.toString();
            case ADD:
                this.taskList.addTask(new Task(args));
                return "added-> "  + args + '\n';
            case MARK:
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "There is no such task you dumbass\n";
                    }
                    this.taskList.markTask(num);
                    return "Wow!!! Great Job!!! You want a pat on the back or something???" + 
                        this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "That's not even a number dumbass\n";
                }
            case UNMARK:
                try {
                    int num = Integer.parseInt(Util.parseNextString(args).first());
                    if (num > this.taskList.size()) {
                        return "There is no such task you dumbass\n";
                    }
                    this.taskList.unmarkTask(num);
                    return "How do you even uncomplete something?" + 
                        this.taskList.get(num);
                } catch (NumberFormatException ex) {
                    return "That's not even a number dumbass\n";
                }


            default:
                return "Case not accounted for, review code\n";
                // for debugging
        }
    }

    private Pair<Command, String> parseCommand(String line) {
        Pair<String, String> pr = Util.parseNextString(line);
        String command = pr.first();
        String rest = pr.second();

        if (command.isEmpty()) {
            return new Pair<>(Command.ERROR, "no input detected\n");
        }
        if (command.equals("bye")) {
            return new Pair<>(Command.EXIT, "");
        }
        if (command.equals("list")) {
            return new Pair<>(Command.LIST, "");
        }
        if (command.equals("mark")) {
            return new Pair<>(Command.MARK, rest);
        }
        if (command.equals("unmark")) {
            return new Pair<>(Command.UNMARK, rest);
        }
        return new Pair<>(Command.ADD, line);
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
