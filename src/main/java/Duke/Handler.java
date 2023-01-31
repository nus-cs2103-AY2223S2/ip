package Duke;

/**
 * Class to handle commands given to Duke.
 * @author Bryan Juniano
 */

public class Handler {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, EVENT, DEADLINE, FIND
    }
    public static Enum getCommand(String input){ //get the command
        return Commands.valueOf(input.split(" ")[0].toUpperCase());
    }

    public static String[] getParameters(String input){ //get all tokens after the command
        return input.split(" ", 2)[1].split(" ");
    }

    public static String[] parseParameters(Enum command, String[] parameters){ //get only the useful tokens
        if(command.equals(Commands.MARK) || command.equals(Commands.UNMARK) || command.equals(Commands.DELETE) ||
                command.equals(Commands.FIND)){
            String[] cleanedParameters = new String[1];
            cleanedParameters[0] = parameters[0];
            return cleanedParameters;
        }
        else if(command.equals(Commands.TODO)){
            String[] cleanedParameters = new String[1];
            cleanedParameters[0] = parameters[0];
            return cleanedParameters;

        }
        else if(command.equals(Commands.DEADLINE)){
            String[] cleanedParameters = new String[2];
            cleanedParameters[0] = parameters[0];
            cleanedParameters[1] = parameters[2];
            return cleanedParameters;

        }
        else if(command.equals(Commands.EVENT)){
            String[] cleanedParameters = new String[3];
            cleanedParameters[0] = parameters[0];
            cleanedParameters[1] = parameters[2];
            cleanedParameters[2] = parameters[4];
            return cleanedParameters;
        }
        else{
            return null;
        }
    }

    public String processCommand(String input, TaskList taskList){
        Enum command = getCommand(input);
        if (command.equals(Commands.BYE)) { // no parameters
            return "bai";
        } else if (command.equals(Commands.LIST)) {
            return taskList.listTasks();
        } else { //has parameters
            String[] parameters = parseParameters(command,getParameters(input));
            if (command.equals(Commands.MARK)) {
                return taskList.markTask(Integer.parseInt(parameters[0]));
            } else if (command.equals(Commands.UNMARK)) {
                return taskList.unmarkTask(Integer.parseInt(parameters[0]));
            } else if (command.equals(Commands.TODO)) {
                return taskList.addTask(parameters[0]);
            } else if (command.equals(Commands.DELETE)) {
                return taskList.deleteTask(Integer.parseInt(parameters[0]));
            } else if (command.equals(Commands.DEADLINE)) {
                return taskList.addTask(parameters[0], (parameters[1]));
            } else if (command.equals(Commands.EVENT)) {
                return taskList.addTask(parameters[0], parameters[1],
                        parameters[2]);
            } else if (command.equals(Commands.FIND)) {
                return taskList.findTasks(parameters[0]);
            }
        }
        return "";
    }
}
