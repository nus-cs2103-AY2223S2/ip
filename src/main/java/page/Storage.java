package page;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import page.quest.Deadline;
import page.quest.Event;
import page.quest.Quest;
import page.quest.Todo;

/**
 * Represents a storage object that handles saving and loading of data.
 */
public class Storage {
    /** File where the save data is stored. */
    private File storageFile;

    /**
     * Constructs a Storage object that saves and loads data to the given file path.
     *
     * @param filePath The file path where data is saved to and loaded from.
     */
    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

    /**
     * Returns the stored list of quests.
     *
     * @return The stored list of quests.
     * @throws PageException If the saved data is incorrectly formatted.
     */
    public ArrayList<Quest> loadData() throws PageException {
        ArrayList<Quest> loadedQuests = new ArrayList<>();
        try {
            Scanner s = new Scanner(storageFile);
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitLine = line.split(" ", 4);
                String questType = splitLine[1];
                String questCompletion = splitLine[2];
                String restOfLine = splitLine[3];
                Quest q;
                if (questType.equals("[T]")) {
                    q = new Todo(restOfLine);
                } else if (questType.equals("[D]")) {
                    String[] splitByBy = restOfLine.split(" by: ", 2);
                    q = new Deadline(splitByBy[0], splitByBy[1]);
                } else if (questType.equals("[E]")) {
                    String[] splitByFromTo = restOfLine.split(" from: | to: ", 3);
                    q = new Event(splitByFromTo[0], splitByFromTo[1], splitByFromTo[2]);
                } else {
                    System.out.println("oops, something wrong with the Quest Log :(");
                    break;
                }

                if (questCompletion.equals("[X]")) {
                    q.markComplete();
                }

                loadedQuests.add(q);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Starting a new Quest Log.\n");
        }

        return loadedQuests;
    }

    /**
     * Saves the given Quest Log into storage.
     *
     * @param questLog The Quest Log to be saved.
     */
    public void saveData(QuestLog questLog) throws PageException {
        try {
            if (storageFile.getParentFile() != null) {
                storageFile.getParentFile().mkdirs();
            }
            storageFile.createNewFile();
            FileWriter fw = new FileWriter(storageFile, false);
            fw.write(questLog.toString());
            fw.close();
        } catch (IOException e) {
            throw new PageException("Sorry, the Quest Log could not be saved!");
        }
    }
}
