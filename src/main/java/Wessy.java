import java.util.Scanner;

public class Wessy {
    static String OPENING_LINE = "    -Wessy---------------------------------------------------------------- ";
    static String CLOSING_LINE = "    ---------------------------------------------------------------------- ";
    static Task[] tasks = new Task[100];
    static int firstUnusedIdx = 0;

    public static void main(String[] args) {
        System.out.println(OPENING_LINE);
        println("Hi, I am Wessy, your personal assistant chatbot.");
        println("Please type something to interact with me.");
        System.out.println(CLOSING_LINE);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            int len = userInput.length();
            if (userInput.equalsIgnoreCase("bye")) {
                printNormal("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList();
            } else if (checkCmd(userInput, "mark")) {
                printMarkOrUnmark(userInput, true);
            } else if (checkCmd(userInput,"unmark")) {
                printMarkOrUnmark(userInput, false);
            } else {
                String[] parsedTexts = new String[] {};
                if (checkCmd(userInput, "todo")) {
                    parsedTexts = parse(userInput, "todo ");
                } else if (checkCmd(userInput, "deadline")) {
                    parsedTexts = parse(userInput, "deadline ");
                } else if (checkCmd(userInput, "event")) {
                    parsedTexts = parse(userInput, "event ");
                }
                printAdded(add(parsedTexts));
            }
        }
    }

    static String[] parse(String description, String type) {
        String byStr = " /by ";
        String fromStr = " /from ";
        String toStr = " /to ";
        int firstIdx;
        int secondIdx;
        description = description.substring(type.length());
        if (type.charAt(0) == 'd') {
            firstIdx = description.indexOf(byStr);
            return new String[] {description.substring(0, firstIdx),
                    description.substring(firstIdx + byStr.length())};
        } else if (type.charAt(0) == 'e') {
            firstIdx = description.indexOf(fromStr);
            secondIdx = description.indexOf(toStr);
            return new String[] {description.substring(0, firstIdx),
                    description.substring(firstIdx + fromStr.length(), secondIdx),
                    description.substring(secondIdx + toStr.length())};
        } else if (type.charAt(0) == 't') {
            return new String[] {description};
        }
        return new String[] {};
    }

    static boolean checkCmd(String userInput, String cmd) {
        int threshold = cmd.length();
        return userInput.length() >= threshold && userInput.substring(0,threshold).equalsIgnoreCase(cmd);
    }

    static void printNormal(String... linesOfString) {
        System.out.println(OPENING_LINE);
        for (String line : linesOfString) {
            println(line);
        }
        System.out.println(CLOSING_LINE);
    }

    static void printAdded(Task task) {
        String numOfTasks = " " + firstUnusedIdx + " task";
        if (firstUnusedIdx > 1) {
            numOfTasks += "s";
        }
        printNormal("Got it. I've added this task:",
                "  " + task, "Now you have" + numOfTasks + " in the list.");
    }

    static void println(String str) {
        int length = str.length();
        String message = "   |   " + str;
        for (int i = 0; i < 70 - length - 3; i++)
            message += " ";
        message += "|";
        System.out.println(message);
    }

    static Task add(String[] strings) {
        int len = strings.length;
        if (len == 1) {
            tasks[firstUnusedIdx++] = new ToDo(strings[0]);
        } else if (len == 2) {
            tasks[firstUnusedIdx++] = new Deadline(strings[0], strings[1]);
        } else if (len == 3) {
            tasks[firstUnusedIdx++] = new Event(strings[0], strings[1], strings[2]);
        }
        return tasks[firstUnusedIdx - 1];
    }

    static void printList() {
        System.out.println(OPENING_LINE);
        if (firstUnusedIdx == 0) {
            println("WOOHOO! You do not have any task on the list.");
        } else {
            println("Here are the tasks in your list:");
            for (int i = 0; i < firstUnusedIdx; i++) {
                println((i + 1) + "." + tasks[i]);
            }
        }
        System.out.println(CLOSING_LINE);
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
