import java.io.IOException;
import java.util.InputMismatchException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage dukeSave = new Storage();
        ArrayList<Task> TaskList = dukeSave.loadTaskList();
        Scanner UserScan = new Scanner(System.in);

        // while LoopEnd = true loop to accept user input
        boolean LoopEnd = false;
        while (!LoopEnd) {

            // try block to catch DukeException and prevent program from terminating itself.
            try{
                // switch case for future commands
                switch (UserScan.next()) {

                    // loop breaks, ending program if input is "bye"
                    case ("bye"):
                        ui.print("Goodbye, then!");
                        LoopEnd = true;
                        break;

                    // Duke lists out all Task names in TaskList when input is "list"
                    case ("list"):
                        // ERROR: list format is anything other than [ list ]
                        if (UserScan.nextLine().length()>0) {
                            throw new DukeException(ui.formatCommandError("list",
                                                                            "list"));
                        }
                        if (TaskList.size()==0) {
                            ui.print("You don't have anything to do right now!");
                            break;
                        }
                        StringBuilder ToPrint = new StringBuilder();
                        for (int i = 1; i < TaskList.size()+1; i++) {
                            ToPrint.append(i).append(". ").append(TaskList.get(i-1).toString());
                            if (i < TaskList.size()){
                                ToPrint.append("\n");
                            }
                        }
                        ui.print("Here are your tasks:\n" + ToPrint);
                        break;

                    // Duke allows user to mark tasks as done when input is "mark"
                    case ("mark"):
                        try {
                            String MarkString = UserScan.nextLine().strip();
                            // ERROR: mark format is anything other than [ mark <insert integer> ]
                            if (MarkString.length()==0) {
                                throw new DukeException(ui.formatCommandError("mark",
                                                                                "mark <insert INTEGER>"));
                            }
                            int MarkInput = Integer.parseInt(MarkString) - 1;
                            TaskList.get(MarkInput).MarkDone();
                            ui.print("Okay, the following task is marked as done!\n" +
                                    (MarkInput+1 + ". ") +
                                    TaskList.get(MarkInput).toString());
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }
                        // ERROR: mark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException(ui.formatLogicError("mark can only be used with an INTEGER. " +
                                                                        "(e.g. 1, 2...)"));
                        }
                        // ERROR: mark target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException(ui.formatLogicError("you can only mark task numbers that exist.\n" +
                                                                        "You have " +
                                                                        TaskList.size() +
                                                                        " task(s) in your list."));
                        }

                        // Duke allows user to mark tasks as NOT done when input is "unmark"
                    case ("unmark"):
                        try {
                            String UnmarkString = UserScan.nextLine().strip();
                            // ERROR: unmark format is anything other than [ unmark <insert integer> ]
                            if (UnmarkString.length()==0) {
                                throw new DukeException(ui.formatCommandError("unmark",
                                                                                "unmark <insert INTEGER>"));
                            }
                            int UnmarkInput = Integer.parseInt(UnmarkString) - 1;
                            TaskList.get(UnmarkInput).MarkNotDone();
                            ui.print("Okay, the following task is marked as NOT done!\n" +
                                    (UnmarkInput+1 + ". ") +
                                    TaskList.get(UnmarkInput).toString());
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }
                        // ERROR: unmark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException(ui.formatLogicError("unmark can only be used with an INTEGER. " +
                                                                        "(e.g. 1, 2...)"));
                        }
                        // ERROR: unmark target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException(ui.formatLogicError("you can only unmark task numbers that exist." +
                                                                        "\nYou have " +
                                                                        TaskList.size() +
                                                                        " task(s) in your list."));
                        }
                        // Duke deletes task when input is "delete"
                    case ("delete"):
                        try {
                            String DeleteString = UserScan.nextLine().strip();
                            // ERROR: delete format is anything other than [ delete <insert integer> ]
                            if (DeleteString.length()==0) {
                                throw new DukeException(ui.formatCommandError("delete",
                                                                                "delete <insert INTEGER>"));
                            }
                            int DeleteInput = Integer.parseInt(DeleteString) - 1;
                            Task DeletedTask = TaskList.get(DeleteInput);
                            TaskList.remove(DeleteInput);
                            ui.print("Okay, i've deleted the following task!\n" +
                                    (DeleteInput + 1 + ". ") +
                                    DeletedTask.toString());
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }
                        // ERROR: delete is NOT paired with an integer (e.g. delete two, delete 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException(ui.formatLogicError("delete can only be used with an INTEGER. " +
                                                                        "(e.g. 1, 2...)"));
                        }
                        // ERROR: delete target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException(ui.formatLogicError("you can only delete task numbers that exist." +
                                                                        "\nYou have " +
                                                                        TaskList.size() +
                                                                        " task(s) in your list."));
                        }

                        // Duke adds Deadline
                    case ("deadline"):
                        try {
                            String DeadlineSentence = UserScan.nextLine();
                            String DeadlineName = DeadlineSentence.substring(0, DeadlineSentence.indexOf(" /by"));
                            // ERROR: deadline description is blank.
                            if (DeadlineName.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("deadline description cannot be blank."));
                            }
                            String DeadlineDate = DeadlineSentence.substring(DeadlineSentence.indexOf(" /by")+5);
                            // ERROR: deadline date is blank.
                            if (DeadlineDate.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("deadline date cannot be blank."));
                            }
                            Task DeadlineToAdd = new Deadline(DeadlineName, DeadlineDate);
                            TaskList.add(DeadlineToAdd);
                            ui.print("Task added:\n " + DeadlineToAdd + "\n" + "There are now " + TaskList.size() +
                                    " task(s) in your list.");
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }
                        // ERROR: deadline format is anything other than [ deadline /by <insert deadline> ]
                        catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException(ui.formatCommandError("deadline",
                                                                            "deadline <insert description> " +
                                                                                   "/by <insert deadline>"));
                        }

                        // Duke adds Event
                    case ("event"):
                        try {
                            String EventSentence = UserScan.nextLine();
                            String EventName = EventSentence.substring(0, EventSentence.indexOf(" /from"));
                            // ERROR: event description is blank.
                            if (EventName.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("event description cannot be blank."));
                            }
                            String FromDate = EventSentence.substring(EventSentence.indexOf(" /from")+7,
                                                                      EventSentence.indexOf(" /to"));
                            // ERROR: event's from field is blank.
                            if (FromDate.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("event's from field cannot be blank."));
                            }
                            String ToDate = EventSentence.substring(EventSentence.indexOf(" /to")+5);
                            // ERROR: event's to field is blank.
                            if (ToDate.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("event's to field cannot be blank."));
                            }
                            Task EventToAdd = new Event(EventName, FromDate, ToDate);
                            TaskList.add(EventToAdd);
                            ui.print("Task added:\n " + EventToAdd + "\n" + "There are now " + TaskList.size() +
                                    " task(s) in your list.");
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }
                        // ERROR: event format is anything other than [ event /from <insert from field> /to <insert to field> ]
                        catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException(ui.formatCommandError("event",
                                                                            "event /from <insert from field> " +
                                                                                   "/to <insert to field>"));
                        }

                        // Duke adds To-Do
                    case ("todo"):
                        try {
                            String ToDoName = UserScan.nextLine();
                            // ERROR: To-Do description is blank.
                            if (ToDoName.strip().length()==0) {
                                throw new DukeException(ui.formatLogicError("ToDo description cannot be blank."));
                            }
                            Task TaskToAdd = new ToDo(ToDoName);
                            TaskList.add(new ToDo(ToDoName));
                            ui.print("Task added:\n " + TaskToAdd + "\n" + "There are now " + TaskList.size() +
                                    " task(s) in your list.");
                            dukeSave.saveTaskList(TaskList);
                            break;
                        }
                        catch (IOException err) {
                            throw new DukeException(ui.formatMessage("[ERROR]\nOops, we couldn't save that!"));
                        }

                    // Duke does not understand any other commands (yet).
                    default:
                        ui.print("Yeah, i'm sorry. I don't understand that.");
                }
            }
            // Catches DukeException if thrown and prevents program from terminating.
            catch (DukeException ex){
                // error message can be formatted in many possible ways beforehand, so println is used.
                System.out.println(ex.PrintErrorMessage());
            }

        }
    }
}
