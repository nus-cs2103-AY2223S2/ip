package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Class that reads from file at the start and updates file when changes are made.
 */
public class Storage {
    private final String filePath;
    private Scanner sc;
    private boolean dailyRefresh = false;
    private boolean weeklyRefresh = false;
    private boolean monthlyRefresh = false;
    private boolean yearlyRefresh = false;
    private LocalDate currRefresh;

    /**
     * Constructs Storage.
     *
     * @param filePath Path of where file should be located.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        assert filePath.equals("tasks.txt");
    }

    /**
     * Creates a file is file does not exist. Otherwise, read from file to restore list of tasks.
     *
     * @throws DukeException Throws exception when there is error reading from file.
     */
    public void openFile() throws DukeException {
        try {
            File file = new File(filePath);
            file.createNewFile();
            sc = new Scanner(file);
        } catch (IOException e) {
            throw new DukeException("Cannot open file");
        }
    }

    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> arrOfTasks = new ArrayList<>();
        if (sc.hasNext()) {
            LocalDate lastRefresh = LocalDate.parse(sc.nextLine());
            updateRefresh(lastRefresh);
        }
        currRefresh = LocalDate.now();
        while (sc.hasNext()) {
            String[] cmd = sc.nextLine().split("\\|");
            Task t = getTask(cmd);
            getRecurrence(cmd, t);
            getTaskDone(cmd, t);
            arrOfTasks.add(t);
        }
        return arrOfTasks;
    }

    public Task getTask(String[] cmd) {
        if (cmd[0].equals("T")) {
            return new Todo(cmd[3]);
        } else if (cmd[0].equals("D")) {
            return  new Deadline(cmd[3], LocalDate.parse(cmd[4]));
        } else {
            assert cmd[0].equals("E");
            return new Event(cmd[3], LocalDate.parse(cmd[4]), LocalDate.parse(cmd[5]));
        }
    }

    public void getRecurrence(String[] cmd, Task t) {
        if (cmd[2].equals("D")) {
            t.setRecurrence("daily");
            if (dailyRefresh) {
                t.refresh();
            }
        } else if (cmd[2].equals("W")) {
            t.setRecurrence("weekly");
            if (weeklyRefresh) {
                t.refresh();
            }
        } else if (cmd[2].equals("M")) {
            t.setRecurrence("month");
            if (monthlyRefresh) {
                t.refresh();
            }
        } else if (cmd[2].equals("Y")) {
            t.setRecurrence("yearly");
            if (yearlyRefresh) {
                t.refresh();
            }
        }
    }

    public void updateRefresh(LocalDate lastRefresh) {
        if (LocalDate.now().isAfter(lastRefresh)) {
            dailyRefresh = true;
            if (LocalDate.now().get(ChronoField.DAY_OF_WEEK) == 1) {
                weeklyRefresh = true;
            }
            if (LocalDate.now().get(ChronoField.DAY_OF_MONTH) == 1) {
                monthlyRefresh = true;
            } if (LocalDate.now().get(ChronoField.DAY_OF_YEAR) == 1) {
                yearlyRefresh = true;
            }
        }
    }

    public void getTaskDone(String[] cmd, Task t) {
        if (cmd[1].equals("1")) {
            t.markDone();
        }
    }

    /**
     * Update file whenever changes are made to list of tasks.
     *
     * @param taskList TaskList class that will write to file.
     * @throws DukeException Throws exception when there is error writing from file.
     */
    public void writeFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(currRefresh + "\n");
            taskList.writeToFile(fw);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }
}

/*


 */