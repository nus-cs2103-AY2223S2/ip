package alfred.storage;

import alfred.task.*;
import alfred.exceptions.AlfredException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File dataFile;

    public Storage(String filePath) {
        dataFile = new File(filePath);
    }

    public ArrayList<Task> load() throws AlfredException {
        class CheckMark {
            public void isMark(int value, Task task) {
                if (value == 1) {
                    task.markAsDone();
                }
            }
        }
        CheckMark checkMark = new CheckMark();

        ArrayList<Task> tasks = new ArrayList<>();
        dataFile.getParentFile().mkdir();
        try {
            if (!dataFile.createNewFile()) {
                Scanner sc = new Scanner(dataFile);
                while (sc.hasNext()) {
                    String[] lineArr = sc.nextLine().split(" \\| ");
                    String taskType = lineArr[0];
                    Task task;
                    // What happens if the data in the file is not as the format given?
                    try {
                        switch (taskType) {
                        case "T":
                            task = new ToDo(lineArr[2]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        case "D":
                            task = new Deadline(lineArr[2], lineArr[3]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        case "E":
                            String[] duration = lineArr[3].split("-");
                            task = new Event(lineArr[2], duration[0], duration[1]);
                            checkMark.isMark(Integer.parseInt(lineArr[1]), task);
                            break;
                        default:
                            throw new AlfredException("I'm sorry but there is an"
                                    + " invalid task in the data file");
                            // No task type
                        }
                        tasks.add(task);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new AlfredException("There is probably a missing separator in your file");
                    }
                }
            }
        } catch (IOException e) {
            throw new AlfredException("Error, invalid file path");
        }
        return tasks;
    }

    public void write(TaskList tasks) throws AlfredException {
        tasks.writeToFile(dataFile);
    }
}
