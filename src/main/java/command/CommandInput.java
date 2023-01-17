package command;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import exception.DukeIllegalArgumentException;
import manager.MainManager;

public class CommandInput {
    public static final String MAIN_INPUT_KEY = "MAIN_INPUT";

    private final HashMap<String, String> inputMap = new HashMap<>();
    private final MainManager mainManager;


    private CommandInput(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public static CommandInput parse(String args, MainManager mainManager) throws DukeIllegalArgumentException {
        CommandInput input = new CommandInput(mainManager);
        args = args.strip() + " ";
        if (args.isBlank()) {
            return input;
        }
        if (!args.startsWith("/" + MAIN_INPUT_KEY)) {
            args = String.format("/%s %s", MAIN_INPUT_KEY, args);
        }
        try (Scanner scanner = new Scanner(args)) {
            scanner.useDelimiter("[\\s]*/");
            while (scanner.hasNext()) {
                addInput(scanner.next(), input.inputMap);
            }
        }
        return input;
    }


    private static void addInput(String token, HashMap<String, String> inputMap)
            throws DukeIllegalArgumentException {
        try (Scanner scanner = new Scanner(token)) {
            String key = scanner.next();
            String value = "";
            if (scanner.hasNext()) {
                value = scanner.nextLine().strip();
            }
            inputMap.put(key, value);
        } catch (NoSuchElementException noElmEx) {
            throw new DukeIllegalArgumentException("Parameter name cannot be empty");
        }
    }


    public Optional<String> getMainInput() {
        return this.get(MAIN_INPUT_KEY);
    }


    public Optional<String> get(String key) {
        return Optional.ofNullable(inputMap.get(key));
    }


    public MainManager getMainManager() {
        return mainManager;
    }
}
