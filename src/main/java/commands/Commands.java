package commands;
import data.MyData;
public abstract class Commands {
    private String line = "    ____________________________________________________________\n";

    public abstract void execute(MyData data);

    public String getLine() {
        return line;
    }
}
