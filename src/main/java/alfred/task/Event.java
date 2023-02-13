package alfred.task;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

import alfred.exceptions.AlfredException;
import alfred.parser.DateTimeParser;

/**
 * Represents an Event task given by the user.
 */
public class Event extends Task {

    private final String description;
    private boolean isDone;
    private final HashSet<String> wordDict = new HashSet<>();
    private final DateTimeParser dateTimeParser = new DateTimeParser();
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an Event object that represents a unique task given by the user.
     * @param description {@inheritDoc}
     * @param startDate Provides the start date of the event.
     * @param endDate Provides the end date of the event.
     */
    public Event(String description, String startDate, String endDate) throws AlfredException {
        this.description = description;
        isDone = false;
        this.startDate = dateTimeParser.parseIntoLocalDateTime(startDate);
        this.endDate = dateTimeParser.parseIntoLocalDateTime(endDate);
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
        return startDate.toLocalDate().isEqual(date) || endDate.toLocalDate().isEqual(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        String str = String.format("E | %d | %s | %s-%s",
                isDone ? 1 : 0, this.description, this.startDate.format(formatter),
                this.endDate.format(formatter));
        return str + "\n";
    }

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
        return String.format("[E][%s] %s (from: %s to: %s)",
                isDone ? "X" : " ", description,
                startDate.format(formatter), endDate.format(formatter));
    }
}

