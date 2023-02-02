package storage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import parser.Parser;
import task.Task;

public class Storage {
    private String filepath;
    // filepath should be "data/duke.txt"

    public Storage(String filePath) {
        this.filepath = filePath;

        // Checks if the file exists, else creates it
        File createFolder = new File(filePath);
        createFolder.mkdirs();

        try {
            File mySaveFile = new File(filePath);
            //../../../
            if (!mySaveFile.exists()) {
                mySaveFile.createNewFile();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error has occurred");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listToStore = new ArrayList<Task>();

        try {
            File mySaveFile = new File("data/duke.txt");
            Scanner s = new Scanner(mySaveFile);
            while (s.hasNext()) {

                Task task;
                String nextLine = s.nextLine();
                if (nextLine.charAt(0) == '1') {
                    task = Parser.parseFileReader(nextLine.substring(2), true);
                } else {
                    task = Parser.parseFileReader(nextLine.substring(2), false);
                }
                listToStore.add(task);
            }
            s.close();
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return listToStore;
    }

    public void save(ArrayList<Task> listToStore) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

        while (!listToStore.isEmpty()) {
            try {

                FileWriter fw = new FileWriter("data/duke.txt", true);
                fw.write(listToStore.get(0).getCommand());
                listToStore.remove(0);
                fw.write(System.lineSeparator());
                fw.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("    An Error has occured");
                break;
            }
        }
    }
}
