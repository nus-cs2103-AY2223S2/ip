package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import jarvis.exception.command.InvalidParameterException;

/**
 * Container class for task filtering information.
 */
public class TaskFilter {
    private LocalDate afterDate;
    private LocalDate beforeDate;
    private final List<String> keywords;

    /**
     * Constructor for a task filter.
     */
    public TaskFilter() {
        this.afterDate = null;
        this.beforeDate = null;
        this.keywords = new LinkedList<>();
    }

    /**
     * @return True if the filter has no information;
     *         False otherwise
     */
    public boolean isEmpty() {
        return this.hasNoDates() && this.hasNoKeywords();
    }

    /**
     * @return True if the filter has no keywords;
     *         False otherwise
     */
    public boolean hasNoKeywords() {
        return this.keywords.isEmpty();
    }

    /**
     * @return True if the filter has no afterDate or beforeDate;
     *         False otherwise
     */
    public boolean hasNoDates() {
        return this.afterDate == null && this.beforeDate == null;
    }

    /**
     * @return The date to search from.
     */
    public LocalDate getAfterDate() {
        return afterDate;
    }

    /**
     * @return The date to search until.
     */
    public LocalDate getBeforeDate() {
        return beforeDate;
    }

    /**
     * @return List of search keywords.
     */
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * @param afterDate Date string to search from.
     * @return This filter.
     * @throws InvalidParameterException If afterDate is invalid.
     */
    public TaskFilter setAfterDate(String afterDate) throws InvalidParameterException {
        try {
            this.afterDate = afterDate == null ? null : LocalDate.parse(afterDate);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid afterDate", "I don't understand the start date.");
        }
        return this;
    }

    /**
     * @param beforeDate Date string to search until.
     * @return This filter.
     * @throws InvalidParameterException If beforeDate is invalid.
     */
    public TaskFilter setBeforeDate(String beforeDate) throws InvalidParameterException {
        try {
            this.beforeDate = beforeDate == null ? null : LocalDate.parse(beforeDate);
        } catch (DateTimeParseException e) {
            throw new InvalidParameterException("Invalid beforeDate", "I don't understand the end date.");
        }
        return this;
    }

    /**
     * Splits the given line into individual words and
     * adds them to the list of keywords.
     *
     * @param fromLine String of search words.
     * @return This filter.
     */
    public TaskFilter addKeywords(String fromLine) {
        if (fromLine == null || fromLine.isBlank()) {
            return this;
        }
        List<String> words = Arrays.stream(fromLine.split("\\s"))
                .filter(s -> !s.isBlank()) // Remove empty and whitespace strings
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        this.keywords.addAll(words);
        return this;
    }
}
