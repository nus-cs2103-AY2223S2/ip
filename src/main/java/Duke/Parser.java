package Duke;
import Duke.Exceptions.DukeException;
import Duke.Exceptions.emptyDescriptionException;
import Duke.Tasks.*;
import Duke.Exceptions.unknownCommandException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Contains Parser object that deals with making sense of the user command
 */
public class Parser {
    private final static DateTimeFormatter  timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final static DateTimeFormatter  HrFormat = DateTimeFormatter.ofPattern("HHmm");


    /**
     * The method for addTodo
     * @param description the description of task to do
     * @param list the Tasklist where tasks are stored in
     */
    private static void addTodo(String description, TaskList list) {
        ToDo tdItem = new ToDo(description);
        list.addTask(tdItem);
        System.out.println("\tGot it. I have added this task:\n" + "\t" + tdItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    /**
     * The method for addEvents
     * @param description of events task
     * @param list Tasklist where task is going to be stored in
     * @param start the starting time of the event
     * @param end the ending time of the event
     */
    private static void addEvents(String description, TaskList list, LocalDateTime start, LocalTime end) {
        Events evItem = new Events(description, start, end);
        list.addTask(evItem);
        System.out.println("\tGot it. I have added this task:\n " + "\t" + evItem.toString());
        System.out.printf("\tNow you have %d tasks in the list.\n", list.size());
    }

    /**
     * The method for addDeadline
     * @param description of deadline task
     * @param list tasklist where task going to be stored in
     * @param doneBy the time of deadline task
     */

    private static void addDeadline(String description, TaskList list, LocalDateTime doneBy) {
        Deadline dlItem = new Deadline(description, doneBy);
        list.addTask(dlItem);
        System.out.println("\tGot it. I have added this task:\n " + "\t" + dlItem.toString());
        System.out.printf("\tYou have %d tasks in the list.\n\n", list.size());
    }

    /**
     * The method for delete
     * @param number the
     * @param list
     */
    public static void delete(String number, TaskList list) {
        Task removed = list.get(Integer.parseInt(number.split(" ")[1]) );
        int nummbering = Integer.parseInt(number.split(" ")[1]);
        list.removeTask(nummbering);
        Ui.Underline();
        Ui.showDeleteMessage();
        System.out.println(String.format("\t%s removed", removed.toString()));
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }
    public static void findTask(TaskList list, String wantedTask) {
        ArrayList<Task> containingList = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i);
            if (task.contains(wantedTask)) {
                containingList.add(task);
            }
        }
        if (containingList.size() == 0) {
            System.out.println("\tSorry you have no matching tasks.\n");
            return;
        }
        System.out.println("\tHere are the matching tasks in your list:");
        for (int i = 1; i <= containingList.size(); i++) {
            System.out.println("\t" + i + "." + containingList.get(i - 1));


        }
        System.out.println();

    }


    /**
     * The method for parse which makes sense of the user input and execute the correct actions
     * @param instct the reader input to parse
     * @param list Tasklist  which actions are to be performed on
     * @return int of -1 if bye instruct is parsed else 1 is return to signal program to continue running
     */
    public static int parse(String instct, TaskList list) throws  IOException  {
        String cmd = instct.split(" ")[0];
        try {
            if (instct.split(" ")[0].equals("list") ) {
                Ui.Underline();
                Ui.listMsg();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("\t" + (i + 1) + "." + list.get(i+1).toString());
                }
                Ui.Underline();

            }
            else if (instct.split(" ")[0].equals("bye")) {
                Ui.sayBye();
                return -1;
            }
            else if ((instct.split(" ").length) > 1 && !instct.split(" ")[0].equals("list")) {
                if (instct.split(" ")[0].equals("mark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]) ;
                    Ui.Underline();
                    Ui.markedMessage();
                    list.markDone(numbering);
                    System.out.println("\t" + list.get(numbering).toString());
                    Ui.Underline();

                } else if (instct.split(" ")[0].equals("unmark")) {
                    int numbering = Integer.parseInt(instct.split(" ")[1]);
                    Ui.Underline();
                    Ui.unMarkedMessage();
                    list.markNotDone(numbering);
                    System.out.println("\t" + list.get(numbering).toString());
                    Ui.Underline();

                } else if (instct.split(" ")[0].equals("todo")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    addTodo(description, list);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("deadline")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    String temp = instct.split(" /by ")[1];
                    LocalDateTime doneBy = LocalDateTime.parse(temp, timeFormat);
                    addDeadline(description, list, doneBy);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("event")) {
                    Ui.Underline();
                    String description = instct.split(" ")[1];
                    String[] temp = instct.split("/from | /to ");
                    LocalDateTime from = LocalDateTime.parse(temp[1], timeFormat);
                    LocalTime to = LocalTime.parse(temp[2], HrFormat);
                    addEvents(description, list, from, to);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("delete")) {

                    delete(instct, list);
                    Ui.Underline();


                } else if (instct.split(" ")[0].equals("find")) {
                    Ui.Underline();
                    String wantedTask = instct.split(" ")[1];
                    findTask(list, wantedTask);
                    Ui.Underline();

                } else {
                    throw new unknownCommandException();
                }


            } else {
                throw new emptyDescriptionException(instct.split(" ")[0]);


            }


        } catch (DukeException | DateTimeParseException ex) {
            System.out.printf("%s\n", ex);
            Ui.Underline();
        }
        return 1;
    }

}

