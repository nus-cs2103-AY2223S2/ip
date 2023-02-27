import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

// load tasks from file, save changes to file
public class Storage {
    private File hardDisk;
    private ArrayList<Task> storage;

    public Storage(String filepath) {
        this.hardDisk = new File("data.txt");
        this.storage = new ArrayList<>();
    }


    public String load() {
        // Level-7 feature: load existing data.txt file if it exists, else create new file
        // STORAGE!!
        try {
            boolean created = hardDisk.createNewFile();
            // load existing data.txt file
            if (!created) {
                Scanner scanner = new Scanner(hardDisk);
                if (!scanner.hasNextLine()) {
                    System.out.println("No Existing Tasks my brother!");
                } else {
                    System.out.println("Existing Tasks my brother!");
                }
                while (scanner.hasNextLine()) {
                    String input = scanner.nextLine();
                    boolean isDone = Character.isLetter(input.charAt(8));
                    Task x;
                    if (input.contains("From:")) {
                        int endDescription = input.indexOf("From:") - 1;
                        int endStart = input.indexOf("To:") - 1;
                        x = new Event(input.substring(11, endDescription), input.substring(endDescription + 7, endStart), input.substring(endStart + 5));
                    } else if (input.contains("By:")) {
                        int endDescription = input.indexOf("By:") - 1;
                        x = new Deadline(input.substring(11, endDescription), input.substring(endDescription + 5));
                    } else {
                        x = new Todo(input.substring(11));
                    }
                    if (isDone) {
                        x.markAsDone();
                    }
                    storage.add(x);
                    // Printout existing storage database
                    int i = 0;
                    for (Task task : storage) {
                        System.out.println((i + 1) + ". " + task.toString());
                        i++;
                    }
                    scanner.close();
                }
            } else {
                System.out.println("New file created: data.txt");
            }
        } catch (IOException e) {
        System.out.println("An error occurred while creating the new file: data.txt");
        }
    }
}

