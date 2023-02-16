package elems;

import dukeexceptions.TaskListIndexOutOfBoundsException;
import items.Deadline;
import items.Event;
import items.Task;
import items.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(Storage storage) {
            loadFromStorageText(storage.load());
    }

    public Task delete(int index) throws TaskListIndexOutOfBoundsException{
        try {
            Task deleted = this.taskList.remove(index - 1);
            return deleted;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to delete a task that is out of task list bounds");
        }

    }

    public String[] enumerate(){
        int length = this.taskList.size();
        String[] taskStringList = new String[length];
        for (int i = 0; i < length; i++) {
            taskStringList[i] = taskList.get(i).toString();
        }
        return taskStringList;
    }

    public void addTask(Task newTask){
        this.taskList.add(newTask);
    }

    public void markTask(int index) throws TaskListIndexOutOfBoundsException {
        try {
            Task mark = this.taskList.get(index - 1);
            mark.setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to mark task that is out of task list bounds");
        }
    }

    public void unmarkTask(int index) throws TaskListIndexOutOfBoundsException{
        try {
            Task unmark = this.taskList.get(index - 1);
            unmark.setNotDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListIndexOutOfBoundsException("Tried to unmark task that is out of task list bounds");
        }
    }

    public String getListStorageText(){
        StringBuilder output = new StringBuilder();
        for (Task task : this.taskList){
            output.append(task.generateStorageForm() + System.lineSeparator());
        }
        return output.toString();
    }

    public String getTaskString(int index) {
        Task task = this.taskList.get(index - 1);
        return task.toString();
    }

    public String[] searchTaskDescription(String searchTerm) {
        ArrayList<String> matchingItems = new ArrayList<>();
        for (Task current : this.taskList) {
            String currentDescription = current.getDescription();
            String[] parsedDescription = currentDescription.split(" ");
            for (String word : parsedDescription) {
                if (word.equals(searchTerm)) {
                    matchingItems.add(current.toString());
                }
            }
        }
        String[] result = new String[matchingItems.size()];
        matchingItems.toArray(result);
        return result;
    }

    private void loadFromStorageText(ArrayList<String> storageText) {
        this.taskList = new ArrayList<>();
        String[] taskStringArray = storageText.toArray(new String[0]);

        for (String taskText : taskStringArray) {
            String[] parsedInput = taskText.split("@");

            boolean isDone = parsedInput[2].equals("X");
            switch (parsedInput[0]){
            case "T":
                this.taskList.add(new ToDo(parsedInput[1], isDone));
                break;
            case "D":
                this.taskList.add(new Deadline(parsedInput[1], isDone, LocalDate.parse(parsedInput[3])));
                break;
            case "E":
                this.taskList.add(new Event(parsedInput[1], isDone, LocalDate.parse(parsedInput[3]), LocalDate.parse(parsedInput[4])));
                break;
            }
        }
    }
}
