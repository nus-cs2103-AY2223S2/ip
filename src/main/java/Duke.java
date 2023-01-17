import java.util.*;
public class Duke {
    public static void main(String[] args) throws DukeException {
        Scanner userInput = new Scanner(System.in);
        List<Task> lst = new ArrayList<>();
        System.out.println("Hello! I'm Zhizhou's Chatbot.");
        System.out.println("What can I do for you?");
        String input;
        while (!(input = userInput.nextLine()).equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("____________________");
                    if (lst.size() < 1) {
                        System.out.println("You currently have no task.");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < lst.size(); i++) {
                            Task currTask = lst.get(i);
                            int taskIndex = i + 1;
                            System.out.println(taskIndex + ". " + currTask);
                        }
                    }
                    System.out.println("____________________");
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int indexToDelete = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToDelete < lst.size()) {
                        taskRemovedMessage(lst.get(indexToDelete),lst.size() - 1);
                        lst.remove(indexToDelete);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                }else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                    int indexToMark = Integer.parseInt(input.substring(5)) - 1;
                    if (indexToMark < lst.size()) {
                        Task toMark = lst.get(indexToMark);
                        toMark.markAsDone();
                        customMessage("Nice! I've marked this task as done:\n" + toMark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                    int indexToUnmark = Integer.parseInt(input.substring(7)) - 1;
                    if (indexToUnmark < lst.size()) {
                        Task toUnmark = lst.get(indexToUnmark);
                        toUnmark.markAsUndone();
                        customMessage("OK, I've marked this task as not done yet:\n" + toUnmark);
                    } else {
                        throw new DukeException("Invalid, there is no such task");
                    }
                } else {
                    typeOfTask(input, lst);
                }
            } catch (DukeException dukeException) {
                customMessage(dukeException.getMessage());
            }
        }
        customMessage("Bye. Hope to see you again soon!");
    }
    private static void customMessage(String message) {
        System.out.println("____________________");
        System.out.println(message);
        System.out.println("____________________");
    }

    private static void taskAddedMessage(Task task, int sizeOfList) {
        System.out.println("____________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + sizeOfList + " task" + (sizeOfList > 1 ? "s" : "") +" in the list.");
        System.out.println("____________________");
    }
    private static void taskRemovedMessage(Task task, int sizeOfList) {
        System.out.println("____________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + sizeOfList + " task" + (sizeOfList > 1 ? "s" : "") +" in the list.");
        System.out.println("____________________");
    }
    private static void typeOfTask(String input, List<Task> lst) throws DukeException {
        if (input.length() >= 4 && input.substring(0,4).equals("todo")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("todo")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Task newTask = new ToDo(input.substring(5, input.length()));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else if (input.length() >= 5 && input.substring(0,5).equals("event")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("event")) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String[] str = input.substring(6).split("/");
            Task newTask = new Event(str[0].substring(0, str[0].length() - 1), str[1].substring(5, str[1].length() - 1), str[2].substring(3));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else if (input.length() >= 8 && input.substring(0,8).equals("deadline")) {
            String check = input.replaceAll("\\s", "");
            if (check.equals("deadline")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] str = input.substring(9).split("/");
            Task newTask = new Deadline(str[0].substring(0, str[0].length() - 1), str[1].substring(3));
            lst.add(newTask);
            taskAddedMessage(newTask, lst.size());
        } else {
            throw new DukeException("I'm sorry, I don't know what that means!");
        }
    }
}
