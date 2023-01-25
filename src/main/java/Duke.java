import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String line = "    ______________________________________________________________";
    public static final String PATH = "./duke.txt";
    private static List list = new List();

    public static void main(String[] args) {
        startDuke();
    }

    public static void startDuke() {
        greeting();
        try {
            openSavedList();
        } catch (FileNotFoundException e) {
            File newFile = new File("duke.txt");
            try {
                boolean createdNewFile = newFile.createNewFile();
            } catch (IOException e2) {
                System.out.println("Could not create new file.");
            }
        }
        Scanner sc = new Scanner(System.in);
        boolean active = true;
        while (active) {
            String command = sc.next();
            System.out.println(line);
            boolean needUpdate = false;
            switch (command) {
            case "bye":
                end();
                active = false;
                break;
            case "list":
                list.print();
                break;
            case "mark":
                int markIdx = sc.nextInt();
                if (markIdx >= list.size()) {
                    DukeException noTaskIdx = new DukeException(
                        "The task with the given index does not exist.");
                    System.out.println(noTaskIdx);
                    break;
                }
                list.get(markIdx).markDone(true);
                needUpdate = true;
                break;
            case "unmark":
                int unmarkIdx = sc.nextInt();
                if (unmarkIdx >= list.size()) {
                    DukeException noTaskIdx = new DukeException(
                        "The task with the given index does not exist.");
                    System.out.println(noTaskIdx);
                    break;
                }
                list.get(unmarkIdx).unmark(true);
                needUpdate = true;
                break;
            case "todo":
                String todoTask = sc.nextLine();
                todoTask = removeSpaces(todoTask);
                try {
                    list.add(todoTask, true);
                } catch (DukeException e) {
                    System.out.println(e);
                }
                needUpdate = true;
                break;
            case "deadline":
                String temp = sc.nextLine();
                if (!temp.matches("^.+(\\s)/by(\\s).+$")) {
                    DukeException msgMismatch = new DukeException(
                            "Please use the correct format to add a deadline.");
                    System.out.println(msgMismatch);
                    break;
                }
                String[] dlTask = temp.split("/by");
                dlTask[0] = removeSpaces(dlTask[0]);
                dlTask[1] = removeSpaces(dlTask[1]);
                try {
                    list.add(dlTask[0], dlTask[1], true);
                } catch (DukeException e) {
                    System.out.println(e);
                    break;
                }
                needUpdate = true;
                break;
            case "event":
                String temp2 = sc.nextLine();
                if (!temp2.matches("^.+(\\s)/from(\\s).+(\\s)/to.*$")) {
                    DukeException msgMismatch = new DukeException(
                        "Please use the correct format to add an event.");
                    System.out.println(msgMismatch);
                    break;
                }
                String[] eventTask = temp2.split("/from|/to");
                eventTask[0] = removeSpaces(eventTask[0]);
                eventTask[1] = removeSpaces(eventTask[1]);
                eventTask[2] = removeSpaces(eventTask[2]);
                try {
                    list.add(eventTask[0], eventTask[1], eventTask[2], true);
                } catch (DukeException e) {
                    System.out.println(e);
                    break;
                }
                needUpdate = true;
                break;
            case "delete":
                int deleteIdx = sc.nextInt();
                list.remove(deleteIdx);
                needUpdate = true;
                break;
            default:
                DukeException unknowCmd = new DukeException("I'm sorry, but I don't know what that means :-(");
                System.out.println(unknowCmd);
            }
            if (needUpdate) {
                try {
                    writeToFile(list.toTxtString());
                } catch (IOException e) {
                    System.out.println("Error during saving");
                }
            }
            System.out.println(line + "\n");
        }
        sc.close();
    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void openSavedList() throws FileNotFoundException {
        File f = new File(PATH);
        Scanner sc = new Scanner(f);
        ArrayList<String> rawData = new ArrayList<>();
        while (sc.hasNext()) {
            rawData.add(sc.nextLine());
        }
        if (rawData.isEmpty()) {
            System.out.println("There is no task in the list!\n");
            sc.close();
            return;
        }
        for (int i = 0; i < rawData.size(); i++) {
            String[] taskData = rawData.get(i).split("\\|");
            switch(taskData[0]) {
            case "T":
                try {
                    list.add(taskData[2], false);
                    if (taskData[1].equals("1")) {
                        list.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "D":
                try {
                    list.add(taskData[2], taskData[3], false);
                    if (taskData[1].equals("1")) {
                        list.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            case "E":
                String[] duration = taskData[3].split("-");
                try {
                    list.add(taskData[2], duration[0], duration[1], false);
                    if (taskData[1].equals("1")) {
                        list.get(i + 1).markDone(false);
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
                break;
            }
        }
        list.print();
        System.out.println(line);
        sc.close();
    }

    public static String removeSpaces(String input) {
        String str = input.replaceFirst(" ", "");
        str = str.substring(0, str.length() - 1);
        return str;
    }

    public static void end() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void writeToFile(String dukeData) throws IOException {
        FileWriter fw = new FileWriter(PATH);
        fw.write(dukeData);
        fw.close();
    }
}
