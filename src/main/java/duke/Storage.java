package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Handles loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    static final String FILE_NAME = "MEL.txt";
    static final int TASK_TYPE_INDEX = 1;
    static final int IS_MARKED_INDEX = 4;
    static final int DESCRIPTION_INDEX = 7;
    static final char MARKED_CHAR = 'X';
    static final String FROM_SUBSTRING = " \\(from: ";
    static final String TO_SUBSTRING = " to: ";
    static final String BY_SUBSTRING = " \\(by: ";
    static final String END_SUBSTRING = "\\)";
    static final String DATE_FORMAT = "MMM d yyyy";
    private final TaskList taskList;

    /**
     * Constructor for Storage class.
     *
     * @param list Task list to read/write from the current list of tasks.
     */
    public Storage(TaskList list) {
        this.taskList = list;
    }

    /**
     * Reads from file and store data (tasks) into tasks list.
     */
    public void initializeList() {
        try {
            File yourFile = new File(FILE_NAME);
            yourFile.createNewFile();
            try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = br.readLine()) != null) {
                    char taskType = line.charAt(TASK_TYPE_INDEX);

                    boolean isMarked = false;
                    Character markedChar = line.charAt(IS_MARKED_INDEX);
                    if (markedChar.equals(MARKED_CHAR)) {
                        isMarked = true;
                    }

                    String description = line.substring(DESCRIPTION_INDEX);

                    switch (taskType) {
                    case 'T':
                        Todo todoTask = new Todo(description, isMarked);
                        taskList.add(todoTask);
                        break;
                    case 'E':
                        String modifiedDescription = description.split(FROM_SUBSTRING)[1];

                        String newEventDescription = description.split(FROM_SUBSTRING)[0];
                        LocalDate fromDate = LocalDate.parse(modifiedDescription.split(TO_SUBSTRING)[0],
                                DateTimeFormatter.ofPattern(DATE_FORMAT));
                        LocalDate toDate = LocalDate.parse(modifiedDescription.split(TO_SUBSTRING)[1]
                                .split(END_SUBSTRING)[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
                        Event eventTask = new Event(newEventDescription, isMarked, fromDate, toDate);
                        taskList.add(eventTask);
                        break;
                    case 'D':
                        String newDeadlineDescription = description.split(BY_SUBSTRING)[0];
                        LocalDate byDate = LocalDate.parse(description.split(BY_SUBSTRING)[1]
                                .split(END_SUBSTRING)[0], DateTimeFormatter.ofPattern(DATE_FORMAT));
                        Deadline deadlineTask = new Deadline(newDeadlineDescription, isMarked, byDate);
                        taskList.add(deadlineTask);
                        break;
                    default:
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets tasks from tasks list and write data (tasks) into file.
     * To be executed every time a task is added, modified or deleted.
     */
    public void saveToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
