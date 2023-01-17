import java.util.Arrays;
import java.util.stream.Stream;

public class UI {

    private String[] helpMsg;

    public void setCommands(Command[] commands) {
        this.helpMsg = generateHelp(commands);
    }

    private String[] generateHelp(Command[] commands) {
        Stream<String> strings = Arrays.stream(commands)
                .map(c -> String.format("\t%4s : %s", c.getName(), c.getHelpStr()));
        return Stream.concat(Stream.of("Usage: <command> [<args>]"), strings).toArray(String[]::new);
    }

    public void print() {
        this.print(this.helpMsg);
    }

    public void print(String string){
        System.out.println(string);
    }

    public void print(String[] strings) {
        StringBuilder outputs = new StringBuilder();
        for (String str : strings) {
            outputs.append("\t").append(str).append("\n");
        }
        System.out.print(outputs);
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ,\n";
        System.out.println("Hello, I'm\n" + logo + "how may I help?");
    }

    public void error(Exception e) {
        System.out.println("\t[ERROR] " + e);
    }

    public void loadError(Exception e) {
        System.out.println("\t[ERROR] While loading, the following error occurred: \n\t" + e);
    }
}
