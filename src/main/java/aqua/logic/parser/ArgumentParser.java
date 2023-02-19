package aqua.logic.parser;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

import aqua.exception.SyntaxException;
import aqua.logic.ArgumentMap;


/** A parser to parse a String into a {@code ArgumentMap}. */
public class ArgumentParser implements Parser<ArgumentMap> {
    private static final String DELIMITER_PATTERN = "[\\s]*/";


    @Override
    public ArgumentMap parse(String input) throws SyntaxException {
        HashMap<String, String> inputMap = new HashMap<>();

        if (input.isBlank()) {
            return new ArgumentMap(inputMap);
        }

        // add main input tag if not present
        input = input.strip() + " ";
        if (!input.startsWith("/" + ArgumentMap.TAG_MAIN_INPUT)) {
            input = String.format("/%s %s", ArgumentMap.TAG_MAIN_INPUT, input);
        }

        try (Scanner scanner = new Scanner(input)) {
            scanner.useDelimiter(DELIMITER_PATTERN);
            while (scanner.hasNext()) {
                addInput(scanner.next(), inputMap);
            }
        }

        return new ArgumentMap(inputMap);
    }


    private static void addInput(String token, HashMap<String, String> inputMap)
                throws SyntaxException {
        try (Scanner scanner = new Scanner(token)) {
            String key = scanner.next();

            String value = "";
            if (scanner.hasNext()) {
                value = scanner.nextLine().strip();
            }

            if (inputMap.containsKey(key)) {
                throw new SyntaxException("Duplicate parameters");
            }

            if (!value.isBlank()) {
                inputMap.put(key, value);
            }
        } catch (NoSuchElementException noElmEx) {
            throw new SyntaxException("Empty parameter!");
        }
    }
}
