package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

import duke.exception.InvalidTodoException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;

/**
 * Represents an arraylist consisting of Task objects.
 * A TaskList object has associated methods to add, delete,
 * modify tasks in the arraylist.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public TaskList(ArrayList<String> stringList) {
        for (String task : stringList) {
            try {
                String[] taskSplit = task.split(" ", 2);
                this.addTask(taskSplit[1]);
                int taskNum = this.getTaskCount();
                this.markTask(taskSplit[0], taskNum);
            } catch (InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public String toString() {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            listString += String.format("%d.%s\n", i + 1, task.toString());
        }
        return listString;
    }

    public String toSortedString() {
        String listString = "";
        ArrayList<Task> sortedList = new ArrayList<>(list);
        sortedList.sort(Comparator.comparing(Task::getStartDate));

        for (int i = 0; i < sortedList.size(); i++) {
            Task task = sortedList.get(i);
            listString += String.format("%d.%s\n", i + 1, task.toString());
        }
        return listString;
    }

    /**
     * Returns the task added to task list, represented as a Task object.
     *
     * @param content Content of task.
     * @return Task object associated with the content.
     * @throws InvalidTodoException     If user input for todo task is invalid.
     * @throws InvalidDeadlineException If user input for deadline task is invalid.
     * @throws InvalidEventException    If user input for event task is invalid.
     */
    public Task addTask(String content) throws InvalidTodoException, InvalidDeadlineException, InvalidEventException {
        String[] contentSplit = content.split(" ", 2);
        String taskType = contentSplit[0];

        Task taskToAdd;

        if (taskType.equals("todo")) {

            if (contentSplit.length == 1) {
                throw new InvalidTodoException();
            }

            content = contentSplit[1];
            taskToAdd = new Todo(content);
        } else if (taskType.equals("deadline")) {
            if (contentSplit.length == 1) {
                throw new InvalidDeadlineException("The description of a deadline task cannot be empty\n");
            }
            content = contentSplit[1];

            String[] dateSplit = content.split(" /by ");

            if (dateSplit.length != 2) {
                throw new InvalidDeadlineException("The description or date of a deadline task cannot be empty\n");
            }

            content = dateSplit[0];
            String dueDate = dateSplit[1];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm", Locale.ENGLISH);
            taskToAdd = new Deadline(content, LocalDateTime.parse(dueDate, formatter));
        } else {
            if (contentSplit.length == 1) {
                throw new InvalidEventException("The description of an event task cannot be empty\n");
            }

            content = contentSplit[1];
            String[] dateSplit = content.split(" /from ");

            if (dateSplit.length != 2) {
                throw new InvalidEventException("The description or date of an event task cannot be empty\n");
            }

            content = dateSplit[0];
            String fromToDate = dateSplit[1];
            taskToAdd = new Event(content, fromToDate);
        }

        this.list.add(taskToAdd);

        return taskToAdd;
    }

    /**
     * Removes task from the task list.
     *
     * @param message Contents of message.
     */
    public Task deleteTask(String message) {
        String[] messageSplit = message.split(" ");
        int taskNum = Integer.parseInt(messageSplit[1]);

        // task to be deleted
        Task deletedTask = this.getTask(taskNum);

        // remove task from arraylist
        list.remove(taskNum - 1);

        return deletedTask;
    }

    public Task getTask(int taskNum) {
        return list.get(taskNum - 1);
    }

    /**
     * Updates status of task from the task list.
     *
     * @param action  Marking or unmarking task.
     * @param taskNum Number assigned to task.
     */
    public void markTask(String action, int taskNum) {
        Task task = this.getTask(taskNum);
        task.setDoneStatus(action.equals("mark") ? true : false);
    }

    public int getTaskCount() {
        return list.size();
    }

    public ArrayList<Task> filter(String messageToFilterBy) {
        ArrayList<Task> filteredList = new ArrayList<>(this.list);
        filteredList.removeIf(task -> !task.getContent().contains(messageToFilterBy));
        return filteredList;
    }

}
