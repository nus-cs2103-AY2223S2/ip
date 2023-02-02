package aqua.graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aqua.logic.command.Command;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * An extention of {@code TextField} with the additional feature to suggest
 * commands.
 */
public class SuggestionTextField extends TextField {
    private final ContextMenu suggestionMenu = new ContextMenu();
    private final ObjectProperty<UserCommand> userCommand = new SimpleObjectProperty<>();


    public SuggestionTextField() {
        initialiseSuggestionMenu();
        initialiseSuggestor();
    }


    private void initialiseSuggestionMenu() {
        suggestionMenu.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.SPACE) || e.getCode().equals(KeyCode.ENTER)) {
                e.consume();
            }
        });
    }


    private void initialiseSuggestor() {
        textProperty().addListener(this::handleTextChange);
        userCommand.addListener(this::handleCommandChange);
    }


    private void handleTextChange(ObservableValue<? extends String> observable, String oldText, String newText) {
        if (newText == null) {
            suggestionMenu.hide();
            return;
        }
        String commandString = newText.split("\\s+", 2)[0].toUpperCase();
        boolean isComplete = newText.matches("[^\\s]+\\s+.*");
        userCommand.set(new UserCommand(commandString, isComplete));
    }


    private void handleCommandChange(
                ObservableValue<? extends UserCommand> observable, UserCommand oldCmd, UserCommand newCmd) {
        if (newCmd == null) {
            suggestionMenu.hide();
            return;
        }
        List<MenuItem> menuItems = getMenuSuggestions(newCmd.commandString, newCmd.isComplete);
        suggestionMenu.getItems().setAll(menuItems);
        if (!suggestionMenu.isShowing()) {
            suggestionMenu.show(SuggestionTextField.this, Side.BOTTOM, 0, 0);
        }
    }


    private List<MenuItem> getMenuSuggestions(String inputCmd, boolean isCompleteCommand) {
        if (inputCmd.isBlank()) {
            return List.of();
        }
        List<Command> cmdList = (isCompleteCommand)
                ? forceCommand(inputCmd)
                : getCommandSuggestions(inputCmd);
        return cmdList.stream()
                .map(command -> getSuggestionMessage(command, isCompleteCommand))
                .map(message -> new MenuItem(message))
                .collect(Collectors.toList());
    }


    private String getSuggestionMessage(Command command, boolean isComplete) {
        String message = command.name();
        if (isComplete) {
            message += " " + command.getSyntax();
        } else {
            message += " - " + command.getDescription();
        }
        return message.strip();
    }


    private List<Command> forceCommand(String inputCmd) {
        try {
            return List.of(Command.valueOf(inputCmd));
        } catch (IllegalArgumentException illArgEx) {
            return List.of();
        }
    }


    private List<Command> getCommandSuggestions(String inputCmd) {
        ArrayList<Command> commandList = new ArrayList<>();
        for (Command command : Command.values()) {
            if (command.toString().contains(inputCmd)) {
                commandList.add(command);
            }
        }
        return commandList;
    }





    private static class UserCommand {
        public final String commandString;
        public final boolean isComplete;


        UserCommand(String commandString, boolean isComplete) {
            this.commandString = commandString;
            this.isComplete = isComplete;
        }
    }
}
