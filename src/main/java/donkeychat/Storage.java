package donkeychat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    /**
     * Overwrites existing save file with provided TaskList.
     * @param taskList TaskList to be serialized.
     */
    public void updateSave(TaskList taskList) {
        String toSave = "";
        for (int i = 0; i < taskList.getSize(); i++) {

            toSave += taskList.getAtIndex(i).serialize() + "\n";
        }
        try {
            FileWriter writer = new FileWriter("donkey.txt");
            writer.write(toSave);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads existing save file into provided TaskList.
     * @param loadTo TaskList to be deserialized into.
     */
    public void loadSave(TaskList loadTo) {
        try {
            File save = new File("donkey.txt");
            if (save.exists()) {
                Scanner scanner = new Scanner(save);
                while (scanner.hasNextLine()) {
                    loadTo.addTask(deserializeTask(scanner.nextLine()));
                }
            } else {
                createSave();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Creates new empty save file if none exists.
     */
    public void createSave() {
        try {
            File save = new File("donkey.txt");
            if (save.createNewFile()) {
                System.out.println("Save created: " + save.getName());
            } else {
                System.out.println("Save already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Task deserializeTask(String data) {
        String[] splitData = data.split(" \\| ");

        switch (splitData[0]) {
            case "T":
                return new ToDo(splitData[2], splitData[1].equals("1"));
            case "E":
                return new Event(splitData[2], splitData[1].equals("1"), splitData[3], splitData[4]);
            case "D":
                return new Deadline(splitData[2], splitData[1].equals("1"), splitData[3]);
            default:
                System.out.println("Attempting to deserialize wrongly formatted task: ");
                System.out.println(data);
                return null;
        }
    }
}
