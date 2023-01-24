package storage;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Storage {
    private File storageFile;
    public Storage(String filePath) {
        this.storageFile = makeFile(filePath);
    }
    private File makeFile(String filePath) {
        File f = new File(filePath);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    //Solution below adapted from https://stackoverflow.com/questions/1377279/find-a-line-in-a-file-and-remove-it
    private void deleteLine(String line) {
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            boolean linesLeft = true;
            while (linesLeft) {
                currLine = reader.readLine();
                if (currLine == null) {
                    linesLeft = false;
                } else {
                    String trimmedLine = currLine.trim();
                    if (trimmedLine.equals(rmLine) && !done) {
                        done = true;
                        continue;
                    }
                    writer.write(currLine + "\n");
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(this.storageFile);
    }
    private void modifyLine(String line, String newLine) {
        File tempFile = new File("temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String rmLine = line;
            String currLine;
            boolean done = false;
            boolean linesLeft = true;
            while (linesLeft) {
                currLine = reader.readLine();
                if (currLine == null) {
                    linesLeft = false;
                } else {
                    String trimmedLine = currLine.trim();
                    if (trimmedLine.equals(rmLine) && !done) {
                        done = true;
                        writer.write(newLine + "\n");
                    } else {
                        writer.write(currLine + "\n");
                    }
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(this.storageFile);
    }

    private void appendToFile(String text){
        try {
            FileWriter fw = new FileWriter(this.storageFile, true); // create a FileWriter in append mode
            fw.write(text + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> userTasks = new ArrayList<>();
        if (this.storageFile.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(this.storageFile));

                String currLine;
                boolean linesLeft = true;
                while (linesLeft) {
                    currLine = reader.readLine();
                    if (currLine == null) {
                        linesLeft = false;
                    } else {
                        String[] fields = currLine.split(Pattern.quote(" | "));
                        if (fields[0].equals("T")) {
                            Task t = new ToDo(fields[2]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        } else if (fields[0].equals("D")) {
                            Task t = new Deadline(fields[2], fields[3]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        } else if (fields[0].equals("E")) {
                            Task t = new Event(fields[2], fields[3], fields[4]);
                            if (fields[1].equals("1")) {
                                t.markAsDone();
                            }
                            userTasks.add(t);
                        }
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new DukeException("Error loading file");
            }
            return userTasks;
        } else {
            return userTasks;
        }
    }
    public void addTask(String taskText) {
        appendToFile(taskText);
    }
    public void modifyTask(String oldText, String newText) {
        modifyLine(oldText, newText);
    }

    public void deleteTask(String taskText) {
        deleteLine(taskText);
    }


}
