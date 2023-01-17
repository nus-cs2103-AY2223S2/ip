import java.util.Scanner;

public class Duke {
    private static final int INDENT_LEVEL = 4;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Duke.say("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();

        whileLoop:
        while (true) {
            String input = scanner.nextLine();
            Command command = new Command(input);
        
            switch (command.baseCommand) {
                case "mark":
                    Duke.mark(command, tasks);
                    break;
                case "unmark":
                    Duke.unmark(command, tasks);
                    break;
                case "list":
                    Duke.list(command, tasks);
                    break;
                case "quit":
                case "exit":
                case "bye":
                    Duke.say("Bye. Hope to see you again soon!");
                    break whileLoop;
                default:
                    Duke.addTask(command, tasks);
                    break;
            }
        }
        
        scanner.close();
    }

    private static void say(String whatToSay) {
        String indentation = " ".repeat(Duke.INDENT_LEVEL);
        String horizontalLine = "_".repeat(60);
        String indentedInput = whatToSay.replaceAll("(?<=^|\n)", indentation);
        
        System.out.println(indentation + horizontalLine);
        System.out.println(indentedInput);
        System.out.println(indentation + horizontalLine + '\n');
    }

    private static void mark(Command command, TaskList tasks) {
        int taskIndex = Integer.parseInt(command.parameters[0]) - 1;
        Task task = tasks.get(taskIndex);
        task.markAsDone();
        Duke.say(
            "Nice! I've marked this task as done:\n"
                + "  " + task.toString()
        );
    }

    private static void unmark(Command command, TaskList tasks) {
        int taskIndex = Integer.parseInt(command.parameters[0]) - 1;
        Task task = tasks.get(taskIndex);
        task.markAsNotDone();
        Duke.say(
            "OK, I've marked this task as not done yet:\n"
                + "  " + task.toString()
        );
    }

    private static void list(Command command, TaskList tasks) {
        if (tasks.isEmpty()) {
            Duke.say("Nothing in the list.");
            return;
        }
        Duke.say(tasks.toString());
    }

    private static void addTask(Command command, TaskList tasks) {
        String commandString = command.toString();
        tasks.add(new Task(commandString));
        Duke.say("added: " + commandString);
    }
}
