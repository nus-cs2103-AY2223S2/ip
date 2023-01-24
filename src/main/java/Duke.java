import tasks.*;
import java.util.*;
import java.io.*;

public class Duke {
    private static ArrayList<Task> todoList;
    public enum Command {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE}
    // static boolean anyChanges;
    static String separator = "\u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾ \u200E✽ ✾";
    static String invalidIndexMessage = "⚠ oops...there's no task with this number\ntry asking for 'list' to check task numbers\n" + separator;
    static String forgottenArgumentMessage = "⚠ oops...seems like you forgot part of a command, please try again\n" + separator;
    static String unknownCommandMessage = "⚠ oops...we don't know what that means, please try again\n" + separator;
    static String invalidFilePathMessage = "⚠ oops...we can't find where the file is\n" + separator;


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        // anyChanges = false; // determines if file should be overwritten
        todoList = new ArrayList<>();

        System.out.println("｡ﾟﾟ･｡･ﾟﾟ｡\n" + "。 welcome to tigerlily to-do\n" + "　ﾟ･｡･ﾟ\n" + "✎ . . . . add your tasks here");

        while(s.hasNext()) {
            try {
                String input = s.nextLine();
                Command command = Command.valueOf(input.split(" ")[0].toUpperCase());

                if(command.equals(Command.BYE)) {
                    updateFile();
                    bye();
                    break;
                } else {
                    handleCommand(command, input);
                }

            } catch(DukeExceptions e) {
                System.out.println(e);
            }
        }
        s.close();
    }

    private static void message(String message) {
        System.out.println(message + separator);
    }

    private static void addTask(Task task) {
        todoList.add(task);
        message("\nperf, your task has been added:\n" + task.toString() + "\nthe list now has " + todoList.size() +
                " task(s) in total\n");
    }

    private static void updateFile() throws DukeExceptions {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < todoList.size(); i++) {
                Task thisTask = todoList.get(i);
                String s = (i + 1) + "." + thisTask.toString() + System.lineSeparator();
                sb.append(s);
            }
            fw.write(sb.toString());
            fw.close();

        } catch(IOException e) {
            throw new DukeExceptions(invalidFilePathMessage);
        }
    }

    private static void handleCommand(Command command, String input) throws DukeExceptions { // work on this method to handle switch cases
        switch (command) {
            case LIST:
                list();
                break;
            case TODO:
                todo(input);
                break;
            case DEADLINE:
                deadline(input);
                break;
            case EVENT:
                event(input);
                break;
            case MARK:
                mark(input);
                break;
            case UNMARK:
                unmark(input);
                break;
            case DELETE:
                delete(input);
                break;
            default:
                throw new DukeExceptions(unknownCommandMessage);
        }
    }

    private static void bye() {
        System.out.println("(\\\\ (\\\\ \n" + "(„• ֊ •„)\n" + "━━O━O━━━━━━━━━━━━━━━\n" +
                "bye, see you again soon!\n" + "━━━━━━━━━━━━━━━━━━━━\n");
    }

    private static void list() {
        System.out.println("\nhere's your list:");
        for (int i = 0; i < todoList.size(); i++) {
            Task thisTask = todoList.get(i);
            System.out.println((i + 1) + "." + thisTask.toString());
        }
        System.out.println(separator);
    }

    private static void todo(String input) throws DukeExceptions {
        try {
            String description = input.substring(5);
            ToDo newToDo = new ToDo(description);
            addTask(newToDo);
        } catch(StringIndexOutOfBoundsException e) {
            throw new DukeExceptions(forgottenArgumentMessage);
        }
    }

    private static void deadline(String input) { // add in exception handling
        String[] s1 = input.substring(9).split("/by");
        Deadline newDeadline = new Deadline(s1[0].trim(), s1[1].trim());
        addTask(newDeadline);
    }

    private static void event(String input) {// add in exception handling
        String[] s1 = input.substring(6).split("/from");
        String[] s2 = s1[1].split("/to");
        Event newEvent = new Event(s1[0].stripTrailing(), s2[0].trim(), s2[1].trim());
        addTask(newEvent);
    }

    private static void mark(String input) throws DukeExceptions {
        if(input.length() < 6) {
            throw new DukeExceptions(forgottenArgumentMessage);
        } else {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= todoList.size()) {
                throw new DukeExceptions(invalidIndexMessage);
            }
            Task thisTask = todoList.get(index);
            thisTask.markDone();
            message("\nwell done! you've completed this task: " + thisTask + "\n");
        }
    }

    private static void unmark(String input) throws DukeExceptions {
        if(input.length() < 8) {
            throw new DukeExceptions(forgottenArgumentMessage);
        } else {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= todoList.size()) {
                throw new DukeExceptions(invalidIndexMessage);
            }
            Task thisTask = todoList.get(index);
            thisTask.unmarkDone();
            message("\noops...this task is now marked as not done yet: " + thisTask + "\n");
        }
    }

    private static void delete(String input) throws DukeExceptions {
        int index = Integer.parseInt(input.substring(7)) - 1;
        if (index >= todoList.size()) {
            throw new DukeExceptions(invalidIndexMessage);
        } else {
            Task thisTask = todoList.get(index);
            todoList.remove(index);
            message("\nokay, this task has been removed: " + thisTask + "\nthe list now has " + todoList.size() + " task(s) left\n");
        }
    }
}