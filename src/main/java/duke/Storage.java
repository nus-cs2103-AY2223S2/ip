package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// class Storage - handles the loading of data when duke is run and
// saving of data when duke is closed
public class Storage {
    private String filePath;

    public Storage(String s) {
        this.filePath = s;
    }

    public static TaskList loadFile() throws IOException, DukeException {
        File file = new File(System.getProperty("user.dir") + "/data/Duke.Duke.txt");
        File dir = new File(System.getProperty("user.dir") + "/data");

        // if directory has not been created, make directory
        if (! dir.exists() ) {
            System.out.println("Directory created");
            dir.mkdir();
        }

        file.createNewFile();

        Scanner sc = new Scanner(file);  //takes file's content as input, scans through files and adds items to the list
        TaskList list = new TaskList();
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

        return list;
    }
    public static boolean strToBool(String str) {
        if (str == "1") {
            return true;
        } else {
            return false;
        }
    }
    public static void saveToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/data/Duke.Duke.txt");
        for (int i = 0; i < list.size(); i++) {
            fw.write(list.get(i).addDivider() +  "\n");
        }
        fw.close();
    }
}

