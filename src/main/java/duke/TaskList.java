package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> tasks;
    private static List<Task> wordList = new ArrayList<>();
    public TaskList() {
        this.tasks = new ArrayList<>();
    }


    public static List<Task> getContent() {
        return tasks;
    }

    public static List<Task> getWordList() { return wordList; }

    public static void findKeyword(String keyword) {
        wordList.clear();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                wordList.add(task);
            }
        }
    }
}
