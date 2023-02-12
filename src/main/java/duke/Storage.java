package duke;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import util.DukeException;

public class Storage {
    // create a file object for the current location
    public static void initSaveFile() {
        File dir = new File("src/main/data/");
        dir.mkdirs();
        // create a file object for the current location
        File file = new File(dir, "duke.txt");

        try {
            // create a new file with name specified
            // by the file object
            boolean value = file.createNewFile();
            if (value) {
                FileWriter output = new FileWriter("duke.txt");
                output.write("---------------LIST OF TASKS--------------------");
                output.append('\n');
                System.out.println("New Save File is created.");
            }
            else {
                System.out.println("Save file already exists.");
            }
        }
        catch(Exception e) {
            e.getStackTrace();
        }
    }

    public static void loadFile(TaskList list) throws IOException, DukeException {
        File file = new File(System.getProperty("user.dir") + "/data/duke.txt");
        File dir = new File(System.getProperty("user.dir") + "/data");

        // if directory has not been created, make directory
        if (! dir.exists() ) {
            dir.mkdir();
        }

        file.createNewFile();

        Scanner sc = new Scanner(file);  //takes file's content as input, scans through files and adds items to the list

        while (sc.hasNext()) {
            String[] currLine = sc.nextLine().split(" \\| ");
            if (currLine[0].equalsIgnoreCase("T")) {
                list.add(new Todo(strToBool(currLine[1]), currLine[2]));
            } else if (currLine[0].equalsIgnoreCase("D")) {
                list.add(new Deadline(strToBool(currLine[1]), currLine[2], currLine[3]));
            } else if (currLine[0].equalsIgnoreCase("E")) {
                list.add(new Event(strToBool(currLine[1]), currLine[2], currLine[3], currLine[4]));
            } else {
                throw new DukeException("Read Error");
            }
        }
    }

    public static boolean strToBool(String str) {
        if (str.equalsIgnoreCase("1")) {
            return true;
        } else {
            return false;
        }
    }

    public static void saveToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/duke.txt");
        ArrayList<Task> tasks = TaskList.getList();
        for (Task t: tasks) {
            fw.write(t.saveFormat() +  "\n");
        }
        fw.close();
    }


    public static void clearFile(String filename) throws IOException {
        FileOutputStream writer = new FileOutputStream(filename);
        writer.write(("").getBytes());
        writer.close();
    }
}
