package aqua.logic.parser;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


public class ArgumentParser implements Parser<ArgumentMap> {
    @Override
    public ArgumentMap parse(String input) throws IllegalSyntaxException {
        HashMap<String, String> inputMap = new HashMap<>();

        if (input.isBlank()) {
            return new ArgumentMap(inputMap);
        }

        input = input.strip() + " ";
        if (!input.startsWith("/" + ArgumentMap.MAIN_INPUT_KEY)) {
            input = String.format("/%s %s", ArgumentMap.MAIN_INPUT_KEY, input);
        }

        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("[\\s]*/");
            while(scanner.hasNext()) {
                addInput(scanner.next(), inputMap);
            }
        }

        return new ArgumentMap(inputMap);
    }

    
    private static void addInput(String token, HashMap<String, String> inputMap)
            throws IllegalSyntaxException {
        try (Scanner scanner = new Scanner(token)) {
            String key = scanner.next();
            String value = "";
            if (scanner.hasNext()) {
                value = scanner.nextLine().strip();
            }
            if (inputMap.containsKey(key)) {
                throw new IllegalSyntaxException("Duplicate parameters");
            }
            inputMap.put(key, value);
        } catch (NoSuchElementException noElmEx) {
            throw new IllegalSyntaxException("Parameter name disappeared!");
        }
    }
}
