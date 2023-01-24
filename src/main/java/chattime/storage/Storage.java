package chattime.storage;

import chattime.exception.ChattimeException;
import chattime.task.Deadline;
import chattime.task.Event;
import chattime.task.Task;
import chattime.task.Todo;
import chattime.ui.Ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {

    private static final String DEFAULT_FILE_PATH = "data/chattimeTask.txt";
    private ArrayList<Task> initList;
    private File storeFile;
    private String filePath;
    private Ui ui;

    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.filePath = (filePath.equals("") ? DEFAULT_FILE_PATH : filePath);
        this.openFile(this.filePath);
        initList = new ArrayList<>();
    }

    public void openFile(String filePath) {
        try {
            storeFile = new File(filePath);
            if (!storeFile.exists()) {
                if (!storeFile.getParentFile().exists()) {
                    if(!storeFile.getParentFile().mkdirs()) {
                        throw new IOException("New directory cannot be created!");
                    }
                }
                if(!storeFile.createNewFile()) {
                    throw new IOException("New file cannot be created!");
                }
            }
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    public ArrayList<Task> loadData() {
        BufferedReader loader;
        try {
            loader = new BufferedReader(new FileReader(storeFile));
            String task = loader.readLine();

            while (task != null) {
                String[] taskSplit = task.split(" @ ", 7);
                Task inputTask = null;
                switch (taskSplit[0]) {
                    case "T":
                        inputTask = new Todo(taskSplit[2]);
                        break;
                    case "D":
                        try {
                            LocalDate byDate = LocalDate.parse(taskSplit[3]);
                            LocalTime byTime = (taskSplit[4].equals("0") ? null : LocalTime.parse(taskSplit[4]));
                            inputTask = new Deadline(taskSplit[2], byDate, byTime);
                        } catch (DateTimeParseException e) {
                            ui.printError("OOPS!!! Datetime error in storage!");
                        }
                        break;
                    case "E":
                        try {
                            LocalDate fromDate = LocalDate.parse(taskSplit[3]);
                            LocalTime fromTime = LocalTime.parse(taskSplit[4]);
                            LocalDate toDate = LocalDate.parse(taskSplit[5]);
                            LocalTime toTime = LocalTime.parse(taskSplit[6]);

                            inputTask = new Event(taskSplit[2], fromDate, fromTime, toDate, toTime);
                        } catch (DateTimeParseException e) {
                            ui.printError("OOPS!!! Datetime error in storage!");
                        }
                        break;
                    default:
                        throw new ChattimeException("chattime.task.Task type error : " + taskSplit[0]);
                }
                if (inputTask != null && taskSplit[1].equals("1")) {
                    inputTask.markAsDone();
                }
                initList.add(inputTask);
                task = loader.readLine();
            }
            return initList;
        } catch (IOException | ChattimeException e) {
            ui.printError(e.getMessage());
        }
        return initList;
    }

    public void saveToFile(Task task) {
        String writeString = task.toDataString();
        writeToFile(writeString + System.lineSeparator(), true);
    }

    public void updateFile(int index, Task task) {
        BufferedReader lineSearch;
        try {
            lineSearch = new BufferedReader(new FileReader(storeFile));
            String content = lineSearch.readLine();
            int lineCount = 1;
            StringBuilder updateString = new StringBuilder();

            while (content != null) {
                if (lineCount == index) {
                    content = task.toDataString();
                }
                updateString.append(content).append(System.lineSeparator());
                lineCount++;
                content = lineSearch.readLine();
            }
            if (lineCount < index) {
                throw new IndexOutOfBoundsException("chattime.task.Task not saved in storage!");
            } else {
                writeToFile(updateString.toString(), false);
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            ui.printError(e.getMessage());
        }
    }

    public void deleteFromFile(int index) {
        BufferedReader lineSearch;
        try {
            lineSearch = new BufferedReader(new FileReader(storeFile));
            String content = lineSearch.readLine();
            int lineCount = 1;
            StringBuilder updateString = new StringBuilder();

            while (content != null) {
                if (lineCount == index) {
                    lineCount++;
                    content = lineSearch.readLine();
                    continue;
                }
                updateString.append(content).append(System.lineSeparator());
                lineCount++;
                content = lineSearch.readLine();
            }
            if (lineCount < index) {
                throw new IndexOutOfBoundsException("chattime.task.Task not saved in storage!");
            } else {
                writeToFile(updateString.toString(), false);
            }
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    public void writeToFile(String content, boolean append) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storeFile, append));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }
}
