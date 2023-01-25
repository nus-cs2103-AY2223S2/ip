package collections;
import models.Deadline;
import models.Event;
import models.Task;
import utilities.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList extends ArrayList<Task> {

    public TaskList() {
        super();
    }

    public void mark(int i) { super.get(i-1).mark(); }

    public void unMark(int i) { super.get(i-1).unMark(); }

    public TaskList getTaskListOnDate(String date) {
        TemporalAccessor inputDate = DateTimeParser.parseDate(date);
        TaskList taskListOnDate = new TaskList();
        if (inputDate instanceof LocalDate) {
            Iterator<Task> iterator = this.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if (task instanceof Deadline) {
                    TemporalAccessor deadline = ((Deadline) task).getDeadline();
                    if (deadline instanceof LocalDate) {
                        if (((LocalDate) inputDate).isEqual((LocalDate) deadline)) {
                            taskListOnDate.add(task);
                        }
                    } else if (deadline instanceof LocalDateTime) {
                        if (((LocalDate) inputDate).isEqual(((LocalDateTime) deadline).toLocalDate())) {
                            taskListOnDate.add(task);
                        }
                    }
                } else if (task instanceof Event) {
                    TemporalAccessor from = ((Event) task).getFrom();
                    TemporalAccessor to = ((Event) task).getTo();
                    if (from instanceof LocalDate && to instanceof LocalDate) {
                        if (((LocalDate) inputDate).isEqual((LocalDate) from) || ((LocalDate) inputDate).isEqual((LocalDate) to)) {
                            taskListOnDate.add(task);
                        }
                    } else if (from instanceof LocalDateTime && to instanceof LocalDateTime) {
                        if (((LocalDate) inputDate).isEqual(((LocalDateTime) from).toLocalDate()) || ((LocalDate) inputDate).isEqual(((LocalDateTime) to).toLocalDate())) {
                            taskListOnDate.add(task);
                        }
                    }
                }
            }
        }
        return taskListOnDate;
    }

    @Override
    public Task get(int i) { return super.get(i-1); }

    @Override
    public Task remove(int i) {
        Task removedTask = super.remove(i-1);
        return removedTask;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Task task : this) {
            if (i == size()) {
                sb.append(i+".").append(task).append("\t");
            } else {
                sb.append(i + ".").append(task).append("\n\t");
                i++;
            }
        }
        return sb.toString();
    }
}
