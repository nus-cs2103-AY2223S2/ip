package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * contains list of tasks in Duke
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * constructor for new TaskList instance from arraylist of tasks
     * 
     * @param tasks ArrayList of tasks to copy over
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * constructor for new TaskList instance
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * adds a new Todo into arraylist
     *
     * @param command input by the user
     * @return message after adding a Todo into arraylist (or error message if it failed to add)
     */
    public String addTodo(String command) {
        try {
            String note;
            String[] descriptionAndNote = command.replace("todo", "").split("/note");
            String description = descriptionAndNote[0];
            if (descriptionAndNote.length == 1) {
                note = "";
            } else {
                note = descriptionAndNote[1].trim();
            }
            Todo todo = new Todo(description, note);
            tasks.add(todo);
            return "Got it. I've added this task:\n" + todo.toString() + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } catch (MissingDescriptionException e) {
            return e.toString();
        }
    }

    /**
     * adds a new Deadline into arraylist
     *
     * @param command input by the user
     * @return message after adding a Deadline into arraylist (or error message if it failed to add)
     */
    public String addDeadline(String command) {
        try {
            String note;
            String fullDescription = command.replace("deadline", "");
            String[] descriptionAndDateAndNote = fullDescription.split("/by ");
            String deadlineDescription = descriptionAndDateAndNote[0];
            String[] dateAndNote = descriptionAndDateAndNote[1].split(" /note");
            String date = dateAndNote[0];
            if (dateAndNote.length == 1) {
                note = "";
            } else {
                note = dateAndNote[1].trim();
            }
            LocalDate dateString = LocalDate.parse(date);
            Deadline deadline = new Deadline(deadlineDescription, dateString, note);
            tasks.add(deadline);
            return "Got it. I've added this task:\n" + deadline.toString() + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } catch (MissingDescriptionException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return "Please input date in YYYY-MM-DD format!";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please fill in all details (task description, date and notes! if no notes, put a -)";
        }
    }

    /**
     * adds a new Event into arraylist
     *
     * @param command input by the user
     * @return message after adding an Event into the arraylist (or error message if it failed to add)
     */
    public String addEvent(String command) {
        try {
            String note;
            String description = command.replace("event", "");
            String[] input = description.split("/from");
            String eventDescription = input[0];
            String[] remainder = input[1].split("/to");
            String from = remainder[0];
            String[] toAndNote = remainder[1].split(" /note");
            String to = toAndNote[0];
            if (toAndNote.length == 1) {
                note = "";
            } else {
                note = toAndNote[1].trim();
            }
            Event event = new Event(eventDescription, from, to, note);
            tasks.add(event);
            return "Got it. I've added this task:\n" + event.toString() + "\nNow you have "
                    + tasks.size() + " tasks in the list.";

        } catch (MissingDescriptionException e) {
            return e.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please fill in all details (task description, start and end time)!";
        }
    }
    /**
     * marks a task in the arraylist as completed
     * 
     * @param command user input
     * @return message after task has been marked (or error message if it failed)
     * @throws IndexOutOfBoundsException invalid index given
     */
    public String markTask(String command) throws IndexOutOfBoundsException {
        try {
            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            tasks.get(index - 1).mark();
            return "Nice! I've marked this task as done:\n" + tasks.get(index - 1).toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please give the index of the task you wish to mark!";
        } catch (IndexOutOfBoundsException e) {
            return "There are only " + tasks.size() + " tasks in the list!";
        }
    }

    /**
     * unmarks a task in arraylist to not done
     * 
     * @param command user input
     * @return message after task has been unmarked
     * @throws IndexOutOfBoundsException invalid index given
     */
    public String unmarkTask(String command) throws IndexOutOfBoundsException {
        try {
            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            tasks.get(index - 1).unmark();
            return "OK, I've marked this task as not done yet:\n" + tasks.get(index - 1).toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please give the index of the task you wish to unmark!";
        } catch (IndexOutOfBoundsException e) {
            return "There are only " + tasks.size() + " tasks in the list!";
        }
    }

    /**
     * removes a task from the arraylist
     * 
     * @param command input by user
     * @return message after task has been deleted
     * @throws IndexOutOfBoundsException invalid index given
     */
    public String deleteTask(String command) throws IndexOutOfBoundsException {
        try {
            String str = command.split(" ", 2)[1];
            int index = Integer.parseInt(str);
            Task task = tasks.remove(index - 1);
            return "Noted. I've removed this task:\n" + task.toString() + "\nNow you have "
                    + tasks.size() + " tasks in the list.";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please give the index of the task you wish to delete!";
        }
    }

    /**
     * finds tasks from the tasklist that contain description matching given keyword
     *
     * @return list of the tasks that contain the keyword as a String
     * @param command input by user
     */
    public String findTask(String command) {
        try {
            String keyword = command.split(" ", 2)[1];
            ArrayList<Task> matchingTasks = new ArrayList<Task>();
            for (Task task : tasks) {
                if (task.toString().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }
            if (matchingTasks.size() == 0) {
                return "There are no tasks with the given keyword";
            }
            TaskList matches = new TaskList(matchingTasks);
            assert matches.getSize() != 0 : "Matching tasklist should not be empty";
            return "Here are the matching tasks in your list:\n" + matches.toString();
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please input the keyword!";
        }
    }

    /**
     * gets the size of the tasklist
     * 
     * @return an integer representing the size of the tasklist
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * returns the string representation of tasks in the tasklist to be saved into
     * the text file
     * 
     * @return string representation of tasks in the tasklist to be saved into the
     *         text file
     */
    public String toStorageData() {
        String data = "";
        for (Task task : tasks) {
            data += task.toStorageData();
            data += "\n";
        }
        return data.trim();
    }

    /**
     * returns the string representation of tasks in the tasklist with indexing
     * starting from 1
     * 
     * @return string representation of tasks in the tasklist with indexing starting
     *         from 1
     */
    @Override
    public String toString() {
        String taskToText = "";
        if (tasks.size() == 0) {
            return "There are no tasks as of now!";
        }
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            String line = Integer.toString(i) + ". " + task.toString();
            taskToText += line;
            taskToText += "\n";
        }
        assert !taskToText.isEmpty() : "Tasklist should not be empty";
        return taskToText.trim();
    }

}
