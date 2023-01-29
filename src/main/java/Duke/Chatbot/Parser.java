package Duke.Chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

public class Parser {
    public static final Map<String, BiPredicate<Chatbot, String>> commands
            = new HashMap<String, BiPredicate<Chatbot, String>>();
    private final Chatbot chatbot;
    private final Ui ui;

    public Parser(Chatbot chatbot, Ui ui) {
        this.chatbot = chatbot;
        this.ui = ui;
        setupCommands();
    }

    private void setupCommands() {
        commands.put(Messages.MESSAGE_END, (chatbot, args) -> {
            System.out.println(Messages.MESSAGE_END);
            chatbot.isActive = false;
            return true;
        });

        commands.put(Messages.MESSAGE_LIST, (chatbot, args) -> {
            chatbot.printTasks();
            return true;
        });

        commands.put(Messages.MESSAGE_MARK, (chatbot, args) -> {

            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexNotSpecifiedError();

                return true;
            }
            index -= 1;

            chatbot.markAsComplete(index);


            return true;

        });
        commands.put(Messages.MESSAGE_UNMARK, (chatbot, args) -> {

            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexError();
                return true;
            }

            index -= 1;
            chatbot.markAsIncomplete(index);
            return true;
        });

        commands.put(Messages.MESSAGE_TODO, (chatbot, args) -> {
            if (args.trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }
            chatbot.addTask(TaskList.Tasktype.TODO, args.trim());
            return true;
        });

        commands.put(Messages.MESSAGE_DEADLINE, (chatbot, args) -> {
            String[] inputs = args.split("/by", 2);

            if (inputs.length != 2) {
                ui.showIncompleteDeadlineArgumentsError();
                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }

            chatbot.addTask(TaskList.Tasktype.DEADLINE, inputs[0].trim(), inputs[1].trim());

            return true;
        });

        commands.put(Messages.MESSAGE_EVENT, (chatbot, args) -> {
            String[] inputs = args.split("(/from | /to)", 3);

            if (inputs.length != 3) {
                ui.showIncompleteEventArgumentsError();
                return true;
            } else if (inputs[0].trim() == "" || inputs[1].trim() == "" || inputs[2].trim() == "") {
                ui.showEmptyDescriptionError();
                return true;
            }

            chatbot.addTask(TaskList.Tasktype.EVENT, inputs[0].trim(), inputs[1].trim(), inputs[2].trim());

            return true;
        });

        commands.put(Messages.MESSAGE_DELETE, (chatbot, args) -> {
            Integer index = -1;
            try {
                index = Integer.valueOf(args);
            } catch (NumberFormatException ex) {
                ui.showIndexError();
                return true;
            }
            index -= 1;
            chatbot.removeTask(index);

            return true;
        });

        commands.put(Messages.MESSAGE_DELETE_ALL_DATA, (chatbot, args) -> {
            chatbot.removeAllTasks();
            return true;
        });

    }

    public void parse(String nextLine) {
        //Assuming commands start with a space.
        String[] userCommand = nextLine.split(" ", 2);
        String upperCaseUserCommand = userCommand[0].toUpperCase();
        if (commands.containsKey(upperCaseUserCommand)) {
            boolean processedCommandState = commands.get(upperCaseUserCommand)
                    .test(chatbot, userCommand.length > 1 ? userCommand[1] : "");
            if (!processedCommandState) {
                chatbot.isActive = false;
                throw new RuntimeException();
            }
        } else {
            //Command not found.
            ui.showUnrecognisedCommandError();
        }
    }

}
