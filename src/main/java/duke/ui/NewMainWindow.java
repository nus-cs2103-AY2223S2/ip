package duke.ui;

import java.util.function.Supplier;
import java.util.stream.Stream;

import duke.App;
import duke.Duke;
import duke.commands.Command;
import duke.commands.ListCommand;
import duke.commands.Parser;
import duke.commands.SaveCommand;
import duke.commands.indexedCommand.DeleteCommand;
import duke.commands.indexedCommand.MarkCommand;
import duke.commands.indexedCommand.UnmarkCommand;
import duke.commands.taskCommand.DeadlineCommand;
import duke.commands.taskCommand.EventCommand;
import duke.commands.taskCommand.TodoCommand;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewMainWindow {
    private Duke instance;
    private Parser parser;

    public void setDuke(Duke instance) {
        this.instance = instance;

        this.parser = new Parser(Stream.<Supplier<Command>>of(
            ListCommand::new,
            MarkCommand::new,
            UnmarkCommand::new,
            TodoCommand::new,
            DeadlineCommand::new,
            EventCommand::new,
            DeleteCommand::new,
            SaveCommand::new
        ).map(func -> func.get().setOutputFunc((string) -> outputTextBox.appendText(string + "\n")))) {
            @Override
            public void output(String str) {
                outputTextBox.appendText(str + "\n");
            }
        };
    }

    @FXML
    private TextArea outputTextBox;

    @FXML
    private TextField inputTextBox;

    @FXML
    private void closeDuke() {
        App.sendCloseRequest();
    }

    @FXML
    private void onSubmit() {
        String inputValue = inputTextBox.getText();
        if (inputValue == null || inputValue.isEmpty()) {
            // Ignore blank lines
            return;
        }

        outputTextBox.appendText(inputValue + "\n");
        System.out.println(inputValue);
        parser.accept(inputValue, instance);
        inputTextBox.clear();
    }
}
