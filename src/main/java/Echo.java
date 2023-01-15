import java.util.List;

public class Echo {
    private final String name;

    public Echo(String name) {
        this.name = name;
    }

    /**
     * Returns the given command as-is.
     * @param command The given command.
     * @return The given command.
     */
    public String echo(String command) {
        return command;
    }

    /**
     * Formats and prints multiple lines of response.
     * @param lines List of response lines.
     */
    public void printResponse(List<String> lines) {
        System.out.printf("| %s:%n", name);
        for (String line : lines) {
            System.out.println("| \t" + line);
        }
        System.out.print("> ");
    }

    /**
     * Formats and prints a single line of response.
     * @param line Response line.
     */
    public void printResponse(String line) {
        this.printResponse(List.of(line));
    }
}
