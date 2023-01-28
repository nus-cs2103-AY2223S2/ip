import java.util.Scanner;

public class Wessy {
    static Task[] tasks = new Task[100];
    static int firstUnusedIdx = 0;

    public static void main(String[] args) {
        System.out.println("    -Wessy------------------------------------------------------ ");
        println("Hi, I am Wessy, your personal assistant chatbot.");
        println("Please type something to interact with me.");
        System.out.println("    ------------------------------------------------------------ ");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            int len = userInput.length();
            if (userInput.equalsIgnoreCase("bye")) {
                printNormal("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else if (len > 3 && userInput.substring(0,4).equalsIgnoreCase("mark")) {
                printMarkOrUnmark(userInput, true);
            } else if (len > 5 && userInput.substring(0,6).equalsIgnoreCase("unmark")) {
                printMarkOrUnmark(userInput, false);
            } else {
                add(userInput);
                printNormal("added: " + userInput);
            }
        }
    }

    static void printNormal(String... linesOfString) {
        System.out.println("    -Wessy------------------------------------------------------ ");
        for (String line : linesOfString) {
            println(line);
        }
        System.out.println("    ------------------------------------------------------------ ");
    }

    static void println(String str) {
        int length = str.length();
        String message = "   |   " + str;
        for (int i = 0; i < 60 - length - 3; i++)
            message += " ";
        message += "|";
        System.out.println(message);
    }

    static void add(String description) {
        Task newTask = new Task(description);
        tasks[firstUnusedIdx++] = newTask;
    }

    static void printList() {
        System.out.println("    -Wessy------------------------------------------------------ ");
        if (firstUnusedIdx == 0) {
            println("WOOHOO! You do not have any task on the list.");
        } else {
            println("Here are the tasks in your list:");
            for (int i = 0; i < firstUnusedIdx; i++) {
                println((i + 1) + "." + tasks[i]);
            }
        }
        System.out.println("    ------------------------------------------------------------ ");
    }

    static void printMarkOrUnmark(String userInput, boolean isMark) {
        int len = userInput.length();
        int minLen = isMark ? 4 : 6;
        // If they only type 'mark' or 'mark '
        if (len == minLen || (len == minLen + 1 && userInput.charAt(minLen) == ' ')) {
            printNormal("Please choose an index.");
        // If they did not leave a space
        } else if (len > minLen && userInput.charAt(minLen) != ' ') {
            printNormal("Please enter the command in the correct format.");
        } else {
            // Check is the input string even integer OR is the index out of the array's bound
            try {
                int idx = Integer.parseInt(userInput.substring(minLen + 1)) - 1;
                String start = isMark ? "Nice! I've" : "OK, I've";
                if (isMark == tasks[idx].isDone) {
                    start = "You have already";
                }
                if (isMark) {
                    tasks[idx].mark();
                } else {
                    tasks[idx].unmark();
                }
                String end = isMark ? "done:" : "not done yet:";
                printNormal(start + " marked this task as " + end, "  " + tasks[idx]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                printNormal("Please choose a valid index.");
            }
        }
    }
}
