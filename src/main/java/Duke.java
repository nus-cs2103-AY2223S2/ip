import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Default welcome message and message border
        String border = "_________________________________________\n";
        System.out.println(border + "Sup, Duke here.\nWhat do you want from me?\n" + border);

        // Initialise list of tasks
        ArrayList<Task> TaskList = new ArrayList<>();

        // while LoopEnd = true loop to accept user input
        boolean LoopEnd = false;
        while (!LoopEnd) {
            Scanner UserScan = new Scanner(System.in);
            // try block to catch DukeException and prevent program from terminating itself.
            try{
                // switch case for future commands
                switch (UserScan.next()) {

                    // loop breaks, ending program if input is "bye"
                    case ("bye"):
                        // ERROR: bye format is anything other than [ bye ]
                        if (UserScan.nextLine().length()>0) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, bye command format is used wrongly.\nCorrect format is as follows:\n" +
                                    "[ bye ]\n" + border);
                        }
                        System.out.println(border + "Goodbye, then!\n" + border);
                        LoopEnd = true;
                        break;

                    // Duke lists out all Task names in TaskList when input is "list"
                    case ("list"):
                        // ERROR: list format is anything other than [ list ]
                        if (UserScan.nextLine().length()>0) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, list command format is used wrongly.\nCorrect format is as follows:\n" +
                                    "[ list ]\n" + border);
                        }
                        if (TaskList.size()==0) {
                            System.out.println(border + "You don't have anything to do right now!\n" + border);
                            break;
                        }
                        StringBuilder ToPrint = new StringBuilder();
                        for (int i = 0; i < TaskList.size(); i++) {
                            ToPrint.append(i).append(1).append(". ").append(TaskList.get(i).toString()).append("\n");
                        }
                        System.out.println(border + "Here are your tasks:\n" + ToPrint + border);
                        break;

                    // Duke allows user to mark tasks as done when input is "mark"
                    case ("mark"):
                        try {
                            String MarkString = UserScan.nextLine().strip();
                            // ERROR: mark format is anything other than [ mark <insert integer> ]
                            if (MarkString.length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, mark command format is used wrongly.\nCorrect format is as follows:\n" +
                                        "[ mark <insert INTEGER> ]\n" + border);
                            }
                            int MarkInput = Integer.parseInt(MarkString) - 1;
                            TaskList.get(MarkInput).MarkDone();
                            System.out.println(border + "Okay, the following task is marked as done!\n");
                            System.out.println((MarkInput+1 + ". ") + TaskList.get(MarkInput).toString() + "\n" + border);
                            break;
                        }
                        // ERROR: mark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, mark can only be used with an INTEGER. (e.g. 1, 2...)\n" + border);
                        }
                        // ERROR: mark target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, you can only mark task numbers that exist.\nYou have "
                                    + TaskList.size() + " task(s) in your list.\n" + border);
                        }

                        // Duke allows user to mark tasks as NOT done when input is "unmark"
                    case ("unmark"):
                        try {
                            String UnmarkString = UserScan.nextLine().strip();
                            // ERROR: unmark format is anything other than [ unmark <insert integer> ]
                            if (UnmarkString.length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, unmark command format is used wrongly.\nCorrect format is as follows:\n" +
                                        "[ unmark <insert INTEGER> ]\n" + border);
                            }
                            int UnmarkInput = Integer.parseInt(UnmarkString) - 1;
                            TaskList.get(UnmarkInput).MarkNotDone();
                            System.out.println(border + "Okay, the following task is marked as NOT done!\n");
                            System.out.println((UnmarkInput+1 + ". ") + TaskList.get(UnmarkInput).toString() + "\n" + border);
                            break;
                        }
                        // ERROR: unmark is NOT paired with an integer (e.g. unmark two, unmark 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, unmark can only be used with an INTEGER. (e.g. 1, 2...)\n" + border);
                        }
                        // ERROR: unmark target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, you can only unmark task numbers that exist.\nYou have "
                                    + TaskList.size() + " task(s) in your list.\n" + border);
                        }

                        // Duke deletes task when input is "delete"
                    case ("delete"):
                        try {
                            String DeleteString = UserScan.nextLine().strip();
                            // ERROR: delete format is anything other than [ delete <insert integer> ]
                            if (DeleteString.length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, delete command format is used wrongly.\nCorrect format is as follows:\n" +
                                        "[ delete <insert INTEGER> ]\n" + border);
                            }
                            int DeleteInput = Integer.parseInt(DeleteString) - 1;
                            Task DeletedTask = TaskList.get(DeleteInput);
                            TaskList.remove(DeleteInput);
                            System.out.println(border + "Okay, i've deleted the following task!\n");
                            System.out.println((DeleteInput+1 + ". ") + DeletedTask.toString() + "\n" + border);
                            break;
                        }
                        // ERROR: delete is NOT paired with an integer (e.g. delete two, delete 2.3)
                        catch (NumberFormatException | InputMismatchException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, delete can only be used with an INTEGER. (e.g. 1, 2...)\n" + border);
                        }
                        // ERROR: delete target does not exist (e.g. task number is out of bounds)
                        catch (IndexOutOfBoundsException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, you can only delete task numbers that exist.\nYou have "
                                    + TaskList.size() + " task(s) in your list.\n" + border);
                        }

                        // Duke adds Deadline
                    case ("deadline"):
                        try {
                            String DeadlineSentence = UserScan.nextLine();
                            String DeadlineName = DeadlineSentence.substring(0, DeadlineSentence.indexOf(" /by"));
                            // ERROR: deadline description is blank.
                            if (DeadlineName.strip().length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, deadline description cannot be blank.\nTry again.\n" + border);
                            }
                            String DeadlineDate = DeadlineSentence.substring(DeadlineSentence.indexOf(" /by")+5);
                            // ERROR: deadline date is blank.
                            if (DeadlineDate.strip().length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, deadline date cannot be blank.\nTry again.\n" + border);
                            }
                            Task DeadlineToAdd = new Deadline(DeadlineName, DeadlineDate);
                            TaskList.add(DeadlineToAdd);
                            System.out.println(border + "Task added:\n " + DeadlineToAdd + "\n"
                                    + "There are now " + TaskList.size() + " task(s) in your list.\n" + border);
                            break;
                        }
                        // ERROR: deadline format is anything other than [ deadline /by <insert deadline> ]
                        catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, deadline command format is used wrongly.\nCorrect format is as follows:\n" +
                                    "[ deadline /by <insert deadline> ]\n" + border);
                        }

                        // Duke adds Event
                    case ("event"):
                        try {
                            String EventSentence = UserScan.nextLine();
                            String EventName = EventSentence.substring(0, EventSentence.indexOf(" /from"));
                            // ERROR: event description is blank.
                            if (EventName.strip().length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, event description cannot be blank.\nTry again.\n" + border);
                            }
                            String FromDate = EventSentence.substring(EventSentence.indexOf(" /from")+7, EventSentence.indexOf(" /to"));
                            // ERROR: event's from field is blank.
                            if (FromDate.strip().length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, event's from field cannot be blank.\nTry again.\n" + border);
                            }
                            String ToDate = EventSentence.substring(EventSentence.indexOf(" /to")+5);
                            // ERROR: event's to field is blank.
                            if (ToDate.strip().length()==0) {
                                throw new DukeException("\n" + border + "[ERROR]\nUh, event's to field cannot be blank.\nTry again.\n" + border);
                            }
                            Task EventToAdd = new Event(EventName, FromDate, ToDate);
                            TaskList.add(EventToAdd);
                            System.out.println(border + "Task added:\n " + EventToAdd + "\n"
                                    + "There are now " + TaskList.size() + " task(s) in your list.\n"+ border);
                            break;
                        }
                        // ERROR: event format is anything other than [ event /from <insert from field> /to <insert to field> ]
                        catch (StringIndexOutOfBoundsException err) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, event command format is used wrongly.\nCorrect format is as follows:\n" +
                                    "[ event /from <insert from field> /to <insert to field> ]\n" + border);
                        }

                        // Duke adds To-Do
                    case ("todo"):
                        String ToDoName = UserScan.nextLine();
                        // ERROR: To-Do description is blank.
                        if (ToDoName.strip().length()==0) {
                            throw new DukeException("\n" + border + "[ERROR]\nUh, To-Do description cannot be blank.\nTry again.\n" + border);
                        }
                        Task TaskToAdd = new ToDo(ToDoName);
                        TaskList.add(new ToDo(ToDoName));
                        System.out.println(border + "Task added:\n " + TaskToAdd + "\n"
                                + "There are now " + TaskList.size() + " task(s) in your list.\n"+ border);
                        break;

                    // Duke does not understand any other commands (yet).
                    default:
                        System.out.println(border + "Yeah, i'm sorry. I don't understand that.\n" + border);
                }
            }
            // Catches DukeException if thrown and prevents program from terminating.
            catch (DukeException ex){
                System.out.println(ex.PrintErrorMessage());
            }

        }
    }
}
