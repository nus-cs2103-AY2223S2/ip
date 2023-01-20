package formatters;

public class Parser {

    public int parseCommand(String input) throws IllegalArgumentException {
        String[] segments = input.split(" ", 2);

        if (segments.length <= 1) {
            throw new IllegalArgumentException("Command must contain additional information");
        }
        int result;
        try {
            result = Integer.parseInt(segments[1]);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Unable to parse user input");
        }
        return result;
    }

}
