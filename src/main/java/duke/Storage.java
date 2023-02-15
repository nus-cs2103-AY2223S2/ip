package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private PrintWriter storeInFile;
    private boolean filePresent = false;
    private File fileCreation;

    /**
     * Constructor to create a directory or file to store the tasklist locally on the user's computer.
     * @param filePath location of where the file should be saved
     * @throws IOException when input or output cannot be interpreted and when the program do not have access to create a new file.
     */
    public Storage(String filePath) throws IOException {
        try {
            this.filePath = filePath;
            String filePath2 = filePath;
            while (filePath2.contains("/")) {
                int slashPos = filePath2.indexOf("/");
                File dirCreation = new File(filePath2.substring(0, slashPos));
                if (dirCreation.mkdir()) {
                    System.out.println("Directory has been created successfully");
                } else {
                    System.out.println("Directory already exist");
                }
                filePath2 = filePath2.substring(slashPos + 1);
            }
            fileCreation = new File(filePath);
            if (fileCreation.createNewFile()) {
                System.out.println("File created: " + fileCreation.getName());
            } else {
                filePresent = true;
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            System.out.println("Folder do not exist.");
            e.printStackTrace();
        }
    }

    /**
     * Saves the current tasklist locally on the user's computer.
     * @param tasklist the current working tasklist that has been created by the user.
     * @throws FileNotFoundException if the location to store the tasklist in is not available.
     */
    public void saveToFile(Tasklist tasklist) throws FileNotFoundException {
        assert (fileCreation != null);
        storeInFile = new PrintWriter(fileCreation);
        for (int i = 0; i < tasklist.getList().size(); i++) {
            storeInFile.println(tasklist.getList().get(i).getFullDescription());
            if (tasklist.getList().get(i).isDoneStatus()) {
                storeInFile.println("mark " + (i + 1));
            }
        }
    }

    /**
     * Closes the directory or file once saving has been complete.
     */
    public void storageClose() {
        assert (storeInFile != null);
        storeInFile.close();
    }

    /**
     * Retrieves the saved tasklist that was previously created by the user.
     * @return the arraylist containing all the previously saved tasks.
     * @throws DukeException when the user input is incorrect or cannot be understood.
     * @throws FileNotFoundException if the file where the old tasklist was stored at is not available.
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        if (filePresent) {
            ArrayList<Task> storage = new ArrayList<Task>();
            File fileScanning = new File(filePath);
            Scanner sc = new Scanner(fileScanning);
            while (sc.hasNextLine()) {
                String content = sc.nextLine();
                String[] type = content.split(" ", 2);
                switch (type[0]) {
                case "mark":
                    storage.get(Integer.parseInt(type[1]) - 1).markAsDone();
                    break;
                case "todo":
                    Todo todoTask = new Todo(content.substring(5), content);
                    storage.add(todoTask);
                    break;
                case "deadline":
                    int position = content.indexOf("/by ");
                    Deadline deadlineTask = new Deadline(content.substring(9, position), content, content.substring(position + 4));
                    storage.add(deadlineTask);
                    break;
                case "event":
                    int position1 = content.indexOf("/from ");
                    int position2 = content.indexOf("/to ");
                    Event eventTask = new Event(content.substring(6, position1), content, content.substring(position1 + 6, position2), content.substring(position2 + 4));
                    storage.add(eventTask);
                    break;
                }
            }
            return storage;
        }
        return new ArrayList<Task>();
    }
}
