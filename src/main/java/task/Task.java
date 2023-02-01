package task;

import exception.InvalidDateFormatException;
import exception.InvalidTaskStringException;

/**
 * Represents a task to be done by the user.
 */
public class Task {
    private String content;
    private boolean done;


    /**
     * Constructor for Task.
     * @param content Content to be done for the task.
     */
    Task(String content) {
        this.content = content;
        this.done = false;
    }

    /**
     * Constructor for Task.
     * @param content Content to be done for the task.
     * @param done Whether the task is completed.
     */
    Task(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    /**
     * Converts a task string from storage into a Task object.
     *
     * @param s The string that represents the task to be parsed.
     * @return The corresponding Task object.
     * @throws InvalidTaskStringException If the task string is corrupted.
     * @throws InvalidDateFormatException If the task input has an invalid date format.
     */
    public static Task parseTask(String s) throws InvalidTaskStringException, InvalidDateFormatException {

        // Check if string format is correct.
        if (!s.matches("\\[.]\\[.] .+")) {
            throw new InvalidTaskStringException("Incorrect task format: Square brackets not in proper format.");
        }

        // Break string apart.
        char taskType = s.charAt(1);
        boolean isMarked = false;
        String content = s.substring(7);

        // Check if mark is correct
        if (s.charAt(4) == 'X') {
            isMarked = true;
        } else if (s.charAt(4) != ' ') {
            throw new InvalidTaskStringException("Incorrect task format: Checkbox is not in proper format.");
        }

        // Determine the task type.
        switch (taskType) {
        case 'T':
            // Return todo object.
            return new ToDo(content, isMarked);
        case 'D':
            // Check if content is in the deadline format.
            if (!content.matches(".+ \\(by: .+\\)")) {
                throw new InvalidTaskStringException("Incorrect deadline format");
            }

            // Find deadline portion of string.
            int deadlineStart = content.length() - 1;

            for (int i = content.length() - 1; i >= 0; i--) {
                if (content.substring(i - 5, i).equals("(by: ")) {
                    deadlineStart = i;
                    break;
                }
            }

            String deadline = content.substring(deadlineStart, content.length() - 1);
            String deadlineContent = content.substring(0, deadlineStart - 6);

            // Return deadline object.
            return new Deadline(deadlineContent, isMarked, deadline);
        case 'E':
            // Check if content is in the event format.
            if (!content.matches(".+ \\(from: .+ to: .+\\)")) {
                throw new InvalidTaskStringException("Incorrect event format");
            }

            // Find start and end portions of the string.
            int startIndex = content.length();
            int endIndex = content.length();

            for (int i = content.length() - 1; i >= 0; i--) {
                if (content.substring(i - 4, i).equals("to: ")) {
                    endIndex = i;
                } else if (content.substring(i - 7, i).equals("(from: ")) {
                    startIndex = i;
                    break;
                }
            }

            String start = content.substring(startIndex, endIndex - 5);
            String end = content.substring(endIndex, content.length() - 1);
            String eventContent = content.substring(0, startIndex - 8);

            // Return event object.
            return new Event(eventContent, isMarked, start, end);
        default:
            throw new InvalidTaskStringException("Unknown task type!");
        }
    }

    /**
     * Marks a task as done.
     */
    public void mark() {
        done = true;
    }

    /**
     * Unmarks a task as not done.
     */
    public void unmark() {
        done = false;
    }

    /**
     * Checks if task contains a word.
     *
     * @param word The word to check
     * @return Whether task contains the word;
     */
    public boolean containsWord(String word) {
        return content.contains(word);
    }

    @Override
    public String toString() {
        // Fill square brackets with X if task is done.
        String checkBox = done ? "[X] " : "[ ] ";

        return checkBox + content;
    }
}