package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void getStatusTest() throws IOException, DukeException {
        UiStub ui = new UiStub(new FunctionStub(new TaskListStub(), new StorageStub("test.txt")));
        String[] temp = {"bye"};
        ui.inpLine = temp;
        assertEquals(false, ui.action());
    }
}
