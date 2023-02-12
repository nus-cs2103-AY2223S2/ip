package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class TaskList extends ArrayList<Task>{
    /** Consistent serialVersionUID value */
    private static final long serialVersionUID = 6529685098267757690L;

    /**
     * Constructs empty task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Marks task in TaskList.
     *
     * @param index Index of task to be marked.
     */
    public void markTask(int index) {
        Task task= this.get(index);
        task.setDone(true);
    }

    /**
     * Unmarks task in TaskList.
     *
     * @param index Index of task to be unmarked.
     */
    public void unmarkTask(int index) {
        Task task= this.get(index);
        task.setDone(false);
    }

    /**
     * Filters tasks by keywords.
     *
     * @param keywords Keywords to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filter(String... keywords) {
        return this.stream()
                .filter(task -> Arrays.stream(keywords)
                        .anyMatch(keyword -> task.getDescription().contains(keyword.trim())))
                .distinct()
                .collect(Collectors.toCollection(TaskList::new));
    }

    /**
     * Checks if task is on date.
     *
     * @param task Task to be checked.
     * @param date Date to be checked.
     * @return boolean of whether task is on date.
     */
    private boolean isTaskOnDate(Task task, LocalDate date) {
        if (task instanceof Deadline) {
            return ((Deadline) task).getDeadline().toLocalDate().isEqual(date);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getStartDateTime().toLocalDate().isEqual(date) ||
                    event.getEndDateTime().toLocalDate().isEqual(date) ||
                    (event.getStartDateTime().toLocalDate().isBefore(date) &&
                            event.getEndDateTime().toLocalDate().isAfter(date));
        }
        return false;
    }

    /**
     * Filters tasks by dates.
     *
     * @param dates Dates to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filterDate(LocalDate... dates) {
        return Arrays.stream(dates)
                .flatMap(date -> this.stream().filter(task -> isTaskOnDate(task, date)))
                .distinct()
                .collect(Collectors.toCollection(TaskList::new));
    }

    /**
     * Shows tasks in TaskList.
     *
     * @return List of tasks in TaskList in strings.
     */
    @Override
    public String toString() {
        String list = "";
        for (int i = 0; i < this.size(); i++) {
            Task t = this.get(i);
            int index = i + 1;
            if (i == this.size() - 1) {
                list += "\t" + index + "." + t;
            } else {
                list += "\t" + index + "." + t + "\n";
            }
        }
        return list;
    }

    public void sort() {
        Collections.sort(this);
    }

    public void sortDate() {
        this.sort((task1, task2) -> {
            int comparison;
            if (task1 instanceof Deadline && task2 instanceof Deadline) {
                comparison = ((Deadline) task1).getDeadline().compareTo(((Deadline) task2).getDeadline());
            } else if (task1 instanceof Deadline && task2 instanceof Event) {
                comparison = ((Deadline) task1).getDeadline().compareTo(((Event) task2).getStartDateTime());
            } else if (task1 instanceof Event && task2 instanceof Deadline) {
                comparison = ((Event) task1).getStartDateTime().compareTo(((Deadline) task2).getDeadline());
            } else if (task1 instanceof Event && task2 instanceof Event) {
                comparison = ((Event) task1).getStartDateTime().compareTo(((Event) task2).getStartDateTime());
            } else if (task1 instanceof Todo && !(task2 instanceof Todo)) {
                comparison = 1;
            } else if (task2 instanceof Todo && !(task1 instanceof Todo)) {
                comparison = -1;
            } else {
                comparison = task1.compareTo(task2);
            }
            return comparison;
        });
    }

    public void sortTask() {
        this.sort((task1, task2) -> {
            int comparison;
            if (task1 instanceof Todo && !(task2 instanceof Todo)) {
                comparison = -1;
            } else if (task2 instanceof Todo && !(task1 instanceof Todo)) {
                comparison = 1;
            } else if (task1 instanceof Deadline && !(task2 instanceof Deadline)) {
                comparison = -1;
            } else if (task2 instanceof Deadline && !(task1 instanceof Deadline)) {
                comparison = 1;
            } else {
                comparison = task1.compareTo(task2);
            }
            return comparison;
        });
    }

    public void sortDone() {
        this.sort((task1, task2) -> {
            int comparison;
            if (task1.isDone() && !(task2.isDone())) {
                comparison = 1;
            } else if (task2.isDone() && !(task1.isDone())) {
                comparison = -1;
            } else {
                comparison = task1.compareTo(task2);
            }
            return comparison;
        });
    }
}
