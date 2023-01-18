/**
 * Class that stores all resource values statically.
 */
public final class Resource {
    public final static String cmdExit = "bye";
    public final static String cmdList = "list";
    public final static String notifAdd = " added: ";
    public final static String logo = " __  __\n|  \\/  |\n| \\  / | ___  __ _  __ _ _   _\n| |\\/| |/ _ \\/ _` |/ _` | | | |\n| |  | |  __/ (_| | (_| | |_| |\n|_|  |_|\\___|\\__, |\\__, |\\__, |\n              __/ | __/ | __/ |\n             |___/ |___/ |___/\n";
    public final static String msgHd = "------------------------------------------------------------\n";
    public final static String msgTl = msgHd + "\n>";
    public final static String greetings = "Wommy! Get REKT by the upcoming star of Inkopolis, Meggy!\n";
    public final static String farewell = "OK gotta go play more Turf Wars. Have a nice day!";
    /**
     * @deprecated Class that stores all resource values statically. Should not be initialized.
     */
    Resource() {
    }
    /**
     * Format to print array index number.
     * */
    public final static String idxFmt(int i){
        return " "+(i+1)+". ";
    }
}
