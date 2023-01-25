import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    // Attributes
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int listIndex = 0;

    // Methods
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("How may I help you today?\n");
    }

    public static void echo(String userInput) {
        System.out.println("> Duke's response:");
        System.out.println(userInput);
        System.out.println("--------------------------------\n");
    }

    public static void addToList(Task listItem) {
        taskList.add(listItem);
        listIndex++;
        System.out.println("> Duke's response:");
        System.out.println("I've added the following task to your list:");
        System.out.println(listItem.toString());
        System.out.println("Current tasks count: " + (listIndex));
        System.out.println("--------------------------------\n");
    }

    public static void removeFromList(int pos) {
        Task curr = taskList.remove(pos - 1);
        listIndex--;
        System.out.println("> Duke's response:");
        System.out.println("I've removed the following task from your list:");
        System.out.println(curr.toString());
        System.out.println("Current tasks count: " + (listIndex));
        System.out.println("--------------------------------\n");
    }

    public static void displayList() {
        int pos = 0;
        System.out.println("Here are the tasks in your list:");
        while (pos < listIndex) {
            System.out.println((pos + 1) + ". " + taskList.get(pos).toString());
            pos++;
        }
        System.out.println("End of list!\n");
    }

    public static void throwException(String exceptionType) throws DukeException {
        throw new DukeException(exceptionType);
    }

    public static void exit() {
        System.out.println("> Duke's response:");
        System.out.println("Bye. Hope to see you again!");
        System.out.println("--------------------------------\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String userInput =  sc.nextLine();
        String exitCommand = "bye";
        while (!userInput.equals(exitCommand)) {
            if (!userInput.equals("list")) {
                // If input = "mark x" set task x completed? to True
                if (userInput.startsWith("mark ")){
                    int taskNum = Integer.parseInt(userInput.substring(5));
                    taskList.get(taskNum - 1).setCompleted(true);
                }

                // If input = "unmark x" set task x completed? to False
                else if (userInput.startsWith("unmark ")){
                    int taskNum = Integer.parseInt(userInput.substring(7));
                    taskList.get(taskNum - 1).setCompleted(false);
                }

                // If input is a deadline, create deadline and add to task list
                else if (userInput.startsWith("deadline ")) {
                    if (userInput.contains("/by ")) {
                        addToList(new Deadline(userInput));
                    } else {
                        try {
                            throwException("deadline");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If input is an event, create event and add to task list
                else if (userInput.startsWith("event ")) {
                    if (userInput.contains("/from ") && userInput.contains("/to ")) {
                        addToList(new Event(userInput));
                    } else {
                        try {
                            throwException("event");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If input is a ToDos item, create ToDos item and add to task list
                else if (userInput.startsWith("todo ")) {
                    if (userInput.length() > 5) {
                        addToList(new Todo(userInput));
                    } else {
                        try {
                            throwException("todo");
                        } catch (DukeException de) {
                            System.out.println(de.toString());
                        }
                    }
                }

                // If command is delete, then remove from task list and return deleted task
                else if (userInput.startsWith("delete ")) {
                    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!___________handle non-int input
                    try {
                        int num = Integer.parseInt(userInput.substring(7));
                        removeFromList(num);
                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                    }

                }

                // Else create and add task to list
                else {
                    try {
                        addToList(new Task(0));
                    }
                    catch (DukeException de){
                        System.out.println(de.toString());
                    }
                }
                // Insert call on method that writes curr version of taskList to data/duke.txt
            }
            else {
                displayList();
            }
            userInput = sc.nextLine();
        }
        exit();

    }
}
