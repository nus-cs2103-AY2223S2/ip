package app.chatbot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.task.InvalidDateTimeException;
import app.task.InvalidInputException;
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

    private static final int SYMBOL_INDEX = 0;
    private static final int ISDONE_INDEX = 1;
    private static final int DESC_INDEX = 2;
    private static final int MIN_STORAGE_ARG_SIZE = 3;
    private static final Path DEFAULT_STORAGE_RESOURCE = Paths.get("storage","default-storage.txt");
    private static final String DEFAULT_STORAGE_MISSING = "Default storage not found, loading an empty TaskList";

    private final File file;
    private final boolean isFirstLoad;

    /**
     * Creates a new Storage object. A file is created at the stated path to store
     * data. If the path location does not exist, the necessary files and directories
     * are created.
     *
     * @param path the relative path for which the text data file will be stored.
     * @throws IOException, propagated from FileWriter.
     */
    Storage(Path path) throws IOException, InvalidStorageException {
        boolean storageExists = Files.exists(path);
        System.out.println("storageExists: " + storageExists);
        this.isFirstLoad = !storageExists;
        System.out.println("isFirstLoad: " + isFirstLoad);
        this.file = new File(path.toString());
        // creates storage directory or data if they don't exist
        this.file.getParentFile().mkdirs();
        if (isFirstLoad) {
            this.file.createNewFile();
        }
        assert this.file.exists();
    }

    public boolean isFirstLoad() {
        return this.isFirstLoad;
    }

    private InputStream getDefaultStorageFromResource() throws InvalidStorageException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(DEFAULT_STORAGE_RESOURCE.toString());
        if (inputStream == null) {
            throw new InvalidStorageException(DEFAULT_STORAGE_MISSING);
        }
        return inputStream;
    }

    public Map<String, Integer> loadDefaultStorageToTaskList(TaskList tl)
            throws InvalidStorageException {
        InputStream is = getDefaultStorageFromResource();

        Map<String, Integer> successRates = new HashMap<>();
        int totalSuccess = 0;
        int totalRowsRead = 0;
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    boolean isSuccess = loadLineToTaskList(tl, line);
                    if (isSuccess) {
                        totalSuccess++;
                    }
                } catch (InvalidStorageException e) {
                    System.out.println(e.getMessage());
                }
                totalRowsRead++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        successRates.put("Successes", totalSuccess);
        successRates.put("Total", totalRowsRead);

        return successRates;
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
    public Map<String, Integer> loadIntoTaskList(TaskList tl)
            throws IOException {
        Map<String, Integer> successRates = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(this.file));
        String line = br.readLine();
        int totalRowsRead = 0;
        int totalSuccess = 0;

        while (line != null) {
            totalRowsRead++;
            System.out.println("Loading task #" + totalRowsRead);

            try {
                boolean isSuccess = loadLineToTaskList(tl, line);
                if (isSuccess) {
                    totalSuccess++;
                }

            } catch (InvalidStorageException e) {
                System.out.println(e.getMessage());
            } finally {
                line = br.readLine();
            }
        }

        successRates.put("Successes", totalSuccess);
        successRates.put("Total", totalRowsRead);
        return successRates;
    }

    private static boolean loadLineToTaskList(TaskList tl, String line) throws InvalidStorageException {
        List<String> args = splitStorageFormatArgs(line);

        TaskTypes.Type taskType = getTaskTypeFromStorageFormat(args);
        Map<String, String> argValues = getArgMapFromStorageFormat(args);
        boolean isDone = getIsDoneFromStorageFormat(args);

        boolean isSuccess = addStorageTaskToTaskList(tl, taskType, argValues, isDone);
        return isSuccess;
    }

    /**
     * Adds a storage format Task to a TaskList, given the processed arguments.
     * @param tl
     * @param taskType
     * @param argValues
     * @param isDone
     * @return a boolean indicating if the addition was successful.
     */
    private static boolean addStorageTaskToTaskList(TaskList tl,
                                             TaskTypes.Type taskType,
                                             Map<String, String> argValues,
                                             boolean isDone) {
        try {
            if (isDone) {
                tl.addDoneTask(taskType, argValues);
            } else {
                tl.addTask(taskType, argValues);
            }
            return true;
        } catch (InvalidInputException | InvalidDateTimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static TaskTypes.Type getTaskTypeFromStorageFormat(List<String> args)
            throws InvalidStorageException {

        String symbol = args.get(SYMBOL_INDEX);
        TaskTypes.Type taskType = TaskTypes.symbolToTask.getValue().get(symbol);

        if (taskType == null) {
            throw new InvalidStorageException("Invalid TaskType");
        }
        return taskType;
    }

    /**
     * Extracts task arguments from the split storageFormat.
     * Compulsorily extracts description.
     * @param args
     * @return
     * @throws InvalidStorageException - if Description is missing
     */
    private static Map<String, String> getArgMapFromStorageFormat(List<String> args)
            throws InvalidStorageException {

        Map<String, String> argValues = new HashMap<>();
        try {
            argValues.put("description", args.get(DESC_INDEX));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidStorageException("Missing description");
        }

        // map additional arguments
        if (args.size() > MIN_STORAGE_ARG_SIZE) {
            for (String pair : args.subList(MIN_STORAGE_ARG_SIZE, args.size())) {
                String[] split = pair.split(SUB_SEPARATOR, 2);
                argValues.put(split[0], split[1]);
            }
        }
        return argValues;
    }

    /**
     * Extracts isDone from Storage. isDone is indicated with 1 for Done, 0 for Undone.
     * Throws an exception for any other value.
     * @param args
     * @return
     * @throws InvalidStorageException
     */
    private static boolean getIsDoneFromStorageFormat(List<String> args) throws InvalidStorageException {
        String arg = args.get(ISDONE_INDEX);
        boolean isDoneTrue = arg.equals("1");
        boolean isUnDoneTrue = arg.equals("0");

        // check if arg value != (1 or 0)
        if (!isDoneTrue && !isUnDoneTrue) {
            throw new InvalidStorageException("error loading isDone");
        }
        return isDoneTrue;
    }

    /**
     * Splits a single line from the storageFormat into a List
     * of arguments.
     * @param line
     * @return
     * @throws InvalidStorageException if the line contains less than min
     * number of args in storage
     */
    private static List<String> splitStorageFormatArgs(String line) throws InvalidStorageException {
        List<String> args = Arrays.asList(line.split(SEPARATOR_REGEX));
        if (args.size() < MIN_STORAGE_ARG_SIZE) {
            throw new InvalidStorageException("Missing inputs for task in this line");
        }
        return args;
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
