package seedu.duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Responsible for parsing user inputs into individual components
 */
public class Parser {

    private static Function<String, HashMap<String, String>> todoParser = createTodoParser();
    private static Function<String, HashMap<String, String>> deadlineParser = createDeadlineParser();
    private static Function<String, HashMap<String, String>> eventParser = createEventParser();
    private static Function<String, HashMap<String, String>> tagParser = createTagParser();

    /**
     * Creates a parser that is able to take in a String and parse it into components.
     * The components are defined by the keywords.
     * @param keywords Defines the components to parse
     * @return The created parser
     */
    private static Function<String, HashMap<String, String>> createParser(ArrayList<String> keywords) {
        return (String chat) -> {
            int waitingForKeyword = 0;
            String currentKey = "";
            String currentValue = "";
            HashMap<String, String> parsed = new HashMap<>();
            for (String word : chat.split(" ")) {
                if (waitingForKeyword < keywords.size() && word.equals(keywords.get(waitingForKeyword))) {
                    if (currentKey != "") {
                        parsed.put(currentKey, currentValue.trim());
                    }
                    currentKey = keywords.get(waitingForKeyword++);
                    currentValue = "";
                } else {
                    currentValue += word + " ";
                }
            }
            parsed.put(currentKey, currentValue.trim());
            assert parsed.size() == keywords.size() : "Parser should be of length (keywords)";
            return parsed;
        };
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "ToDo"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createTodoParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("todo"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "deadline", "/by"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createDeadlineParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("deadline", "/by"));
        return createParser(toParse);
    }

    /**
     * Creates a parser that converts a user inputted String into a HashMap containing: "event", "/from", "/to"
     * @return created parser
     */
    private static Function<String, HashMap<String, String>> createEventParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("event", "/from", "/to"));
        return createParser(toParse);
    }

    private static Function<String, HashMap<String, String>> createTagParser() {
        ArrayList<String> toParse = new ArrayList<>(Arrays.asList("/tag"));
        return createParser(toParse);
    }

    /**
     * Parses input from user
     * @param chat user input
     * @return parsed input
     */
    public static HashMap<String, String> parseTodo(String chat) {
        HashMap<String, String> parsed = todoParser.apply(chat);
        assert parsed.size() == 1 : "Parsed should be of length 1";
        return parsed;
    }

    /**
     * Parses input from user
     * @param chat user input
     * @return parsed input
     */
    public static HashMap<String, String> parseDeadline(String chat) {
        HashMap<String, String> parsed = deadlineParser.apply(chat);
        assert parsed.size() == 2 : "Parsed should be of length 2";
        return parsed;
    }

    /**
     * Parses input from user
     * @param chat user input
     * @return parsed input
     */
    public static HashMap<String, String> parseEvent(String chat) {
        HashMap<String, String> parsed = eventParser.apply(chat);
        assert parsed.size() == 3 : "Parsed should be of length 3";
        return parsed;
    }

    public static HashMap<String, String> parseTag(String chat) {
        return tagParser.apply(chat);
    }

    /**
     * Converts String to LocalDateTime
     * @param by String inputted by user
     * @return LocalDateTime generated
     */
    public static LocalDateTime stringToDate(String by) {
        String[] date = by.split(" ")[0].split("/");
        int day = Integer.valueOf(date[0]);
        int month = Integer.valueOf(date[1]);
        int year = Integer.valueOf(date[2]);
        String time = by.split(" ")[1];
        int hour = Integer.valueOf(time.substring(0, 2));
        int minute = Integer.valueOf(time.substring(2, 4));
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        return dateTime;
    }
}
