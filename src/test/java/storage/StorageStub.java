package storage;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Simulates a Storage object that will not actually write to file
 */
public class StorageStub extends Storage {
    public StorageStub() {
        super("src/test/java/storage/duke.txt");
    }

    @Override
    public void writeToFile(TaskList taskList) {
        for (Task t: taskList.getList()) {
            System.out.println(t.toTxt() + System.lineSeparator());
        }
    }

    @Override
    public void appendToFile(Task task) {
        System.out.println(task.toTxt() + System.lineSeparator());
    }
}
