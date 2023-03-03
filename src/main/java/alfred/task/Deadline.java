package alfred.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

import alfred.exceptions.AlfredException;
import alfred.parser.DateTimeParser;

/**
 * Represents a task by the user that has a deadline.
 */
public class Deadline extends Task {

    private final String description;
    private boolean isDone;
    private final HashSet<String> wordDict = new HashSet<>();
    private final DateTimeParser dateTimeParser = new DateTimeParser();
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline object that represents a unique task given by the user.
     * @param description {@inheritDoc}
     * @param deadline Provides the deadline of the task.
     */
    public Deadline(String description, String deadline) throws AlfredException {
        this.description = description;
        isDone = false;
        this.deadline = dateTimeParser.parseIntoLocalDateTime(deadline);
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
    public boolean containsDate(LocalDate date) {
        return deadline.toLocalDate().isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String str = String.format("D | %d | %s | %s",
                isDone ? 1 : 0, this.description, this.deadline.format(formatter));
        return str + "\n";
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");
        return String.format("[D][%s] %s(by: %s)",
                this.isDone ? "X" : " ", this.description,
                this.deadline.format(formatter));
    }
}
