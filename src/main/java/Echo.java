import java.util.Objects;

public class Echo {
    public final String name;

    public Echo(String name) {
        this.name = name;
    }

    public String onCommand(String command) {
        if (Objects.equals(command, "bye")) {
            return null;
        } else {
            return command;
        }
    }
}
