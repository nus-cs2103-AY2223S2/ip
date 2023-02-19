package cluck;

import java.io.File;
import java.util.Scanner;

import cluck.messages.Messages;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;
import cluck.taskList.TaskList;
import cluck.ui.Ui;


public class Cluck {
    private static final String MAKE_DEADLINE = "deadline";
    private static final String MAKE_TODO = "todo";
    private static final String MAKE_EVENT = "event";
    private static final String DUE_DATE_FLAG = "/by ";
    private static final String EVENT_START_FLAG = "/from ";
    private static final String EVENT_END_FLAG = "/to ";

    private static final String SAVE_DIR_STRING = "SavedData";
    private static final String SAVE_FILE_STRING = "CluckSave.txt";

    private TaskList taskList;
    private Ui userInterface;

    /**
     * @param strNum String of interest.
     * @return returns true if strNum is a number in string format, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Cluck class contains tasklist, User interface and parser needed.
     */
    public Cluck() {
        File savedFile = new File(SAVE_FILE_STRING);
        this.userInterface = new Ui();
        this.taskList = new TaskList().readSave(savedFile);
    }

    /**
     * Starts cluck instance such that it loads saved data and
     * begins taking in user commands.
     */
    public void run() {
        this.userInterface.greetUser();

    }

    /**
     * Saves taskList and stop cluck instance
     */
    public void stop() {

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Messages.MESSAGE_LOGO);
        System.out.println(Messages.MESSAGE_WELCOME);

        boolean loop = true;
        TaskList toDoList = TaskList.readSave();
        Scanner sc = new Scanner(System.in);

        while (loop) {
            String input = sc.nextLine();
            System.out.println(input);
            String[] words = input.split(" ");

            switch (words[0]) {
            case "bye":
                writeSave(toDoList);
                System.out.println(Messages.MESSAGE_GOODBYE);
                loop = false;
                break;

            case "list":
                System.out.println(Messages.MESSAGE_LIST_DISPLAY);
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + toDoList.get(i).toString());
                }
                break;

            case "mark":
                if (words.length == 1) {
                    System.out.println(Messages.MESSAGE_INDEX_MISSING);
                } else if (isNumeric(words[1])) {
                    Integer itemNumber = Integer.parseInt(words[1]);
                    if (itemNumber > toDoList.size() || itemNumber <= 0) {
                        System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                    } else {
                        toDoList.get(itemNumber - 1).mark();
                        System.out.println(Messages.MESSAGE_MARK_SUCCESSFUL
                                + toDoList.get(itemNumber - 1).toString());
                    }
                } else {
                    System.out.println(Messages.MESSAGE_INDEX_INVALID);
                }
                break;

            case "unmark":
                if (words.length == 1) {
                    System.out.println(Messages.MESSAGE_INDEX_MISSING);
                } else if (isNumeric(words[1])) {
                    Integer itemNumber = Integer.parseInt(words[1]);
                    if (itemNumber > toDoList.size() || itemNumber <= 0) {
                        System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                    } else {
                        toDoList.get(itemNumber - 1).unmark();
                        System.out.println(Messages.MESSAGE_UNMARK_SUCCESSFUL
                                + toDoList.get(itemNumber - 1).toString());
                    }
                } else {
                    System.out.println(Messages.MESSAGE_INDEX_INVALID);
                }
                break;

            case MAKE_TODO:
                Task newTodo = new ToDo(input.substring(5));
                toDoList.add(newTodo);
                System.out.println(Messages.MESSAGE_TODO_ADDED + "\n" + newTodo.toString());
                System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                break;

            case MAKE_DEADLINE:
                String body = input.substring(9);
                if (body.contains(DUE_DATE_FLAG)) {
                    String[] fields = body.split(" " + DUE_DATE_FLAG);
                    String description = fields[0];
                    String dueDate = fields[1];
                    Task currDeadline = new Deadline(description, dueDate);
                    toDoList.add(currDeadline);
                    System.out.println(Messages.MESSAGE_DEADLINE_ADDED + currDeadline.toString());
                    System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                    break;
                }
                System.out.println("    You're missing the '/by' flag, bucko!");
                break;

            case MAKE_EVENT:
                String substring = input.substring(6);
                if (substring.contains(EVENT_START_FLAG) && substring.contains(EVENT_END_FLAG)) {
                    String[] fields = substring.split("\\s/\\w{2,4}\\s");
                    Task currEvent = new Event(fields[0], fields[1], fields[2]);
                    toDoList.add(currEvent);
                    System.out.println(Messages.MESSAGE_EVENT_ADDED + currEvent.toString());
                    System.out.println(String.format(Messages.MESSAGE_LIST_COUNT, toDoList.size()));
                    break;
                }
                System.out.println("    You're missing the either the '/from' or '/to' flag, or both! Buhcock!");
                break;

            case "delete":
                if (words.length == 1) {
                    System.out.println(Messages.MESSAGE_INDEX_MISSING);
                } else if (isNumeric(words[1])) {
                    Integer itemNumber = Integer.parseInt(words[1]);
                    if (itemNumber > toDoList.size() || itemNumber <= 0) {
                        System.out.println(Messages.MESSAGE_INDEX_OUT_OF_BOUNDS);
                    } else {
                        System.out.println(Messages.MESSAGE_DELETE_SUCCESSFUL + "\n"
                                + toDoList.get(itemNumber - 1).toString());
                    }
                } else {
                    System.out.println(Messages.MESSAGE_INDEX_INVALID);
                }
                break;

            default:
                System.out.println(Messages.MESSAGE_INVALID_COMMAND);
            }
        }
    }
}
