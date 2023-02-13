package sebastian.main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sebastian.exceptions.DeadlineFormatMismatchException;
import sebastian.exceptions.EventFormatMismatchException;
import sebastian.exceptions.LackOfArgumentException;
import sebastian.exceptions.UpdateFormatMismatchException;
import sebastian.task.Deadline;
import sebastian.task.Event;
import sebastian.task.Task;
import sebastian.task.Todo;
import sebastian.time.DatePattern;
import sebastian.time.Duration;
import sebastian.time.EndTime;

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
    public Task addTodo(boolean isCompleted, String taskDescription) {
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
    public Task addDeadline(boolean isCompleted, String taskDescription, String endTime) throws
            DeadlineFormatMismatchException {
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
    public Task addEvent(boolean isCompleted, String taskDescription, String from, String to) throws
            EventFormatMismatchException {
        try {
            Task newTask = new Event(isCompleted, taskDescription,
                    new Duration(convertStringToDate(from), convertStringToDate(to)));
            this.taskList.add(newTask);
            return newTask;
        } catch (DateTimeParseException e) {
            throw new EventFormatMismatchException();
        }
    }


    /**
     * Mark the task as specified by the task index as done
     * @param taskIndex the index of the task to be marked as done
     * @return the task
     * @throws IndexOutOfBoundsException when the task index is out of range
     */
    public Task markTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        try {
            return this.taskList.get(taskIndex - 1).mark();
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
            return this.taskList.get(taskIndex - 1).unmark();
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
            return this.taskList.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Retrieve tasks occurring on a specific date
     * @param date retrieve tasks occurring on the date
     * @return a Tasklist containing tasks occurring on the specific date
     * @throws DateTimeParseException when date provided is invalid or is in the wrong format
     */
    public TaskList getTasksOnDate(String date) throws DateTimeParseException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(DatePattern.TASK_ON_DATE_FORMAT.toString());
        LocalDate ld = LocalDate.parse(date, dtf);
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task t : taskList) {
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                if (d.isOnSameDay(ld)) {
                    tasksOnDate.add(t);
                }
            } else if (t instanceof Event) {
                Event e = (Event) t;
                if (e.isOnSameDay(ld)) {
                    tasksOnDate.add(t);
                }
            }
        }
        return new TaskList(tasksOnDate);
    }

    /**
     * Find all tasks which contains keyword as part of its task description
     * @param keyword the keyword to be checked against
     * @return A new Tasklist containing all the qualified tasks
     */
    public TaskList findTasks(String keyword) {
        List<Task> res = taskList.stream().filter(x -> x.containsKeyword(keyword)).collect(Collectors.toList());
        return new TaskList(res);
    }

    /**
     * Update the details of a task
     * @param taskIndex index of the task to be updated
     * @param updateDetail the update detail
     * @return the updated task
     * @throws IndexOutOfBoundsException when user attempt to update a non-exist task
     * @throws LackOfArgumentException when user did not specify what to update
     * @throws UpdateFormatMismatchException when the update command is given in the wrong format
     */
    public Task updateTask(int taskIndex, String updateDetail) throws IndexOutOfBoundsException,
            LackOfArgumentException, UpdateFormatMismatchException {
        try {
            Task taskToBeUpdated = taskList.get(taskIndex - 1);
            if (taskToBeUpdated instanceof Deadline) {
                Deadline d = (Deadline) taskToBeUpdated;
                updateDeadline(d, updateDetail);
            } else if (taskToBeUpdated instanceof Event) {
                Event e = (Event) taskToBeUpdated;
                updateEvent(e, updateDetail);
            } else if (taskToBeUpdated instanceof Todo) {
                Todo t = (Todo) taskToBeUpdated;
                updateTodo(t, updateDetail);
            } else {
                throw new Error("Internal Error");
            }
            return taskToBeUpdated;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void updateDeadline(Deadline d, String updateDetail)
            throws LackOfArgumentException, UpdateFormatMismatchException {
        int pos1 = updateDetail.indexOf("/desc");
        int pos2 = updateDetail.indexOf("/by");
        LocalDateTime newEndTime = null;
        String newDesc = null;
        if (pos1 != -1) {
            int endPos = pos2 == -1 ? updateDetail.length() : pos2;
            newDesc = updateDetail.substring(pos1 + 5, endPos).trim();
        }
        if (pos2 != -1) {
            try {
                newEndTime = convertStringToDate(updateDetail.substring(pos2 + 3).trim());
            } catch (DateTimeParseException e) {
                throw new UpdateFormatMismatchException();
            }
        }
        if (pos1 == -1 && pos2 == -1) {
            throw new LackOfArgumentException("Please specify a body for your update command");
        }
        d.update(newDesc, newEndTime);
    }

    private void updateEvent(Event e, String updateDetail) throws LackOfArgumentException,
            UpdateFormatMismatchException {
        int pos1 = updateDetail.indexOf("/desc");
        int pos2 = updateDetail.indexOf("/from");
        int pos3 = updateDetail.indexOf("/to");
        String newDesc = null;
        LocalDateTime newFrom = null;
        LocalDateTime newTo = null;
        if (pos1 != -1) {
            int endPos = pos2 == -1
                    ? pos3 == -1 ? updateDetail.length() : pos3
                    : pos2;
            newDesc = updateDetail.substring(pos1 + 5, endPos).trim();
        }
        if (pos2 != -1) {
            int endPos = pos3 == -1 ? updateDetail.length() : pos3;
            try {
                newFrom = convertStringToDate(updateDetail.substring(pos2 + 5, endPos).trim());
            } catch (DateTimeParseException exp) {
                throw new UpdateFormatMismatchException();
            }
        }
        if (pos3 != -1) {
            try {
                newTo = convertStringToDate(updateDetail.substring(pos3 + 3).trim());
            } catch (DateTimeParseException exp) {
                throw new UpdateFormatMismatchException();
            }
        }
        if (pos1 == -1 && pos2 == -1 && pos3 == -1) {
            throw new LackOfArgumentException("Please specify a body for your update command");
        }
        e.update(newDesc, newFrom, newTo);
    }

    private void updateTodo(Todo t, String updateDetail) throws LackOfArgumentException {
        int pos = updateDetail.indexOf("/desc");
        String newDesc = null;
        if (pos != -1) {
            newDesc = updateDetail.substring(pos + 5).trim();
            t.update(newDesc);
            return;
        }
        throw new LackOfArgumentException("Please specify a body for your update command");
    }

    /**
     * The total tasks currently in the task list
     * @return the total number of tasks
     */
    public int getTotalTasks() {
        return taskList.size();
    }

    private LocalDateTime convertStringToDate(String dateTime) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DatePattern.USER_INPUT_FORMAT.toString());
        return LocalDateTime.parse(dateTime, dateTimeFormatter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int totalTasks = this.getTotalTasks();
        for (int i = 0; i < totalTasks; i++) {
            Task t = taskList.get(i);
            sb.append(i + 1).append(".").append(t.toString()).append("\n");
        }
        if (sb.length() != 0) {
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * Format tasks in the taskList into a String ready to be written into the hard disk
     * @return formatted String ready to be written to disk
     */
    public String formatTaskListForSave() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.taskList) {
            sb.append(task.formatForSave()).append("\n");
        }
        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }
}
