package duke;

import duke.tasks.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private void handleList() {
        try { //check if list is empty
            if (tasks.isEmpty()) {
                throw new DukeExceptions("List is empty!");
            }
        } catch (DukeExceptions de) {
            System.out.println("List is empty!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i).toString());
        }
    }

    private void handleBye() {
        System.out.println("See you again, thanks for visiting!");
        Storage.upload(this.tasks);
    }

    private void handleMark(String input) {
        if (input.startsWith("mark ")) {
            String[] inp = input.split(" ");
            int id = Integer.parseInt(inp[1]);
            try {
                Task marked = tasks.get(id - 1);
                marked.setIsDone(true);
                System.out.println("Good job! This task is now marked done!\n" + marked);
            } catch (Exception e) {
                System.out.println("No such task found!");
            }
        }
    }

    private void handleUnmark(String input) {
        String[] inp = input.split(" ");
        int id = Integer.parseInt(inp[1]);
        try {
            Task unmarked = tasks.get(id - 1);
            unmarked.setIsDone(false);
            System.out.println("What a bummer! This task is now unmarked\n" + unmarked);
        } catch (Exception e) {
            System.out.println("No such task found!");
        }
    }

    private void handleToDo(String input) {
        ToDo processed = Parser.parseToDo(input);
        addTask(processed);
    }

    private void handleDeadline(String input) {
        Deadline dl = Parser.parseDeadline(input);
        addTask(dl);
    }

    private void handleEvent(String input) {
        Event ev = Parser.parseEvent(input);
        addTask(ev);
    }

    private void handleDelete(String input) {
        String[] inp = input.split(" ");
        int id = Integer.parseInt(inp[1]);
        try {
            removeTask(id - 1);
        } catch (Exception e) {
            System.out.println("No such task found!");
        }
    }

    /**
     * Finds tasks in the list that matches keyword
     * @param input is user's input
     */
    private void handleFind(String input) {
        String keyword = input.substring(5);
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task tsk = tasks.get(i);
            String desc = tsk.getDesc();
            if (desc.contains(keyword)) {
                tasksFound.add(tsk);
            }
        }
        for(int i = 0; i < tasksFound.size(); i++) {
            System.out.println(i + 1 + ". " + tasksFound.get(i));
        }
    }

    /**
     * Processes all input commands
     * @param input is user's input
     */
    public void handleInput(String input) {
        if (input.equals("list")) {
            handleList();
        }
        if (input.equals("bye")) {
            handleBye();
        }
        if (input.startsWith("mark ")) {
            handleMark(input);
        }
        if (input.startsWith("unmark ")) {
            handleUnmark(input);
        }
        if (input.startsWith("todo ")) {
            handleToDo(input);
        }
        if (input.startsWith("deadline ")) {
            handleDeadline(input);
        }
        if (input.startsWith("event ")) {
            handleEvent(input);
        }
        if (input.startsWith("delete ")) {
            handleDelete(input);
        }
        if (input.startsWith("find ")) {
            handleFind(input);
        }
    }
}
