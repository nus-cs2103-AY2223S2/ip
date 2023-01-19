import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        introDuke();
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        List<Task> listOfWords = new ArrayList<>();
        while (!word.equals("bye")) {
            System.out.println("-".repeat(20));
            if (word.equals("list")) {
                int count = 1;
                for (Task words: listOfWords) {
                    System.out.println(count++ + words.toString());
                }
            } else if (word.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(word.substring(5));
                    Task task = listOfWords.get(--index);
                    task.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(word);
                }
            } else if (word.startsWith("deadline ")) {
                String[] words = word.split("/", 2);
                if (words.length == 2 && words[1].startsWith("by ")){
                    Task task = new Deadline(words[0].substring(9), words[1].substring(3));
                    addingTask(task, listOfWords);
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("Command: " + word);
                }

            } else if (word.startsWith("todo ")) {
                Task task = new ToDo(word.substring(5));
                addingTask(task, listOfWords);
            } else if (word.startsWith("event ")) {
                String[] words = word.split("/", 3);
                if (words.length == 3 && words[1].startsWith("from ") && words[2].startsWith("to ")) {
                    Task task = new Event(words[0].substring(6), words[1].substring(5), words[2].substring(3));
                    addingTask(task, listOfWords);
                } else {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("Command: " + word);
                }
            } else if (word.startsWith("delete ")) {
                try {
                    int index = Integer.parseInt(word.substring(7));
                    Task task = listOfWords.remove(--index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(task);
                    System.out.println("Now you have " + listOfWords.size() + " tasks in the list.");
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    // incorrect syntax
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(word);
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("Command: " + word);
            }
            System.out.println("-".repeat(20));
            word = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addingTask(Task task, List<Task> list) {
        if (task.isEmpty()) {
            System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
        } else {
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            list.add(task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }
    public static void introDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

    }
}
