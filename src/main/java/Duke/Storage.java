package Duke;

import Duke.Exception.InvalidCommandException;
import Duke.Exception.NoDescriptionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static File dataDir = new File(System.getProperty("user.dir")
            + System.getProperty("file.separator") + "lists");
    public static File fileAddress = new File(System.getProperty("user.dir")
            + System.getProperty("file.separator") + "lists" + System.getProperty("file.separator") + "taskList.txt");

    public static void loadData() { //initialise in the beginning
        try {
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            if (!fileAddress.exists()) {
                fileAddress.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the universe");
        }

        try {
            Scanner sf = new Scanner(fileAddress);
            while (sf.hasNext()) {
                TaskList.add_to_list(sf.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Something is wrong with the universe");
        } catch (InvalidCommandException e) {
            System.out.println("Something is wrong with the universe");
        } catch (NoDescriptionException e) {
            System.out.println("Something is wrong with the universe");
        }
    }


    public static void storeData() {
        try {
            FileWriter fb = new FileWriter(fileAddress);
            fb.write("");
            fb.close();

            FileWriter fw = new FileWriter(fileAddress, true);

            for (int i = 0; i < TaskList.list.size(); i++) {
                fw.write(TaskList.list.get(i).task_name + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println("Something is wrong with the universe");
        }

    }


}
