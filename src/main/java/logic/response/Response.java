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
		return "I'm sorry, but I don't know what that means :-(";
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
}
