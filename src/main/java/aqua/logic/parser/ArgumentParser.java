package aqua.logic.parser;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aqua.exception.IllegalSyntaxException;
import aqua.logic.ArgumentMap;


/** A parser to parse a String into a {@code ArgumentMap}. */
public class ArgumentParser implements Parser<ArgumentMap> {
    @Override
    public ArgumentMap parse(String input) throws IllegalSyntaxException {
        // initialize input map
        HashMap<String, String> inputMap = new HashMap<>();

        // check if input is blank, return empty map if it is
        if (input.isBlank()) {
            return new ArgumentMap(inputMap);
        }

        // format main input to fit argument parsing syntax
        input = input.strip() + " ";
        if (!input.startsWith("/" + ArgumentMap.TAG_MAIN_INPUT)) {
            input = String.format("/%s %s", ArgumentMap.TAG_MAIN_INPUT, input);
        }

        // parse tags
        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter("[\\s]*/");
            while (scanner.hasNext()) {
                addInput(scanner.next(), inputMap);
            }
        }

        // create and return argument map
        return new ArgumentMap(inputMap);
    }


    private static void addInput(String token, HashMap<String, String> inputMap)
                throws IllegalSyntaxException {
        try (Scanner scanner = new Scanner(token)) {
            // parse tag
            String key = scanner.next();

            // parse tag value
            String value = "";
            if (scanner.hasNext()) {
                value = scanner.nextLine().strip();
            }

            // check for duplicates
            if (inputMap.containsKey(key)) {
                throw new IllegalSyntaxException("Duplicate parameters");
            }

            // add tag(key) - value pair to map
            if (!value.isBlank()) {
                inputMap.put(key, value);
            }
        } catch (NoSuchElementException noElmEx) {
            throw new IllegalSyntaxException("Parameter name disappeared!");
        }
    }
}
