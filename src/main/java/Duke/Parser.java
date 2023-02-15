package Duke;
import Duke.Exceptions.DukeException;
import Duke.Exceptions.EmptyDescriptionException;
import Duke.Exceptions.DuplicateException;
import Duke.Tasks.*;
import Duke.Exceptions.UnknownCommandException;
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

    private static boolean isRunning = true;

    /**
     * The method for listOut
     * @param listOfTasks the TasklistOfTasks where tasks are stored in
     */
    private static String listOut(TaskList listOfTasks) {
        System.out.println(Ui.Underline());
        String listString = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            listString += String.format("\t" + (i + 1) + ".%s\n", listOfTasks.get(i + 1).toString());
        }
        return String.format(Ui.listMsg() + "\n" + "%s\n" + Ui.Underline(), listString);
    }

    /**
     * The method for addTodo
     * @param instct the description of task to do
     * @param listOfTasks the TasklistOfTasks where tasks are stored in
     */
    private static String addTodo(String instct, TaskList listOfTasks) throws DuplicateException, IndexOutOfBoundsException {
        System.out.println(Ui.Underline());
        String description = instct.split(" ", 2)[1];
        int lenBefore = listOfTasks.size();
        ToDo tdItem = new ToDo(description);
        listOfTasks.addTask(tdItem);
        assert listOfTasks.size() == lenBefore + 1;
        return String.format("\tGot it. I have added this mission:\n"
                             + "\t%s\n"
                             + "\tNow you have %d missions in the listOfTasks.\n" + Ui.Underline(),tdItem.toString(), listOfTasks.size());
    }

    /**
     * The method for addEvents
     * @param description of events task
     * @param listOfTasks TasklistOfTasks where task is going to be stored in
     * @param start the starting time of the event
     * @param end the ending time of the event
     */
    private static String addEvents(String description, TaskList listOfTasks, LocalDateTime start, LocalTime end) throws DuplicateException{
        System.out.println(Ui.Underline());
        Events evItem = new Events(description, start, end);
        int lenBefore = listOfTasks.size();
        listOfTasks.addTask(evItem);
        assert listOfTasks.size() == lenBefore + 1;
        return String.format("\tGot it. I have added this mission:\n "
                             + "\t%s\n"
                             + "\tNow you have %d missions in the listOfTasks.\n" + Ui.Underline(), evItem.toString(), listOfTasks.size());
    }

    /**
     * The method for addDeadline
     * @param description of deadline task
     * @param listOfTasks tasklistOfTasks where task going to be stored in
     * @param doneBy the time of deadline task
     */

    private static String addDeadline(String description, TaskList listOfTasks, LocalDateTime doneBy) throws DuplicateException {
        System.out.println(Ui.Underline());
        int lenBefore = listOfTasks.size();
        Deadline dlItem = new Deadline(description, doneBy);
        listOfTasks.addTask(dlItem);
        assert listOfTasks.size() == lenBefore + 1;
        return String.format("\tGot it. I have added this mission:\n "
                             + "\t%s\n"
                             + "\tYou have %d missions in the listOfTasks.\n" + Ui.Underline(), dlItem.toString(), listOfTasks.size());
    }

    /**
     * The method for delete
     * @param number the index of task to delete + 1
     * @param listOfTasks list of task task is deleted from
     */
    public static String delete(String number, TaskList listOfTasks) {
        System.out.println(Ui.Underline());
        int lenBefore = listOfTasks.size();
        Task removed = listOfTasks.get(Integer.parseInt(number.split(" ")[1]) );
        int nummbering = Integer.parseInt(number.split(" ")[1]);
        listOfTasks.removeTask(nummbering);
        assert listOfTasks.size() == lenBefore - 1;
        return String.format( "\n%s\n"
                             + "\t%s removed\n"
                             + "\tNow you have %d missions in the listOfTasks.\n" + Ui.Underline() , Ui.showDeleteMessage(), removed.toString(), listOfTasks.size());

    }
    public static String findTask(TaskList listOfTasks, String wantedTask) {
        System.out.println(Ui.Underline());
        ArrayList<Task> containingList = new ArrayList<>();
        String filteredTasks = "";
        for (int i = 1; i <= listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.contains(wantedTask)) {
                containingList.add(task);
            }
        }
        if (containingList.size() == 0) {
            return String.format("\tSorry you have no matching missions.\n");
        }
        for (int i = 1; i <= containingList.size(); i++) {
            filteredTasks += String.format("\t" + i + ".%s" ,containingList.get(i - 1));
        }
        return String.format("\tHere are the matching missions in your listOfTasks:\n" + filteredTasks + "\n" + Ui.Underline());

    }

    private static boolean excptCheck(Object ex)  {
        if (ex instanceof IndexOutOfBoundsException){
            return true;
        }
        return false;
    }
    public static boolean getFlag() {
        return isRunning;
    }


    /**
     * The method for parse which makes sense of the user input and execute the correct actions
     * @param instct the reader input to parse
     * @param listOfTasks TasklistOfTasks  which actions are to be performed on
     * @return int of -1 if bye instruct is parsed else 1 is return to signal program to continue running
     */
    public static String parse(String instct, TaskList listOfTasks) throws IOException, EmptyDescriptionException  {
        try {
             if((instct.split(" ").length) == 1) {
                 if (instct.split(" ")[0].equals("list")) {
                     String response = listOut(listOfTasks);
                     return response;

                 } else if (instct.split(" ")[0].equals("bye")) {
                     isRunning = false;
                     return Ui.sayBye();
                 }
                 throw new UnknownCommandException();
             } else {
                 if (instct.split(" ")[0].equals("mark")) {
                     int numbering = Integer.parseInt(instct.split(" ")[1]);
                     return listOfTasks.markDone(numbering);

                 } else if (instct.split(" ")[0].equals("unmark")) {
                     int numbering = Integer.parseInt(instct.split(" ")[1]);
                     return listOfTasks.markNotDone(numbering);


                 } else if (instct.split(" ")[0].equals("todo")) {
                     String description = instct.split(" ", 2)[1];
                     return addTodo(description, listOfTasks);

                 } else if (instct.split(" ")[0].equals("deadline")) {
                     String temp = instct.split(" /by ")[1];
                     String temp2 = instct.split(" /by ")[0];
                     String description = temp2.split(" ", 2)[1];
                     LocalDateTime doneBy = LocalDateTime.parse(temp, timeFormat);
                     return addDeadline(description, listOfTasks, doneBy);

                 } else if (instct.split(" ")[0].equals("event")) {
                     String[] temp = instct.split("/from | /to ");
                     String description = temp[0].split(" ", 2)[1];
                     LocalDateTime from = LocalDateTime.parse(temp[1], timeFormat);
                     LocalTime to = LocalTime.parse(temp[2], HrFormat);
                     return addEvents(description, listOfTasks, from, to);

                 } else if (instct.split(" ")[0].equals("delete")) {
                     return delete(instct, listOfTasks);

                 } else if (instct.split(" ")[0].equals("find")) {
                     String wantedTask = instct.split(" ")[1];
                     return findTask(listOfTasks, wantedTask);

                 } else {
                     throw new UnknownCommandException();
                 }
             }
        } catch (DukeException | DateTimeParseException | IndexOutOfBoundsException ex) {
            if(excptCheck(ex)){
                throw new EmptyDescriptionException(instct.split(" ")[0]);
            }
            return String.format("%s\n", ex);
        }
    }

}

