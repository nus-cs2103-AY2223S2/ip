package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javafx.util.Pair;

/**
 * Load user's existing plan every duke's start up,
 * and save his/ her current progress after program is finished.
 *
 * <p> Keep a database by implementing {@code HashMap}
 * to find Tasks that contain a specified keyword. </p>
 */

public class Storage {
    private HashMap<String, TaskList> keywordDatabase;
    private HashMap<String, PriorityQueue<Pair<LocalDateTime, Task>>> taskScheduleOnDates;

    /**
     * Construct the {@code Storage} object with
     * empty database
     */

    public Storage() {
        this.keywordDatabase = new HashMap<>();
        this.taskScheduleOnDates = new HashMap<>();
    }

    /**
     * Save the user's current progress to the
     * output directory.
     */

    private static void saveProgress(TaskList taskList) {
        File savedFile = new File("MY_GRAND_PLAN.txt");
        System.out.println("[X] FILE CREATED");
        try {
            FileWriter myWriter = new FileWriter(savedFile, false);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task currenttask = taskList.getTask(i);
                myWriter.write(currenttask.getStatus() + " " + currenttask.getNature() + " "
                        + currenttask.getAction() + " " + currenttask.getTimeInfo() + '\n');
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
                String[] availableTaskArray = data.split(" ");
                List<String> availableTaskAsList = Arrays.asList(availableTaskArray);

                boolean isDone = Boolean.parseBoolean(availableTaskArray[0]);

                String actionAndDate = "";
                for (int i = 1; i < availableTaskAsList.size(); i++) {
                        actionAndDate += availableTaskAsList.get(i) + " ";
                }

                actionAndDate = actionAndDate.stripTrailing();
                Task parsedTask = Parser.parseTask(actionAndDate);

                if (isDone) {
                    parsedTask = parsedTask.markDone();
                }

                returnTaskList.addTask(parsedTask);
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

    public TaskList getTaskWithKeywords(String keyword) {
        if (this.keywordDatabase.containsKey(keyword)) {
            return this.keywordDatabase.get(keyword);
        } else {
            return new TaskList();
        }
    }

    /**
     * Add the newly parsed {@code Task} to the keyword and schedule database
     * to facilitate keyword searching and searching of scheduled events on a
     * specified date
     *
     * @param task the {@code Task} to be added to database
     */

    public Storage addToDatabase(Task task) {
        addTaskToSchedule(task);
        addToKeywordStorage(task);
        return this;
    }

    /**
     * Add a task to the database and assign it to a key for easy retrieval.
     * @param task the keywords to assign the {@code Task}
     * @return a new {@code Storage} with the task added to the database
     */

    private void addToKeywordStorage(Task task) {
        String toUpdateKeywordDatabase = task.getNature() +
                " " + task.getAction() + " " + task.getTimeInfo();

        for (String keyword : toUpdateKeywordDatabase.split(" ")) {
            if (this.keywordDatabase.containsKey(keyword)) {
                TaskList currentList = this.keywordDatabase.get(keyword);
                currentList = currentList.addTask(task);
                this.keywordDatabase.put(keyword, currentList);
            } else {
                TaskList newList = new TaskList();
                newList = newList.addTask(task);
                this.keywordDatabase.put(keyword, newList);
            }
        }
    }

    /**
     * Remove a task from the keyword and schedule database
     *
     * @param task the task to remove from the
     *                list of Tasks in database assigned to a specified
     *                keyword
     * @return a new {@code Storage} with the task removed from the keyword and schedule database
     */

    public Storage removeFromStorage(Task task) {
        removeFromKeywordStorage(task);
        removeFromScheduleStorage(task);
        return this;
    }

    private void removeFromKeywordStorage(Task task) {
        String removedTask = task.getNature() +
                " " + task.getAction() + " " + task.getTimeInfo();
        for (String keyword : removedTask.split(" ")) {
            if (this.keywordDatabase.containsKey(keyword)) {
                TaskList currentList = this.keywordDatabase.get(keyword);
                int index = currentList.searchIndexOf(task);
                if (index >= 0) {
                    currentList = currentList.removeTask(index);
                }

                if (currentList.getSize() > 0) {
                    this.keywordDatabase.put(keyword, currentList);
                } else {
                    this.keywordDatabase.remove(keyword);
                }
            }
        }
    }

    private void removeFromScheduleStorage(Task task) {
        List<LocalDateTime> datesOfTasks = task.getDates();
        if (datesOfTasks.size() > 1) { //EVENT
            List<LocalDateTime> listOfDate = new ArrayList<>();
            LocalDateTime dateBegin = datesOfTasks.get(0);
            LocalDateTime dateEnd = datesOfTasks.get(1);

            while (dateBegin.compareTo(dateEnd) < 0) {
                listOfDate.add(dateBegin);
                dateBegin = dateBegin.plusDays(1);
            }
            datesOfTasks = listOfDate;
        }

        for (LocalDateTime date : datesOfTasks) {
            String searchDate = date.toLocalDate().toString();
            assert taskScheduleOnDates.containsKey(searchDate);

            PriorityQueue<Pair<LocalDateTime, Task>> scheduleOnDate = taskScheduleOnDates.get(searchDate);
            Pair<LocalDateTime, Task> deletedPair = new Pair<>(datesOfTasks.get(0), task);

            for (Pair<LocalDateTime, Task> pair : scheduleOnDate) {
                if (pair.getKey().equals(datesOfTasks.get(0)) &&
                        pair.getValue().toString().equals(task.toString())) {
                    deletedPair = pair;
                    break;
                }
            }
            scheduleOnDate.remove(deletedPair);

            if (scheduleOnDate.size() == 0) {
                taskScheduleOnDates.remove(searchDate);
            } else {
                taskScheduleOnDates.put(searchDate, scheduleOnDate);
            }
        }
    }



    /**
     * Asking the user whether he wants to save the current progress
     * Works with CLI version of Duke
     *
     * @param listOfCurrentTasks the current list of tasks
     *
     */

    public static void saveProgressQuery(TaskList listOfCurrentTasks) {
        Scanner sc = new Scanner(System.in);
        System.out.println("SAVE YOUR GRAND PLAN FOR ANOTHER DAY? ");
        String isSaving = sc.nextLine();
        if (isSaving.equals("YES")) {
            saveProgress(listOfCurrentTasks);
        }
    }

    /**
     * Asking the user whether he wants to save the current progress
     * Works with GUI version of Duke
     *
     * @param listOfCurrentTasks the current list of tasks
     *
     */

    public static void saveProgressGUI(String userCommand, TaskList listOfCurrentTasks) {
        if (userCommand.equals("YES")) {
            saveProgress(listOfCurrentTasks);
        }
    }

    private void addTaskToSchedule(Task task) {
        List<LocalDateTime> eventDates = task.getDates();

        if (task.getNature().equals("DEADLINE")) {
            LocalDateTime deadlineDate = eventDates.get(0);
            LocalDate convertedDate = deadlineDate.toLocalDate();

            String date = convertedDate.toString();
            PriorityQueue<Pair<LocalDateTime, Task>> currentQueue;

            if (this.taskScheduleOnDates.containsKey(date)) {
                currentQueue = this.taskScheduleOnDates.get(date);
            } else {
                currentQueue = new PriorityQueue<>(new DatetimeComparator());
            }
            currentQueue.add(new Pair<LocalDateTime, Task>(deadlineDate, task));
            this.taskScheduleOnDates.put(date, currentQueue);

        } else if (task.getNature().equals("EVENT")) {

            LocalDateTime dateBeginUnformatted = eventDates.get(0);
            LocalDate dateBeginFormatted = dateBeginUnformatted.toLocalDate();

            LocalDateTime dateEndUnformatted = eventDates.get(1);
            LocalDate dateEndFormatted = dateEndUnformatted.toLocalDate();

            while (dateBeginFormatted.compareTo(dateEndFormatted) <= 0) {
                String searchDate = dateBeginFormatted.toString();
                System.out.println(searchDate);
                PriorityQueue<Pair<LocalDateTime, Task>> currentQueue;
                if (this.taskScheduleOnDates.containsKey(searchDate)) {
                    currentQueue = this.taskScheduleOnDates.get(searchDate);
                } else {
                    currentQueue =
                            new PriorityQueue<>(new DatetimeComparator());
                }
                currentQueue.add(new Pair<LocalDateTime, Task>(dateBeginUnformatted, task));
                this.taskScheduleOnDates.put(searchDate, currentQueue);
                dateBeginFormatted = dateBeginFormatted.plusDays(1);
            }
        } else {
            return;
        }
    }
    /**
     * Return a queue consisting of scheduled {@code Task} on a specified date
     *
     * @param searchDate the date to search for schedule
     * @return a {@code PriorityQueue} with the scheduled tasks on the specified date
     */

    public PriorityQueue<Pair<LocalDateTime, Task>> getTaskScheduleOnDates(String searchDate) {
        if (this.taskScheduleOnDates.containsKey(searchDate)) {
            return this.taskScheduleOnDates.get(searchDate);
        } else {
            return new PriorityQueue<>();
        }
    }
}
