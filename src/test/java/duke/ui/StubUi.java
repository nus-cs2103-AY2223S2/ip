package duke.ui;

import java.util.ArrayList;

public class StubUi implements Ui {
    private ArrayList<String> buffer;

    public StubUi() {
        this.buffer = new ArrayList<>();
    }

    @Override
    public void showText(String text) {
        buffer.add(text);
    }

    @Override
    public void showLine() {
        // Do nothing.
    }

    @Override
    public void showStartup() {
        // Do nothing.
    }

    public ArrayList<String> getBuffer() {
        return buffer;
    }
}
