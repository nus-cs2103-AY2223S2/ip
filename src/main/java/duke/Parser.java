package duke;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalTime;

public class Parser {

	enum Type {
		TODO,
		DEADLINE,
		EVENT,
		FIND,
		MARK,
		UNMARK,
		LIST,
		DELETE,
		BYE,
		ERR
	}

	public static String isReceivedCommand(Tasklist tasklist, String msg) {
		String returnedString = "OOPS!!! I'm sorry, but I don't know what that means :-(";
		String firstWord = "";
		if (msg.contains(" ")) {
			firstWord = msg.substring(0, msg.indexOf(" "));
		} else {
			firstWord = msg;
		}

		Type cmdType = Type.ERR;

		try {
			cmdType = Type.valueOf(firstWord.toUpperCase());
		} catch (Exception ex) {
			System.err.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
		Integer secondInt = 0;
		String byDate = "";
		String byTime = "";
		String fromWhen = "";
		String toWhen = "";

		switch (cmdType) {
		case MARK:
			try {
				firstWord = msg.substring(0, msg.indexOf(" "));
				secondInt = Integer
						.parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
				returnedString = tasklist.mark(secondInt);
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Please indicate a valid task!");
			}
			break;
		case UNMARK:
			try {
				firstWord = msg.substring(0, msg.indexOf(" "));
				secondInt = Integer
						.parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
				returnedString = tasklist.unmark(secondInt);
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Please indicate a valid task!");
			}
			break;
		case DELETE:
			try {
				secondInt = Integer.parseInt(msg.substring(msg.indexOf(" ") + 1, msg.length()));
				returnedString = tasklist.delete(secondInt);
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Please indicate a valid task to delete!");
			}
			break;

		case FIND:
			// Todo: fix the list to string implementation
			try {
				String searchWord = msg.substring(msg.indexOf(" ") + 1, msg.length());
				Predicate<Task> byMatch = task -> task.description.contains(searchWord);
				List<Task> filteredList = tasklist.listOfThings.stream()
						.filter(byMatch).collect(Collectors.toList());
				if (filteredList.isEmpty()) {
					System.out.println("There were no matching occurences of this task!");
					returnedString = "There were no matching occurences of this task!";
				} else {
					System.out.println("Here are some matching tasks in your list!");
					for (int i = 0; i < filteredList.size(); i++) {
						System.out.println(i + 1 + ". " + filteredList.get(i));
					}
					returnedString = "Here are some matching tasks in your list!\n"
							+ filteredList;
				}

			} catch (Exception ex) {
				System.err.println("There were no matching occurences of this task!");
			}

			break;
		case TODO:
			try {
				firstWord = msg.substring(msg.indexOf(" ") + 1, msg.length());
				Task.Todo newTodo = new Task.Todo(firstWord);
				returnedString = tasklist.add(newTodo);
				System.out.println("Got it. I've added this task:");
				System.out.println(newTodo);
				System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Whoops! The description of a todo cannot be empty!");
			}

			break;
		case DEADLINE:
			try {
				String[] splitted = msg.split("\\s+");
				firstWord = splitted[1];
				byDate = splitted[3];
				byTime = splitted[4];
				System.out.println(byDate);
				System.out.println(byTime);
				LocalDate d1 = LocalDate.parse(byDate);
				LocalTime t1 = LocalTime.parse(byTime);
				Task.Deadline newDeadline = new Task.Deadline(firstWord, d1, t1);
				returnedString = tasklist.add(newDeadline);
				System.out.println("Got it. I've added this task:");
				System.out.println(newDeadline);
				System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Whoops! Please enter the deadline followed by its due date preceeded by a '/by'." +
						"The date time format should be yyyy-mm-dd hh:mm");
			}
			break;
		case EVENT:
			try {
				firstWord = msg.substring(msg.indexOf(" ") + 1, msg.indexOf("/from") - 1);
				fromWhen = msg.substring(msg.indexOf("/from") + 6, msg.indexOf("/to") - 1);
				toWhen = msg.substring(msg.indexOf("/to") + 4, msg.length());
				Task.Event newEvent = new Task.Event(firstWord, fromWhen, toWhen);
				returnedString = tasklist.add(newEvent);
				System.out.println("Got it. I've added this task:");
				System.out.println(newEvent);
				System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
				try {
					Storage.save(tasklist);
				} catch (Exception ex) {
					System.err.println("Error! There is no save file!");
				}
			} catch (Exception ex) {
				System.err.println("Whoops! Please enter the event followed by its /from and /to timings.");
			}
			break;
		case BYE:
			Ui.showGoodbyeMessage();
			returnedString = "Bye. Hope to see you again soon!";
//			echoScanner.close();
//			loop = false;
			break;
		case LIST:
			tasklist.printList();
			returnedString = tasklist.listOfThings.toString();
			break;
		}
		return returnedString;
	}
}
