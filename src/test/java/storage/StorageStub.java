package storage;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class StorageStub extends Storage {
    public StorageStub() {
        super("src/test/java/storage/duke.txt");
    }

    @Override
    public void writeToFile(TaskList taskList) {
        for (Task t: taskList.getList()) {
            System.out.println(t.toTXT() + System.lineSeparator());
        }
    }

    @Override
    public void appendToFile(Task task) {
        System.out.println(task.toTXT() + System.lineSeparator());
    }
}
