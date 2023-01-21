import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    /**
     * The file path of duke.txt.
     */
    private String taskFilePath;

    private ArrayList<Task> tasksFromFile;

    public FileManager() {
        makeDukeDirectory();

        try {
            this.tasksFromFile = getTaskArrayFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void init(DukeMemory dukeMemory) {
        dukeMemory.syncTasks(this.tasksFromFile);
    }

    public void fileAppend(Task t) {
        try {
            String textToAdd = t.getFileFormatString() + System.lineSeparator();
            FileWriter fw = new FileWriter(this.taskFilePath, true);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not append to file");
        }
    }

    public void fileMarkTask(int index) {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    String tmp = scanner.nextLine();
                    Task oldTask = Task.getTaskFromFileFormat(tmp);
                    oldTask.markAsDone();
                    buffer.append(oldTask.getFileFormatString() + System.lineSeparator());
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }

    public void fileUnmarkTask(int index) {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    String tmp = scanner.nextLine();
                    Task oldTask = Task.getTaskFromFileFormat(tmp);
                    oldTask.markAsNotDone();
                    buffer.append(oldTask.getFileFormatString() + System.lineSeparator());
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }

    public void fileDeleteTask(int index) {
        int i = 0;

        try {
            Scanner scanner = new Scanner(new File(this.taskFilePath));
            StringBuffer buffer = new StringBuffer();

            while (scanner.hasNext()) {
                if (i != index) {
                    buffer.append(scanner.nextLine() + System.lineSeparator());
                } else {
                    scanner.nextLine();
                }
                i++;
            }

            String newFileContents = buffer.toString();
            scanner.close();


            FileWriter fw = new FileWriter(this.taskFilePath, false);
            fw.write(newFileContents);
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not edit file");
        }

    }

    public ArrayList<Task> getTaskArrayFromFile() throws FileNotFoundException {
        File taskFile = new File(this.taskFilePath);
        Scanner scanner = new Scanner(taskFile);
        ArrayList<Task> returnList = new ArrayList<>();

        while (scanner.hasNext()) {
            String taskInFileFormat = scanner.nextLine();
            Task curTask = Task.getTaskFromFileFormat(taskInFileFormat);
            returnList.add(curTask);
        }

        return returnList;

    }

    public void makeDukeDirectory() {
        String dukeDirectory = "data";
        File directory = new File(dukeDirectory);

        if (!directory.exists()) {
            directory.mkdir();
        }

        this.taskFilePath = dukeDirectory + "/duke.txt";
    }
}
