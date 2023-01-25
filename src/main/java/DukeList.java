import java.io.*;
import java.util.ArrayList;

public class DukeList implements Serializable {
    private static final String SAVED_FILE_PATH = "./savedTasks.txt";
    private static final String IO_EXCEPTION_MESSAGE = "I/O error occurred while retrieving list from save.";
    private static final String FILE_HANDLING_EXCEPTION = "File error occurred while retrieving list from save.";

    private static final String INVALID_INDEX_EXCEPTION = "Invalid task index given for Mark/Unmark/Delete command.";
    private static final String LIST_RESPONSE = "Current tasks in list:";
    private static final String ADDED_TASK_RESPONSE = "Task added:\n";
    private static final String REMAINING_TASK_RESPONSE ="\nRemaining task count: ";
    private static final String REMOVE_TASK_RESPONSE = "Task removed:\n";
    private static final String LIST_INDEX_SEPARATOR = ". ";
    private ArrayList<Task> taskList;
    DukeList() throws DukeException {
        taskList = initialiseListFromFile();
    }

    private ArrayList<Task> initialiseListFromFile() throws DukeException {
        File file = new File(SAVED_FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (FileInputStream fileInputStream = new FileInputStream(SAVED_FILE_PATH);
             ObjectInputStream objectInputStream
                     = new ObjectInputStream(fileInputStream)) {
            return (ArrayList<Task>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveChangesToFile() throws DukeException {
        try (FileOutputStream fileOutputStream
                = new FileOutputStream(SAVED_FILE_PATH);
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(taskList);
            objectOutputStream.flush();
        } catch (FileNotFoundException e) {
            throw new DukeException(FILE_HANDLING_EXCEPTION);
        } catch (IOException e) {
            throw new DukeException(IO_EXCEPTION_MESSAGE);
        }

    }

    public void addTask(String input) throws DukeException {
        Task task = Task.parseTaskFromInput(input);
        taskList.add(task);
        saveChangesToFile();
        int count = taskList.size() - 1;
        System.out.println(DukeIO.wrapContent(ADDED_TASK_RESPONSE + taskList.get(count) + REMAINING_TASK_RESPONSE
                + taskList.size()));
    }

    public void markTask(String input) throws DukeException {
        try {
            String indexString = input.substring(DukeIO.MARK_COMMAND.length());
            int taskIndex = Integer.parseInt(indexString);
            if (taskIndex > taskList.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            taskList.get(taskIndex - 1).markTask();
            saveChangesToFile();
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public void unmarkTask(String input) throws DukeException {
        try {
            String indexString = input.substring(DukeIO.UNMARK_COMMAND.length());
            int taskIndex = Integer.parseInt(indexString);
            if (taskIndex > taskList.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            taskList.get(taskIndex - 1).unmarkTask();
            saveChangesToFile();
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public void deleteTask(String input) throws  DukeException {
        try {
            String indexString = input.substring(DukeIO.DELETE_COMMAND.length());
            int taskIndex = Integer.parseInt(indexString);
            if (taskIndex > taskList.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            Task toRemove = taskList.get(taskIndex - 1);
            taskList.remove(taskIndex - 1);
            saveChangesToFile();
            System.out.println(DukeIO.wrapContent(
                    REMOVE_TASK_RESPONSE + toRemove.toString() + REMAINING_TASK_RESPONSE + taskList.size()));
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(LIST_RESPONSE);
        for (int i=0; i < taskList.size(); i++) {
            result.append("\n").append(i + 1).append(LIST_INDEX_SEPARATOR).append(taskList.get(i));
        }
        return result.toString();
    }
}
