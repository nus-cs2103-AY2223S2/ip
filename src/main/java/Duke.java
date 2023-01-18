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
                    System.out.println("added " + word);
                    listOfWords.add(new Task(word));
                }
            } else {
                System.out.println("added " + word);
                listOfWords.add(new Task(word));
            }
            System.out.println("-".repeat(20));
            word = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
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
