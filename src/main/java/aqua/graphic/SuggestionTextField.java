package aqua.graphic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import aqua.logic.command.Command;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;


/**
 * An extention of {@code TextField} with the additional feature to suggest
 * commands.
 */
public class SuggestionTextField extends TextField {
    private final ContextMenu suggestionMenu = new ContextMenu();


    public SuggestionTextField() {
        initialiseSuggestor();
    }


    private void initialiseSuggestor() {
        textProperty().addListener((ob, o, n) -> {
            if (n == null || n.isBlank()) {
                suggestionMenu.hide();
                return;
            }

            String inputCommand = n.split("\\s+")[0].toUpperCase();
            boolean isCompleteCommand = n.matches("[^\\s]+\\s+.*");
            List<MenuItem> menuSuggestions = getMenuSuggestions(inputCommand, isCompleteCommand);

            if (menuSuggestions.isEmpty()) {
                suggestionMenu.hide();
                return;
            }
            suggestionMenu.getItems().setAll(menuSuggestions);
            suggestionMenu.show(SuggestionTextField.this, Side.BOTTOM, 0, 0);
        });
    }


    private List<MenuItem> getMenuSuggestions(String inputCmd, boolean isCompleteCommand) {
        List<Command> cmdList = (isCompleteCommand) ?
                forceCommand(inputCmd) : getCommandSuggestions(inputCmd);
        return cmdList.stream()
                .map(command -> new MenuItem(command.name()))
                .collect(Collectors.toList());
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
}
