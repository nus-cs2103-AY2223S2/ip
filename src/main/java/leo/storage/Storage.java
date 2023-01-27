package leo.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import leo.leoException.IncorrectMarkException;
import leo.leoException.LeoException;
import leo.leoException.NoTaskFoundException;
import leo.leoException.NoStorageFileException;
import leo.ui.Ui;

public class Storage {

    private final TaskList data;
    private final String dataFilePath;
    private final File taskFile;

    public Storage(String filePath) throws NoStorageFileException, IncorrectMarkException {
        String root = Paths.get("").toAbsolutePath().toString();
        this.dataFilePath = Paths.get(root, filePath).toString();

        this.taskFile = new File(this.dataFilePath);
        this.data = new TaskList(loadData());
    }

    private List<Task> loadData() throws IncorrectMarkException, NoStorageFileException {
        List<Task> taskList = new ArrayList<>();
        if (!taskFile.exists()) {
            return taskList;
        }

        try {
            Scanner scanner = new Scanner(taskFile);
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine().strip();
                char taskType = task.charAt(1);
                char completion = task.charAt(4);
                String description = task.substring(7).strip();

                try {
                    if (taskType == 'T') {
                        Task t = new ToDoTask(description);
                        if (completion == 'X')
                            t.mark();
                        taskList.add(t);
                    } else {
                        String[] temp = description.split("\\|");
                        String taskDescription = temp[0].strip();
                        String time = temp[1].strip();
                        LocalDateTime dt = convertString(time);
                        if (taskType == 'D') {
                            Task t = new DeadlineTask(taskDescription, dt);
                            if (completion == 'X')
                                t.mark();
                            taskList.add(t);
                        } else if (taskType == 'E') {
                            String to = temp[2].strip();
                            LocalDateTime dtTo = convertString(to);
                            Task t = new EventTask(taskDescription, dt, dtTo);
                            if (completion == 'X')
                                t.mark();
                            taskList.add(t);
                        }
                    }
                } catch (LeoException e) {
                    throw new IncorrectMarkException("This task was already marked previously.");
                }
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new NoStorageFileException("No file found!! >:-(");
        }
    }

    public void addTask(Task task) {
        data.addTask(task);
        Ui.displayMessage(Ui.leoResponse("added " + task.getTask() + " to your tasks :-) !"));
    }

    public void showList() throws NoTaskFoundException {
        try {
            int length = data.size();
            for (int i = 0; i < length; i++) {
                Ui.displayMessage((i + 1) + ". " + getTask(i).toString());
            }
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    public void mark(int num) {
        try {
            getTask(num - 1).mark();
            Ui.displayMessage(Ui.leoResponse("Good work! You have completed task " + num + ":"));
            Ui.displayMessage(Ui.notFirstLine(getTask(num - 1).toString()));
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    public void unmark(int num) {
        try {
            getTask(num - 1).unmark();
            Ui.displayMessage(Ui.leoResponse("No worries! I have unmarked task " + num + ":"));
            Ui.displayMessage(Ui.notFirstLine(getTask(num - 1).toString()));
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    public void delete(int num) {
        Task removed;
        try {
            removed = getTask(num -1);
            data.removeTask(num - 1);
            Ui.displayMessage(Ui.leoResponse("I have removed task " + num + ":"));
            assert removed != null;
            Ui.displayMessage(Ui.notFirstLine(removed.toString()));
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

    private Task getTask(int num) throws NoTaskFoundException {
        try {
            return data.getTask(num);
        } catch (Exception e) {
            throw new NoTaskFoundException("Hm, this task does not exist...");
        }
    }

    public String getDataFilePath() {
        return this.dataFilePath;
    }

    public int getDataLength() {
        return data.size();
    }

    public void writeToFile() throws NoStorageFileException {
        try {
            int len = getDataLength();
            FileWriter fileWriter = new FileWriter(getDataFilePath());

            for (int i = 0; i < len; i++) {
                fileWriter.write(data.getTask(i).saveFormat());
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new NoStorageFileException("No file found!! >:-(");
        }
    }

    private LocalDateTime convertString(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

}
