package sebastian.main;

import sebastian.sebastianExceptions.DeadlineFormatMismatchException;
import sebastian.sebastianExceptions.EventFormatMismatchException;
import sebastian.task.Deadline;
import sebastian.task.Event;
import sebastian.task.Task;
import sebastian.task.Todo;
import sebastian.time.DatePattern;
import sebastian.time.Duration;
import sebastian.time.EndTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of all tasks
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Add a to-do to the task list
     * @param isCompleted completion status of the task 0:not done 1:done
     * @param taskDescription a description of the to-do to be added
     * @return the to-do generated
     */
    public Task addTodo(int isCompleted, String taskDescription) {
        Task newTask = new Todo(isCompleted, taskDescription);
        this.taskList.add(newTask);
        return newTask;
    }

    /**
     * Add a deadline to the task list
     * @param isCompleted completion status of the task 0:not done 1:done
     * @param taskDescription a description of the deadline to be added
     * @return the deadline generated
     */
    public Task addDeadline(int isCompleted, String taskDescription, String endTime) throws DeadlineFormatMismatchException{
        try {
            Task newTask = new Deadline(isCompleted, taskDescription, new EndTime(convertStringToDate(endTime)));
            this.taskList.add(newTask);
            return newTask;
        } catch (DateTimeParseException e) {
            throw new DeadlineFormatMismatchException();
        }
    }

    /**
     * Add an event to the task list
     * @param isCompleted completion status of the task 0:not done 1:done
     * @param taskDescription a description of the event to be added
     * @return the event generated
     */
    public Task addEvent(int isCompleted, String taskDescription, String from, String to) throws EventFormatMismatchException {
        try {
            Task newTask = new Event(isCompleted, taskDescription, new Duration(convertStringToDate(from), convertStringToDate(to)));
            this.taskList.add(newTask);
            return newTask;
        } catch (DateTimeParseException e) {
            throw new EventFormatMismatchException();
        }
    }

    private LocalDateTime convertStringToDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.USER_INPUT_FORMAT.toString());
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    /**
     * Mark the task as specified by the task index as done
     * @param taskIndex the index of the task to be marked as done
     * @return the task
     * @throws IndexOutOfBoundsException when the task index is out of range
     */
    public Task markTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        try {
            return this.taskList.get(taskIndex-1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Mark the task as specified by the task index as not done
     * @param taskIndex the index of the task to be marked as not done
     * @return the task
     * @throws IndexOutOfBoundsException when the task index is out of range
     */
    public Task unmarkTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        try {
            return this.taskList.get(taskIndex-1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Delete the task as specified by the task index
     * @param taskIndex the index of the task to be deleted
     * @return the task
     * @throws IndexOutOfBoundsException when the task index is out of range
     */
    public Task deleteTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        try {
            return this.taskList.remove(taskIndex-1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public TaskList getTasksOnDate(String date) throws DateTimeParseException{
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.TASK_ON_DATE_FORMAT.toString());
        LocalDate ld = LocalDate.parse(date, dtf);
        List<Task> tasksOnDate = new ArrayList<>();
        for(Task t: taskList) {
            if(t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if(d.isOnSameDay(ld)) {
                    tasksOnDate.add(t);
                }
            } else if(t instanceof Event) {
                Event e = (Event) t;
                if(e.isOnSameDay(ld)) {
                    tasksOnDate.add(t);
                }
            }
        }
        return new TaskList(tasksOnDate);
    }

    /**
     * The total tasks currently in the task list
     * @return the total number of tasks
     */
    public int getTotalTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int totalTasks = this.getTotalTasks();
        for(int i = 0; i < totalTasks; i ++ ) {
            Task t = taskList.get(i);
            sb.append(i+1).append(".").append(t.toString()).append("\n");
        }
        if(sb.length()!=0) {
            sb.delete(sb.length()-1, sb.length());
        }
        return sb.toString();
    }

    /**
     * Format tasks in the taskList into a String ready to be written into the hard disk
     * @return formatted String ready to be written to disk
     */
    public String formatTaskListForSave() {
        StringBuilder sb = new StringBuilder();
        for(Task task: this.taskList){
            sb.append(task.formatForSave()).append("\n");
        }
        if(sb.length()>0){
            return sb.substring(0,sb.length()-1);
        }
        return sb.toString();
    }
}
