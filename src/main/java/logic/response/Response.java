package logic.response;

import model.TaskList;
import model.tasks.Task;

public class Response {
	public static String getAddTaskResponse(Task task, TaskList taskList) {
		return "Got it. I've added this task:\n" + task + "\nNow you have " +
		 taskList.size() + " tasks in the list.";
	}

	public static String getDeleteTaskResponse(Task task, TaskList taskList) {
		return "Noted. I've removed this task:\n" + task + "\nNow you have " +
		 taskList.size() + " tasks in the list.";
	}

	public static String getMarkTaskResponse(Task task) {
		return "Nice! I've marked this task as done:\n" + task;
	}

	public static String getUnmarkTaskResponse(Task task) {
		return "Noted. I've marked this task as undone:\n" + task;
	}

	public static String getEmptyCommandResponse() {
		return "Please enter a command.";
	}

	public static String getEmptyListResponse() {
		return "There are no tasks in your list.";
	}

	public static String getListResponse(TaskList taskList) {
		String response = "Here are the tasks in your list:\n";
		for (int i = 0; i < taskList.size(); i++) {
			response += (i + 1) + ". " + taskList.get(i) + "\n";
		}
		return response;
	}

	public static String getExitResponse() {
		return "Bye. Hope to see you again soon!";
	}

	public static String getFindTaskResponse(TaskList taskList) {
		String response = "Here are the matching tasks in your list:\n";
		for (int i = 0; i < taskList.size(); i++) {
			response += (i + 1) + ". " + taskList.get(i) + "\n";
		}
		return response;
	}

	public static String getInvalidCommandResponse() {
		return "I'm sorry, but the command you entered is invalid :-(";
	}

	public static String getInvalidIndexResponse() {
		return "Please enter a valid index.";
	}

	public static String returnChatError() {
		return "I'm sorry, but I don't know what that means :-(";
	}

	public static String getWelcome() {
		return "Hello! I'm Corky!\n" +
			"What can I do for you?";
	}

	public static String returnChatCreateNewDirectory(String storageDirname) {
		return "Creating new directory: " + storageDirname + "/n";
	}

	public static String returnChatCreateNewStorage(String storageName) {
		return "Creating new storage: " + storageName + "/n";
	}

	public static String getHelpResponse() {
		return "Here are the commands you can use:\n" +
			"1. todo <task name> - adds a todo task\n" +
			"2. deadline <task name> /by <deadline> - adds a deadline task\n" +
			"3. event <task name> /at <start time> to <end time> - adds an event task\n" +
			"4. doafter <task name> /after <task number> - adds a doafter task\n" +
			"5. list - lists all tasks\n" +
			"6. done <task number> - marks a task as done\n" +
			"7. delete <task number> - deletes a task\n" +
			"8. find <keyword> - finds tasks with the keyword\n" +
			"9. bye - exits the program";
	}
}
