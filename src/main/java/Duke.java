import java.util.Scanner;

import java.util.ArrayList;

public class Duke {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();
    private void Input() {
        while (true) {
            String userInput = this.scanner.nextLine();
            try{
                if (userInput.equals("bye")) {
                    this.exit();
                    break;
                }
                if (userInput.equals("list")) {
                    this.showList();
                    continue;
                }
                if (userInput.startsWith("mark")) {
                    int taskNum = Integer.parseInt(userInput.substring(5));
                    this.markTask(taskNum);
                    continue;
                }
                if (userInput.startsWith("unmark")) {
                    int taskNum = Integer.parseInt(userInput.substring(7));
                    this.unmarkTask(taskNum);
                    continue;
                }
                if (userInput.startsWith("todo")) {
                    String todo = userInput.replace("todo", "");
                    emptyTodo(todo);
                    addTodo(todo);
                    continue;
                }
                if (userInput.startsWith("event")) {
                    String[] event = ErrorEventOrDeadline(userInput, "event", "/at");
                    addEvent(event[0], event[1]);
                    continue;
                }
                if (userInput.startsWith("deadline")) {
                    String[] deadline = ErrorEventOrDeadline(userInput, "deadline", "/by");
                    addDeadline(deadline[0], deadline[1]);
                    continue;
                }
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException exception) {
                printMessage(exception.getMessage());
            }
        }
    }

    private void emptyTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private String[] ErrorEventOrDeadline(String input, String textToReplace, String textToSplit) throws DukeException {
        String[] splitInput = input.replaceFirst(textToReplace, "")
                .trim().split(textToSplit);

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("☹ OOPS!!! Please make sure the date is not empty");
        }
        return splitInput;
    }

    private void addTodo(String description) {
        Todo newTodo = new Todo(description);
        list.add(newTodo);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newTodo);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    private void addDeadline(String description, String by) {
        Deadline newDeadline = new Deadline(description, by);
        list.add(newDeadline);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newDeadline);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    private void addEvent(String description, String from) {
        Event newEvent = new Event(description, from);
        list.add(newEvent);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + newEvent);
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    private void markTask(int taskNum) {
        this.list.get(taskNum - 1).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + this.list.get(taskNum - 1) + "\n");
    }

    private void unmarkTask(int taskNum) {
        this.list.get(taskNum - 1).markAsUndone();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t" + this.list.get(taskNum - 1) + "\n");
    }

    private void showList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }

    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }

    private void printMessage(String message) {
        System.out.println("\t" + message);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
        duke.Input();
    }
}