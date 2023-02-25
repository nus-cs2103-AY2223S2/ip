import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        this(new ArrayList<Task>());
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public Task get(int index) throws IndexOutOfBoundsException {
        return tasks.get(index - 1);
    }
    public Task remove(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index - 1);
    }
    public int size() {
        return tasks.size();
    }
    // below is unfactored properly yet
    static String add(String cmd, ArrayList<Task> arr) {
        String[] words = cmd.split(" ");
        String typeOfTask = words[0];

        if (!MINVALIDLENGTH.containsKey(typeOfTask)) {
            return errorMsg("I'm sorry, but I don't know what that means");
        } else if (cmd.length() < MINVALIDLENGTH.get(typeOfTask)) {
            return errorMsg(String.format(
                    "The description of a %s cannot be empty.", typeOfTask));
        }

        try {
            Task task = makeTask(typeOfTask, cmd);
            //.replace(typeOfTask + " ", ""));
            arr.add(task);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, arr.size());
        } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | DateTimeParseException wrongFormat) {
            return errorMsg("Please enter the command in the correct format.") + "\n" + CORRECTFORMAT.get(typeOfTask);
        }
    }
    static String mark(String num, ArrayList<Task> arr) {     //try catch, possibility of error if user enter wrong cmd
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            arr.get(index).markAsDone();
            return String.format("Nice! I've marked this task as done: \n\t%s", arr.get(index));
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
    static String unmark(String num, ArrayList<Task> arr) {   //try catch, possibility of error if user enter wrong cmd
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            arr.get(index).unmarkAsDone();
            return String.format("Ok, I've marked this task as not done yet: \n\t%s", arr.get(index));
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
    static String delete(String num, ArrayList<Task> arr) {
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            String taskDescription = arr.get(index).toString();
            arr.remove(index);
            return String.format("Noted, I've removed this task: \n\t%s\nNow you have %d tasks in this list.",
                    taskDescription, arr.size());
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
}
