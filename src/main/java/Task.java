import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    public final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    protected static String encodeValues(String[] values) {
        UnaryOperator<String> escapeVerticalBar = str -> str.replace("|", "\\|");
        return Stream.of(values)
            .map(escapeVerticalBar)
            .collect(Collectors.joining(" | "));
    }

    protected static String[] decodeValues(String encodedValues) {
        UnaryOperator<String> unescapeVerticalBar = str -> str.replace("\\|", "|");
        return Stream.of(encodedValues.split(" \\| "))
            .map(unescapeVerticalBar)
            .toArray(String[]::new);
    }

    public static Task loadFromString(String input) throws DukeSaveLoadException {
        String[] values = Task.decodeValues(input);
        String taskType = values[0];
        switch (taskType) {
            case "T":
                return TaskTodo.loadFromString(input);
            case "D":
                return TaskDeadline.loadFromString(input);
            case "E":
                return TaskEvent.loadFromString(input);
            default:
                String errorMessage = String.format("Unknown task in save-file: \"%s\"", input);
                throw new DukeSaveLoadException(errorMessage);
        }
    }

    public abstract String encodeAsString();

    @Override
    public String toString() {
        String statusIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", statusIcon, description);
    }

    protected static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
