package chattime.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void getExecuteStatus() {
        Ui test = new Ui();
        assertTrue(test.getExecuteStatus());
    }
}
