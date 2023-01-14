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
                        , new String[]{"\\s"}
                        , data::add),
                new ArgCommand("mark"
                        , "mark/unmark task as done"
                        , new String[]{}
                        , data::mark)
        };
        formatter.setCommands(commands);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| ,\n";
        System.out.println("Hello, I'm\n" + logo + "how may I help?");

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
                System.out.println("\t" + e.getMessage());
                formatter.print();
            }
        }
    }

    private static String[] parse(Command[] commands, String[] inputs) {
        for (Command cmd: commands) {
            if (inputs[0].equals(cmd.getName())){
                String[] args = new String[0];
                if (inputs.length > 1) {
                     args = Duke.split(inputs[1], cmd.getParams());
                }
                return cmd.execute(args);
            }
        }
        throw new IllegalArgumentException("Command not found: " + inputs[0]);
    }

    public static String[] split(String input, String[] regexes) {
        String[] outputs = new String[regexes.length + 1];
        for (int i = 0; i < regexes.length; i++) {
            String[] temp = input.split(regexes[i],2);
            if (temp.length > 1){
                outputs[i] = temp[0];
                input = temp[1];
            } else {
                throw new IllegalArgumentException("Missing argument.");
            }
        }
        outputs[regexes.length] = input;
        return outputs;
    }
}