import formatters.Formatter;
import sebastianExceptions.DeadlineFormatMismatchException;
import sebastianExceptions.EventFormatMismatchException;
import task.*;
import time.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        saveToDisk();
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
            saveToDisk();
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
            saveToDisk();
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
            Task task = this.taskList.get(taskIndex-1).mark();
            saveToDisk();
            return task;
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
            Task task = this.taskList.get(taskIndex-1).unmark();
            saveToDisk();
            return task;
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
            Task task = this.taskList.remove(taskIndex-1);
            saveToDisk();
            return task;
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

    /**
     * Used to save existing tasks in the list into the hard disk
     */
    public void saveToDisk() {
        try {
            DataSaver.writeToDisk(this);
        } catch (IOException e) {
            Formatter.printFormattedString(
                    Formatter.space() + "Task cannot be saved to disk, exiting the program will cause data to be lost"
            );
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int totalTasks = this.getTotalTasks();
        for(int i = 0; i < totalTasks; i ++ ) {
            Task t = taskList.get(i);
            sb.append(Formatter.space()).append(i+1).append(".").append(t.toString()).append("\n");
        }
        if(sb.length()!=0) {
            sb.delete(sb.length()-1, sb.length());
        }
        return sb.toString();
    }

    /**
     * inner class used to save task list data to the disk
     */
    static class DataSaver {

        private static File file;

        /**
         * Save tasks in the taskList into the hard disk.
         * @param taskList a TaskList object
         * @throws IOException if the file exists but is a directory rather than a regular file,
         * does not exist but cannot be created, or cannot be opened for any other reason
         */
        private static void writeToDisk(TaskList taskList) throws IOException {
            if(file == null){
                file = new File("SebastianData.txt");
            }
            FileWriter fw = new FileWriter(file);
            fw.write(formatTaskList(taskList));
            fw.flush();
            fw.close();
        }

        /**
         * Format tasks in the taskList into a String ready to be written into the hard disk
         * @param l the TaskList object
         * @return formatted String
         */
        private static String formatTaskList(TaskList l) {
            StringBuilder sb = new StringBuilder();
            for(Task task: l.taskList){
                sb.append(task.formatForSave()).append("\n");
            }
            if(sb.length()>0){
                return sb.substring(0,sb.length()-1);
            }
            return sb.toString();
        }
    }
}
