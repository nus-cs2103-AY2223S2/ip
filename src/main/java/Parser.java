import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Parser {
    protected static Map<String,String> splitArgs(String input) {
        // returns a Map mapping name of arg to arg value based on the raw format
        // raw format according to task is /<name> <value>

        Map<String,String> map = new HashMap<String,String>();
        String[] args = input.split("/");

        // process main command
        String[] mainCommand = args[0].split(" ",2);
        map.put("Command", mainCommand[0]);
        String desc = mainCommand.length == 1 ? "" : mainCommand[1].stripTrailing();
        map.put(mainCommand[0], desc); // 0 refers to name of command, 1 refers to its additional desc

        // process additional args, if present
        if (args.length > 1) {
            List<String> addtlArgs = Arrays.asList(args).subList(1, args.length);
            for (String arg : addtlArgs) {
                String[] s = arg.split(" ", 2);
                String name = s[0];
                String value = s.length == 1 ? "" : s[1].stripTrailing();
                map.put(name, value);
            }
        }
        return map;
    }

    public static Command parse(String input) throws CommandNotFoundException {
        Map<String,String> argValues = Parser.splitArgs(input);
        String command = argValues.get("Command");

        switch (command) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkAsDoneCommand(argValues.get(command)); // pass in index to command
            case "unmark":
                return new MarkAsUndoneCommand(argValues.get(command)); // pass in index to command
            case "delete":
                return new DeleteCommand(argValues.get(command)); // pass in index to command
            case "bye":
                return new ExitCommand();
            default:
                if (TaskList.inputToTask.containsKey(command)) {
                    return new AddCommand(argValues);
                }
                throw new CommandNotFoundException("I'm sorry, I don't recognise this command ://");
        }
    }
}
