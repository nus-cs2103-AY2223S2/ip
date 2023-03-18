package seedu.duke;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles reading and writing to a storage file.
 */
public class Storage {
    private FileWriter fw = null;
    private FileReader fr = null;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return ArrayList of tasks loaded from the storage file.
     * @throws IOException If an I/O error occurs.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> list = new ArrayList<>();

        try {
            String tasks = getFileContents();

            String[] lines = tasks.split("\n");
            for (String line : lines) {
                String[] taskSplit = line.split(" ");
                String taskLine = getTask(taskSplit);
                String[] split = taskLine.split(" ");
                String taskType = taskSplit[1];
                switch (taskType) {
                    case "T":
                        list.add(new ToDo(taskLine));
                        break;
                    case "D":
                        addDeadline(split, list);
                        break;
                    case "E":
                        addEvent(split, list);
                        break;
                }

                if (taskSplit[3].equals("?")) {
                    list.get(list.size() - 1).isDone = true;
                }
            }

        } catch (FileNotFoundException fe) {
            System.out.println("File not found...creating the file");
            fw = new FileWriter("duke.txt");
        } catch (ArrayIndexOutOfBoundsException a) {
            fw = new FileWriter("duke.txt");
        } finally {
            fw = new FileWriter("duke.txt");
        }

        return list;
    }

    /**
     * Retrieves the description of a task from a given array of strings.
     *
     * @param taskSplit The array of strings.
     * @return A string representing the description of a task.
     */
    public String getTask(String[] taskSplit){
        String description = "";
        if (taskSplit[1].equals("T")) {
            description = "";
        }
        if (taskSplit[1].equals("D")) {
            description = "deadline";
        }
        if (taskSplit[1].equals("E")) {
            description = "event";
        }
        if(taskSplit[1].equals("T")) {
            for (int j = 5; j < taskSplit.length; j++) {
                description += taskSplit[j] + " ";
            }

        } else {
            for (int j = 5; j < taskSplit.length; j++) {
                description += " " + taskSplit[j];
            }
        }
        return description;

    }

    /**
     * Reads the contents of the storage file.
     *
     * @return A string containing the contents of the storage file.
     * @throws IOException If an I/O error occurs.
     */
    public String getFileContents() throws IOException {
        fr = new FileReader(this.fileName);
        String task = "";
        int ch;
        while ((ch=fr.read()) != -1)
            task += (char)ch;
        fr.close();
        return task;
    }

    /**
     * Updates the storage file with the current task list.
     *
     * @param tasks The task list to be written to the storage file.
     * @throws IOException If an I/O error occurs.
     */
    public void updateFile(TaskList tasks) throws IOException {
        fw = new FileWriter(this.fileName);
        for (int i = 0; i < tasks.list.size(); i++) {
            fw.write(tasks.list.get(i).toString()+'\n');
        }
        fw.flush();
    }
    /**
     * Closes the file readers and writers.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void close() throws IOException {
        fw.close();
        fr.close();
    }

    public void addEvent(String[] echoSplit, ArrayList<Task> list) {
        int fromIndex = findIndex(echoSplit, "/from", "from:");
        int toIndex = findIndex(echoSplit, "/to", "to:");

        String task = extractData(echoSplit, 1, fromIndex) + " ";
        String from = extractData(echoSplit, fromIndex + 1, toIndex);
        String to = extractData(echoSplit, toIndex + 1, echoSplit.length);

        list.add(new Event(task, from, to));
    }

    public void addDeadline(String[] echoSplit, ArrayList<Task> list) {
        int byIndex = findIndex(echoSplit, "/by", "by:");

        String task = extractData(echoSplit, 1, byIndex);
        String date = extractData(echoSplit, byIndex + 1, echoSplit.length);

        list.add(new Deadline(task, date));
    }

    private String extractData(String[] echoSplit, int start, int end) {
        StringBuilder data = new StringBuilder();

        for (int i = start; i < end; i++) {
            if (i != start) {
                data.append(" ");
            }
            data.append(echoSplit[i]);
        }

        return data.toString();
    }

    private int findIndex(String[] echoSplit, String... targets) {
        for (int i = 0; i < echoSplit.length; i++) {
            for (String target : targets) {
                if (echoSplit[i].equals(target)) {
                    return i;
                }
            }
        }

        return -1;
    }




}
