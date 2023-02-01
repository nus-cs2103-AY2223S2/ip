package page;

import page.quest.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File storageFile;

    public Storage(String filePath) {
        this.storageFile = new File(filePath);
    }

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

    public void saveData(QuestLog questLog) {
        try {
            if (storageFile.getParentFile() != null) {
                storageFile.getParentFile().mkdirs();
            }
            storageFile.createNewFile();
            FileWriter fw = new FileWriter(storageFile, false);
            fw.write(questLog.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: failed to save Quest Log :(" + e.getMessage());
        }
    }
}
