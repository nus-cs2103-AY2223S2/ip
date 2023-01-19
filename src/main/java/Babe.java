import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import exception.NoDescriptionException;
import exception.NonsenseInputException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * <h1> Hi Babe! </h1>
 * Babe is a chat bot created as part of the individual project (iP) under the course CS2109T.
 * It is a duplicate of Duke with some personal flair.
 *
 * @author Shan Hern Hng
 * @version 1.0
 * @since 17 January 2023
 */
public class Babe {


    /**
     * A string input from user.
     */
    private ArrayList<String> userInput = new ArrayList<>();

    /**
     * Length of user input.
     */
    private int userInputLen = 0;

    /**
     * List of Tasks Babe received from the user.
     */
    private ArrayList<Task> memory = new ArrayList<>();

    /**
     * Number of Tasks currently stored in this Babe.
     */
    private int memoryCount = 0;

    /**
     * Draws a horizontal line.
     * Draws a line for cosmetic purposes.
     */
    private static void drawLine() {
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Welcome users of Babe.
     * Greets user and prompts for help. This method does not expect a response.
     */
    private void welcome() {
        Babe.drawLine();
        System.out.println("HELLO! Greetings from Babe.");
        System.out.println("How may I help you?");
        Babe.drawLine();
    }

    /**
     * Receives user's input.
     * Receives input from the user and stores it in input field.
     */
    private void listen(Scanner scanner) {
            userInput = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));
            userInputLen = userInput.size();
    }

    /**
     * Rebuilds a string from ArrayList from the starting index to the ending index (not inclusive).
     * A helper function to recover the original user input from userInput starting from the startingIndex
     * to the ending index (not inclusive).
     */
    private String rebuildUserInput(int startingIndex, int endingIndex) {
        String result = "";
        for (int i = startingIndex; i < endingIndex; i++) {
            result += userInput.get(i);
            result += " ";
        }
        return result.stripTrailing();
    }

    /**
     * Finds and returns index of command arguments in userInput demarcated by given String pattern
     *
     * @args pattern A String pattern that precedes the input of command argument
     * @return An integer that is the index of command argument.
     */
    private int findArgument(String pattern) {

       int argIndex = -1;

        for (int i = 0; i < userInputLen; i++) {
            String currentString = userInput.get(i);
            if (currentString.equals(pattern)) {
                argIndex = i + 1;
                break;
            }
        }

        return argIndex;
    }

    /**
     * Adds a ToDo to memory.
     * Calls the ToDo constructor and inserts created ToDo into this Babe's memory.
     * @param content The description of the ToDo item.
     */
    private void addToDo(String content) {
        ToDo item = new ToDo(content);
        this.memory.add(memoryCount++, item);
        Babe.drawLine();
        System.out.println("Got it, babe. Added this for you:");
        System.out.println(item.toString());
        System.out.printf("Now you have %d task in the list.\n", memoryCount);
        Babe.drawLine();
    }

    /**
     * Adds a Deadline to memory.
     * Calls the Deadline constructor and inserts created Deadline into this Babe's memory.
     * @param content The content of the Deadline item.
     * @param date The date of the deadline. May include time too.
     */
    private void addDeadline(String content, String date) {
        Deadline item = new Deadline(content, date);
        this.memory.add(memoryCount++, item);
        Babe.drawLine();
        System.out.println("Got it, babe. Added this for you:");
        System.out.println(item.toString());
        System.out.printf("Now you have %d task in the list.\n", memoryCount);
        Babe.drawLine();
    }

    /**
     * Adds an Event to memory.
     * Calls the Event constructor and inserts created Event into this Babe's memory.
     * @param content The content of the Event item.
     * @param startDate The start date of the Event. May include time too.
     * @param endDate The end date of the Event. May include time too.
     */
    private void addEvent(String content, String startDate, String endDate) {
        Event item = new Event(content, startDate, endDate);
        this.memory.add(memoryCount++, item);
        Babe.drawLine();
        System.out.println("Got it, babe. Added this for you:");
        System.out.println(item.toString());
        System.out.printf("Now you have %d task in the list.\n", memoryCount);
        Babe.drawLine();
    }

    /**
     * Prints list of Tasks stored in this Babe.
     * Prints a numbered list of Items stored in memory.
     */
    private void printList() {
        Babe.drawLine();
        if (memoryCount == 0) {
            System.out.println("Nothing added yet. Add something babygorl.");
        }
        for (int i = 0; i < this.memoryCount; i++) {
            System.out.printf("%d. ", i + 1);
            System.out.println(memory.get(i).toString());
        }
        Babe.drawLine();
    }

    /**
     * Bids farewell to the user.
     * Prints a line of farewell before ending the program.
     */
    private void sayBye() {
        Babe.drawLine();
        System.out.println("Bye, babyboo. Can't wait to meet you again!");
        Babe.drawLine();
        System.exit(0);
    }

    /**
     * Marks/Unmarks the item of given index in Babe's list as Done/Undone.
     * If user keys in "mark", this function will extract the index to be marked and sets the index to True in
     * doneStatus. Sets the index to False if "unmark"is keyed in.
     */
    private void changeStatus(boolean toMark) {
        String userInputString = rebuildUserInput(1, userInputLen);
        int index = Integer.parseInt(userInputString);
        Task itemAtIndex = memory.get(index - 1);
        if (toMark) {
            itemAtIndex.mark();
        } else {
            itemAtIndex.unmark();
        }
        Babe.drawLine();
        System.out.println(toMark ? "Okay, babygorl. I've marked this as Done:" : "We have un-Done this for you:");
        System.out.println(itemAtIndex.toString());
        Babe.drawLine();
    }

    /**
     * Delete Task in memory specified by given index.
     *
     * @param index An integer that represents the index of the Task to be removed from memory.
     */
    public void deleteTask(int index) {
        Task removedTask = this.memory.remove(index - 1);
        memoryCount--;
        Babe.drawLine();
        System.out.println("One task down! I removed this from your list of tasks:");
        System.out.println(removedTask.toString());
        System.out.printf("Now you have %d task(s) left!\n", memoryCount);
        Babe.drawLine();
    }

    public static void main(String[] args) {

        Babe chatBot = new Babe();
        chatBot.welcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                chatBot.listen(scanner);

                String instruction = chatBot.userInput.get(0);
                int inputLength = chatBot.userInput.size();


                if (instruction.equalsIgnoreCase("bye") && inputLength == 1) {
                    scanner.close();
                    chatBot.sayBye();
                } else if (instruction.equalsIgnoreCase("list") && inputLength == 1) {
                    chatBot.printList();
                } else if (instruction.equalsIgnoreCase("mark")) {
                    chatBot.changeStatus(true);
                } else if (instruction.equalsIgnoreCase("unmark")) {
                    chatBot.changeStatus(false);
                } else if (instruction.equalsIgnoreCase("todo")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    chatBot.addToDo(chatBot.rebuildUserInput(1, inputLength));
                } else if (instruction.equalsIgnoreCase("deadline")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    int deadline = chatBot.findArgument("/by");
                    chatBot.addDeadline(chatBot.rebuildUserInput(1, deadline - 1),
                            chatBot.rebuildUserInput(deadline, inputLength));
                } else if (instruction.equalsIgnoreCase("event")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    int startDate = chatBot.findArgument("/from");
                    int endDate = chatBot.findArgument("/to");
                    chatBot.addEvent(chatBot.rebuildUserInput(1, startDate - 1),
                            chatBot.rebuildUserInput(startDate, endDate - 1),
                            chatBot.rebuildUserInput(endDate, inputLength));
                } else if (instruction.equalsIgnoreCase("delete")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    int deleteIndex = chatBot.findArgument("delete");
                    chatBot.deleteTask(deleteIndex);

                } else {
                    throw new NonsenseInputException();
                }
            } catch (NonsenseInputException e1) {
                Babe.drawLine();
                System.out.println("I do not know how to read this. SORRRYY </3");
                Babe.drawLine();
            } catch (NoDescriptionException e2) {
                Babe.drawLine();
                System.out.println("I need to know what is the description of your task, bestie!");
                Babe.drawLine();
            }
        }

    }


}
