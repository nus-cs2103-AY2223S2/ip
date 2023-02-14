package duke.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class for parsing the input from a user into tokens that commands can use.
 */
public class Parser {
    public static Arguments parseIntoArguments(String[] args) {
        Map<String, String[]> output = new HashMap<>();

        int start = 1;
        while (start < args.length && !args[start].startsWith("/")) {
            start++;
        }

        String[] excess;
        if (start == 0) {
            excess = null;
        } else if (start == args.length) {
            excess = Arrays.copyOfRange(args, 1, args.length);
            return new Arguments(output, excess, args);
        } else {
            excess = Arrays.copyOfRange(args, 1, start);
        }

        int index = start;

        while (index < args.length) {
            while (index < args.length && !args[index].startsWith("/")) {
                index++;
            }

            String optionName = args[index].substring(1).toLowerCase();
            start = index;
            index++;

            while (index < args.length && !args[index].startsWith("/")) {
                index++;
            }
            String[] options = Arrays.copyOfRange(args, start + 1, index);
            output.put(optionName, options);
        }

        if (start < args.length - 1) {
            String optionName = args[start].substring(1).toLowerCase();
            String[] options = Arrays.copyOfRange(args, start + 1, args.length);
            output.put(optionName, options);
        }

        return new Arguments(output, excess, args);
    }
}
