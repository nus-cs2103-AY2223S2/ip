package book;

import java.util.Scanner;

import book.task.Task;

/**
 * Class that handles user interaction.
 */
public class Ui {
    /** Indentation. */
    private static final String INDENT = "    ";
    /** {@code Scanner} instance for reading user input. */
    private static final Scanner input = new Scanner(System.in);

    /**
     * Returns the {@code String} welcome message for {@code Book}.
     *
     * @return {@code String} welcome message for {@code Book}.
     */
    public static String showWelcome() {
        return "Good day, this is Book!\nWhat may I help you with?";
    }

    /**
     * Returns the {@code String} exit message for {@code Book}.
     *
     * @return {@code String} exit message for {@code Book}
     */
    public String showExit() {
        return "Bye! Pick up Book again soon!";

    }

    /**
     * Returns the {@code String} message for adding a {@code Task} to {@code Book}.
     *
     * @param task {@code Task} associated with the message.
     * @return {@code String} message for adding a {@code Task} to {@code Book}.
     */
    public String showAdd(Task task) {
        return "Understood, adding\n" + INDENT + task + "\nto Book.";
    }

    /**
     * Returns the {@code String} message for deleting a {@code Task} from {@code Book}.
     *
     * @param task {@code Task} associated with the message.
     * @return {@code String} message for deleting a {@code Task} from {@code Book}.
     */
    public String showDelete(Task task) {
        return "Acknowledged, striking the following task off of Book's pages:\n" + INDENT + task;
    }

    /**
     * Returns the {@code String} message for marking a {@code Task} from {@code Book}.
     *
     * @param task {@code Task} associated with the message.
     * @return {@code String} message for marking a {@code Task} from {@code Book}.
     */
    public String showMark(Task task) {
        return "The following task has been marked complete:\n" + INDENT + task;
    }

    /**
     * Returns the {@code String} message for marking a {@code Task} from {@code Book}.
     *
     * @param task {@code Task} associated with the message.
     * @return {@code String} message for marking a {@code Task} from {@code Book}.
     */
    public String showUnmark(Task task) {
        return "The following task has been marked incomplete:\n" + INDENT + task;
    }

    /**
     * Returns the {@code String} message for showing the size of the {@code TaskList} in
     * {@code Book}.
     *
     * @param list {@code TaskList} associated with the message.
     * @return {@code String} message for showing the size of the {@code TaskList} in
     *         {@code Book}.
     */
    public String showTaskListSize(TaskList list) {
        return list.listSize() + " task(s) exist in Book's pages.";
    }

    /**
     * Returns the {@code String} message for showing the {@code TaskList} in {@code Book}.
     *
     * @param list {@code TaskList} associated with the message.
     * @return {@code String} message for showing the {@code TaskList} in {@code Book}.
     */
    public String showList(TaskList list) {
        return "Here are the tasks stored in Book:\n" + list;
    }

    /**
     * Returns the {@code String} message for showing the {@code Task}s matching the given
     * {@code String} keyword in {@code TaskList} in {@code Book}.
     *
     * @param list {@code TaskList} associated with the message.
     * @param keyword {@code String} keyword associated with the message.
     * @return {@code String} message for showing the {@code Task}s matching the given
     *         {@code String} keyword
     */
    public String showMatchingTasksList(TaskList list, String keyword) {
        return "Book found the following matching tasks stored:\n"
                + list.findMatchingTasks(keyword);
    }

    /**
     * Returns the {@code String} message for showing the sorted {@code TaskList} in {@code Book}.
     *
     * @param list {@code TaskList} associated with the message.
     * @return {@code String} message for showing the sorted {@code TaskList} in {@code Book}.
     */
    public String showSortedList(TaskList list) {
        return "Book sorted your tasks:\n" + list;
    }

    /**
     * Returns the {@code String} the message for showing errors in the execution of {@code Book}.
     *
     * @param message {@code String} message associated with the error.
     * @return {@code String} the message for showing errors in the execution of {@code Book}.
     */
    public String showError(String message) {
        return message;
    }
}
