package duke.task;

import duke.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{

    /**
     * Constructs task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }

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

    /**
     * Filter tasks by keywords.
     *
     * @param keywords Keywords to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filter(String... keywords) {
        TaskList filteredTasks = new TaskList();
        for (String keyword : keywords) {
            for (Task task : this) {
                String description = task.getDescription();
                if (description.contains(keyword.trim())) {
                    if (!filteredTasks.contains(task)) {
                        filteredTasks.add(task);
                    }
                }
            }
        }
        return filteredTasks;
    }

    /**
     * Filter tasks by their dates.
     *
     * @param dates Dates to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filterDate(LocalDate... dates) {
        TaskList filteredTasks = new TaskList();
        for (LocalDate date : dates) {
            for (Task task : this) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getDeadline().toLocalDate().isEqual(date)) {
                        if (!filteredTasks.contains(task)) {
                            filteredTasks.add(task);
                        }
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getStartDateTime().toLocalDate().isEqual(date) ||
                            event.getEndDateTime().toLocalDate().isEqual(date) ||
                            (event.getStartDateTime().toLocalDate().isBefore(date) &&
                                    event.getEndDateTime().toLocalDate().isAfter(date))) {
                        if (!filteredTasks.contains(task)) {
                            filteredTasks.add(task);
                        }
                    }
                }
            }
        }
        return filteredTasks;
    }
}
