package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.ToDo;
import duke.tasktypes.Task;

public class Storage {
    
    Path dataPath;

    public Storage(String filePath, String fileName) throws IOException {
        this.dataPath = getData(filePath, fileName);
    }

    public Path getData(String filePath, String fileName) throws IOException {
        Path toCheck = Paths.get(filePath);
        if (!Files.exists(toCheck)) {
            Files.createDirectory(toCheck);
        }

        Path fileToCheck = Paths.get(filePath, fileName);
        if (!Files.exists(fileToCheck)) {
            Files.createFile(fileToCheck);
        }
        return fileToCheck;
    }

    public void handleToDo(Character isDone, String nameOfTask, ArrayList<Task> listOfTasks) throws DukeExceptions {
        Task toAdd = new ToDo(nameOfTask);
        if (isDone.equals('X')) {
            toAdd.setDone();
        }
        listOfTasks.add(toAdd);
    }

    public void handleDeadline(Character isDone, ArrayList<Task> listOfTasks, String requiredInformation) throws DukeExceptions {
                String deadlineWithBy = requiredInformation.substring(requiredInformation.lastIndexOf("("));
                String rawDate = deadlineWithBy.split("\\(by: ")[1].split("\\)")[0];
                String[] nameSplitInArr = requiredInformation.substring(6).split(" ");
                String nameOfTask = nameSplitInArr[0];
                for (int i = 1; i < nameSplitInArr.length; i++) {
                    if (nameSplitInArr[i].equals("(by:")) {
                        break;
                    }
                    nameOfTask = nameOfTask + " " + nameSplitInArr[i];
                }
                String toInitialize = nameOfTask + " /by" + " " + rawDate;
                Task toAdd = new Deadlines(toInitialize);
                if (isDone.equals('X')) {
                    toAdd.setDone();
                }
                listOfTasks.add(toAdd);
    }

    public void handleEvents(Character isDone, ArrayList<Task> listOfTasks, String requiredInformation) throws DukeExceptions {
                String timeframe = requiredInformation.substring(requiredInformation.lastIndexOf("("));
                String from = timeframe.substring(1).split(" to:")[0].substring(5);
                String to = timeframe.substring(1).split("to: ")[1].split("\\)")[0];
                String[] nameSplitInArr = requiredInformation.substring(6).split(" ");
                String nameOfTask = nameSplitInArr[0];
                for (int i = 1; i < nameSplitInArr.length; i++) {
                    if (nameSplitInArr[i].equals("(from:")) {
                        break;
                    }
                    nameOfTask = nameOfTask + " " + nameSplitInArr[i];
                }
                String toInitialize = nameOfTask + " /from" + from + " /to " + to;
                Task toAdd = new Events(toInitialize);
                if (isDone.equals('X')) {
                    toAdd.setDone();
                }
                listOfTasks.add(toAdd); 
    }

    public ArrayList<Task> loadTask() throws IOException, DukeExceptions {
        ArrayList<Task> useThis = new ArrayList<>();
        Scanner scannerForFileData = new Scanner(this.dataPath);
        if (!scannerForFileData.hasNext()) {
            scannerForFileData.close();
            return useThis;
        }
        while (scannerForFileData.hasNextLine()) {
            String taskToLoad = scannerForFileData.nextLine();  
            String dataWithoutIndexes = taskToLoad.substring(2);
            Character isDone = dataWithoutIndexes.charAt(4);
            Character taskType = dataWithoutIndexes.charAt(1);

            if (taskType.equals('T')) {
                String nameOfTask = dataWithoutIndexes.substring(6);
                handleToDo(isDone, nameOfTask, useThis);
            }

            if (taskType.equals('D')) {
                handleDeadline(isDone, useThis, dataWithoutIndexes);
            }

            if (taskType.equals('E')) {
                handleEvents(isDone, useThis, dataWithoutIndexes);
            }
        }
        scannerForFileData.close();
        return useThis;
    }

    public void storeTask(ArrayList<Task> listOfTasks) throws IOException {
        if (listOfTasks.size() == 0) {
            Files.write(dataPath, "".getBytes());
            return;
        }
        Files.write(dataPath, "".getBytes());
        Task firstTask = listOfTasks.get(0);
        String toSave = "1." + firstTask.toString() + " \n";
        Files.write(dataPath, toSave.getBytes(), StandardOpenOption.APPEND);
        for (int i = 1; i < listOfTasks.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString() + " \n";
            Files.write(dataPath, toUse.getBytes(), StandardOpenOption.APPEND);
        }
    }

}