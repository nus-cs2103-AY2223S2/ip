package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class for Ui object.
 * This class handles the interface that the user interacts with.
 *
 * @author Bryan Tan
 */
public class Ui {
    private static final String errMsg = "Sorry I don't recognise that command :( Please try again.";
    private static final String intro = "Hi! I'm Duke! :)\nHow may I help?";
    private static final String outro = "Goodbye!";
    private TaskList tList;
    private Storage savedList;

    /**
     * Initialise parameters.
     *
     * @return String on whether previously saved list is available.
     * @throws IOException when file path is invalid
     */
    public String initialise() throws IOException {
        Storage saved = new Storage("./dukeSaved.txt");
        this.savedList = saved;
        this.tList = new TaskList();

        if (saved.isSaved()) {
            this.tList.setList(saved.load());
            if (this.tList.size() > 0) {
                return (this.intro + "\n" + "Previously saved list available!");
            } else {
                return (this.intro + "\n" + "No previously saved list found.");
            }
        } else {
            return (this.intro + "\n" + "No previously saved list found.");
        }
    }

    /**
     * Saves list of tasks in current session to hard drive.
     *
     * @throws IOException when file to write to is not found.
     */
    public void save() throws IOException {
        savedList.save(this.tList.getList());
    }

    public static String getIntro() {
        return intro;
    }

    public static String getOutro() {
        return outro;
    }

    /**
     * Signify end of session
     *
     * @return goodbye message.
     * @throws IOException when file path invalid.
     */
    public String end() throws IOException {
        save();
        return outro;
    }

    /**
     * Obtain list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tList.getList();
    }

    /**
     * Adds a task to the current list of tasks.
     *
     * @return Message of succesful addition of specified task to list.
     * @param t Task to be added.
     */
    public String addToList(Task t) {
        this.tList.add(t);
        return "Added " + t.getTask() + "!" + "\n" + "Now you have " + tList.size() + " tasks in the list.";
    }

    /**
     * Handles the exception when access or manipulation of an empty is attempted.
     *
     * @return Empty list error message.
     */
    public String emptyErr() {
        return "Error!! List empty! Try again.";
    }

