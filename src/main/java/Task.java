import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task {
    private String content;
    private boolean done;

    public static Task parseTask(String s) throws InvalidTaskStringException {

        // check if task format is correct
        if (!s.matches("\\[.]\\[.] .+")) {
            throw new InvalidTaskStringException("Incorrect task format: Square brackets not in proper format.");
        }

        char taskType = s.charAt(1);
        boolean isMarked = false;
        String content = s.substring(7);

        // check if mark is correct
        if (s.charAt(4) == 'X') {
            isMarked = true;
        } else if (s.charAt(4) != ' ') {
            throw new InvalidTaskStringException("Incorrect task format: Checkbox is not in proper format.");
        }

        switch (taskType) {
            case 'T':
                // parse todo
                return new ToDo(content, isMarked);
            case 'D':
                // parse deadline
                if (!content.matches(".+ \\(by: .+\\)")) {
                    throw new InvalidTaskStringException("Incorrect deadline format");
                }

                // find deadline
                int deadlineStart = content.length() - 1;

                for (int i = content.length() - 1; i >= 0; i--) {
                    if (content.substring(i - 5, i).equals("(by: ")) {
                        deadlineStart = i;
                        break;
                    }
                }

                String deadline = content.substring(deadlineStart, content.length() - 1);
                String deadlineContent = content.substring(0, deadlineStart - 6);

                try {
                    return new Deadline(deadlineContent, isMarked, deadline);
                } catch (InvalidDateFormatException e) {
                    System.out.println(e.getMessage());
                }
            case 'E':
                // parse Event
                if (!content.matches(".+ \\(from: .+ to: .+\\)")) {
                    throw new InvalidTaskStringException("Incorrect event format");
                }

                // find start and end
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

                try {
                    return new Event(eventContent, isMarked, start, end);
                } catch (InvalidDateFormatException e) {
                    System.out.println(e.getMessage());
                }
            default:
                throw new InvalidTaskStringException("Unknown task type!");
        }
    }

    public static void saveTasks(TaskList tl) {

    }

    Task(String content) {
        this.content = content;
        this.done = false;
    }

    Task(String content, boolean done) {
        this.content = content;
        this.done = done;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String checkBox = this.done ? "[X] " : "[ ] ";
        return checkBox +  this.content;
    }
}