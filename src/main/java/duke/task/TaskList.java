package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * Filter tasks by keywords.
     *
     * @param keywords Keywords to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filter(String... keywords) {
        List<String> keywordList = Arrays.asList(keywords);
        return this.stream()
                .filter(task -> keywordList.stream().anyMatch(keyword -> task.getDescription().contains(keyword.trim())))
                .distinct()
                .collect(Collectors.toCollection(TaskList::new));
    }

    /**
     * Filter tasks by their dates.
     *
     * @param dates Dates to filter tasks by.
     * @return TaskList of filtered tasks.
     */
    public TaskList filterDate(LocalDate... dates) {
        TaskList filteredTasks = new TaskList();
        Arrays.stream(dates)
                .flatMap(date -> this.stream()
                        .filter(task -> {
                            if (task instanceof Deadline) {
                                Deadline deadline = (Deadline) task;
                                return deadline.getDeadline().toLocalDate().isEqual(date);
                            } else if (task instanceof Event) {
                                Event event = (Event) task;
                                return event.getStartDateTime().toLocalDate().isEqual(date) ||
                                        event.getEndDateTime().toLocalDate().isEqual(date) ||
                                        (event.getStartDateTime().toLocalDate().isBefore(date) &&
                                                event.getEndDateTime().toLocalDate().isAfter(date));
                            }
                            return false;
                        })
                        .filter(task -> !filteredTasks.contains(task)))
                .forEach(filteredTasks::add);
        return filteredTasks;
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
}
