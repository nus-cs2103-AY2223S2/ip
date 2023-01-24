import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    //@@author IceFire
    //Reused from https://stackoverflow.com/questions/36514289
    // with minor modifications
    private static String dashedLine()
    {
        StringBuilder sb = new StringBuilder(90);
        for (int i = 0; i < 90; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/

        Scanner userInput = new Scanner(System.in);
        System.out.println(dashedLine());
        System.out.println("My name is Skyler White yo \n"
                + "How can I help you?");
        System.out.println(dashedLine());
        ArrayList<Task> list = new ArrayList<>();
        boolean end = false;
        while(!end) {
            System.out.print("Please Input: ");
            end = processInput(userInput.nextLine(), list, end);
        }
    }

    private static boolean processInput(String input, ArrayList<Task> list, boolean end) {
        String[] inputAnalyzed = input.split(" ");
        System.out.println(dashedLine());
        switch (inputAnalyzed[0].toLowerCase(Locale.ROOT)) {
            case "bye":
                System.out.println("Pleasure doing business with you.");
                end = true;
                break;
                //Check if there is anything other than bye
            case "list":
                for (int i = 1; i <= list.size(); i++){
                    System.out.println(i + ". " + list.get(i-1));
                }
                break;
                //Check if the list input contains anything else other than list
            case "mark":
                int index = parseInt(inputAnalyzed[1]);
                if (list.size() >= index) {
                    list.get(index - 1).setChecked(true);
                    System.out.println("It's all good man, just marked this task as done:\n"
                            + list.get(index - 1).toString());
                }
                //array out of bounds, numberformatexception
                break;
            case "unmark":
                int index1 = parseInt(inputAnalyzed[1]);
                if (list.size() >= index1) {
                    list.get(index1 - 1).setChecked(false);
                    System.out.println("Alright, I marked this task as not done:\n"
                            + list.get(index1 - 1).toString());
                }
                break;
            case "deadline":
                String[] deadlineAnalyze = input.split("/by");
                String deadline = deadlineAnalyze[1].trim();
                String deets = deadlineAnalyze[0].split("deadline")[1].trim();
                Deadline newDead = new Deadline(deets.toString(), deadline);
                list.add(newDead);
                System.out.println("Got it sir, just added this task to the list.\n"
                                    + newDead + "\nYou now have " + list.size() + " tasks. Anything else?");
                break;
            case "todo":
                String[] todoAnalyze = input.split("todo ");
                try {
                    Todo newTodo = new Todo(todoAnalyze[1].trim());
                    list.add(newTodo);
                    System.out.println("Got it sir, just added this task to the list.\n"
                            + newTodo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Whoops, you need to put in what you're about to do!");
                }
                break;
            case "event":
                String[] eventAnalyze = input.split("/from");
                String[] timeAnalyze = eventAnalyze[1].split("/to");
                String start = timeAnalyze[0].trim();
                String over = timeAnalyze[1].trim();
                String details = eventAnalyze[0].split("event")[1].trim();
                Event newEvent = new Event(details, start, over);
                list.add(newEvent);
                System.out.println("Got it sir, just added this task to the list.\n"
                        + newEvent + "\nYou now have " + list.size() + " tasks. Anything else?");
                break;
            default:
                System.out.println("Sorry sir, didn't quite get that." +
                        "\nYou have " + list.size() + " tasks. Anything else?");
        }
        System.out.println(dashedLine());
        return end;
    }
}
