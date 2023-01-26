package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Handles the memory of duke list, load and save of task list memory
 * @author oliverloo
 * @version 1.0
 *
 */
public class Storage {

    /**
     * Creates a directory path for the Tasklist to be loaded and read
     * @param list
     * @return void
     */
    public static void loadFile(Tasklist list) throws IOException, DukeException {
        File file = new File(System.getProperty("user.dir") + "/data/Duke.Duke.txt");
        File dir = new File(System.getProperty("user.dir") + "/data");

        // if directory has not been created, make directory
        if (! dir.exists() ) {
            System.out.println("Directory created");
            dir.mkdir();
        }

        file.createNewFile();

        Scanner sc = new Scanner(file);  //takes file's content as input, scans through files and adds items to the list

        while (sc.hasNext()) {
            String[] currLine = sc.nextLine().split(" \\| ");
            if (currLine[0].equalsIgnoreCase("T")) {
                list.add(new ToDo(strToBool(currLine[1]), currLine[2]));
            } else if (currLine[0].equalsIgnoreCase("D")) {
                list.add(new Deadline(strToBool(currLine[1]), currLine[2], currLine[3]));
            } else if (currLine[0].equalsIgnoreCase("E")) {
                list.add(new Event(strToBool(currLine[1]), currLine[2], currLine[3], currLine[4]));
            } else {
                throw new DukeException("Read Error");
            }
        }
    }

    /**
     * Takes in a string literal "1" or "0" converts it to a boolean
     * @param str
     * @return boolean value, true if 1, false if 0
     */
    public static boolean strToBool(String str) {
        if (str == "1") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * takes in a Tasklist and writes it into memory
     * @param list
     * @throws IOException
     */
    public static void saveToFile(Tasklist list) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/Duke.Duke.txt");
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i).addDivider() +  "\n");
        }
        fw.close();
    }
}

