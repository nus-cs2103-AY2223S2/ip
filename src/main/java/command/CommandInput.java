package command;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.manager.AppManager;

public class CommandInput {
    public static final String MAIN_INPUT_KEY = "MAIN_INPUT";

    private final HashMap<String, String> inputMap = new HashMap<>();
    private final AppManager mainManager;


    private CommandInput(AppManager mainManager) {
        this.mainManager = mainManager;
    }


    public static CommandInput parse(String args, AppManager mainManager) throws IllegalSyntaxException {
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
            throws IllegalSyntaxException {
        try (Scanner scanner = new Scanner(token)) {
            String key = scanner.next();
            String value = "";
            if (scanner.hasNext()) {
                value = scanner.nextLine().strip();
            }
            inputMap.put(key, value);
        } catch (NoSuchElementException noElmEx) {
            throw new IllegalSyntaxException("Parameter name cannot be empty");
        }
    }


    public Optional<String> getMainInput() {
        return this.get(MAIN_INPUT_KEY);
    }


    public Optional<String> get(String key) {
        return Optional.ofNullable(inputMap.get(key));
    }


    public AppManager getMainManager() {
        return mainManager;
    }
}
