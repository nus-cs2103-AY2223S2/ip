package duke.storage;

import duke.exception.InvalidDateTimeException;
import duke.helper.TaskList;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class FileSystem {
    private File file;

    public FileSystem(String relFilePath) throws IOException {
        String dirPath = relFilePath.split("/")[0];
        File dir = new File(dirPath);
        file = new File(relFilePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public void updateFile(TaskList taskList) {
        try {
            file.delete();
            file.createNewFile();
            ArrayList<Task> tasks = taskList.getTasks();

            for (Task t : tasks) {
                appendToFile(t.toString());
            }
        } catch (IOException e) {
            e.toString();
        }

    }

    public ArrayList<Task> loadFromFile() throws FileNotFoundException, InvalidDateTimeException {
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();

            char taskType = task.charAt(1);
            boolean isMark = task.charAt(4) == 'X';

            switch (taskType) {
            case 'T':
                tasks.add(new Task("T", isMark, task.substring(7)));
                break;
            case 'D':
                tasks.add(new Task("D", isMark, task.substring(7)));
                break;
            case 'E':
                tasks.add(new Task("E", isMark, task.substring(7)));
                break;
            }

            if (isMark) {
                tasks.get(tasks.size() - 1).setIsDone(true);
            }
        }
        return tasks;
    }

    public void appendToFile(String textToAppend) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
        fw.write(textToAppend);
        fw.newLine();
        fw.close();
    }
}
