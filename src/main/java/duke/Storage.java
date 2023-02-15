package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

/**
 * Class used to help in storing and loading user's list of tasks for Duke chatbot.
 */
public class Storage {
    protected Path dataPath;
    protected Path dataPathForTag;

    /**
     * Constructor to initiate a Storage instance.
     *
     * @param filePath  directory whereby the list of tasks should be stored.
     * @param fileName file in which the list of tasks should or will be stored.
     * @throws IOException if force-closed.
     */
    public Storage(String filePath, String fileName, String tagFileName) throws IOException {
        this.dataPath = getData(filePath, fileName);
        this.dataPathForTag = getData(filePath, tagFileName);
    }

    /**
     * Method to get, or otherwise create, file containing list of tasks.
     *
     * @param filePath
     * @param fileName
     * @return Path whereby the file containing the list of tasks is supposed to be. If there is no such file, the
     *         file will be created in the specified filePath with fileName.
     * @throws IOException
     */
    public Path getData(String filePath, String fileName) throws IOException {
        Path toCheck = Paths.get(filePath);
        if (!Files.exists(toCheck)) {
            Files.createDirectory(toCheck);
        }

        Path fileToCheck = Paths.get(filePath, fileName);
        if (!Files.exists(fileToCheck)) {
            Files.createFile(fileToCheck);
        }

        assert Files.exists(fileToCheck) : "File not being freshly created despite never being initialized";
        return fileToCheck;
    }

    /**
     * Helper method to handle loading of ToDo tasks from the file into the Duke chatbot.
     *
     * @param isDone Character read from the file to indicate whether the task is done or not.
     * @param nameOfTask A string containing the name of the task to be handled.
     * @param listOfTasks The list which will have tasks written into from the file.
     * @throws DukeExceptions
     */
    public void handleToDo(Character isDone, String nameOfTask, ArrayList<Task> listOfTasks) throws DukeExceptions {
        Task toAdd = new ToDo(nameOfTask);
        if (isDone.equals('X')) {
            toAdd.setDone();
        }
        listOfTasks.add(toAdd);
    }

    /**
     * Helper method to handle loading of Deadlines tasks from the file into the Duke chatbot.
     *
     * @param isDone Character read from the file to indicate whether the task is done or not.
     * @param listOfTasks The list which will have tasks written into from the file.
     * @param requiredInformation A string containing the full name of the task as written into the file previously.
     * @throws DukeExceptions
     */
    public void handleDeadline(
            Character isDone, ArrayList<Task> listOfTasks, String requiredInformation) throws DukeExceptions {
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

    /**
     * Helper method to handle loading of Events tasks from the file into the Duke chatbot.
     *
     * @param isDone Character read from the file to indicate whether the task is done or not.
     * @param listOfTasks The list which will have tasks written into from the file.
     * @param requiredInformation A string containing the full name of the task as written into the file previously.
     * @throws DukeExceptions
     */
    public void handleEvents(
        Character isDone, ArrayList<Task> listOfTasks, String requiredInformation) throws DukeExceptions {
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

    /**
     * Function to load tasks from stored dataPath into an arraylist to be used by the Duke chatbot.
     *
     * @return An arraylist which contains the tasks as stored from the previous session.
     * @throws IOException
     * @throws DukeExceptions
     */
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

    /**
     * Function to load tags from the data file into an arraylist to be used by Duke chatbot.
     *
     * @return An arraylist containing tags loaded from the data file.
     * @throws IOException
     */
    public ArrayList<String> loadTags() throws IOException {
        ArrayList<String> useThis = new ArrayList<>();
        Scanner scannerForFileData = new Scanner(this.dataPathForTag);
        if (!scannerForFileData.hasNext()) {
            scannerForFileData.close();
            return useThis;
        }
        while (scannerForFileData.hasNextLine()) {
            String tagName = scannerForFileData.nextLine();
            useThis.add(tagName);
        }
        scannerForFileData.close();
        return useThis;
    }

    /**
     * Function to store the list of tasks from the current Duke chatbot session into the dataPath file.
     *
     * @param listOfTasks The list of tasks from the current Duke chatbot session.
     * @throws IOException
     */
    public void storeTask(ArrayList<Task> listOfTasks) throws IOException {
        if (listOfTasks.size() == 0) {
            Files.write(dataPath, "".getBytes());
            return;
        }
        Files.write(dataPath, "".getBytes());
        Task firstTask = listOfTasks.get(0);
        String toSave = "1." + firstTask.toString() + "\n";
        Files.write(dataPath, toSave.getBytes(), StandardOpenOption.APPEND);
        for (int i = 1; i < listOfTasks.size(); i++) {
            Integer currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            String toUse = currIndex.toString() + "." + currTask.toString() + "\n";
            Files.write(dataPath, toUse.getBytes(), StandardOpenOption.APPEND);
        }
    }

    /**
     * Function to store list of tags from the current Duke chatbot session into the dataPathForTag file.
     *
     * @param listOfTags The list of tags from the current Duke chatbot session.
     * @throws IOException
     */
    public void storeTags(ArrayList<String> listOfTags) throws IOException {
        if (listOfTags.size() == 0) {
            Files.write(dataPathForTag, "".getBytes());
            return;
        }
        Files.write(dataPathForTag, "".getBytes());
        String firstTag = listOfTags.get(0);
        String toSave = firstTag + "\n";
        Files.write(dataPathForTag, toSave.getBytes(), StandardOpenOption.APPEND);
        for (int i = 1; i < listOfTags.size(); i++) {
            Integer currIndex = i + 1;
            String currTag = listOfTags.get(i);
            String toUse = currTag + "\n";
            Files.write(dataPathForTag, toUse.getBytes(), StandardOpenOption.APPEND);
        }
    }

}
