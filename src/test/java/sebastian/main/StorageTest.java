package sebastian.main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sebastian.exceptions.CannotLoadDataException;

public class StorageTest {
    @Test
    public void loadDataTest() {
        try {
            Storage storage = new Storage("SebastianData.txt");
            TaskList taskList = storage.formTaskListFromData();
            Assertions.assertEquals(taskList.getTotalTasks(), 8);
        } catch (CannotLoadDataException e) {
            e.printStackTrace();
        }

    }
}
