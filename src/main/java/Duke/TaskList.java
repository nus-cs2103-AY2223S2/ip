package Duke;


import Duke.Exceptions.CommandNotFoundException;
import Duke.Exceptions.FileException;
import Duke.Exceptions.NoDeadlineException;
import Duke.Exceptions.NoDescriptionException;
import Duke.Storage.Storage;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> taskList;
    private static int index;
    private static final String LINES = "\t--------------------------\n";

    /**
     * The constructor that uses to create a TaskList instance.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
        this.index = 0;
    }

    public TaskList(BufferedReader strTasks) throws FileException, CommandNotFoundException {
        super();
        String str;
        try {
            while ((str = strTasks.readLine()) != null) {
                this.taskList.add(Task.strToTask(str));
            }
        } catch (IOException ioException) {
            throw new FileException();
        }
    }

    /**
     * Return the size of the task list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return this.index;
    }

    /**
     * Get the specific task correspond to the given index.
     *
     * @param index The index of the task.
     * @return Return the task of the given index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public String toFormattedString() {
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : this.taskList) {
            result.append(count++)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }
        return result.toString();
    }

    /**
     * Delete the task according to the input index.
     *
     * @param input The index of the task that need to be deleted.
     */

    public String  delete(int input) {
        Task currTask = this.taskList.get(input);
        this.taskList.remove(input);
        this.index--;
        String output = String.format(LINES +
                "\tNoted. I've removed this task:\n" +
                "\t" + currTask.toString() +
                "\n\tNow you have " + this.index + " tasks in the list.\n" +
                LINES);
        return output;
    }

    /**
     * Add the task of Todo type into the task list.
     *
     * @param input A Duke.command by the user which contains task description.
     * @throws NoDescriptionException Return error message if there is no task description.
     */
    public Task addTodo(String input) throws NoDescriptionException {
        if (input.trim().equals("")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo(input);
        this.index++;
        return newTodo;
    }

    /**
     * Add tasks of Deadline type into the task list.
     *
     * @return Task Return the created task.
     * @param input A Duke.command by the user which contains task description and due date.
     * @throws NoDescriptionException Return error message if there is no task description.
     * @throws NoDeadlineException Return error message if there is no specified deadline.
     */
    public Task addDeadline(String input) throws NoDescriptionException,
            NoDeadlineException {
        String[] splitDesWithBy = input.split(" /by ", 2);
        String description = splitDesWithBy[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of a deadline cannot be empty.");
        } else if (splitDesWithBy.length != 2) {
            throw new NoDeadlineException("The due date cannot be empty.");
        }

        String dueDate = splitDesWithBy[1].trim();

        Task newDeadline = new Deadline(description, dueDate);
        this.index++;
        return newDeadline;
    }

    /**
     * Add task of Event type into the task list.
     *
     * @param input A Duke command by the user which contains task description, event starting
     *              date and event ending date.
     * @throws NoDeadlineException Return error message if there is no specified starting time or ending time.
     * @throws NoDescriptionException Return error message if there is no task description.
     */
    public Task addEvent(String input) throws NoDeadlineException,
            NoDescriptionException{
        String[] splitDesWithFrom = input.split(" /from ", 2);
        String description = splitDesWithFrom[0].trim();

        if (description.equals("")) {
            throw new NoDescriptionException("The description of an event cannot be empty.");
        } else if (splitDesWithFrom.length != 2) {
            throw new NoDeadlineException("The starting time cannot be empty");
        }

        String[] period = splitDesWithFrom[1].split(" /to ", 2);
        String startingTime = period[0].trim();

        if (startingTime.equals("")) {
            throw new NoDeadlineException("The starting time cannot be empty.");
        } else if (period.length != 2) {
            throw new NoDeadlineException("The ending time cannot be empty.");
        }

        String endingTime = period[1].trim();

        Task newEvent = new Event(description, startingTime, endingTime);
        this.index++;
        return newEvent;
    }

    /**
     * Print out the current task list that has been stored.
     */
    public String list() {
        if (this.index == 0) {
            String output = String.format(LINES +
                    "\tThere is no task in the list." + "\n" +
                    LINES);
            return output;
        } else {
            String output = String.format(LINES +
                    "\tHere are the tasks in your list:\n" + "\n" +
                    this.toFormattedString() +
                    LINES);
            return output;
        }
    }

    /**
     * Mark the task as done correspond to the inserted index by the user.
     *
     * @param input The index of the task that is done.
     */
    public Task mark(int input) {
        Task currTask = this.taskList.get(input - 1);
        currTask.markDone();
        return currTask;

    }

    /**
     * Mark the task as not done yet correspond to the inserted index by the user.
     *
     * @param input The index of the task that is not done yet.
     */
    public Task unmark(int input) {
        Task currTask = this.taskList.get(input - 1);
        currTask.markNotDone();
        return currTask;
    }

    public void addTask(Task task, Storage storage) throws NoDescriptionException {
        //Implement assert feature into the code
        assert !task.noDescription();
        if (task.noDescription()) {
            throw new NoDescriptionException("No description is provided, please try again");
        } else {
            this.taskList.add(task);
        }
        storage.store(this);
    }

    public ArrayList<Task> findRelevantTasks(String keywords) {
        if (keywords.trim().equals("")) {
            throw new NoDescriptionException("The description of a todo cannot be empty.");
        }
        return (ArrayList<Task>) this.taskList
                .stream().filter(task -> task.toString().contains(keywords))
                .collect(Collectors.toList());
    }

    public String findTaskDueSoon() {
        String output = "";
        for (int i = 0; i < this.index; i++) {
            Task currTask = this.getTask(i);
            if (currTask.isDueSoon()) {
                String str = String.format("%d. " + currTask.toString() + "\n", i + 1);
                output += str;
            }
        }
        return output;
    }
}
