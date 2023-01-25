import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.IndexOutOfBoundsException;
import java.lang.NullPointerException;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    // Enum list to contain instructions to be entered by user to control system
    public enum Instructions {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    private Scanner sc = new Scanner(System.in);
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this.storage = new Storage();
        this.taskList = new TaskList();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        new Duke().userInputs();

    }

    // Allow users to add, mark and un-mark, delete, add tasks (to-do, deadline,
    // event) or show items in a list
    public void userInputs() {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        this.storage.loadFileData();

        try {
            while (true) {
                String input = sc.nextLine();
                String[] part = input.split(" ");
                int index = 0;
                String first_word = part[0].toUpperCase();

                if (part[0].equals("mark") || part[0].equals("unmark") || part[0].equals("delete")) {
                    index = Integer.parseInt(part[1]) - 1;
                }

                if (!Arrays.stream(Instructions.values()).anyMatch(x -> (x.toString()).equals(first_word))) {
                    throw new TaskException("Sorry! Duke has no idea what it is as it is not an instruction");
                }

                Instructions instruction = Instructions.valueOf(first_word);

                switch (instruction) {
                    // Exit the system upon entering
                    case BYE:
                        System.out.println("Oh no! Don't give up pls.. you still haven't found a gf yet :(");
                        break;

                    // Display a list of tasks that shows its completion and types
                    case LIST:
                        System.out.println("Take a look at ye DREAM goals for 2023");
                        this.storage.displayList();
                        break;

                    // Mark to complete the task, the second bracket will show a cross
                    case MARK:
                        this.storage.markListItem(index);
                        break;

                    // Un-mark to redo the completion of the task, the cross will be
                    // removed from the second bracket
                    case UNMARK:
                        this.storage.unmarkListItem(index);
                        break;

                    // Add task of type (To do)

                    case TODO:
                        if (input.length() < 5) {
                            throw new TaskException("Please enter an to-do item");
                        }
                        this.storage.addTodoItem(input);
                        break;

                    // Add task of type (Deadline)
                    case DEADLINE:
                        if (!input.contains("/by")) {
                            throw new TaskException("Enter an valid item followed by a deadline");
                        }
                        String[] deadline_part = input.substring(9, input.length()).split("/by ");
                        this.storage.addDeadlineItem(deadline_part[0], deadline_part[1]);
                        // list.add(new Deadline(deadline_part[0], deadline_part[1]));
                        break;

                    // Add task of type (Event)
                    case EVENT:
                        boolean from = input.contains("/from");
                        boolean to = input.contains("/to");
            
                        if ((!from || !to) || !(from && to)) {
                            throw new TaskException("Event item must include a start time and an end time");
                        }
                        String[] event_part = input.substring(6, input.length()).split("/from ");
                        String[] range = event_part[1].split("/to ");
                        this.storage.addEventItem(event_part[0], range[0], range[1]);
                        // list.add(new Event(event_part[0], range[0], range[1]));
                        break;

                    // Delete task from the list according to its numbering on the list
                    case DELETE:
                        this.storage.deleteListItem(index);
                        break;

                    // default will throw an exception in case switch-case is unable to find
                    // instruction
                    // default will throw an exception in case switch-case is unable to find
                    // instruction
                    default:
                        throw new TaskException("Sorry! Duke has no idea what it is as it is not an instruction");
                }

                this.storage.writeToFile();

            }
        } catch (TaskException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Object pointing to null, please check code");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Check if the index is within the size of the array");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Nothing to mark/unmark!");
        }
        
    }

}
// Exception that return a custom message that handles errors in task
// instructions
class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}
