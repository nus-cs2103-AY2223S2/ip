package classes;

import exceptions.DukeException;
import exceptions.FolderNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String filePath, folderPath;

    private StringBuilder sb;

    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
        this.sb = new StringBuilder();
    }

    public ArrayList<String> load() throws IOException {
        ArrayList<String> fileElements = new ArrayList<>();
        try {
            fileElements = getFileContents();
        } catch (FolderNotFoundException e) {
            Path tempFilePath = Paths.get("data/storage.txt");
            Files.createDirectories(tempFilePath.getParent());
            Files.createFile(tempFilePath);
            System.out.println(e);
        } catch (FileNotFoundException e) {
            sb.append("    ____________________________________________________________\n")
                    .append("    File 'storage' cannot be found in the folder 'data'.\n")
                    .append("    A new file 'storage' has been created for you under the folder 'data'\n")
                    .append("    for storing the tasks!\n")
                    .append("    ____________________________________________________________\n");
            Path tempFilePath2 = Paths.get("data/storage.txt");
            Files.createFile(tempFilePath2);
            System.out.println(sb.toString());
            sb.setLength(0);
        } finally {
            return fileElements;
        }
    }

    public ArrayList<String> getFileContents() throws IOException, FolderNotFoundException {
        ArrayList<String> fileElements = new ArrayList<>();
        DukeException.folderCheck(this.folderPath);  // Checks if the folder exists
        BufferedReader fr = new BufferedReader(new FileReader(this.filePath));
        // Checks if the storage file is in the right folder
        String currLine;
        while ( (currLine = fr.readLine()) != null) {
            fileElements.add(currLine);  // Copy tasks from file over
        }
        fr.close();
        return fileElements;
    }

    public void writeToFile(String textToAdd, ArrayList<Task> taskList) throws IOException {
        ArrayList<String> fileTasks = new ArrayList<>();
        try {
            fileTasks = load();
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter(this.filePath, true);
            if (fileTasks.size() != 0) {    // file has information inside
                fw.write(textToAdd);
            } else {    // file is empty
                for (int i = 0; i < taskList.size(); i++) {
                    fw.write(taskList.get(i).getTaskInfo() + "\n");
                }
            }
            fw.close();
        }
    }

    public void writeToFile(String oldText, String newText, int oldTextIndex,
                                    ArrayList<Task> taskList) throws IOException {
        ArrayList<String> fileTasks = new ArrayList<>();
        try {
            fileTasks = load();
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter("data/storage.txt");
            if (fileTasks.size() != 0) {    // file not empty
                for (int i = 0; i < fileTasks.size(); i++) {
                    if ( (fileTasks.get(i).equals(oldText)) && (i == oldTextIndex) ) {
                        if (!newText.equals("")) {
                            fw.write(newText + "\n");
                        }
                        continue;
                    }
                    fw.write(fileTasks.get(i) + "\n");
                }
            } else {    // file is empty
                for (int i = 0; i < taskList.size(); i++) {
                    if (i == oldTextIndex) {
                        if (newText.equals("")) {
                            continue;
                        }
                        fw.write(newText + "\n");
                        continue;
                    }
                    fw.write(taskList.get(i).getTaskInfo() + "\n");
                }
            }
            fw.close();
        }
    }
}
