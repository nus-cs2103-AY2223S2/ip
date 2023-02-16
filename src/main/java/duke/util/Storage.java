package duke.util;

import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;
import duke.util.service.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Load user's existing plan every duke's start up,
 * and save his/ her current progress after program is finished.
 *
 * <p> Keep a database by implementing {@code HashMap}
 * to find Tasks that contain a specified keyword. </p>
 */

public class Storage {

    private HashMap<String, TaskList> database;

    /**
     * Construct the {@code Storage} object with
     * empty database
     */
    public Storage() {
        this.database = new HashMap<>();
    }

    /**
     * Save the user's current progress to the
     * output directory.
     */

    public static void saveProgress(TaskList taskList) {
        File savedFile = new File("MY_GRAND_PLAN.txt");
        System.out.println("[X] FILE CREATED");
        try {
            FileWriter myWriter = new FileWriter("MY_GRAND_PLAN.txt", false);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currenttask = taskList.getTask(i);
                myWriter.write(currenttask.getNature() + " " + currenttask.getStatus() + " "
                        + currenttask.getAction() + " "+ currenttask.getAdditionalInfo() + '\n');
            }
            myWriter.close();
            System.out.println("[X] FINISHED WRITING");
        } catch (IOException e) {
            System.out.println("BEE BOO BOOP...");
        }
    }

    /**
     * Load the user's existing progress from the
     * output directory.
     *
     * @return a {@code TaskList} with the user's
     *          existing Task
     */
    public static TaskList loadProgress() {
        try {
            File previousProgress = new File("MY_GRAND_PLAN.txt");
            Scanner progressScanner = new Scanner(previousProgress);
            TaskList returnTaskList = new TaskList();
            while (progressScanner.hasNextLine()) {
                String data = progressScanner.nextLine();
                String[] availableTask = data.split(" ");
                List<String> availableTaskAsList = Arrays.asList(availableTask);
                boolean isDone = Boolean.parseBoolean(availableTaskAsList.get(1));
                if (availableTaskAsList.get(0).equals("T")) {
                    String action = "";
                    for (int i = 2; i < availableTaskAsList.size(); i++) {
                        action += availableTaskAsList.get(i) + " ";
                    }
                    returnTaskList.addTask(new ToDo(action, isDone));
                } else if (availableTaskAsList.get(0).equals("D")) {
                    String actionAndDate = "";
                    for (int i = 2; i < availableTaskAsList.size(); i++) {
                        actionAndDate += availableTaskAsList.get(i) + " ";
                    }
                    String[] deadlineInfo = actionAndDate.split(" /BY ");
                    List<String> deadlineInfoAsList = Arrays.asList(deadlineInfo);
                    String date = deadlineInfoAsList.get(1);

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");

                    returnTaskList.addTask(
                            new Deadline(LocalDateTime.parse(date,format), deadlineInfoAsList.get(0), isDone));
                } else {
                    String actionAndDate = "";
                    for (int i = 2; i < availableTaskAsList.size(); i++) {
                        actionAndDate += availableTaskAsList.get(i) + " ";
                    }

                    String[] eventInfo = actionAndDate.split(" /FROM ");
                    List<String> eventInfoAsList = Arrays.asList(eventInfo);

                    String dateInfo = eventInfoAsList.get(1);

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");

                    String[] dateInfoAsArray = dateInfo.split(" /TO ");
                    List<String> dateInfoAsList = Arrays.asList(dateInfoAsArray);

                    returnTaskList.addTask(
                            new ScheduledEvent(LocalDateTime.parse(dateInfoAsList.get(0), format),
                                    LocalDateTime.parse(dateInfoAsList.get(1), format), eventInfoAsList.get(0), isDone));


                }

            }
            progressScanner.close();
            return returnTaskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    /**
     * Retrieve the list of tasks that contains a specified keyword.
     * @param keyword the keyword to search the list of tasks
     * @return a {@code TaskList} where each task in the list contains
     *          the specified keyword
     */

    public TaskList getTaskList(String keyword) {
        if (this.database.containsKey(keyword)) {
            return this.database.get(keyword);
        } else {
            return new TaskList();
        }
    }

    /**
     * Add a task to the database and assign it to a key for easy retrieval.
     * @param keyword the keyword to assign the {@code Task}
     * @return a new {@code Storage} with the task added to the database
     */

    public Storage addToStorage(String keyword, Task task) {
        if (this.database.containsKey(keyword)) {
            TaskList currentList = this.database.get(keyword);
            currentList = currentList.addTask(task);
            this.database.put(keyword, currentList);
        } else {
            TaskList newList = new TaskList();
            newList = newList.addTask(task);
            this.database.put(keyword, newList);
        }
        return this;
    }

    /**
     * Remove a task from the database
     *
     * @param keyword the keyword to remove the {@code Task} from the
     *                list of Tasks in database with assigned to that
     *                keyword
     * @return a new {@code Storage} with the task removed from the database
     */

    public Storage removeFromStorage(String keyword, Task task) {
        if (this.database.containsKey(keyword)) {
            TaskList currentList = this.database.get(keyword);
            int index = -1;
            for (int i = 0; i < currentList.getSize(); i++) {
                if (currentList.getTask(i).toString().equals(task.toString())) {
                    index = i;
                    break;
                }
            }
            if (index >= 0) {
                currentList = currentList.removeTask(index);
            }
            this.database.put(keyword, currentList);
        }
        return this;
    }
}
