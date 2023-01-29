package duke.command;
import duke.task.*;

/**
 * This class executes the task creation commands stored in the command queue, by parsing the user input.
 */
public class ExecuteCommandQueue extends Commands {

    public ExecuteCommandQueue(String str) {
        this.setCommandStorage(str);
    }

    @Override
    public void execute(TaskList list){
        String content = this.getCommandStorage();
        Tasks task = null;
        if (content.matches("^deadline\\s.*$")) {
            String back = content.substring(9);
            String[] substrings = back.split(" /by ");
            String date = substrings[1];
            task = new Deadline(substrings[0], date, false);
        } else if (content.matches("^event\\s.*$")) {
            String back = content.substring(6);
            String[] substrings = back.split(" /from ");
            String[] dates = substrings[1].split(" /to ");
            String from = dates[0];
            String to = dates[1];
            task = new Event(substrings[0], false, from, to);
        } else if (content.matches("^todo\\s.*$")) {
            String desc = content.substring(5);
            task = new Todo(desc, false);
        }
        list.addTask(task);
        System.out.println("Got it. I've added this task: \n" + task);
        System.out.println("Now you have " + list.getTaskCount() + " tasks in the list.");
    }
}