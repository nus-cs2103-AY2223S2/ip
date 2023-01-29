public class Parser {

    public Parser() {

    }

    public String convertToUserInput(String[] input, TypeOfTask action,String limiter ) throws DukeException{
        switch(action){
            case todo: {
                // changed the way the string is outputted from the array
                String userInput = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                if (userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.todo, 0);
                else
                    return userInput;
            }
            case deadline: { // added braces to scope the variables below locally to each case
                // algo to detect deadline's input content
                String userInput = "";
                if(!limiter.equals("/by")){
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/by")) {
                            break;
                        } else {
                            userInput += input[i] + " ";
                        }
                    }
                } else {
                    // to get the date and time after "/by"
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/by")) {
                            for(int j = i + 1; j < input.length; j++){
                                userInput += input[j] + " ";
                            }
                            break;
                        }
                    }
                }
                if(userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.deadline,0);
                else
                    return userInput;

            }
            case event: {
                String userInput = "";
                if(limiter.equals("/from")){
                    for(int i = 1; i < input.length; i++){
                        if(input[i].equals("/from")){
                            for(int j = i + 1; j < input.length; j++){
                                if(input[j].equals("/to")){
                                    break;
                                } else {
                                    userInput += input[j] + " ";
                                }
                            }
                            break;
                        }
                    }
                } else if (limiter.equals("/to")) {
                    for(int i = 1; i < input.length; i++){
                        if(input[i].equals("/to")){
                            for(int j = i + 1; j < input.length; j++){
                                userInput += input[j] + " ";
                            }
                            break;
                        }
                    }
                } else {
                    // to get the user's input before the "/from" limiter
                    for (int i = 1; i < input.length; i++) {
                        if (input[i].equals("/from")) {
                            break;
                        } else {
                            userInput += input[i] + " ";
                        }
                    }
                }
                if(userInput.equals("") || userInput == null)
                    throw new DukeException(TypeOfTask.event,0);
                else
                    return userInput;
            }
            case delete: {
                if(input.length == 1)
                    throw new DukeException(TypeOfTask.delete,0);
                else if(input.length > 2)
                    throw new DukeException(TypeOfTask.delete,1);
                else
                    return input[1];
            }
            default:
                throw new DukeException();
        }
    }

    /*
        convert the beginning of the user's input into a TypeOfTask enum type
     */
    public TypeOfTask convertToAction(String input) throws DukeException {
        switch(input){
            case "mark":
                return TypeOfTask.mark;
            case "unmark":
                return TypeOfTask.unmark;
            case "list":
                return TypeOfTask.list;
            case "bye":
            case "quit":
                return TypeOfTask.bye;
            case "todo":
                return TypeOfTask.todo;
            case "deadline":
                return TypeOfTask.deadline;
            case "event":
                return TypeOfTask.event;
            case "delete":
                return TypeOfTask.delete;
            default: throw new DukeException();
        }
    }
}
