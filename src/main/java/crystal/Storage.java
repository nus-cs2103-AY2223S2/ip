package crystal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import crystal.task.Task;
import crystal.task.Todo;
import crystal.task.Deadline;
import crystal.task.Event;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    //Save to file
    public void writeToFile(String filepath,String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    public void saveFile(TaskList tasks) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).toString().startsWith("[T]")) {
                String s = tasks.get(i).toString().replace("[T]", "T ");
                if (tasks.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |"); //task done
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    str.append(s + "\n");
                }

            } else if (tasks.get(i).toString().startsWith("[D]")) {
                String s = tasks.get(i).toString().replace("[D]", "D ");
                if (tasks.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |");
                    s = s.replace("(by:", "|");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    s = s.replace("(by:", "|");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                }

            } else if (tasks.get(i).toString().startsWith("[E]")) {
                String s = tasks.get(i).toString().replace("[E]", "E ");
                if (tasks.get(i).toString().contains("[X]")) {
                    s = s.replace("[X]", "| 0 |");
                    s = s.replace("(from:", "|");
                    s = s.replace("to:", "-");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                } else {
                    s = s.replace("[ ]", "| 1 |");
                    s = s.replace("(from:", "|");
                    s = s.replace("to:", "-");
                    s = s.replace(")", "");
                    str.append(s + "\n");
                }
            }


        }
        try {
            writeToFile(this.filepath, str + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    //Load file
    public ArrayList<Task> readFileContents() {

        File f = new File(this.filepath); // create a File for the given file path
        ArrayList<Task> temp = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String t = s.nextLine();
                if (t.startsWith("T")) {
                    if (t.contains("| 0 |")) {
                        String description = t.replace("T | 0 |", "");
                        Task n = new Todo(description.trim());
                        n.isDone = true;
                        temp.add(n);
                    } else {
                        String description = t.replace("T | 1 |", "");
                        Task n = new Todo(description.trim());
                        n.isDone = false;
                        temp.add(n);
                    }

                } else if (t.startsWith("D")) {
                    if (t.contains("| 0 |")) {
                        String description = t.replace("D | 0 |", "");
                        int index = description.lastIndexOf("|");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        description = description.replace(description.substring(index), "");
                        System.out.println(time);
                        Task n = new Deadline(description.trim(), time.trim());
                        n.isDone = true;
                        temp.add(n);
                    } else {
                        String description = t.replace("D | 1 |", "");
                        int index = description.lastIndexOf("|");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        description = description.replace(description.substring(index), "");
                        Task n = new Deadline(description.trim(), time.trim());
                        n.isDone = false;
                        temp.add(n);
                    }
                } else if (t.startsWith("E")) {
                    if (t.contains("| 0 |")) {
                        String description = t.replace("E | 0 |", "");
                        int index = description.lastIndexOf("|");
                        int index2 = description.lastIndexOf("-");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        int index3 = time.lastIndexOf("-");
                        time = time.replace(time.substring(index3), "");
                        String endtime = description.substring(description.lastIndexOf("-") + 1);
                        description = description.replace(description.substring(index), "");
                        Task n = new Event(description.trim(), time.trim(), endtime.trim());
                        n.isDone = true;
                        temp.add(n);
                    } else {
                        String description = t.replace("E | 1 |", "");
                        int index = description.lastIndexOf("|");
                        int index2 = description.lastIndexOf("-");
                        String time = description.substring(description.lastIndexOf("|") + 1);
                        int index3 = time.lastIndexOf("-");
                        time = time.replace(time.substring(index3), "");
                        String endtime = description.substring(description.lastIndexOf("-") + 1);
                        description = description.replace(description.substring(index), "");
                        Task n = new Event(description.trim(), time.trim(), endtime.trim());
                        n.isDone = false;
                        temp.add(n);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return temp;
    }
}
