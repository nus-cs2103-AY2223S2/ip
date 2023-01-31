package alfred.task;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class Task {
    protected final String description;
    protected boolean isDone;
    private final HashSet<String> wordDict;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.wordDict = new HashSet<>();
    }
    // I remember there's a modifier only allowing classes in same file to access?
    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String addToFile() {
        String str = String.format(" | %d | %s",
                isDone ? 1 : 0, this.description);
        return str + "\n";
    }

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
        } else {
            for (String keyWord : keyWordsArr) {
                if (!wordDict.contains(keyWord)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.isDone ? "X" : " ", this.description);
    }
}
