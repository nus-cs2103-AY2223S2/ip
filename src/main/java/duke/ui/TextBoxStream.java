package duke.ui;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

/**
 * Wrapper around an OutputStream to direct output to a TextArea
 */
public class TextBoxStream extends OutputStream {
    private final TextArea textBox;

    private final StringBuffer buffer;

    public TextBoxStream(TextArea textBox) {
        this.textBox = textBox;
        buffer = new StringBuffer();
    }

    @Override
    public void write(int b) throws IOException {
        buffer.append((char) b);
    }

    @Override
    public void flush() {
        textBox.appendText(buffer.toString());
        buffer.setLength(0);
    }
}
