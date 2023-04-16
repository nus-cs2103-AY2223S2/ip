package logic.response;

import model.TaskList;
import model.tasks.Task;

/**
 * Class representing the Response
 */
public class Response {
	/**
	 * Returns the response to the Add Command
	 * @param task The Task to be added
	 * @param taskList The TaskList to be modified
	 * @return The response to the Add Command
	 */
	public static String getAddTaskResponse(Task task, TaskList taskList) {
		return "Got it. I've added this task:\n" + task + "\nNow you have " +
		 taskList.size() + " tasks in the list.";
	}

	/**
	 * Returns the response to the Delete Command
	 * @param task The Task to be deleted
	 * @param taskList The TaskList to be modified
	 * @return The response to the Delete Command
	 */
	public static String getDeleteTaskResponse(Task task, TaskList taskList) {
		return "Noted. I've removed this task:\n" + task + "\nNow you have " +
		 taskList.size() + " tasks in the list.";
	}

	/**
	 * Returns the response to the Mark Command
	 * @param task The Task to be marked
	 * @return The response to the Mark Command
	 */
	public static String getMarkTaskResponse(Task task) {
		return "Nice! I've marked this task as done:\n" + task;
	}

	/**
	 * Returns the response to the Unmark Command
	 * @param task The Task to be unmarked
	 * @return The response to the Unmark Command
	 */
	public static String getUnmarkTaskResponse(Task task) {
		return "Noted. I've marked this task as undone:\n" + task;
	}

	/**
	 * Returns the response to the Empty Command
	 * @return The response to the Empty Command
	 */
	public static String getEmptyCommandResponse() {
		return "Please enter a command.";
	}

	/**
	 * Returns the response to the Empty List Command
	 * @return The response to the Empty List Command
	 */
	public static String getEmptyListResponse() {
		return "There are no tasks in your list.";
	}

	/**
	 * Returns the response to the List Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the List Command
	 */
	public static String getListResponse(TaskList taskList) {
		String response = "Here are the tasks in your list:\n";
		for (int i = 0; i < taskList.size(); i++) {
			response += (i + 1) + ". " + taskList.get(i) + "\n";
		}
		return response;
	}

	/**
	 * Returns the response to the Exit Command
	 * @return The response to the Exit Command
	 */
	public static String getExitResponse() {
		return "Bye. Hope to see you again soon!";
	}

	/**
	 * Returns the response to the Find Command
	 * @param taskList The TaskList to be modified
	 * @return The response to the Find Command
	 */
	public static String getFindTaskResponse(TaskList taskList) {
		String response = "Here are the matching tasks in your list:\n";
		for (int i = 0; i < taskList.size(); i++) {
			response += (i + 1) + ". " + taskList.get(i) + "\n";
		}
		return response;
	}

	/**
	 * Returns the response to the Invalid Command
	 * @return The response to the Invalid Command
	 */
	public static String getInvalidCommandResponse() {
		return "I'm sorry, but the command you entered is invalid :-(";
	}

	/**
	 * Returns the response to the Invalid Index
	 * @return The response to the Invalid Index
	 */
	public static String getInvalidIndexResponse() {
		return "Please enter a valid index.";
	}

	/**
	 * Returns the response to the Invalid Task Type
	 * @return The response to the Invalid Task Type
	 */
	public static String returnChatError(Exception e) {
		return "Error: " + e.getMessage() + "/n";
	}

	/**
	 * Get Welcome Message
	 * @return Welcome Message
	 */
	public static String getWelcome() {
		return "Hello! I'm Corky!\n" +
			"What can I do for you?";
	}

	/**
	 * Returns that the storage directory is created
	 * @return The response to the storage directory creation
	 */
	public static String returnChatCreateNewDirectory(String storageDirname) {
		return "Creating new directory: " + storageDirname + "/n";
	}

	/**
	 * Returns that the storage file is created
	 * @return The response to the storage file creation
	 */
	public static String returnChatCreateNewStorage(String storageName) {
		return "Creating new storage: " + storageName + "/n";
	}

	/**
	 * Returns help message
	 * @return The help message
	 */
	public static String getHelpResponse() {
		return "Here are the commands you can use:\n" +
			"1. todo <task name> - adds a todo task\n" +
			"2. deadline <task name> /by <deadline> - adds a deadline task\n" +
			"3. event <task name> /at <start time> to <end time> - adds an event task\n" +
			"4. doafter <task name> /after <task number> - adds a doafter task\n" +
			"5. list - lists all tasks\n" +
			"6. done <task number> - marks a task as done\n" +
			"7. delete <task number> - deletes a task\n" +
			"8. help - shows this help message\n" +
			"9. find <keyword> - finds tasks with the keyword\n" +
			"10. bye - exits the program";
	}

	/**
	 * Returns that the storage file is created
	 * @return The response to the storage file creation
	 */
	public static String createFileMessage(String fileName) {
		return "Creating new file: " + fileName + System.lineSeparator();
	}
}
