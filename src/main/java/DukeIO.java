import java.io.*;
import java.util.Arrays;

public class DukeIO extends PrintWriter {
    private BufferedReader bf;

    public enum EXTRACTFOR {
        TODO, EVENT, DEADLINE
    }

    public DukeIO(){
        super(new BufferedOutputStream(System.out));
        bf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readln() {
        String userInput = "";
        try{
            userInput = bf.readLine().trim();
        } catch (IOException e){
            System.out.println(String.format("DUKEIO : %s", e.getMessage()));
        }
        return userInput;
    }

    public int extractIndexParams(String userIn) throws DukeException {
        String[] userInSplit = userIn.split(" ",3);
        if (userInSplit.length < 2) {
            throw new DukeException.Missing.Parameter(userInSplit[0]);
        }
        try {
            int ind = Integer.parseInt(userInSplit[1]);
            return ind - 1; // Count starting from 0
        } catch (NumberFormatException e) {
            throw new DukeException.Invalid.Input(String.format("%s is not an integer", userInSplit[1]));
        }
    }
    public String[] extractTaskParams(String userIn, Duke.SWITCHTYPERELATED desire) throws DukeException {

        switch (desire) {
            case TODO: {
                // Check for descriptor
                String[] LHS = userIn.split(" ", 2);
                if (LHS.length < 2) {
                    throw new DukeException.Missing.Description(LHS[0]);
                }
                // Return descriptor
                return new String[]{ LHS[1].trim() };
            }

            case EVENT: {
                // Removal of 'event'
                String[] userInSplit = userIn.split(" ", 2);
                if (userInSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                }

                // Check for /from keyword
                String[] fromSplit = userInSplit[1].split("/from",3);
                if (fromSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                } else if (fromSplit.length > 2) {
                    throw new DukeException.Invalid.Input("Multiple `/from` detected, only one is allowed, please try again.");
                }

                // Check for descriptor
                if (fromSplit[0].isEmpty()) {
                    throw new DukeException.Missing.Description(userInSplit[0]);
                }

                // Check from /to keyword
                String[] toSplit = fromSplit[1].split("/to",3);
                if (toSplit.length < 2) {
                    throw new DukeException.Missing.Parameter(userInSplit[0]);
                } else if (toSplit.length > 2) {
                    throw new DukeException.Invalid.Input("Multiple `/to` detected, only one is allowed, please try again.");
                }

                return new String[]{ fromSplit[0].trim() , toSplit[0].trim(), toSplit[1].trim() };
            }

            case DEADLINE: {
                    // Check for /by keyword
                    String[] userInSplit = userIn.split("/by",3);
                    if (userInSplit.length < 2) {
                        throw new DukeException.Missing.Parameter(userInSplit[0]);
                    } else if (userInSplit.length > 2) {
                        throw new DukeException.Invalid.Input("Multiple `/by` detected, only one is allowed, please try again.");
                    }

                    // Check for descriptor
                    String[] LHS = userInSplit[0].split(" ", 2);
                    if (LHS.length < 2) {
                        throw new DukeException.Missing.Description(userInSplit[0]);
                    }
                    return new String[]{ LHS[1].trim() , userInSplit[1].trim()};
                }

            default:
                throw new DukeException.Invalid.Input("DIO - extractTaskParams - used unexpectedly");
        }

    }

    public void lb(){
        this.println("____________________________________________________________");
    }

}