    /**
     * Prints out all tasks created so far.
     *
     * @return String format of list of tasks currently stored.
     */
    public String viewList() {
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Heres your list of tasks: " + "\n");
        for (int i  = 0; i < this.tList.size(); i++) {
        sb.append(i + 1 + ". " + this.tList.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks specified task as done and informs user.
     *
     * @return Message of successful marking of task.
     * @param num Index no. of task in the list to be marked as done
     */
    public String markTask(int num) {
        assert num > 0: "Wrong num!!";
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        if (num >= this.tList.size()) {
           return ("Task no." + (num + 1) + " not found. Try again.");
        } else {
            this.tList.get(num).mark();
            return "Nice It's marked!" + "\n" + this.tList.get(num);
        }
    }

    /**
     * Unmarks specified task as undone and informs user.
     *
     * @return Message upon successful unmarking of task.
     * @param num Index no. of task in the list to be marked as undone.
     */
    public String unmarkTask(int num) {
        assert num > 0: "Wrong num!!";
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        if (num >= this.tList.size()) {
            return "Task no." + (num + 1) + " not found. Try again.";
        } else {
            this.tList.get(num).unmark();
            return "Ok! It's unmarked!" + "\n" + this.tList.get(num);
        }
    }

    /**
     * Creates a ToDo object.
     *
     * @param task String array containing descriptions of the task.
     * @return String format of ToDo object.
     */
    public String makeToDo(String[] task) {
        if (!(task.length > 1)) {
            return "Please specify task to do!!";
        }
        assert task[0].equalsIgnoreCase("todo") && task.length > 1: "Wrong format!!";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < task.length; i++) {
            sb.append(task[i] + " ");
        }
        return addToList(new ToDo(sb.toString()));
    }

    /**
     * Creates Event object.
     *
     * @param task String array containing descriptions of the event.
     * @return String format of Event object.
     * @throws DateTimeParseException if user inputs date and time in the wrong format.
     */
    public Event createEvent(String[] task) throws DateTimeParseException {
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        StringBuilder desc = new StringBuilder();
        boolean isDescripton = true;
        boolean isFromDate = false;
        boolean isToDate = false;

        for (int i = 1; i < task.length; i++) {
            if (task[i].equalsIgnoreCase("from")) {
                isFromDate = true;
                isDescripton = false;
                continue;
            }
            if (task[i].equalsIgnoreCase("to")) {
                isToDate = true;
                isFromDate = false;
                continue;
            }
            if (isFromDate) {
                start.append(task[i]);
                if (!task[i + 1].equalsIgnoreCase("to")) {
                    start.append(" ");
                }
            }
            if (isToDate) {
                end.append(task[i]);
                if (i != task.length - 1) {
                    end.append(" ");
                }
            }
            if (isDescripton) {
                desc.append(task[i] + " ");
            }
        }
        return new Event(desc.toString(), start.toString(), end.toString());
    }

    public String makeEvent(String[] task) throws DateTimeParseException {
        assert task[0].equalsIgnoreCase("event") && task.length > 1: "Wrong format!!";
        try {
            Event temp = createEvent(task);
            return addToList(temp);
        } catch (DateTimeParseException e) {
            return "Wrong date/time format!" + "\n"
                    + "Please enter correct format (from yyyy/MM/dd HHmm to yyyy/MM/dd HHmm)!";
        }
    }

    /**
     * Creates a task with given deadline.
     *
     * @param task String array containing descriptions of the event.
     * @return String format of Deadline object.
     * @throws DateTimeParseException if user inputs date and time in the wrong format.
     */
    public Deadline createDeadline(String[] task) throws DateTimeParseException {
        StringBuilder desc = new StringBuilder();
        StringBuilder by = new StringBuilder();
        boolean isDesc = true;

        for (int i = 1; i < task.length; i++) {
            if (task[i].equalsIgnoreCase("by")) {
                isDesc = false;
                continue;
            }
            if( isDesc) {
                desc.append(task[i] + " ");
            } else {
                by.append(task[i]);
                if (i != task.length - 1) {
                    by.append(" ");
                }
            }
        }
        return new Deadline(desc.toString(), by.toString());
    }

    public String makeDeadline(String[] task) throws  DateTimeParseException {
        assert task[0].equalsIgnoreCase("deadline") && task.length > 1: "Wrong format!!";
        try {
            Deadline temp = createDeadline(task);
            return addToList(temp);
        } catch (DateTimeParseException e) {
            return "Wrong date/time format!" + "\n" + "Please enter correct format (by yyyy/MM/dd HHmm)!";
        }
    }

    /**
     * Deletes a specified task.
     *
     * @return Message upon successful deletion of task.
     * @param num Index no. of task in the list to be deleted.
     */
    public String delete(int num) {
        assert num > 0: "Wrong num!!";
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        if (num >= this.tList.size()) {
            return "Task no." + (num + 1) + " not found. Try again.";
        } else {
            Task temp = this.tList.get(num);
            this.tList.remove(num);
            return "Ok! Following task is removed: " + "\n" + temp;
        }
    }

    /**
     * Prints list of tasks containing specified keyword.
     *
     * @return String format list of tasks containing the keyword.
     * @param keyword keyword to search with.
     */
    public String find(String keyword) {
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here's your list of tasks relating to " + "\"" + keyword + "\"" + ":" + "\n");
        int counter = 1;
        boolean hasMatch = false;

        for (int i = 0; i < this.tList.size(); i++) {
            Task curr = this.tList.get(i);
            if (curr.getTask().contains(keyword)) {
                hasMatch = true;
                sb.append(counter + ") " + curr.toString() + "\n");
                counter++;
            }
        }

        if (!hasMatch) {
            return "Sorry! No matching tasks found. Please try again.";
        } else {
            return sb.toString();
        }
    }

    /**
     * Deletes all task from the tasklist.
     */
    public String clearList() {
        ArrayList<Task> newEmptyList = new ArrayList<>();
        this.tList.setList(newEmptyList);
        return "Ok! All tasks deleted.";
    }

    public String tag(int num, String s) {
        assert num > 0: "Wrong num!!";
        if (this.getList().isEmpty()) {
            return emptyErr();
        }
        if (s.equals("")) {
            return "Empty tag error!!";
        }
        if (num >= this.tList.size()) {
            return ("Task no." + (num + 1) + " not found. Try again.");
        } else {
            this.tList.get(num).tag(s);
            return "Nice! " + this.tList.get(num) + " is tagged as " + this.tList.get(num).getTag() + ".";
        }
    }

    public String wrongInput() {
        return errMsg;
    }
}
