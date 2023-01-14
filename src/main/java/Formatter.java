import java.util.Arrays;
import java.util.stream.Stream;

public class Formatter {

    private String[] helpMsg;

    public void setCommands(Command[] commands) {
        this.helpMsg = generateHelp(commands);
    }

    private String[] generateHelp(Command[] commands) {
        Stream<String> strings = Arrays.stream(commands)
                .map(c -> String.format("\t%s\t: %s", c.getName(), c.getHelpStr()));
        return Stream.concat(Stream.of("Usage: <command> [<args>]"), strings).toArray(String[]::new);
    }

    public void print() {
        this.print(this.helpMsg);
    }

    public void print(String[] strings) {
        StringBuilder outputs = new StringBuilder();
        for (String str : strings) {
            outputs.append("\t").append(str).append("\n");
        }
        System.out.print(outputs);
    }
}
