import java.util.HashMap;
import java.util.function.Supplier;

public class BasicCommand extends Command {
    private final Supplier<String[]> supplier;

    public BasicCommand(String name, String helpStr, Supplier<String[]> supplier) {
        super(name, helpStr, new String[]{});
        this.supplier = supplier;
    }

    @Override
    String[] execute(String[] params) {
        return supplier.get();
    }
}
