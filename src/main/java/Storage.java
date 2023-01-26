import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    private Path memoryPath;
    private File memory;

    public Storage(String[] memoryPathArray) {
//        String[] memoryPathArray = memoryPathString.split(
//                Matcher.quoteReplacement(System.getProperty("file.separator")));
        this.memoryPath = Paths.get(memoryPathArray[0],
                Arrays.copyOfRange(memoryPathArray, 1, memoryPathArray.length));
        this.memory = new File(String.valueOf(memoryPath));
    }

    public void loadTasks(TaskList allTasks) throws MemoryFailedException {
        try {
            boolean success = this.memory.createNewFile();
            Scanner memoryScanner = new Scanner(memory);
            while (memoryScanner.hasNext()) {
                String taskLine = memoryScanner.nextLine();
                loadTaskLine(taskLine, allTasks);
            }
        } catch (IOException e) {
            throw new MemoryFailedException();
        }
    }

    private void loadTaskLine(String taskLine, TaskList allTasks) {
        // TODO: Handle corruption in file, leading to incorrect syntax
        // TODO: Edit such that it doesn't print anything
        String[] attributes = taskLine.split(", ");
        String type = attributes[0];
        String doneNumber = attributes[1];
        boolean done = Objects.equals(doneNumber, "1");
        String title = attributes[2];
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm a");
        if (Objects.equals(type, "T")) {
            allTasks.addToList(title, TaskType.TODO, null, null, done, false);
        } else if (Objects.equals(type, "D")) {
            LocalDateTime dateBy = LocalDateTime.parse(attributes[3], dateFormat);
            allTasks.addToList(title, TaskType.DEADLINE, null, dateBy, done, false);
        } else if (Objects.equals(type, "E")) {
            LocalDateTime start = LocalDateTime.parse(attributes[3], dateFormat);
            LocalDateTime end = LocalDateTime.parse(attributes[4], dateFormat);
            allTasks.addToList(title, TaskType.EVENT, start, end, done, false);
        } else {
            System.out.println("Some task in memory does not fall into the three task categories!");
        }
    }

    public void saveTasks(TaskList allTasks) {
        // TODO: Handle case where file is destroyed while script is running
        try {
            BufferedWriter fw = Files.newBufferedWriter(this.memoryPath , StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task: allTasks.getArray()) {
                fw.write(task.convertToMemoryString());
                fw.newLine();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
