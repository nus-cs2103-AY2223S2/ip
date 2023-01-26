package task;

import exception.CommandNotFoundException;
import exception.InvalidCommandInputException;
import exception.InvalidDateFormatException;

import helper.DateTimeHelper;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    public Task addTask(String input) throws CommandNotFoundException, InvalidCommandInputException {
        if (input != null && (input.equals("todo") || input.equals("deadline") || input.equals("event"))) {
            throw new InvalidCommandInputException("Empty argument", input);
        } else if (input.matches("deadline .* /by .*")) {
            // Break apart input string.
            String[] arr = input.split(" /by ");
            String content = arr[0].substring(9, arr[0].length());

            // Check if argument exists.
            if (content.length() == 0 || arr[1].length() == 0) {
                throw new InvalidCommandInputException("Empty argument", "deadline");
            }

            // Add new deadline task to the list.
            try {
                this.tasks.add(new Deadline(content, arr[1]));
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.matches("event .* /from .* /to .*")) {
            // Break apart input string.
            String[] arr = input.split(" /from ");
            String content = arr[0].substring(6, arr[0].length());
            String[] startEnd = arr[1].split(" /to ");

            // Check if arguments exists.
            if (content.length() == 0 || startEnd[0].length() == 0 || startEnd[1].length() == 0) {
                throw new InvalidCommandInputException("Empty argument", "event");
            }

            // Add new event task to the list.
            try {
                this.tasks.add(new Event(content, startEnd[0], startEnd[1]));
            } catch (InvalidDateFormatException e) {
                System.out.println(e.getMessage());
            }
        } else if (input.matches("todo .*")) {
            // Check if argument exists.
            if (input.length() == 5) {
                throw new InvalidCommandInputException("Empty argument", "todo");
            }

            // Add new todo task to the list.
            this.tasks.add(new ToDo(input.substring(5, input.length())));
        } else {
            throw new CommandNotFoundException("Duke command is invalid.", input);
        }

        // Return the added task.
        return tasks.get(tasks.size() - 1);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task deleteTask(int i) {
        // Find and remove the task at the given index.
        Task res = this.getTask(i);
        tasks.remove(i);

        return res;
    }

    public void addDeadline(String task, String deadline) {
        try {
            this.tasks.add(new Deadline(task, deadline));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addEvent(String task, String start, String end) {
        try {
            this.tasks.add(new Event(task, start, end));
        } catch (InvalidDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void markTask(int i) {
        tasks.get(i).mark();
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int numberOfTasks() {
        return tasks.size();
    }

    public void printTasksOnDate(String input) throws InvalidDateFormatException {
        // Convert string to LocalDateTime object.
        LocalDateTime datetime = DateTimeHelper.parse(input);
        int counter = 1;

        // Find all valid tasks by iterating through them.
        for (Task t: tasks) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.occursOn(datetime)) {
                    System.out.println(counter + ". " + d);
                    counter++;
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.occursOn(datetime)) {
                    System.out.println(counter + ". " + e);
                    counter++;
                }
            }
        }
    }

    /**
     * Prints out the tasks containing a given word.
     *
     * @param word The word to check.
     */
    public void findTasksWithWord(String word) {

        System.out.println("Here are the matching tasks in your list:");

        int counter = 1;

        for (Task t: tasks) {
            if (t.containsWord(word)) {
                System.out.println(counter + "." + t);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";
        Integer curr = 1;

        for (Task t: tasks) {
            result += curr.toString() + ". " + t;

            if (curr != tasks.size()) {
                result += '\n';
            }

            curr++;
        }

        return result;
    }
}
