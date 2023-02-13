package task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import util.Pair;

/**
 * List of tasks with formatting methods
 * 
 * @see Serializable
 * @see Task
 */
public class TaskList implements Serializable {
    private final List<Task> lst;

    /**
     * Empty tasklist
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * @return Number of tasks in list
     */
    public int size() {
        return lst.size();
    }

    /**
     * @return true if tasklist is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * @return Task description with formatting applied
     * @param number Number of task in the list
     */
    public String get(int number) {
        int index = number - 1;
        return String.format("\n\t%d) %s", number, this.lst.get(index));
    }

    /**
     * @return Description of last task in list with formatting applied
     */
    public String getLast() {
        return this.get(this.size());
    }

    /**
     * Adds task to list
     * 
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.lst.add(task);
    }

    /**
     * Marks task as completed
     * 
     * @param number Number of task in the list
     */
    public void markTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markDone());
    }

    /**
     * Unmarks task
     * 
     * @param number Number of task in the list
     */
    public void unmarkTask(int number) {
        int index = number - 1;
        this.lst.set(index, this.lst.get(index).markNotDone());
    }

    /**
     * Deletes task
     * 
     * @param number Number of task in the list
     */
    public void deleteTask(int number) {
        int index = number - 1;
        this.lst.remove(index);
    }

    /**
     * Finds tasks in the list via date
     * 
     * @return Task descriptions with formatting applied
     * @param date Date to compare with
     * @see LocalDate
     */
    public String findByDate(LocalDate date) {
        String res = IntStream.range(0, this.size())
                .filter(i -> this.lst.get(i).hasDate(date))
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b);
        if (res.isEmpty()) {
            return "No tasks found.";
        }
        return res;
    }

    /**
     * Finds tasks in list via keywords
     * 
     * @param keywords List of keywords
     * @return Task descriptions with formatting applied
     */
    public String findByKeywords(List<String> keywords) {
        String res = IntStream.range(0, this.size())
                .filter(i -> this.lst.get(i).hasKeywords(keywords))
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b);
        if (res.isEmpty()) {
            return "No tasks found.";
        }
        return res;

    }

    /**
     * Sorts all tasks with dates, leaving other tasks in the same position
     */
    public void sort() {
        List<Integer> indexesToSort = IntStream.range(0, this.size())
                .mapToObj(i -> new Pair<>(i, this.lst.get(i)))
                .filter(pr -> pr.second().getDate().isPresent())
                .map(pr -> pr.first())
                .collect(Collectors.toList());

        List<Task> sortedFilteredTasks = this.lst.stream()
                .filter(task -> task.getDate().isPresent())
                .sorted((a, b) -> a.getDate().get().compareTo(b.getDate().get()))
                .collect(Collectors.toList());
        
        IntStream.range(0, this.size())
                .mapToObj(i -> new Pair<>(indexesToSort.get(i), sortedFilteredTasks.get(i)))
                .forEach(pr -> this.lst.set(pr.first(), pr.second()));
    }

    @Override
    public String toString() {
        if (this.lst.isEmpty()) {
            return "You have no tasks.";
        }

        return IntStream.range(0, this.size())
                .mapToObj(i -> String.format("\n\t%d) %s", i + 1, this.lst.get(i)))
                .reduce("", (a, b) -> a + b);
    }
}
