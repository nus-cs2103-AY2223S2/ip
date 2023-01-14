import java.util.HashMap;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        DataStore data = new DataStore();
        Formatter formatter = new Formatter();
        Command[] commands = {
                new BasicCommand("exit"
                        , "exit the app"
                        , () -> new String[]{"Goodbye."}),
                new BasicCommand("help"
                        , "show this help message"
                        , () -> {
                    formatter.print();
                    return new String[]{};
                }),
                new BasicCommand("list"
                        , "list tasks"
                        , data::stringify),
                new ArgCommand("add"
                        , "add task"
                        , new String[]{""}
                        , data::add),
                new ArgCommand("mark"
                        , "mark/unmark task as done"
                        , new String[]{""}
                        , data::mark),
        };
        formatter.setCommands(commands);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ,\n";
        System.out.println("Hello from\n" + logo + "how may I help?");

        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String[] inputs = scanner.nextLine().split("\\s",2);
            try {
                String[] outputs = Duke.parse(commands, inputs);
                formatter.print(outputs);
                if (inputs[0].equals("exit")){
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t"+e.getMessage());
                formatter.print();
            }
        }
    }

    private static String[] parse(Command[] commands, String[] inputs) {
        for (Command cmd: commands) {
            if (inputs[0].equals(cmd.getName())){
                String[] params = new String[cmd.getParams().length];
                if (cmd.getParams().length > 0) {
                    int argsNeeded = 0;
                    while (argsNeeded < cmd.getParams().length - 1 && inputs.length > 1) {
                        inputs = inputs[1].split(cmd.getParams()[++argsNeeded]);
                        params[argsNeeded - 1] = inputs[0];
                    }
                    try {
                        params[argsNeeded] = inputs[1];
                    } catch (IndexOutOfBoundsException e) {
                        throw new IllegalArgumentException("Missing argument: " + cmd.getParams()[argsNeeded]);
                    }
                }
                return cmd.execute(params);
            }
        }
        throw new IllegalArgumentException("Command not found: " + inputs[0]);
    }
}