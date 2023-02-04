import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    private static String checkPlural(ArrayList<Task> tasks) {
        return tasks.size() == 1 ? " task " : " tasks ";
    }

    public static void main(String[] args) {
        Storage storage = new Storage("./data.txt");
        ArrayList<Task> tasks = storage.getTasksFromFile();
        Scanner sc = new Scanner(System.in );

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello I'm Duke!\n" + "How can I help you?");

        while (true) {
            try {
                String input = sc.nextLine();
                String[] splitInput = input.split(" ", 2);
                String command = splitInput[0];
                String remainingInput = splitInput.length > 1 ? splitInput[1] : null;

                if (command.equals(commands.BYE.toString())) {
                    System.out.println("Saving your data...");
                    storage.storeTasksInFile(tasks);
                    System.out.println("Bye!");
                    break;
                } else if (command.equals(commands.LIST.toString())) {
                    System.out.println("Here are the tasks in your list");
                    if (tasks.size() != 0) {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i).toString());
                        }
                    }
                } else if (command.equals(commands.MARK.toString())) {
                    int indexOfTask = Integer.parseInt(remainingInput) - 1;
                    Task currentTask = tasks.get(indexOfTask);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                } else if (command.equals(commands.UNMARK.toString())) {
                    int indexOfTask = Integer.parseInt(remainingInput) - 1;
                    Task currentTask = tasks.get(indexOfTask);
                    currentTask.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
                } else if (command.equals(commands.TODO.toString())) {
                    if (remainingInput == null) {
                        throw new IncorrectInputException("Enter a description!");
                    }
                    Task newTask = new Todo(remainingInput);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + Duke.checkPlural(tasks) + "in the list.");
                } else if (command.equals(commands.DEADLINE.toString())) {
                    if (remainingInput == null) {
                        throw new IncorrectInputException("Enter a description with a deadline!");
                    }
                    String[] deadlineDetails = remainingInput.split(" /by ", 2);
                    String deadlineDescription = deadlineDetails[0];
                    String deadline = deadlineDetails.length > 0 ? deadlineDetails[1] : null;

                    if (deadline == null) {
                        throw new IncorrectInputException("Enter a valid deadline!");
                    }
                    Task newTask = new Deadline(deadlineDescription, deadline);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + Duke.checkPlural(tasks) + "in the list.");
                } else if (command.equals(commands.EVENT.toString())) {
                    if (remainingInput == null) {
                        throw new IncorrectInputException("Enter a description and event period!");
                    }
                    String[] eventDetails = remainingInput.split(" /from ", 2);
                    String eventDescription = eventDetails[0];
                    String eventPeriod = eventDetails.length > 0 ? eventDetails[1] : null;

                    if (eventPeriod == null) {
                        throw new IncorrectInputException("Enter a valid event period!");
                    }
                    String[] splitPeriod = eventPeriod.split(" /to ");

                    if (splitPeriod.length < 2) {
                        throw new IncorrectInputException("Enter a valid event period!");
                    }
                    Task newTask = new Event(eventDescription, splitPeriod);
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + tasks.size() + Duke.checkPlural(tasks) + "in the list.");
                } else if (command.equals(commands.DELETE.toString())) {
                    int indexOfTask = Integer.parseInt(remainingInput) - 1;
                    Task toDelete = tasks.get(indexOfTask);
                    System.out.println("Alright, removing this task:");
                    System.out.println(toDelete.toString());
                    tasks.remove(toDelete);
                    System.out.println("Now you have " + tasks.size() + Duke.checkPlural(tasks) + "in the list.");
                } else {
                    throw new IncorrectInputException("Enter a valid task!");
                }
            } catch (IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
