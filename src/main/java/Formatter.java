import java.util.Arrays;

public class Formatter {
    public void printHelp(Command[] commands) {
        String[] strings = Arrays.stream(commands)
                                .map(c -> String.format("\t%s\t: %s\n", c.getName(), c.getHelpStr()))
                                .toArray(String[]::new);
        System.out.println("\tUsage: <command> [<args>]");
        this.print(strings);
    }

    public void print(String[] strings) {
        StringBuilder outputs = new StringBuilder();
        for (String str : strings) {
            outputs.append("\t").append(str);
        }
        System.out.println(outputs);
    }
}
