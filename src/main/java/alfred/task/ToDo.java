package alfred.task;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Represents a to-do task given by the user.
 */
public class ToDo extends Task {

    private final String description;
    private boolean isDone = false;
    private final HashSet<String> wordDict = new HashSet<>();

    /**
     * Constructs a to-do object that represents a unique task given by the user.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void markAsDone() {
        isDone = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unmarkTask() {
        isDone = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileFormat() {
        String str = String.format("T | %d | %s", isDone ? 1 : 0, this.description);
        return str + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsDate(LocalDate date) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKeyWords(String keyWords) {
        String[] keyWordsArr = keyWords.split(" ");
        if (wordDict.isEmpty()) {
            wordDict.addAll(Arrays.asList(description.split(" ")));
        }
        for (String keyWord : keyWordsArr) {
            if (!wordDict.contains(keyWord)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
