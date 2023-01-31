package duke.io.input.ui;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import duke.util.Storage;
import duke.workflow.Event;
import  duke.workflow.Greeting;

/**
 * Initiates Duke's interaction workflow with use.
 * The flow starts with the {@code Greeting} event where Duke
 * says hello to the user.
 *
 * <p> It then proceeds to the {@code DoTask} event
 * where Duke makes sense of the user input and determine the possible
 * actions. </p>
 *
 * <p> The workflow ends with the {@code Ending} event where Duke
 * says goodbye to the user. </p>
 *
 */

public class UserInteraction {
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("_".repeat(22));
        System.out.println("Hello from\n" + logo);
        System.out.println("_".repeat(22));
        System.out.println("");
    }

    public static void printWithBracket(String input) {
        if (input.contains("\n")) {
            String[] inputSplitArray = input.split("\n");
            List<String> inputSplitList = Arrays.asList(inputSplitArray);
            int max = 0;
            for (int i = 0; i < inputSplitList.size(); i++) {
                if (inputSplitList.get(i).length() > max) {
                    max = inputSplitList.get(i).length();
                }
            }
            System.out.println("_".repeat(max));
            System.out.println(input);
            System.out.println("_".repeat(max));
        } else {
            int size = input.length();
            System.out.println("_".repeat(size));
            System.out.println(input);
            System.out.println("_".repeat(size));
        }
    }

    public void chatBegin() {
        Scanner sc = new Scanner(System.in);
        Greeting greeting = new Greeting();
        UserInteraction.printWithBracket(greeting.toString());
        String isPlaying = sc.nextLine();
        if (isPlaying.equals("NO")) {
            greeting = new Greeting(0);
            Event nextEvent = greeting.toNext();
            UserInteraction.printWithBracket(nextEvent.toString());
        } else if (isPlaying.equals("YES")) {
            greeting = new Greeting(1);
            Event nextEvent = greeting.toNext();
            UserInteraction.printWithBracket(nextEvent.toString());
            while (nextEvent.getStatus() == false) {
                nextEvent = nextEvent.toNext();
                UserInteraction.printWithBracket(nextEvent.toString());
            }
            System.out.println("SAVE YOUR GRAND PLAN FOR ANOTHER DAY? ");
            String isSaving = sc.nextLine();
            if (isSaving.equals("YES")) {
                Storage.saveProgress(nextEvent.getTaskList());
            }
        } else {
            while (!isPlaying.equals("YES") && !isPlaying.equals("NO")) {
                UserInteraction.printWithBracket("FOCUS, HUMAN. " +
                        "YOU ARE TO ENTER INPUT WITH FULL CAPS.");
                isPlaying = sc.nextLine();
            }
            if (isPlaying.equals("NO")) {
                greeting = new Greeting(0);
                Event end = greeting.toNext();
                UserInteraction.printWithBracket(end.toString());
            } else {
                greeting = new Greeting(1);
                Event nextEvent = greeting.toNext();
                UserInteraction.printWithBracket(nextEvent.toString());
                while (nextEvent.getStatus() == false) {
                    nextEvent = nextEvent.toNext();
                    UserInteraction.printWithBracket(nextEvent.toString());
                }
            }
        }
    }
}