package duke;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage represents the methods that are used to store the user's tasks to a storage file so that they
 * can be saved and retrieved for later use.
 */
public class Storage {


    /**
     * Constructor for a Storage object, which will facilitate the storing of the user's requests for later retrieval
     * @param list The TaskList object that will store the tasks entered by the user
     */
    public Storage(TaskList list) {

        File Task_Data = createStorageFile(list);
        try {
            loadTaskData(Task_Data, list);
        } catch (StorageFileFormatException e) {
            System.out.println("There is an error in the format of at least one date in the duke.Storage Data File");
        }

    }

    /**
     * Method to create a file that will store all the tasks created by the user
     * @param list The TaskList Object that will store all the tasks created by the user
     * @return The File object that was created for storage purposes
     */
    public File createStorageFile(TaskList list) {
        File Task_Data = new File("duke.Task Data.txt");
        try {
            Task_Data.createNewFile();
        } catch (FileAlreadyExistsException e){ // nothing should be done if the file already exists
            System.out.println("The file already exists");
        } catch (IOException e) {
            System.out.println("File creation was unsuccessful");

        }
        return Task_Data;
    }


    /**
     * Method to update the storage file with the latest task list
     * @param list The TaskList object that will store tasks entered by the user
     * @throws IOException
     */
    public void updateTasksInFile (TaskList list) throws IOException {
        PrintWriter logger = new PrintWriter("duke.Task Data.txt");
        for (int i = 0; i < list.getNumberOfTasks(); i++) {
            logger.write(list.getTaskAtIndex(i) + "\n");
        }
        logger.close();
    }

    /**
     * Method that reads the storage file and adds the tasks to the TaskList object that stores all the user tasks.
     * @param taskDataFile The file from which the stored tasks will be read
     * @param list The TaskList Object that stores all the user's tasks
     * @throws StorageFileFormatException
     */
    public void loadTaskData (File taskDataFile, TaskList list) throws StorageFileFormatException {
        try {
            Scanner scanner = new Scanner(taskDataFile);

            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String taskDescription = task.substring(7);
                boolean isMarked = (task.charAt(4) == 'X');
                char taskIdentifier = task.charAt(1);
                if (taskIdentifier == 'T') {
                    list.addTaskWhenLoading(new ToDo(taskDescription));
                    if (isMarked) {
                       list.markDone(list.getNumberOfTasks());
                    }

                } else if (taskIdentifier == 'E') {
                    String[] reformattedDateAndTimeComponents = reformatEventDateAndTime(taskDescription);
                    String startDayTime = reformattedDateAndTimeComponents[0];
                    String endDayTime = reformattedDateAndTimeComponents[1];
                    String[] splitDescription = taskDescription.split("\\.");
                    String description = splitDescription[0].strip();
                    list.addTaskWhenLoading(new Event(startDayTime, endDayTime, description));
                    if (isMarked) {
                        list.markDone(list.getNumberOfTasks());
                    }

                } else if (taskIdentifier == 'D') {
                    String[] splitDeadline = taskDescription.split("\\.");
                    String description = splitDeadline[0].strip();
                    String deadline = splitDeadline[1].split(":")[1];
                    try {
                        String reformattedDate = reformatDeadline(taskDescription);
                        list.addTaskWhenLoading(new Deadline(description, reformattedDate));
                    } catch (InvalidDateFormatException e) {
                        throw new StorageFileFormatException();
                    }
                    if (isMarked) {
                        list.markDone(list.getNumberOfTasks());
                    }
                } else {

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
        }

    }

    /**
     * Method that reads the deadline in the format stored in the data file and returns a string that can be read
     * by the addTaskWhenLoading method
     * @param taskDetails This is a String that captures the format in which a deadline is stored in the data file
     * @return A String that is the deadline that is reformatted to fit the format required by the addTaskWhenLoading
     * method
     */
    private String reformatDeadline(String taskDetails) {
        String[] splitDeadline = taskDetails.split("\\.");
        String description = splitDeadline[0].strip();
        String deadline = splitDeadline[1].split(":")[1];
        String dateDetected = deadline.split(",")[1].strip();
        String[] dateComponents = dateDetected.split(" ");
        String day = dateComponents[0];
        String month = dateComponents[1].substring(0, 3);
        String year = dateComponents[2];
        String reformattedDate = day + "-" + month + "-" + year;
        return reformattedDate;
    }

    /**
     * Method that reads the start date/time and end date/time in the format stored in the data file and returns
     * a string that can be read by the addTaskWhenLoading method
     * @param taskDetails This is a String that captures the format in which a event date/time is stored in the data
     *                    file
     * @return A String that is the event start date/time and end date/time that is reformatted to fit the format
     * required by the addTaskWhenLoading method
     */
    private String[] reformatEventDateAndTime(String taskDetails) {
        String[] splitDatesAndTimes = taskDetails.split("\\.");
        String startDate = splitDatesAndTimes[1].split(",")[1].split("@")[0].strip();
        String startTime = splitDatesAndTimes[1].split(",")[1].split("@")[1].substring(1, 6);
        String endDate = splitDatesAndTimes[2].split(",")[1].split("@")[0].strip();
        String endTime = splitDatesAndTimes[2].split(",")[1].split("@")[1].substring(1, 6);
        String[] startDateComponents = startDate.split(" ");
        String startDay = startDateComponents[0];
        String startMonth = startDateComponents[1].substring(0, 3);
        String startYear = startDateComponents[2];
        String startDetails = startDay + "-" + startMonth + "-" + startYear + " " + startTime.replace(":", "");
        String[] endDateComponents = endDate.split(" ");
        String endDay = startDateComponents[0];
        String endMonth = startDateComponents[1].substring(0, 3);
        String endYear = startDateComponents[2];
        String endDetails = endDay + "-" + endMonth + "-" + endYear + " " + endTime.replace(":", "");;
        return new String[] {startDetails.strip(), endDetails.strip()};

    }
}
