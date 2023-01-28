import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }
    private static final ArrayList<Task> list = new ArrayList<Task>();

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    private static void addTask(String description, TaskType taskType) {
        switch (taskType) {
            case TODO:
                Todo todoTask = new Todo(description);
                list.add(todoTask);
                System.out.println("Got it. I've added this task:\n" + todoTask);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                break;
            case EVENT:
                try {
                    String modifiedDescription = description.split(" /from ")[1];
                    LocalDate fromDate = LocalDate.parse(modifiedDescription.split(" /to ")[0]);
                    LocalDate toDate = LocalDate.parse(modifiedDescription.split(" /to ")[1]);
                    Event eventTask = new Event(description.split(" /from ")[0], fromDate, toDate);
                    list.add(eventTask);
                    System.out.println("Got it. I've added this task:\n" + eventTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid format, please try again using [task] /from [YYYY-MM-DD] /to [YYYY-MM-DD] format!");
                    break;
                }
            case DEADLINE:
                try {
                    Deadline deadlineTask = new Deadline(description.split(" /by ")[0], LocalDate.parse(description.split(" /by ")[1]));
                    list.add(deadlineTask);
                    System.out.println("Got it. I've added this task:\n" + deadlineTask);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid format, please try again using [task] /by [YYYY-MM-DD] format!");
                    break;
                }

        }
    }

    private static void mark(int itemNo) throws DukeException {
        list.get(itemNo).setStatus(true);
        System.out.println("Nice! I've marked this task as done:\n" + list.get(itemNo).toString());
    }

    private static void unmark(int itemNo) {
        list.get(itemNo).setStatus(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + list.get(itemNo).toString());
    }

    private static void delete(int itemNo) {
        System.out.println("Noted. I've removed this task:\n" + list.get(itemNo).toString());
        list.remove(itemNo);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }


    public static void main(String[] args) {
        String logo
                = " ___________________\n"
                + " | _______________ |\n"
                + " | |             | |\n"
                + " | |     MEL     | |\n"
                + " | |     <3      | |\n"
                + " | |   CS2103    | |\n"
                + " | |_____________| |\n"
                + " |_________________|\n"
                + "     _[_______]_\n"
                + " ___[___________]___\n"
                + "|         [_____] []|__\n"
                + "|         [_____] []|  \\__\n"
                + "L___________________J     \\ \\___\\/\n"
                + " ___________________      / \\\n"
                + "/###################\\    (__)\n";
        System.out.println(logo + "Hello! I'm MEL\nWhat can I do for you?\n-----------------------");

        Scanner sc = new Scanner(System.in);
        boolean isQuit = false;

        while (!isQuit) {
            System.out.print("> ");
            String userInput = sc.nextLine();
            String cmd = userInput.split(" ")[0];

            try {
                switch (cmd) {
                    case "bye":
                        isQuit = true;
                        System.out.println("MEL: Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        displayList();
                        break;
                    case "mark":
                        mark(Integer.parseInt(userInput.split(" ")[1]) - 1);
                        break;
                    case "unmark":
                        unmark(Integer.parseInt(userInput.split(" ")[1]) - 1);
                        break;
                    case "todo":
                        addTask(userInput.split("todo ")[1], TaskType.TODO);
                        break;
                    case "event":
                        addTask(userInput.split("event ")[1], TaskType.EVENT);
                        break;
                    case "deadline":
                        addTask(userInput.split("deadline ")[1], TaskType.DEADLINE);
                        break;
                    case "delete":
                        delete(Integer.parseInt(userInput.split(" ")[1]) - 1);
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means");
                }
            }
            catch(DukeException exception) {
                System.out.println(exception.getMessage());
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("MEL: Invalid format of input, please try again!");
            }
            catch (NumberFormatException e) {
                System.out.println("MEL: Invalid integer, please try again!");
            }
        }
    }
}
