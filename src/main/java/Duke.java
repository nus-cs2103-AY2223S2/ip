import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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
                end = byeOperation(inputAnalyzed);
                break;
            case "list":
                listOperation(inputAnalyzed,list);
                break;
            case "mark":
                markOperation(inputAnalyzed,list);
                break;
            case "unmark":
                unmarkOperation(inputAnalyzed,list);
                break;
            case "deadline":
                ddlOperation(inputAnalyzed,input,list);
                break;
            case "todo":
                todoOperation(inputAnalyzed,input,list);
                break;
            case "event":
                eventOperation(inputAnalyzed,input,list);
                break;
            case "delete":
                deleteOperation(inputAnalyzed,list);
                break;
            default:
                System.out.println("Sorry sir, didn't quite get that." +
                        "\nYou have " + list.size() + " tasks. Anything else?");
        }
        System.out.println(dashedLine());
        return end;
    }

    private static boolean byeOperation(String[] inputAnalyzed){
        //Check if there is anything other than bye
        if (inputAnalyzed.length > 1) {
            System.out.println("Did you want to close the program? A simple bye would be enough.");
            return false;
        } else {
            System.out.println("Pleasure doing business with you.");
            return true;
        }
    }

    private static void listOperation(String[] inputAnalyzed,  ArrayList<Task> list){
        //Check if there is anything other than list
        if (inputAnalyzed.length > 1) {
            System.out.println("Did you want to see your list? Just type \"list\". Nothing else.");
        } else {
            for (int i = 1; i <= list.size(); i++){
                System.out.println(i + ". " + list.get(i-1));
            }
        }
    }

    private static void markOperation(String[] inputAnalyzed, ArrayList<Task> list){
        try {
            if (inputAnalyzed.length > 2) {
                System.out.println("If you want to mark a task, just type \"mark\" and a number. Nothing else. Sheesh.");
                return;
            }
            int index = parseInt(inputAnalyzed[1]);
            list.get(index - 1).setChecked(true);
            System.out.println("It's all good man, just marked this task as done:\n"
                    + list.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            //Inputted a number beyond the list's length
            System.out.println("Woah there. That entry doesn't exist yo.");
        } catch (NumberFormatException e) {
            System.out.println("Label the place you want to mark with a NUMBER, not a word.");
        }

        //array out of bounds, numberformatexception
    }

    private static void unmarkOperation(String[] inputAnalyzed, ArrayList<Task> list){
        try {
            if (inputAnalyzed.length > 2) {
                System.out.println("If you want to unmark a task, just type \"unmark\" and a number. Nothing else.");
                return;
            }
            int index = parseInt(inputAnalyzed[1]);
            list.get(index - 1).setChecked(false);
            System.out.println("Alright, I marked this task as not done:\n"
                    + list.get(index - 1).toString());

        } catch (IndexOutOfBoundsException e) {
            //Inputted a number beyond the list's length
            System.out.println("Woah there. That entry doesn't exist yo.");
        } catch (NumberFormatException e) {
            System.out.println("Label the place you want to unmark with a NUMBER, not a word.");
        }
    }

    private static void deleteOperation(String[] inputAnalyzed, ArrayList<Task> list) {
        try {
            if (inputAnalyzed.length > 2) {
                System.out.println("If you want to delete a task, just type \"delete\" and a number. That's it.");
                return;
            }
            int index = parseInt(inputAnalyzed[1]);
            Task temp = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Righto, just removed this task from your list:\n"
                    + temp + "\nYou now have " + list.size() + " tasks left.");

        } catch (IndexOutOfBoundsException e) {
            //Inputted a number beyond the list's length
            System.out.println("Woah there. That entry doesn't exist yo.");
        } catch (NumberFormatException e) {
            System.out.println("Label the place you want to delete with a NUMBER, and not just any number; an INTEGER.");
        }
    }

    private static void ddlOperation(String[] inputAnalyzed, String input, ArrayList<Task> list){
        //Possible Errors:
            //No descriptor
            //No /by
            //No deadline
        try {
            String[] deadlineAnalyze = input.split("/by");
            String deadline = deadlineAnalyze[1].trim();
            String deets = deadlineAnalyze[0].split("deadline")[1].trim();
            if (deets.equals("")) {
                System.out.println("Where's the description?");
                return;
            }
            Deadline newDead = new Deadline(deets.toString(), deadline);
            list.add(newDead);
            System.out.println("Got it sir, just added this task to the list.\n"
                    + newDead + "\nYou now have " + list.size() + " tasks. Anything else?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You forgot to add the deadline to your deadline. Reformat your deadline info and try again.");
        }
    }

    private static void todoOperation(String[] inputAnalyzed, String input, ArrayList<Task> list){
        //Possible Errors:
        //No descriptor
        String[] todoAnalyze = input.split("todo ");
        try {
            Todo newTodo = new Todo(todoAnalyze[1].trim());
            list.add(newTodo);
            System.out.println("Got it sir, just added this task to the list.\n"
                    + newTodo);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Whoops, you need to put in what you're about to do!");
        }
    }

    private static void eventOperation(String[] inputAnalyzed, String input, ArrayList<Task> list){
        //Possible Errors:
        //No descriptor
        //No /from
        //No /to
        //No things in between or following these two labels
        try {
            String[] eventAnalyze = input.split("/from");
            String[] timeAnalyze = eventAnalyze[1].split("/to");
            String start = timeAnalyze[0].trim();
            String over = timeAnalyze[1].trim();
            String details = eventAnalyze[0].split("event")[1].trim();
            if (start.equals("") || over.equals("") || details.equals("")) {
                System.out.println("Where's the specifics?");
                return;
            }
            Event newEvent = new Event(details, start, over);
            list.add(newEvent);
            System.out.println("Got it sir, just added this task to the list.\n"
                    + newEvent + "\nYou now have " + list.size() + " tasks. Anything else?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You forgot to add the time frame. Reformat your event info and try again.");
        }

    }
}
