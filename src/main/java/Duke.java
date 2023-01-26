import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * A talking robot
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng 
 */

public class Duke {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Task> strArr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Eren\nWhat can I do for you?");
        boolean bool;
        while (true) {
            bool = runCommand();
            if(!bool)
                break;
        }

    }

    /**
     * Run the input command given by the user
     * 
     * @return return False if command is bye, otherwise return true
     */
    private static boolean runCommand() throws IOException {
        String[] words;
        System.out.print("Type your input below: \n");
        words = br.readLine().split(" ");
        words[0] = words[0].toUpperCase();
        try { 
            switch(words[0]) {
            case "BYE":
                horizontalLine();
                System.out.println("Bye. Hope to see you again soon!");
                return false;
            case "LIST":
                printList();
                break;
            case "MARK":
                Task markTask = getTask(Integer.parseInt(words[1]));
                markTask.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(markTask);
                break;
            case "UNMARK":
                Task unmarkTask = getTask(Integer.parseInt(words[1]));
                unmarkTask.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unmarkTask);
                break;
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                String[] sliceWords = Arrays.copyOfRange(words, 1, words.length);
                specialTask(sliceWords, words[0]);
                break;
            case "DELETE":
                deleteTask(Integer.parseInt(words[1]));
                break;
            default:
                throw new InvalidCommandException("");
            }
            horizontalLine();
            return true;
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Number out of range/empty. Please try again!");
        } catch(NumberFormatException e) {
            System.out.println("Invalid number. Please enter a number!");
        } catch(InvalidCommandException e) {
            System.out.println("Invalid command. Pleas try again!");
        } 
        horizontalLine();
        return true;
    }



    /**
     * Print a double horizontal line
     */ 
    private static void horizontalLine() { 
        for (int i = 0; i < 20; i++) {
            System.out.print("=");
        }
        System.out.println("");
    }

    /**
     * Print the list
     */ 
    private static void printList() {
        for (int i = 0; i < strArr.size(); i++) {
            System.out.println((i+1) + ". " + strArr.get(i));
        }
    }

    /**
     * Retrieve the Task object from the list given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     */ 
    private static Task getTask(int num) throws IndexOutOfBoundsException {
        return strArr.get(num-1);
    }

    /**
     * Add a Task to the list given the description
     * 
     * @param words Array of strings to be added to the list
     * @param type The type of task
     * 
     */ 
    private static void specialTask(String[] words, String type) {
        Task task;
        if (words.length == 0) {
            System.out.println("The description of " + type + " cannot be empty. Please try again");
            return;
        }
        if (type.equals("TODO")) {
            task = new Todo(String.join(" ", words ));
        } else {
            String description;
            if (type.equals("DEADLINE")) {
                int indexForBy = getIndexOfWord(words, "/by");
                if (indexForBy == 0) {
                    System.out.println("The description of " + type + " cannot be empty. Please try again");
                    return;
                }
                LocalDateTime dateTimeBy = getDateTime(words,indexForBy);
                if (dateTimeBy == null) {
                    System.out.println("Please enter in this format {description} /by DD/MM/YYYY HHMM. Try again");
                    return;
                }
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForBy));
                task = new Deadline(description,getDateTime(words,indexForBy));
            }
            else {
                int indexForFrom = getIndexOfWord(words, "/from");
                if (indexForFrom == 0) {
                    System.out.println("The description of " + type + " cannot be empty. Please try again");
                    return;
                }
                int indexForTo = getIndexOfWord(words, "/to");
                if (getDateTime(words,indexForFrom) == null || getDateTime(words,indexForTo) == null) {
                    System.out.println("Please enter in this format {description} /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM. Try again");
                    return;
                }

                LocalDateTime dateTimeFrom = getDateTime(words,indexForFrom);
                LocalDateTime dateTimeTo = getDateTime(words,indexForTo);
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForFrom));
                task = new Event(description,dateTimeFrom,dateTimeTo);
            }
        }
        strArr.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    /**
     * Delete a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     * 
     */ 
    private static void deleteTask(int num) throws IndexOutOfBoundsException {
        Task selectedTask = getTask(num);
        strArr.remove(selectedTask);
        System.out.println("Noted. I've removed this task:\n" + selectedTask);
        System.out.println("Now you have " + strArr.size() + " tasks in the list.");
    }

    /**
     * Return index of given word
     * 
     * @param words the array of words to look from
     * @param word the word to look for
     * 
     * @return return the index of the given word, if it doesnt exist, return -1
     */
    private static int getIndexOfWord(String[] words, String word) {
        int index = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static LocalDateTime getDateTime(String[] words, int index) {
        LocalDateTime dateTime;

        if (index != -1 && words.length >= index+2) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                dateTime = LocalDateTime.parse(words[index+1] + " " + words[index+2], formatter); 
                return dateTime;
            } catch (DateTimeParseException e) {
                return null;
            }
        } else {
            return null;
        }
    }
}

