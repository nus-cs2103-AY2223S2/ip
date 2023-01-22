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
        ArrayList<Entry> list = new ArrayList<>();
        boolean end = false;
        while(!end) {
            System.out.print("Please Input: ");
            end = processInput(userInput.nextLine(), list, end);
        }
    }

    private static boolean processInput(String input, ArrayList<Entry> list, boolean end) {
        System.out.println(dashedLine());
        switch (input) {
            case "bye":
                System.out.println("Pleasure doing business with you.");
                end = true;
                break;
            case "list":
                for (int i = 1; i <= list.size(); i++){
                    System.out.println(i + ". " + list.get(i-1));
                }
                break;
            default:
                if (input.substring(0,4).toLowerCase(Locale.ROOT).equals("mark")) {
                    int index = parseInt(input.substring(input.length() - 1));
                    if (list.size() >= index) {
                        list.get(index - 1).setChecked(true);
                        System.out.println("It's all good man, just marked this task as done:\n"
                                                + list.get(index - 1).toString());
                    }
                } else if (input.substring(0,6).toLowerCase(Locale.ROOT).equals("unmark")) {
                    int index = parseInt(input.substring(input.length() - 1));
                    if (list.size() >= index) {
                        list.get(index - 1).setChecked(false);
                        System.out.println("Alright, I marked this task as not done:\n"
                                + list.get(index - 1).toString());
                    }
                } else {
                    System.out.println("Just added " + "\"" + input + "\" to the list. Anything else?");
                    list.add(new Entry(input));
                }
        }
        System.out.println(dashedLine());
        return end;
    }
}
