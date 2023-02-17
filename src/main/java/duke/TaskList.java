package duke;

import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/** List containing all Tasks*/
public class TaskList {
    /** ArrayList containing all Tasks*/
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Adds a task to the list
     * @param tsk is Task to be added
     */
    public void addTask(Task tsk) {
        this.tasks.add(tsk);
    }
    /**
     * Removes a task from the list
     * @param id is id of Task to be removed
     */
    public void removeTask(int id) {
        this.tasks.remove(id);
    }

    public boolean handleDup(Task t) {
        return tasks.contains(t);
    }

    /**
     * Handles the list command from user
     * @return list of all Tasks
     */
    public String handleList() {
        String output = "";
        try { //check if list is empty
            if (tasks.isEmpty()) {
                throw new DukeExceptions("List is empty!");
            }
        } catch (DukeExceptions de) {
            return "List is empty!";
        }
        for (int i = 0; i < tasks.size(); i++) {
            output += i + 1 + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    private String handleBye() {
        Storage.upload(this.tasks);
        return "Bye!!!!";
    }

    private String handleMark(String input) {
        String[] inp = input.split(" ");
        int id = Integer.parseInt(inp[1]);
        try {
            Task marked = tasks.get(id - 1);
            marked.setIsDone(true);
            return "Duke wants to be productive too...\n" + marked;
        } catch (Exception e) {
            return "Uhhhhh Duke cant find it...";
        }
    }

    private String handleUnmark(String input) {
        String[] inp = input.split(" ");
        int id = Integer.parseInt(inp[1]);
        try {
            Task unmarked = tasks.get(id - 1);
            unmarked.setIsDone(false);
            return "Duke says come back to it later....\n" + unmarked;
        } catch (Exception e) {
            return "Uhhhhh Duke cant find it...";
        }
    }

    private String handleToDo(String input) {
        ToDo processed = Parser.parseToDo(input);
        if (!handleDup(processed)) {
            addTask(processed);
            return "Duke done adding todo...\n" + processed;
        } else {
            return "Task already exists......";
        }
    }

    private String handleDeadline(String input) {
        Deadline dl = Parser.parseDeadline(input);
        if (!handleDup(dl)) {
            addTask(dl);
            return "Duke done adding deadline...\n" + dl;
        } else {
            return "Task already exists......";
        }
    }

    private String handleEvent(String input) {
        Event ev = Parser.parseEvent(input);
        if (!handleDup(ev)) {
            addTask(ev);
            return "Duke done adding event...\n" + ev;
        } else {
            return "Event already exists......";
        }
    }

    private String handleDelete(String input) {
        String[] inp = input.split(" ");
        int id = Integer.parseInt(inp[1]);
        Task item = tasks.get(id - 1);
        try {
            removeTask(id - 1);
        } catch (Exception e) {
            System.out.println("Uhhhhh Duke cant find it...");
        }
        return "Farewell...\n" + item;
    }

    /**
     * Finds tasks in the list that matches keyword
     * @param input is user's input
     */
    private String handleFind(String input) {
        String output = "";
        String keyword = input.substring(5).toLowerCase();
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task tsk : tasks) {
            String desc = tsk.getDesc().toLowerCase();
            if (desc.contains(keyword)) {
                tasksFound.add(tsk);
            }
        }
        for (int i = 0; i < tasksFound.size(); i++) {
            output += i + 1 + ". " + tasksFound.get(i) + "\n";
        }
        if (output.equals("")) {
            return "Uhhhhh Duke cant find any...";
        }
        return output;
    }

    /**
     * Processes all input commands
     * @param input is user's input
     */
    public String handleInput(String input) {
        if (input.equals("list")) {
            return handleList();
        }
        if (input.equals("bye")) {
            return handleBye();
        }
        if (input.startsWith("mark ")) {
            return handleMark(input);
        }
        if (input.startsWith("unmark ")) {
            return handleUnmark(input);
        }
        if (input.startsWith("todo ")) {
            return handleToDo(input);
        }
        if (input.startsWith("deadline ")) {
            return handleDeadline(input);
        }
        if (input.startsWith("event ")) {
            return handleEvent(input);
        }
        if (input.startsWith("delete ")) {
            return handleDelete(input);
        }
        if (input.startsWith("find ")) {
            return handleFind(input);
        }
        return "Duke does not understand....";
    }
}
