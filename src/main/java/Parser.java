import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {

    void parse(Scanner userScan) throws DukeException{
        // switch case for future commands
        switch (userScan.next()) {
            // loop breaks, ending program if input is "bye"
            case ("bye"):
                Duke.ui.print("Goodbye, then!");
                Duke.loopEnd = true;
                break;

            // Duke lists out all Task names in TaskList when input is "list"
            case ("list"):
                // ERROR: list format is anything other than [ list ]
                if (userScan.nextLine().length()>0) {
                    throw new DukeException(Duke.ui.formatCommandError("list",
                            "list"));
                }
                if (Duke.taskList.size()==0) {
                    Duke.ui.print("You don't have anything to do right now!");
                    break;
                }
                StringBuilder toPrint = new StringBuilder();
                for (int i = 1; i < Duke.taskList.size()+1; i++) {
                    toPrint.append(i).append(". ").append(Duke.taskList.get(i-1).toString());
                    if (i < Duke.taskList.size()){
                        toPrint.append("\n");
                    }
                }
                Duke.ui.print("Here are your tasks:\n" + toPrint);
                break;

            // Duke allows user to mark tasks as done when input is "mark"
            case ("mark"):
                try {
                    String markString = userScan.nextLine().strip();
                    // ERROR: mark format is anything other than [ mark <insert integer> ]
                    if (markString.length()==0) {
                        throw new DukeException(Duke.ui.formatCommandError("mark",
                                "mark <insert INTEGER>"));
                    }
                    int markInput = Integer.parseInt(markString) - 1;
                    Duke.taskList.get(markInput).MarkDone();
                    Duke.ui.print("Okay, the following task is marked as done!\n" +
                            (markInput+1 + ". ") +
                            Duke.taskList.get(markInput).toString());
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }
                // ERROR: mark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                catch (NumberFormatException | InputMismatchException err) {
                    throw new DukeException(Duke.ui.formatLogicError("mark can only be used with an INTEGER. " +
                            "(e.g. 1, 2...)"));
                }
                // ERROR: mark target does not exist (e.g. task number is out of bounds)
                catch (IndexOutOfBoundsException err) {
                    throw new DukeException(Duke.ui.formatLogicError("you can only mark task numbers that exist.\n" +
                            "You have " +
                            Duke.taskList.size() +
                            " task(s) in your list."));
                }

            // Duke allows user to mark tasks as NOT done when input is "unmark"
            case ("unmark"):
                try {
                    String unmarkString = userScan.nextLine().strip();
                    // ERROR: unmark format is anything other than [ unmark <insert integer> ]
                    if (unmarkString.length()==0) {
                        throw new DukeException(Duke.ui.formatCommandError("unmark",
                                "unmark <insert INTEGER>"));
                    }
                    int unmarkInput = Integer.parseInt(unmarkString) - 1;
                    Duke.taskList.get(unmarkInput).MarkNotDone();
                    Duke.ui.print("Okay, the following task is marked as NOT done!\n" +
                            (unmarkInput+1 + ". ") +
                            Duke.taskList.get(unmarkInput).toString());
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }
                // ERROR: unmark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                catch (NumberFormatException | InputMismatchException err) {
                    throw new DukeException(Duke.ui.formatLogicError("unmark can only be used with an INTEGER. " +
                            "(e.g. 1, 2...)"));
                }
                // ERROR: unmark target does not exist (e.g. task number is out of bounds)
                catch (IndexOutOfBoundsException err) {
                    throw new DukeException(Duke.ui.formatLogicError("you can only unmark task numbers that exist." +
                            "\nYou have " +
                            Duke.taskList.size() +
                            " task(s) in your list."));
                }
            // Duke deletes task when input is "delete"
            case ("delete"):
                try {
                    String deleteString = userScan.nextLine().strip();
                    // ERROR: delete format is anything other than [ delete <insert integer> ]
                    if (deleteString.length()==0) {
                        throw new DukeException(Duke.ui.formatCommandError("delete",
                                "delete <insert INTEGER>"));
                    }
                    int deleteInput = Integer.parseInt(deleteString) - 1;
                    Task deletedTask = Duke.taskList.get(deleteInput);
                    Duke.taskList.remove(deleteInput);
                    Duke.ui.print("Okay, i've deleted the following task!\n" +
                            (deleteInput + 1 + ". ") +
                            deletedTask.toString());
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }
                // ERROR: delete is NOT paired with an integer (e.g. delete two, delete 2.3)
                catch (NumberFormatException | InputMismatchException err) {
                    throw new DukeException(Duke.ui.formatLogicError("delete can only be used with an INTEGER. " +
                            "(e.g. 1, 2...)"));
                }
                // ERROR: delete target does not exist (e.g. task number is out of bounds)
                catch (IndexOutOfBoundsException err) {
                    throw new DukeException(Duke.ui.formatLogicError("you can only delete task numbers that exist." +
                            "\nYou have " +
                            Duke.taskList.size() +
                            " task(s) in your list."));
                }

            // Duke adds Deadline
            case ("deadline"):
                try {
                    String deadlineSentence = userScan.nextLine();
                    String deadlineName = deadlineSentence.substring(0, deadlineSentence.indexOf(" /by"));
                    // ERROR: deadline description is blank.
                    if (deadlineName.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("deadline description cannot be blank."));
                    }
                    String deadlineDate = deadlineSentence.substring(deadlineSentence.indexOf(" /by")+5);
                    // ERROR: deadline date is blank.
                    if (deadlineDate.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("deadline date cannot be blank."));
                    }
                    Task deadlineToAdd = new Deadline(deadlineName, deadlineDate);
                    Duke.taskList.add(deadlineToAdd);
                    Duke.ui.print("Task added:\n " + deadlineToAdd + "\n" + "There are now " + Duke.taskList.size() +
                            " task(s) in your list.");
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }
                // ERROR: deadline format is anything other than [ deadline /by <insert deadline> ]
                catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(Duke.ui.formatCommandError("deadline",
                            "deadline <insert description> " +
                                    "/by <insert deadline>"));
                }

            // Duke adds Event
            case ("event"):
                try {
                    String eventSentence = userScan.nextLine();
                    String eventName = eventSentence.substring(0, eventSentence.indexOf(" /from"));
                    // ERROR: event description is blank.
                    if (eventName.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("event description cannot be blank."));
                    }
                    String fromDate = eventSentence.substring(eventSentence.indexOf(" /from")+7,
                            eventSentence.indexOf(" /to"));
                    // ERROR: event's from field is blank.
                    if (fromDate.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("event's from field cannot be blank."));
                    }
                    String toDate = eventSentence.substring(eventSentence.indexOf(" /to")+5);
                    // ERROR: event's to field is blank.
                    if (toDate.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("event's to field cannot be blank."));
                    }
                    Task eventToAdd = new Event(eventName, fromDate, toDate);
                    Duke.taskList.add(eventToAdd);
                    Duke.ui.print("Task added:\n " + eventToAdd + "\n" + "There are now " + Duke.taskList.size() +
                            " task(s) in your list.");
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }
                // ERROR: event format is anything other than [ event /from <insert from field> /to <insert to field> ]
                catch (StringIndexOutOfBoundsException err) {
                    throw new DukeException(Duke.ui.formatCommandError("event",
                            "event /from <insert from field> " +
                                    "/to <insert to field>"));
                }

            // Duke adds To-Do
            case ("todo"):
                try {
                    String toDoName = userScan.nextLine();
                    // ERROR: To-Do description is blank.
                    if (toDoName.strip().length()==0) {
                        throw new DukeException(Duke.ui.formatLogicError("ToDo description cannot be blank."));
                    }
                    Task taskToAdd = new ToDo(toDoName);
                    Duke.taskList.add(new ToDo(toDoName));
                    Duke.ui.print("Task added:\n " + taskToAdd + "\n" + "There are now " + Duke.taskList.size() +
                            " task(s) in your list.");
                    Duke.dukeSave.saveTaskList(Duke.taskList);
                    break;
                }
                catch (IOException err) {
                    throw new DukeException(Duke.ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                }

            // Duke does not understand any other commands (yet).
            default:
                Duke.ui.print("Yeah, i'm sorry. I don't understand that.");
        }
    }
}
