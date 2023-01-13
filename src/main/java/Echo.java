import java.util.List;

public class Echo {
    private final String name;

    public Echo(String name) {
        this.name = name;
    }

    public String echo(String command) {
        return command;
    }

    public void printLines(List<String> lines) {
        System.out.println("------");
        System.out.printf("%s:%n", name);
        for (String line : lines) {
            System.out.println("\t" + line);
        }
        System.out.println("------");
    }

    public void printLine(String line) {
        this.printLines(List.of(line));
    }
}
