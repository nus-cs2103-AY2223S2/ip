import java.util.Scanner;
import java.util.ArrayList;

public class Book {
    /** Logo for Book */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <  \n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Horizontal line for separation. */
    private static final String LINE =
            "________________________________________________________________________________\n";

    private static final Scanner input = new Scanner(System.in);
    private static String command = "";
    private static ArrayList<Task> list = new ArrayList<Task>(100);
    private static int index = 0;
    public static void main(String[] args) {
        System.out.print(LINE + "Good day! This is\n" + LOGO + "What may I help you with?\n"
                + LINE);
        command = input.nextLine();
        while (!command.equals("bye")) {
            try {
                parse(command);
            } catch (InvalidInputException exception){
                System.out.print(LINE + exception.getMessage() + "\n" + LINE);
            } catch (IncompleteInputException exception){
                System.out.print(LINE + exception.getMessage() + "\n" + LINE);
            } finally {
                command = input.nextLine();
            }
        }
        System.out.print(LINE + "Bye! Pick up Book again soon!\n" + LINE);
    }

    private static void parse(String text) throws InvalidInputException, IncompleteInputException {
        String[] inputs = text.split(" ", 2);
        if (inputs[0].equals("list")) {
            System.out.print(LINE + "Here are the tasks stored in Book:\n");
            for (int i = 0; i < index; i++) {
                System.out.println((i + 1) + ".  " + list.get(i));
            }
            System.out.print(LINE);
        } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")
                || inputs[0].equals("delete")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("Without the index, Book cannot find the task" +
                        "you are looking for.");
            }
            int taskIndex = Integer.parseInt(inputs[1]) - 1;
            Task task = list.get(taskIndex);
            if (inputs[0].equals("mark")) {
                task.mark();
                System.out.print(LINE + "The following task has been marked as done:\n    "
                        + task + "\n" + LINE);
            } else if (inputs[0].equals("unmark")) {
                task.unmark();
                System.out.print(LINE + "The following task has been marked as not done:\n    "
                        + task + "\n" + LINE);
            } else {
                list.remove(taskIndex);
                index--;
                System.out.print(LINE + "Acknowledged, striking the following task off the list:\n    "
                        + task + "\n" + index + " task(s) remain on the list.\n" + LINE);
            }
        } else if (inputs[0].equals("todo")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The description of the todo is missing.");
            }
            int addedToDoIndex = index++;
            ToDo newToDo = new ToDo(inputs[1]);
            list.add(newToDo);
            System.out.print(LINE + "Understood, adding:\n    " + newToDo
                    + "\nThere are " + index + " task(s) in Book\n" + LINE);
        } else if (inputs[0].equals("deadline")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The deadline is missing some information.");
            }
            int addedDeadlineIndex = index++;
            String[] deadlineDetails = inputs[1].split("/by ", 2);
            if (deadlineDetails.length < 2) {
                throw new IncompleteInputException("The deadline is missing.");
            }
            Deadline newDeadLine = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            list.add(newDeadLine);
            System.out.print(LINE + "Understood, adding the deadline:\n    " + newDeadLine
                    + "\nThere are " + index + " task(s) in Book.\n" + LINE);
        } else if (inputs[0].equals("event")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The event is missing some information.");
            }
            int addedEventIndex = index++;
            String[] eventDetails = inputs[1].split("/from |/to ", 3);
            if (eventDetails.length < 2) {
                throw new IncompleteInputException("The information regarding event duration is "
                        + "missing.");
            }
            Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
            list.add(newEvent);
            System.out.print(LINE + "Understood, adding the event:\n    " + newEvent
                    + "\nThere are " + index + " task(s) in Book.\n" + LINE);
        } else {
            throw new InvalidInputException("Sorry, this command is not in Book's dictionary.");
        }
    }
}
