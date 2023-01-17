import java.util.HashMap;

public class Command {
    private final String fullCommand;
    public final String baseCommand;
    public final String body;
    public final HashMap<String, String> namedParameters = new HashMap<>();

    public Command(String commandString) {
        this.fullCommand = commandString;
        String[] temp = commandString.split(" ", 2);
        this.baseCommand = temp[0];
        String rawBody = temp.length > 1 ? temp[1] : "";
        String body = "";

        boolean isFirstElement = true;
        for (String str : rawBody.split(" /")) {
            if (isFirstElement) {
                isFirstElement = false;
                body = str;
                continue;
            }
            String[] temp2 = str.split(" ", 2);
            namedParameters.put(temp2[0], temp2.length > 1 ? temp2[1] : null);
        }
        this.body = body;
    }

    @Override
    public String toString() {
        return fullCommand;
    }
}
