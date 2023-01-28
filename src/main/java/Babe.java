import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
     * Default file address where Babe's content will be stored.
     */
    private String fileAddress = "./src/main/java/babe.txt";

    /**
     * BufferedWriter Object for this Babe
     */
    private BufferedWriter bufferedWriter;

    /**
     * Draws a horizontal line.
     */
    private static void drawLine() {
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Constructor for Babe.
     */
    public Babe() {
        if (!Files.exists(Paths.get(fileAddress))) {
            try {
                File file = new File(fileAddress);
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Babe.drawLine();
            System.out.println("We can't find a save file for Babe, so we just went ahead \n"
                    + "and created one for you <3!");
            Babe.drawLine();
        }

        this.load();
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
    private Task addToDo(String content, boolean toNotify) {
        ToDo item = new ToDo(content);
        this.memory.add(memoryCount++, item);
        if (toNotify) {
            Babe.drawLine();
            System.out.println("Got it, babe. Added this for you:");
            System.out.println(item.toString());
            System.out.printf("Now you have %d task in the list.\n", memoryCount);
            Babe.drawLine();
        }
        return item;
    }

    /**
     * Adds a Deadline to memory.
     * Calls the Deadline constructor and inserts created Deadline into this Babe's memory.
     * @param content The content of the Deadline item.
     * @param date The date of the deadline. May include time too.
     */
    private Task addDeadline(String content, String date, boolean toNotify) {
        Deadline item = new Deadline(content, date);
        this.memory.add(memoryCount++, item);
        if (toNotify) {
            Babe.drawLine();
            System.out.println("Got it, babe. Added this for you:");
            System.out.println(item.toString());
            System.out.printf("Now you have %d task in the list.\n", memoryCount);
            Babe.drawLine();
        }
        return item;
    }

    /**
     * Adds an Event to memory.
     * Calls the Event constructor and inserts created Event into this Babe's memory.
     * @param content The content of the Event item.
     * @param startDate The start date of the Event. May include time too.
     * @param endDate The end date of the Event. May include time too.
     */
    private Task addEvent(String content, String startDate, String endDate, boolean toNotify) {
        Event item = new Event(content, startDate, endDate);
        this.memory.add(memoryCount++, item);
        if (toNotify) {
            Babe.drawLine();
            System.out.println("Got it, babe. Added this for you:");
            System.out.println(item.toString());
            System.out.printf("Now you have %d task in the list.\n", memoryCount);
            Babe.drawLine();
        }
        return item;
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
     * Deletes Task in memory specified by given index.
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

    /**
     * Saves Tasks stored in Babe's memory to a file specified by fileAddress.
     */
    private void save() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileAddress, false));
            String data = "";

            for (int i = 0; i < memoryCount; i++) {
                data = memory.get(i).toSaveFormat();
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            Scanner scanner = new Scanner(new File(fileAddress));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split("\\|");
                String taskType = arr[0].strip();
                Boolean isDone = arr[1].equals("1");
                String desc = arr[2];
                Task item = null;
                switch (taskType) {
                case "T":
                    item = addToDo(desc, false);
                    break;
                case "D":
                    String deadline = arr[3];
                    item = addDeadline(desc, deadline, false);
                    break;
                case "E":
                    String startDate = arr[3];
                    String endDate = arr[4];
                    item = addEvent(desc, startDate, endDate, false);
                    break;
                default:
                    // Need to add a file corruption error here
                    break;
                }

                if (isDone) {
                    item.mark();
                } else {
                    item.unmark();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Formats date specified by user.
     * The date will be formatted using java.LocalDate.
     *
     * @param dateAndTime A String that contains date and time specified by the user.
     * @return A String containing the formatted date and original specified time.
     */
    private String formatDate(String dateAndTime) {
        String[] words = dateAndTime.split(" ");

        LocalDate d1 = LocalDate.parse(words[0]);
        String formattedDateAndTime = Integer.toString(d1.getDayOfMonth()) + " "
                + d1.getMonth() + " " + d1.getYear() + " "
                + (words.length == 2 ? words[1] : "");

        return formattedDateAndTime;

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
                    chatBot.save();
                    
                } else if (instruction.equalsIgnoreCase("unmark")) {
                    chatBot.changeStatus(false);
                    chatBot.save();
                    
                } else if (instruction.equalsIgnoreCase("todo")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    chatBot.addToDo(chatBot.rebuildUserInput(1, inputLength), true);
                    chatBot.save();
                    
                } else if (instruction.equalsIgnoreCase("deadline")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }

                    int deadlineIndex = chatBot.findArgument("/by");
                    String deadline = "";

                    try {
                        deadline = chatBot.formatDate(chatBot.rebuildUserInput(deadlineIndex,
                                inputLength));
                        chatBot.addDeadline(chatBot.rebuildUserInput(1, deadlineIndex - 1),
                                deadline,
                                true);
                        chatBot.save();
                    } catch (Exception e) {
                        Babe.drawLine();
                        System.out.println("The date format should be yyyy-mm-dd, luv. Please try again.");
                        Babe.drawLine();
                    }


                } else if (instruction.equalsIgnoreCase("event")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }

                    int startDateIndex = chatBot.findArgument("/from");
                    int endDateIndex = chatBot.findArgument("/to");
                    String startDateAndTime = "";
                    String endDateAndTime = "";

                    try {
                        startDateAndTime = chatBot.formatDate(chatBot.rebuildUserInput(startDateIndex,
                                endDateIndex - 1));
                        endDateAndTime = chatBot.formatDate(chatBot.rebuildUserInput(endDateIndex,
                                inputLength));
                        chatBot.addEvent(chatBot.rebuildUserInput(1, startDateIndex - 1),
                                startDateAndTime,
                                endDateAndTime,
                                true);
                        chatBot.save();

                    } catch (Exception e) {
                        Babe.drawLine();
                        System.out.println("The date format should be yyyy-mm-dd, luv. Please try again.");
                        Babe.drawLine();
                    }


                }
                else if (instruction.equalsIgnoreCase("delete")) {
                    if (inputLength == 1) {
                        throw new NoDescriptionException();
                    }
                    int deleteIndex = chatBot.findArgument("delete");
                    chatBot.deleteTask(deleteIndex);
                    chatBot.save();

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
