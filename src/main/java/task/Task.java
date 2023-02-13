package task;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import util.Util;
/**
 * General Task abstract class
 * 
 * @see Serializable
 */
public abstract class Task implements Serializable {
    protected final String desc;
    protected final boolean isDone;

    /**
     * @param desc Task description
     * @param done Task completed info
     */
    protected Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        char mark = ' ';
        if (isDone) {
            mark = 'X';
        }

        return String.format("[%c] %s", mark, this.desc);
    }

    /**
     * @return true if task has date, false otherwise.
     * @see LocalDate
     */
    public abstract boolean hasDate(LocalDate date);

    /**
     * Checks if task description contains keywords
     * 
     * @param keywords List of keywords
     */
    public boolean hasKeywords(List<String> keywords) {
        Set<String> descKeywords = Util.splitWhitespace(desc).stream()
                .map(s -> s.toLowerCase())
                .collect(Collectors.toCollection(HashSet::new));

        
        return keywords.stream()
                .map(word -> descKeywords.contains(word))
                .reduce(true, (a, b) -> a && b);
    }

    /**
     * @return new task that is marked as done.
     */
    public abstract Task markDone();

    /**
     * @return new task that is marked as not done.
     */
    public abstract Task markNotDone();
}
