import java.util.List;

public class Echo {
    private final String name;

    public Echo(String name) {
        this.name = name;
    }

    public String echo(String command) {
        return command;
    }

    public void printResponse(List<String> lines) {
        System.out.printf("| %s:%n", name);
        for (String line : lines) {
            System.out.println("| \t" + line);
        }
    }

    public void printResponse(String line) {
        this.printResponse(List.of(line));
    }
}
