import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    static LinkedList<Task> todo = new LinkedList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static String line = "---------------------------------";

    static void add(Task cur) {
        todo.add(cur);
        System.out.println("Added: " + cur.description);
    }
    static void list(LinkedList<Task> list) {
        for (Task cur : todo) {
            System.out.println(todo.indexOf(cur) + 1 + ". [" + cur.getStatusIcon() + "] " + cur.description);
        }
    }

    static boolean isNumeric(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    static void markTask(int index) {
        Task cur = todo.get(index);
        cur.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);

    }
    static void unmarkTask(int index) {
        Task cur = todo.get(index);
        cur.markUndone();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("[" + cur.getStatusIcon() + "] " + cur.description);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        System.out.println(line);

        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");
            if (input.equals("list")) {
                list(todo);
            } else if (inputArr.length == 2 && inputArr[0].equals("mark") && isNumeric(inputArr[1]) ) {
                markTask(Integer.parseInt(inputArr[1]) -1);
            } else if (inputArr.length == 2 && inputArr[0].equals("unmark") && isNumeric(inputArr[1])){
                unmarkTask(Integer.parseInt(inputArr[1]) -1);
            }
            else {
                add(new Task(input));
            }
            System.out.println(line);
            input = sc.nextLine();
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
