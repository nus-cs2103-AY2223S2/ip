package jane;

import java.util.ArrayList;
import java.util.List;
/**
 * Processes input commands
 */
public class TaskList {
    protected static ArrayList<jane.task.Task> tasks;

    public TaskList(ArrayList<jane.task.Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Processes input command "bye
     */
    public String handleBye() {
        List<String> currentList = new ArrayList<>();
        for (jane.task.Task t : tasks) {
            currentList.add(t.save());
        }
        try {
            jane.Storage.updateList(tasks);
            return "BYE! see you soon";
        } catch (Exception err) {
            err.printStackTrace();
            return ("cannot save list");
        }
    }
    /**
     * Processes input commands "find"
     * @param output is user's input
     */
    public String handleFind(String output) {
        String[] s = output.split(" ");
        StringBuilder finalOutput = new StringBuilder();
        String action = s[1];
        for (jane.task.Task i : tasks) {
            if (i.getDesc().contains(action)) {
                finalOutput.append(i);
            }
        }
        return finalOutput.toString();
    }
    /**
     * Processes input command "mark"
     * @param output is user's input
     */
    public String handleMark(String output) {
        String[] s = output.split(" ");
        StringBuilder finalOutput = new StringBuilder();
        int num = Integer.parseInt(s[1]);
        try {
            finalOutput.append("Nice! I've marked this task as done");
            jane.task.Task n = tasks.get(num - 1);
            n.changeState(true);
            finalOutput.append(n);
            return finalOutput.toString();
        } catch (Exception err) {
            err.printStackTrace();
            return ("Number out of index");
        }
    }
    /**
     * Processes input command "unmark"
     * @param output is user's input
     */
    public String handleUnmark(String output) {
        String[] s = output.split(" ");
        StringBuilder finalOutput = new StringBuilder();
        int num = Integer.parseInt(s[1]);
        try {
            finalOutput.append("OK, I've marked this task as not done yet" + "\n");
            jane.task.Task n = tasks.get(num - 1);
            n.changeState(false);
            finalOutput.append(n);
            return finalOutput.toString();
        } catch (Exception err) {
            finalOutput.append("Number out of index");
            err.printStackTrace();
            return finalOutput.toString();
        }
    }
    /**
     * Processes input command "delete"
     * @param output is user's input
     */
    public String handleDelete(String output) {
        String[] s = output.split(" ");
        StringBuilder finalOutput = new StringBuilder();
        int num = Integer.parseInt(s[1]);
        try {
            finalOutput.append("Noted. I've removed this task:" + "\n");
            jane.task.Task n = tasks.get(num - 1);
            finalOutput.append(n.toString());
            for (int j = num; j < tasks.size(); j++) {
                jane.task.Task t = tasks.get(j);
                t.changeNum();
            }
            tasks.remove(n);
            finalOutput.append("You now have " + tasks.size() + " tasks");
            return finalOutput.toString();
        } catch (Exception err) {
            finalOutput.append("Number out of index");
            err.printStackTrace();
        }
        return finalOutput.toString();
    }
    /**
     * Processes input command "list"
     */
    public String handleList() {
        StringBuilder finalOutput = new StringBuilder();
        for (jane.task.Task task : tasks) {
            String s1 = task.toString() + "\n";
            finalOutput.append(s1);
        }
        return finalOutput.toString();
    }
    /**
     * Processes input commands
     * @param output is user's input
     */
    public String useCommand(String output) {
        StringBuilder finalOutput = new StringBuilder();
        if (output.equals("bye")) {
            return handleBye();
        } else if (output.startsWith("find")) {
            return handleFind(output);
        } else if (output.startsWith("mark")) {
            return handleMark(output);
        } else if (output.equals("todo") || output.equals("deadline") || output.equals("event")) {
            finalOutput.append("Please specify the task to be done :(((");
            return finalOutput.toString();
        } else if (output.startsWith("todo") || output.startsWith("t ")) {
            jane.task.Todo todo = jane.Parser.parserT(output, tasks.size());
            tasks.add(todo);
            finalOutput.append(todo);
            return finalOutput.toString();
        } else if (output.startsWith("deadline") || output.startsWith("d ")) {
            jane.task.Deadline d = jane.Parser.parserD(output, tasks.size());
            tasks.add(d);
            finalOutput.append(d);
            return finalOutput.toString();
        } else if (output.startsWith("event") || output.startsWith("e ")) {
            //here I am assuming an event only lasts 1 day since the day it starts is the day it ends
            jane.task.Event e = jane.Parser.parserE(output, tasks.size());
            tasks.add(e);
            finalOutput.append(e);
            return finalOutput.toString();
        } else if (output.startsWith("unmark")) {
            return handleUnmark(output);
        } else if (output.startsWith("delete")) {
            return handleDelete(output);
        } else if (output.equals("list")) {
            return handleList();
        }
        return "Sorry I don't understand what do you mean :((";
    }
}


// comment so that I can make the pull request



