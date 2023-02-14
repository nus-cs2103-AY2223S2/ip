package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;
    private static List<Task> wordList = new ArrayList<>();
    private static List<Task> remindList = new ArrayList<>();
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public static List<Task> getContent() {
        return tasks;
    }

    public static List<Task> getWordList() { return wordList; }

    public static List<Task> getRemindList() {
        return remindList;
    }

    public static void findKeyword(String keyword) {
        wordList.clear();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                wordList.add(task);
            }
        }
    }

    public static void findReminders() {
        remindList.clear();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime remindLimit = today.plusDays(7);
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                LocalDateTime date = Parser.getTime(((Deadline) task).getDeadlineTimeString());
                if (!date.isAfter(remindLimit)) {
                    remindList.add(task);
                }
            }
            if (task instanceof Event) {
                LocalDateTime date = Parser.getTime(((Event) task).getStartTime());
                if (!date.isAfter(remindLimit)) {
                    remindList.add(task);
                }
            }
        }
    }
}
