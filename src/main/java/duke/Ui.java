package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;

// class Ui - handles all the lines to be printed and all the user inputs (commands)
public class Ui {

    public Ui() {

    }

    // prints out welcome greeting to user when Duke is run
    public String showWelcome() {
        return "    Hi! I'm Duke\n    How can I help?";
    }

    public static String handleCommand(String s, TaskList t) throws DukeException {
        assert t.getTasks().size() >= 0: "Number of tasks should be not be a  negative number";
        assert s.length() >= 0: "number of letters in command should not be a negative number";
        ArrayList<Task> tasks = t.getTasks();
        // user enters list command
        if (Parser.is_List(s)) {
            TaskList taskListing = new TaskList(tasks);
            return taskListing.displayTasks();
            // return string of tasks -> make displayTasks() return string first

            // user enters mark or unmark command
        } else if (Parser.is_Mark(s) || Parser.isUnmark(s)) {
            int taskNumber = Integer.parseInt(s.substring(s.length() - 1)) - 1;
            tasks.get(taskNumber).toggleMarked();
            String output = "";
            if (Parser.isUnmark(s)) {
                output += "    OK, I've marked this task as not done yet:";
            } else {
                output += "    Nice! I've marked this task as done:";
            }
            return output + "  " + tasks.get(taskNumber).toString();

            // user enters a new task
        } else if (Parser.is_toDo(s)) {
            Todo todo = new Todo(Parser.getTodo(s));
            tasks.add(todo);
            return "   added: " + todo;
        } else if (Parser.is_Deadline(s)) {
            Deadline deadline = Parser.parseDeadline(s);
            tasks.add(deadline);
            return "   added: " + deadline;

        } else if (Parser.isEvent(s)) {
            Event event = Parser.parseEvent(s);
            return "    added: " + event;

        } else if (Parser.is_Delete(s)) {
                int index = Parser.getIndex(s);
            if (tasks.size() != 0 && index > 0 && index <= tasks.size()) {
                Task deletedTask = tasks.get(index);
                tasks.remove(index);
                return "    Noted. I've removed this task:\n      " + deletedTask +
                        "\n    Now you have " + tasks.size()+ " tasks in the list";
            } else {
                return " OOPS!!!  task number is out of bounds!";
            }


            }
        else if (Parser.is_Find(s)) {
            String findString = s.split(" ", 2)[1];
            ArrayList<Task> foundTasks = new ArrayList<Task>();
            for (Task task : tasks) {
                if (task.toString().contains(findString)) {
                    foundTasks.add(task);
                }
            }
            TaskList searchResults = new TaskList(foundTasks);
            return "Here are the tasks I found!\n" + searchResults.displayTasks();


        } else if (Parser.isSnooze(s)) {
            //edit existing deadline
            //format: snooze 1 /to
            try {
                Parser.getTaskNum(s);
            } catch (DukeException e) {
                return e.getMessage();
            }

            int taskNumber = Parser.getTaskNum(s) - 1;


            Task snoozedTask = tasks.get(taskNumber);
            if (!(snoozedTask instanceof Deadline)) {
                return "    OOPS!!! Task must be a deadline to snooze!";
            }

            Deadline deadline = (Deadline) snoozedTask;

            String date  = (s.split(" /to", 2)[1]).trim();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                LocalDate snoozedDate = LocalDate.parse(date, formatter);
            } catch(DateTimeParseException e) {
                return "Date is an invalid format! Should be yyyy-MM-dd HH:mm";
            }

            if (LocalDateTime.parse(date, formatter).compareTo(deadline.getDeadline()) > 0) {
                tasks.set(taskNumber, new Deadline(deadline.getDescription(), date));
                Deadline newDeadline = (Deadline) tasks.get(taskNumber);
                return deadline + " has been snoozed to " + newDeadline.getDeadline();
            } else {
                return  "    OOPS!!! Cannot snooze to an earlier or same timing!";
            }
        } else if (s.contains("bye")) {
            return "    Bye. Hope to see you soon!";
        } else {
            //throw new duke.DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return "    OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
