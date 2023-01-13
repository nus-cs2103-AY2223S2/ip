import java.util.HashMap;
import java.util.function.Supplier;

public abstract class Command {
    private final String name;
    private final String helpStr;

    private final String[] params;

    public Command(String name, String helpStr, String[] params) {
        this.name = name;
        this.helpStr = helpStr;
        this.params = params;
    }

    public String getHelpStr() {
        return helpStr;
    }

    public String getName() {
        return name;
    }

    public String[] getParams() {
        return params;
    }

    abstract String[] execute(String[] params);
}