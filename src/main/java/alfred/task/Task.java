package alfred.task;

import java.time.LocalDate;
/**
 * Represents a task given by the user.
 */
@SuppressWarnings("checkstyle:Regexp")
public abstract class Task {

    /**
     * Marks the task as completed.
     */
    public abstract void markAsDone();

    /**
     * Un-mark the task as completed.
     */
    public abstract void unmarkTask();

    /**
     * Converts the file into a format that is suitable for data storage.
     *
     * @return The converted format of the task that describes its attributes.
     */
    public abstract String getFileFormat();


    /**
     * Checks if the task contains a LocalDate object.
     *
     * @param date The LocalDate object that represents the date of the task.
     * @return True if the task contains the given date, else False.
     */
    public abstract boolean containsDate(LocalDate date);

    // only problem is task: read book, keywords "find book read" will work
    // need to find the order. Questions only mentions "keyword"

    /**
     * Checks if the task description contains the key word. Order doesn't matter.
     * Eg: Keywords: book read. Description: read book. The method will still return true.
     *
     * @param keyWords The words that we are looking for in the task.
     * @return True if the task description contains the keywords else false.
     */
    public abstract boolean containsKeyWords(String keyWords);
}
