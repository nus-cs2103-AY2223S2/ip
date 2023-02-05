package app.chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.task.Task;
import app.task.TaskList;
import app.task.TaskTypes;

/**
 * Handles Storage of data. Converts Task data saved in text file into
 * Tasks the user interacts with during runtime. Saves Tasks into txt file
 * and overwrites changes.
 */
public class Storage {
    public static final String SEPARATOR_REGEX = " \\| ";
    public static final String SEPARATOR = " | ";
    public static final String SUB_SEPARATOR = ":"; // for commands with additional args
    private final File file;

    /**
     * Creates a new Storage object. A file is created at the stated path to store
     * data. If the path location does not exist, the necessary files and directories
     * are created.
     *
     * @param path the relative path for which the text data file will be stored.
     * @throws IOException, propagated from FileWriter.
     */
    Storage(Path path) throws IOException {
        this.file = new File(path.toString());

        // creates storage directory or data if they don't exist
        this.file.getParentFile().mkdirs();
        if (!Files.exists(path)) {
            this.file.createNewFile();
        }
        assert this.file.exists();
    }

    /**
     * Loads a single line in storage into a given task.TaskList.
     * Method is under review to return totalSuccesses and totalFailures.
     *
     * It is assumed that a line in storage follows the format specified here:
     * taskSymbol | isDone | desc | addtl-arg1:value | addtl-arg2:value ...
     *
     * For example, a project meeting task.Event from 1pm to 3pm marked done:
     * E | 1 | project meeting | from:1pm | to:3pm
     *
     * @param tl empty TaskList, assuming this is executed upon startup of app.
     * @return totalSuccess, the number of lines that have been successfully loaded into the tl.
     * @throws
     */
    public int loadIntoTaskList(TaskList tl) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(this.file));
        String line = br.readLine();
        int totalTaskCounter = 0;
        int totalSuccess = 0;
        while (line != null) {
            totalTaskCounter++;
            List<String> args = Arrays.asList(line.split(SEPARATOR_REGEX));

            TaskTypes.Type taskType = getTaskTypeFromStorageFormat(args);
            Map<String, String> argValues = getArgMapFromStorageFormat(args);
            boolean isDone = getIsDoneFromStorageFormat(args);

            try {
                if (isDone) {
                    tl.addDoneTask(taskType, argValues);
                } else {
                    tl.addTask(taskType, argValues);
                }
                totalSuccess++;
                line = br.readLine();
            } catch (Exception e) {
                line = br.readLine();
            }

        }
        return totalSuccess;
    }

    private TaskTypes.Type getTaskTypeFromStorageFormat(List<String> args) {
        String symbol = args.get(0);
        return TaskTypes.symbolToTask.getValue().get(symbol);
    }

    private Map<String,String> getArgMapFromStorageFormat(List<String> args) {
        Map<String, String> argValues = new HashMap<>();
        argValues.put("Description", args.get(2));

        // remaining named arguments
        if (args.size() > 3) {
            for (String pair : args.subList(3, args.size())) {
                String[] split = pair.split(SUB_SEPARATOR, 2);
                argValues.put(split[0], split[1]);
            }
        }
        return argValues;
    }

    private boolean getIsDoneFromStorageFormat(List<String> args) throws InvalidStorageException {
        String arg = args.get(1);
        boolean isDoneTrue = arg.equals("1");
        boolean isUnDoneTrue = arg.equals("0");
        if (!isDoneTrue && !isUnDoneTrue) {
            throw new InvalidStorageException("error loading isDone");
        }
        return isDoneTrue;
    }

    /**
     * Overwrites the storage file <strong>completely</strong> with data for the TaskList passed in.
     * Thus, this should only be executed when the TaskList contains the right information.
     *
     * @param tl TaskList with updated data to be stored.
     * @throws IOException, propagated from FileWriter.
     */
    public void saveToStorage(TaskList tl) throws IOException {
        BufferedWriter br = new BufferedWriter(new FileWriter(this.file));
        for (Task task : tl.getAllTasks()) {
            String data = task.asDataFormat();
            br.write(data);
            br.newLine();
        }
        br.close();
    }

    /**
     * Indicates invalid storage data
     */
    public static class InvalidStorageException extends Exception {
        InvalidStorageException(String msg) {
            super(msg);
        }
    }
}
