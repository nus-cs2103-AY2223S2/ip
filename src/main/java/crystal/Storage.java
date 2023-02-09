package crystal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import crystal.task.Deadline;
import crystal.task.Event;
import crystal.task.Task;
import crystal.task.Todo;


/**
 * Represents the Storage task.
 *
 */

public class Storage {
    private String filepath;

    /**
     * Constructor for Storage class.
     *
     * @param filepath filepath where the list is stored at.
     *
     */

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Method to write to the file.
     *
     * @param filepath Filepath to store the list at
     * @param textToAdd Text to be printed on the file
     *
     */
    //Save to file
    public void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Method to save the file.
     *
     * @param tasks Tasklist to be saved
     *
     */
    public void saveFile(TaskList tasks) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).toString().startsWith("[T]")) {
                String stringReplaceT = tasks.get(i).toString().replace("[T]", "T ");
                if (tasks.get(i).toString().contains("[X]")) {
                    stringReplaceT = stringReplaceT.replace("[X]", "| 0 |"); //task done
                    str.append(stringReplaceT + "\n");
                } else {
                    stringReplaceT = stringReplaceT.replace("[ ]", "| 1 |");
                    str.append(stringReplaceT + "\n");
                }

            } else if (tasks.get(i).toPrint().startsWith("[D]")) {
                String stringReplaceD = tasks.get(i).toPrint().replace("[D]", "D ");
                if (tasks.get(i).toPrint().contains("[X]")) {
                    stringReplaceD = stringReplaceD.replace("[X]", "| 0 |");
                    stringReplaceD = stringReplaceD.replace("(by:", "|");
                    stringReplaceD = stringReplaceD.replace(")", "");
                    str.append(stringReplaceD + "\n");
                } else {
                    stringReplaceD = stringReplaceD.replace("[ ]", "| 1 |");
                    stringReplaceD = stringReplaceD.replace("(by:", "|");
                    stringReplaceD = stringReplaceD.replace(")", "");
                    str.append(stringReplaceD + "\n");
                }

            } else if (tasks.get(i).toPrint().startsWith("[E]")) {
                String stringReplaceE = tasks.get(i).toPrint().replace("[E]", "E ");
                if (tasks.get(i).toPrint().contains("[X]")) {
                    stringReplaceE = stringReplaceE.replace("[X]", "| 0 |");
                    stringReplaceE = stringReplaceE.replace("(from:", "|");
                    stringReplaceE = stringReplaceE.replace("to:", " - ");
                    stringReplaceE = stringReplaceE.replace(")", "");
                    str.append(stringReplaceE + "\n");
                } else {
                    stringReplaceE = stringReplaceE.replace("[ ]", "| 1 |");
                    stringReplaceE = stringReplaceE.replace("(from:", "|");
                    stringReplaceE = stringReplaceE.replace("to:", "-");
                    stringReplaceE = stringReplaceE.replace(")", "");
                    str.append(stringReplaceE + "\n");
                }
            }

        }

        try {
            writeToFile(this.filepath, str + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /**
     * Method to read the file and return a Task arraylist.
     * @throws CrystalException When the date format is not recognised
     *
     */
    //Load file
    public ArrayList<Task> readFileContents() throws CrystalException {

        File file = new File(this.filepath); // create a File for the given file path
        ArrayList<Task> temp = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String nextInput = s.nextLine();
                if (nextInput.startsWith("T")) {
                    if (nextInput.contains("| 0 |")) {
                        String description = nextInput.replace("T | 0 |", "");
                        Task newTask = new Todo(description.trim());
                        newTask.setIsDone(true);
                        temp.add(newTask);
                    } else {
                        String description = nextInput.replace("T | 1 |", "");
                        Task newTask = new Todo(description.trim());
                        newTask.setIsDone(false);
                        temp.add(newTask);
                    }

                } else if (nextInput.startsWith("D")) {
                    if (nextInput.contains("| 0 |")) {
                        String description = nextInput.replace("D | 0 |", "");
                        int index = description.lastIndexOf("|");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        description = description.replace(description.substring(index), "");
                        try {
                            Task newTask = new Deadline(description.trim(), time.trim());
                            newTask.setIsDone(true);
                            temp.add(newTask);
                        } catch (CrystalException e) {
                            System.out.println("Wrong date format! Please change!");
                        }

                    } else {
                        String description = nextInput.replace("D | 1 |", "");
                        int index = description.lastIndexOf("|");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        description = description.replace(description.substring(index), "");
                        try {
                            Task newTask = new Deadline(description.trim(), time.trim());
                            newTask.setIsDone(false);
                            temp.add(newTask);
                        } catch (CrystalException e) {
                            System.out.println("Wrong date format! Please change!");
                        }
                    }

                } else if (nextInput.startsWith("E")) {
                    if (nextInput.contains("| 0 |")) {
                        String description = nextInput.replace("E | 0 |", "");
                        int index = description.lastIndexOf("|");
                        int index2 = description.lastIndexOf("-");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        int index3 = time.lastIndexOf(" - ");
                        time = time.replace(time.substring(index3), "");
                        String endTime = description.substring(description.lastIndexOf(" - ") + 3);
                        description = description.replace(description.substring(index), "");
                        Task newTask = new Event(description.trim(), time.trim(), endTime.trim());
                        newTask.setIsDone(true);
                        temp.add(newTask);
                    } else {
                        String description = nextInput.replace("E | 1 |", "");
                        int index = description.lastIndexOf("|");
                        int index2 = description.lastIndexOf("-");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        int index3 = time.lastIndexOf(" - ");
                        time = time.replace(time.substring(index3), "");
                        String endTime = description.substring(description.lastIndexOf(" - ") + 3);
                        description = description.replace(description.substring(index), "");
                        Task newTask = new Event(description.trim(), time.trim(), endTime.trim());
                        newTask.setIsDone(false);
                        temp.add(newTask);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return temp;
    }
}
