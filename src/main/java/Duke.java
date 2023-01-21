import java.util.Scanner;

enum Action {
    LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"),
    TODO("todo"), DEADLINE("deadline"), EVENT("event"), ERROR("error");

    private String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public static Action getAction(String action) {
        for (Action a : Action.values()) {
            if (a.getAction().equalsIgnoreCase(action)) {
                return a;
            }
        }
        return ERROR;
    }
}

public class Duke {
    public static void main(String[] args) throws DukeException {

        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");

        System.out.println("\t____________________________________________________________");
        FileHandler fileHandler = new FileHandler();
        TaskList taskList = fileHandler.readFromFile();
        Commands commands = new Commands(taskList);

        String userInput = sc.nextLine().strip();

        while (!userInput.equals("bye")) {
            Action taskType = Action.getAction(userInput.split(" ")[0]);
            System.out.println("\t____________________________________________________________");
            try {
                switch (taskType) {
                case LIST:
                    commands.listTasks();
                    break;
                case MARK:
                    commands.markTask(userInput);
                    break;
                case UNMARK:
                    commands.unmarkTask(userInput);
                    break;
                case DELETE:
                    commands.deleteTask(userInput);
                    break;
                case TODO:
                    commands.addToDoTask(userInput);
                    break;
                case DEADLINE:
                    commands.addDeadlineTask(userInput);
                    break;
                case EVENT:
                    commands.addEventTask(userInput);
                    break;
                case ERROR: {
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("\t____________________________________________________________");
                userInput = sc.nextLine().strip();
            }
        }

        sc.close();
        fileHandler.writeToFile(taskList);
        System.out.println("\t____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }
}
