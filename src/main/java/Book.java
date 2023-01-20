import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
    /** Logo for Book */
    private static final String LOGO = " ____\n"
            + "|  _ \\  ___   ___  _\n"
            + "| |_| |/ _ \\ / _ \\| |  _\n"
            + "|  _ <| | | | | | | |/ /\n"
            + "| |_| | |_| | |_| |   <\n"
            + "|____/ \\___/ \\___/|_|\\_\\\n";
    /** Horizontal line for separation. */
    private static final String LINE =
            "________________________________________________________________________________\n";

    private static final Scanner input = new Scanner(System.in);
    private static String command = "";
    private static ArrayList<Task> list = new ArrayList<Task>(100);
    private static int index = 0;
    public static void main(String[] args) {
        Path savePath = Paths.get("save", "book.txt");
        File bookSave = savePath.toFile();
        File saveDir = bookSave.getParentFile();
        if (!saveDir.exists()) {
            try {
                saveDir.mkdir();
            } catch (SecurityException exception) {
                System.out.println("Book had an issue with the save directory.");
            }
        }
        if (!bookSave.exists()) {
            try {
                bookSave.createNewFile();
            } catch (IOException exception) {
                System.out.print("Book had an issue loading the history book.\n");
            }
        }
        load(bookSave);
        System.out.print(LINE + "Good day! This is\n" + LOGO + "What may I help you with?\n"
                + LINE);
        command = input.nextLine();
        while (!command.equals("bye")) {
            try {
                parse(command);
            } catch (InvalidInputException exception){
                System.out.print(LINE + exception.getMessage() + "\n" + LINE);
            } catch (IncompleteInputException exception){
                System.out.print(LINE + exception.getMessage() + "\n" + LINE);
            } finally {
                save(bookSave, list);
                command = input.nextLine();
            }
        }
        System.out.print(LINE + "Bye! Pick up Book again soon!\n" + LINE);
    }

    private static void load(File bookSave) {
        try {
            Scanner fileReader = new Scanner(bookSave);
            while (fileReader.hasNextLine()) {
                String taskLine = fileReader.nextLine();
                String[] task = taskLine.split(";", 5);
                if (task[0].equals("T")) {
                    list.add(new ToDo(task[2]));
                } else if (task[0].equals("D")) {
                    list.add(new Deadline(task[2], task[3]));
                } else {
                    list.add(new Event(task[2], task[3], task[4]));
                }
                if (task[1].equals("true")) {
                    list.get(index).mark();
                }
                index++;
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Book had an issue finding the history book.");
        }
    }
    private static void save(File bookSave, ArrayList<Task> list) {
        try {
            FileWriter writeToFile = new FileWriter(bookSave);
            for (Task task : list) {
                writeToFile.write(task.saveString() + "\n");
            }
            writeToFile.close();
        } catch (IOException exception) {
            System.out.println("Book was unable to write to the history book.");
        }
    }
    private static void parse(String text) throws InvalidInputException, IncompleteInputException {
        String[] inputs = text.split(" ", 2);
        if (inputs[0].equals("list")) {
            System.out.print(LINE + "Here are the tasks stored in Book:\n");
            for (int i = 0; i < index; i++) {
                System.out.println((i + 1) + ".  " + list.get(i));
            }
            System.out.print(LINE);
        } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")
                || inputs[0].equals("delete")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("Without the index, Book cannot find the task" +
                        "you are looking for.");
            }
            int taskIndex = Integer.parseInt(inputs[1]) - 1;
            Task task = list.get(taskIndex);
            if (inputs[0].equals("mark")) {
                task.mark();
                System.out.print(LINE + "The following task has been marked as done:\n    "
                        + task + "\n" + LINE);
            } else if (inputs[0].equals("unmark")) {
                task.unmark();
                System.out.print(LINE + "The following task has been marked as not done:\n    "
                        + task + "\n" + LINE);
            } else {
                list.remove(taskIndex);
                index--;
                System.out.print(LINE + "Acknowledged, striking the following task off the list:\n    "
                        + task + "\n" + index + " task(s) remain on the list.\n" + LINE);
            }
        } else if (inputs[0].equals("todo")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The description of the todo is missing.");
            }
            int addedToDoIndex = index++;
            ToDo newToDo = new ToDo(inputs[1]);
            list.add(newToDo);
            System.out.print(LINE + "Understood, adding:\n    " + newToDo
                    + "\nThere are " + index + " task(s) in Book\n" + LINE);
        } else if (inputs[0].equals("deadline")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The deadline is missing some information.");
            }
            int addedDeadlineIndex = index++;
            String[] deadlineDetails = inputs[1].split("/by ", 2);
            if (deadlineDetails.length < 2) {
                throw new IncompleteInputException("The deadline is missing.");
            }
            Deadline newDeadLine = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            list.add(newDeadLine);
            System.out.print(LINE + "Understood, adding the deadline:\n    " + newDeadLine
                    + "\nThere are " + index + " task(s) in Book.\n" + LINE);
        } else if (inputs[0].equals("event")) {
            if (inputs.length < 2) {
                throw new IncompleteInputException("The event is missing some information.");
            }
            int addedEventIndex = index++;
            String[] eventDetails = inputs[1].split("/from |/to ", 3);
            if (eventDetails.length < 2) {
                throw new IncompleteInputException("The information regarding event duration is "
                        + "missing.");
            }
            Event newEvent = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
            list.add(newEvent);
            System.out.print(LINE + "Understood, adding the event:\n    " + newEvent
                    + "\nThere are " + index + " task(s) in Book.\n" + LINE);
        } else {
            throw new InvalidInputException("Sorry, this command is not in Book's dictionary.");
        }
    }
}
