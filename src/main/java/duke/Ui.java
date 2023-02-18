package duke;

import java.util.Scanner;


/**
 * The Ui class encapsulates interactions with
 * the user.
 */
public class Ui {
	/* The logo of the program to be displayed at the start */
	private static final String LOGO = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

	private static final String INDENT = "    ";
	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * Prints the welcome message whenever
	 * the program is started.
	 *
	 * @return The welcome message.
	 */
	public static String showWelcomeMessage() {
		String welcomeMsg = "Hello from\n" + LOGO +
				"\n" + "Hello! I'm Duke\nWhat can I do for you?";
		return welcomeMsg;
	}

	/**
	 * Returns the message for showing the size of the {@code Tasklist}.
	 *
	 * @param tasklist {@code Tasklist} of the program.
	 * @return {@code String} Message for showing the size of the tasklist
	 * in the to-do list.
	 */
	public static String showTasklistSize(Tasklist tasklist) {
		return "Now you have " + tasklist.size() + " tasks in the list.";
	}

	/**
	 * Returns the message for adding a task.
	 *
	 * @param task {@code task} added.
	 * @return {@code String} Message for adding the task.
	 */
	public static String showAdd(Task task) {
		return "Got it. I've added this task:\n"
				+ INDENT + task;
	}

	/**
	 * Returns the message for deleting a task.
	 *
	 * @param task {@code task} deleted.
	 * @return {@code String} Message for deleting the task.
	 */
	public static String showDelete(Task task) {
		return "Got it. Deleting this task :\n"
				+ INDENT + task;
	}

	/**
	 * Returns the message for saving a file.
	 *
	 * @param saveNo Slot to be saved.
	 * @return {@code String} Message for saving to that slot.
	 */
	public static String showSave(int saveNo) {
		return "Saved to slot number " + saveNo + "!";
	}

	/**
	 * Returns the message for loading a file.
	 *
	 * @param saveNo Slot to be loaded from.
	 * @return {@code String} Message for loading from that slot.
	 */
	public static String showLoad(int saveNo) {
		return "Loaded from slot number " + saveNo + "!";
	}

	/**
	 * Returns the list of saves available.
	 *
	 * @param saves List of saves.
	 * @return {@code String} List of saves.
	 */
	public static String listSaves(SaveList saves) {
		String returnedString = "Here are the current save files!\n";
		returnedString += saves;
		return returnedString;
	}
	/**
	 * Returns the message for marking a task as done.
	 *
	 * @param task {@code task} marked.
	 * @return {@code String} Message for marking the task.
	 */
	public static String showMark(Task task) {
		return "Nice! I've marked this task as done\n"
				+ INDENT + task;
	}
	/**
	 * Returns the message for marking a task as undone.
	 *
	 * @param task {@code task} unmarked.
	 * @return {@code String} Message for unmarking the task.
	 */
	public static String showUnmark(Task task) {
		return "Nice! I've marked this task as undone\n"
				+ INDENT + task;
	}

	/**
	 * List out all the tasks in the tasklist.
	 *
	 * @param tasklist {@code Tasklist} to be shown.
	 * @return {@code String} All the tasks in the tasklist.
	 */
	public static String showList(Tasklist tasklist) {
		return "Here are all the tasks in the list!\n" + tasklist;
	}

	/**
	 * Returns the goodbye message whenever
	 * the program is terminated.
	 *
	 * @return The goodbye message.
	 */
	public static String showGoodbyeMessage() {
		return "Bye. Hope to see you again soon!";
	}
}
