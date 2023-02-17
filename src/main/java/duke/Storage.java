package duke;

import duke.exceptions.InvalidDateFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** A class Storage that deals with loading tasks from the file and saving tasks in the file */
public class Storage {
    /** The file path */
    private String taskListPath;
    private String noteListPath;

    /**
     * Initializes an Storage object with the given path.
     *
     * @param taskListPath The relative path of tasklist
     * @param noteListPath The relative path of noteList
     * @return A Storage instance
     */
    public Storage(String taskListPath, String noteListPath) {

        this.taskListPath = taskListPath;
        this.noteListPath = noteListPath;
    }

    /**
     * Initializes and loads tasks from file indicated when Duke is started.
     *
     * @throws FileNotFoundException If file is not found
     * @return A list of tasks saved in file
     */
    public ArrayList<Task> initializeTasks() throws FileNotFoundException {
        File f = new File(taskListPath);
        ArrayList<Task> og = new ArrayList<>();
        if (!f.exists()) {
            return og;
        }

        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] keywords = splitString(str, " \\| ");
            String type = keywords[0];
            String doneStatus = keywords[1];
            String taskName = keywords[2];
            boolean isDone = doneStatus == "X" ? true : false;
            Task toInsert = null;

            switch (type) {
            case "T":
                toInsert = new ToDo(taskName, isDone);
                break;
            case "D":
                try {
                    toInsert = new Deadline(taskName, Parser.getDateOfInputForm(keywords[3].trim()), isDone);
                } catch (InvalidDateFormatException err) {
                    System.out.println(err.getMessage());
                }
                break;
            case "E":
                String[] duration = splitString(keywords[3], " - ");
                toInsert = new Event(taskName, duration[0], duration[1], isDone);
                break;
            }
            og.add(toInsert);
        }
        sc.close();

        return og;
    }



    /**
     * Separate the string by |
     *
     * @param str The string given
     * @return An array of strings separated by |
     */
    private String[] splitString(String str, String regex) {
        return str.split(regex);
    }

    /**
     * Load tasks from another place into the file
     *
     * @throws IOException If there is a problem with the writer class
     */
    public void loadTasks() throws IOException {
        File f = handleOpenFile(taskListPath);
        FileWriter fw = new FileWriter(taskListPath);

        ArrayList<Task> changed = TaskList.getList();
        for (int i = 0; i < changed.size(); i++) {
            fw.write(changed.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }

    /**
     * Initializes and loads tasks from file indicated when Duke is started.
     *
     * @throws FileNotFoundException If file is not found
     * @return A list of tasks saved in file
     */
    public ArrayList<Note> initializeNotes() throws FileNotFoundException {
        File f = new File(noteListPath);
        ArrayList<Note> og = new ArrayList<>();
        if (!f.exists()) {
            return og;
        }

        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String str = sc.nextLine().trim();
            Note toInsert = new Note(str);
            og.add(toInsert);
        }
        sc.close();

        return og;
    }



    /**
     * Load tasks from another place into the file
     *
     * @throws IOException If there is a problem with the writer class
     */
    public void loadNotes() throws IOException{
        File f = handleOpenFile(noteListPath);
        FileWriter fw = new FileWriter(noteListPath);

        ArrayList<Note> changed = NoteList.getList();
        for (int i = 0; i < changed.size(); i++) {
            fw.write(changed.get(i).toString());
            fw.write("\n");
        }
        fw.close();
    }

    private File handleOpenFile (String path) throws IOException{
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }

        f.getParentFile().mkdirs();
        f.createNewFile();
        return f;
    }
}
