package alfred.task;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;


// Can consider this class to be an abstract class?

/**
 * Represents a task given by the user.
 */
public class Task {
    protected final String description;
    protected boolean isDone;
    private final HashSet<String> wordDict;

    /**
     * Constructs a Task object that represents a unique task given by the user.
     * @param description Provides the name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.wordDict = new HashSet<>();
    }
    // I remember there's a modifier only allowing classes in same file to access?

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Un-mark the task as completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Converts the file into a format that is suitable for data storage.
     * @return The converted format of the task that describes its attributes.
     */
    public String addToFile() {
        String str = String.format(" | %d | %s",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    /**
     * Checks if the task contains a LocalDate object.
     * @param date The LocalDate object that represents the date of the task.
     * @return True if the task contains the given date, else False.
     */
    public boolean containsDate(LocalDate date) {
        return false;
    }

    // only problem is task: read book, keywords "find book read" will work
    // need to find the order. Questions only mentions "keyword"
    /**
     * Checks if the task description contains the key word. Order doesn't matter.
     * Eg: Keywords: book read. Description: read book. The method will still return true.
     * @param keyWords The words that we are looking for in the task.
     * @return True if the task description contains the keywords else false.
     */
    public boolean containsKeyWords(String keyWords) {
        String[] keyWordsArr = keyWords.split(" ");
        if (wordDict.isEmpty()) {
            wordDict.addAll(Arrays.asList(description.split(" ")));
        }
        assert wordDict.size() > 0 : "dictionary shouldn't be empty";
        for (String keyWord : keyWordsArr) {
            if (!wordDict.contains(keyWord)) {
                return false;
            }
            assert wordDict.contains(keyWord) : "Should contain key word";
        }
        return true;
    }

    /**
     * Converts the task into a String which represents a readable format for the user.
     * @return The readable format of the task.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
