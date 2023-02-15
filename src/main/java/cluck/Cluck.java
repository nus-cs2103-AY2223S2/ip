package cluck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import cluck.messages.Messages;
import cluck.tasks.Deadline;
import cluck.tasks.Event;
import cluck.tasks.Task;
import cluck.tasks.ToDo;


public class Cluck {
    private static final String MAKE_DEADLINE = "deadline";
    private static final String MAKE_TODO = "todo";
    private static final String MAKE_EVENT = "event";
    private static final String DUE_DATE_FLAG = "/by ";
    private static final String EVENT_START_FLAG = "/from ";
    private static final String EVENT_END_FLAG = "/to ";

    private static final String SAVE_DIR_STRING = "SavedData";
    private static final String SAVE_FILE_STRING = "CluckSave.txt";

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

    private static ArrayList<Task> readSave() {
        try {
            if (new File(SAVE_DIR_STRING).mkdirs()) { // returns true if directory was made as it did not exist
                return new ArrayList<Task>(); // no save file can possibly exist if directory does not exist
            }
            File saveFile = new File(SAVE_FILE_STRING);
            if (saveFile.exists()) {
                try {
                    ArrayList<Task> savedTasks = new ArrayList<>();
                    Scanner saveFileScanner = new Scanner(saveFile);

                    while (saveFileScanner.hasNextLine()) {
                        populateTaskListFromSave(savedTasks, saveFileScanner.nextLine());
                    }
                    return savedTasks;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return new ArrayList<Task>();
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private static void populateTaskListFromSave(ArrayList<Task> taskList, String taskData) {
        Task task = buildTask(taskData.split("\\|"));
        if (task == null) {
            return;
        }
        taskList.add(task);
    }

    private static Task buildTask(String[] savedTaskFields) {
        System.out.println(Arrays.toString(savedTaskFields));
        boolean isMarked;
        if (savedTaskFields[1].equals("1")) {
            isMarked = true;
        } else if (savedTaskFields[1].equals("0")) {
            isMarked = false;
        } else {
            System.out.println("Corrupted data!");
            return null;
        }

        switch (savedTaskFields[0]) {
        case "E":
            return new Event(isMarked, savedTaskFields[2], savedTaskFields[3], savedTaskFields[4]);

        case "D":
            return new Deadline(isMarked, savedTaskFields[2], savedTaskFields[3]);

        case "T":
            return new ToDo(isMarked, savedTaskFields[2]);

        default:
            return null;
        }
    }

    /**
     * Saves the current list of tasks into 'CluckSave.txt'.
     * This will overwrite previous saves.
     * There should be no missing directory error since readSave()
     * will create the save directory if it does not exist.
     *
     * @param toDolist is the list of tasks in the currently running Cluck instance
     */
    private static void writeSave(ArrayList<Task> toDolist) {
        try {
            File saveFile = new File(SAVE_FILE_STRING);
            FileWriter writer = new FileWriter(saveFile);
            for (Task t : toDolist) {
                writer.write(t.makeSaveFormat());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Buh oh! An error occurred!!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Messages.MESSAGE_LOGO);
        System.out.println(Messages.MESSAGE_WELCOME);

        boolean loop = true;
        ArrayList<Task> toDoList = readSave();
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
