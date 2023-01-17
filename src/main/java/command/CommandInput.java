package command;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import manager.MainManager;

public class CommandInput {
    public static final String MAIN_INPUT_KEY = "MAIN_INPUT";

    private final HashMap<String, String> inputMap = new HashMap<>();
    private final MainManager mainManager;


    private CommandInput(MainManager mainManager) {
        this.mainManager = mainManager;
    }


    public static CommandInput parse(String args, MainManager mainManager) throws IllegalArgumentException {
        CommandInput input = new CommandInput(mainManager);
        if (args.isEmpty()) {
            return input;
        }
        try (Scanner scanner = new Scanner(args)) {
            scanner.useDelimiter("/");

            // main input
            input.inputMap.put(MAIN_INPUT_KEY, scanner.next());

            // rest of the input
            while (scanner.hasNext()) {
                addInput(scanner.next(), input.inputMap);
            }
        }
        return input;
    }


    private static void addInput(String token, HashMap<String, String> inputMap)
            throws IllegalArgumentException {
        try (Scanner scanner = new Scanner(token)) {
            String key = scanner.next();
            String value = scanner.nextLine();
            inputMap.put(key, value);
        } catch (NoSuchElementException noElmEx) {
            throw new IllegalArgumentException();
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
