import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;



public class Duke {

    private final ArrayList<Task> storage = new ArrayList<>();

    private final Scanner sc = new Scanner(System.in);

    public void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        separator();
    }

    public void exit() {
        separator();
        System.out.println("\tBye. Hope to see you again soon!");
        separator();
        System.exit(0);
    }


    public void list() {
        separator();
        for (int i=0; i < this.storage.size(); i++) {
            int currentNumber = i+1;
            Task task = storage.get(i);

            System.out.println("\t" + currentNumber + "." + task);
        }
        separator();
    }

    public void delete(int index) {
        separator();
        Task task = storage.get(index - 1);
        storage.remove(index - 1);
        System.out.println("Noted. I've removed this task:" + "\n\t" + task);
        System.out.println("Now you have " + storage.size() +" tasks in the list.");
        separator();
    }

    public void addToDo(String taskDetails) {
        ToDo task = new ToDo(taskDetails);
        storage.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        separator();
    }

    public void addDeadline(String description, String by ) {
        Deadline task = new Deadline(description, by);
        storage.add(task);
        System.out.println("\t" + task);
        System.out.println("Now you have " + storage.size() + " tasks in the list.");
        separator();
    }

    public void addEvent(String description, String from, String to) {
            Event task = new Event(description, from, to);
            storage.add(task);
            System.out.println("\t" + task);
            System.out.println("Now you have " + storage.size() + " tasks in the list.");
            separator();
    }

    public void setTaskStatus(int index, boolean isDone) {
        Task task = storage.get(index - 1);
        task.setDone(isDone);
        separator();
        System.out.println("\tOk, I have marked this task as " + (isDone ? "done" : "not done yet")  +  ":\n\t\t"
                + task);
        separator();
    }

    public static void separator() {
        System.out.println("---------------------------------------------------------------");
    }

    public void fileHandler(File f) throws DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ");
                switch (task[0]) {
                    case "T":
                        addToDo(task[2]);
                        break;
                    case "D":
                        addDeadline(task[2], task[3]);
                        break;
                    case "E":
                        addEvent(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
                    setTaskStatus(storage.size(), true);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Could not find file.");
        }
    }

    public void writeFile() throws DukeException{
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            StringBuilder str = new StringBuilder();
            for (Task task : storage) {
                str.append(task.toFileString());
            }
            fw.write(String.valueOf(str));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file path");
        }
    }



    public static void main(String[] args) throws DukeException {
        Duke dukeList = new Duke();
        dukeList.greet();

//        File folderFile = new File("/data");
//        folderFile.mkdirs();
        File f = new File("data/duke.txt");
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to open and create file.");
        }

        dukeList.fileHandler(f);

        while (dukeList.sc.hasNextLine()) {
            try {

                String str = dukeList.sc.nextLine();
                String[] arr = str.split(" ", 2);
                boolean details = arr.length != 1;
                switch (arr[0]) {
                    case "bye":
                        dukeList.exit();
                        break;
                    case "list":
                        dukeList.list();
                        break;
                    case "mark":
                        if (!details) {
                            throw new DukeException("Please include the task index to mark");
                        } else {
                            dukeList.setTaskStatus(Integer.parseInt(arr[1]), true);
                            break;
                        }
                    case "unmark":
                        if (!details) {
                            throw new DukeException("Please include the task index to unmark.");
                        }
                        dukeList.setTaskStatus(Integer.parseInt(arr[1]), false);
                        break;
                    case "delete":
                        if (!details) {
                            throw new DukeException("Please include the task index to delete.");
                        }
                        dukeList.delete(Integer.parseInt(arr[1]));
                        break;
                    case "todo":
                        if (!details) {
                            throw new DukeException("Please include the todo details.");
                        }
                        dukeList.addToDo(arr[1]);
                        break;
                    case "deadline":
                        if (!details) {
                            throw new DukeException("Please include the deadline details.");
                        }
                        String[] descriptionBy = arr[1].split("/by", 2);
                            if (descriptionBy.length == 1) {
                                throw new DukeException("Please insert deadline date after /by");
                            }

                        dukeList.addDeadline(descriptionBy[0], descriptionBy[1]);
                        break;
                    case "event":
                        if (!details) {
                            throw new DukeException("Please include the event details.");
                        }
                        String[] descriptionOthers = arr[1].split("/from", 2);
                        if (descriptionOthers.length == 1) {
                            throw new DukeException("Please insert the date the event takes place from, after /from ");
                        }
                        String[] fromTo = descriptionOthers[1].split("/to", 2);
                        if (fromTo.length == 1) {
                            throw new DukeException("Please insert the date the event takes place until, after /to ");
                        }
                        dukeList.addEvent(descriptionOthers[0], fromTo[0], fromTo[1]);
                        break;
                    default:
                        throw new DukeException("Sorry, I don't know what that means.");
                }
            } catch (DukeException e) {
                separator();
                System.out.println("\t" + e);
                separator();
            }

            // handle changes to arraylist
            dukeList.writeFile();

        }
    }
}
