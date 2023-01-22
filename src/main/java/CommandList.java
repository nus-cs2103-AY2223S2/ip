public class CommandList extends Command{
    @Override
    public void handle() throws DukeException{
        // ERROR: list format is anything other than [ list ]
        if (Duke.userScan.nextLine().length()>0) {
            throw new DukeException(Duke.ui.formatCommandError("list",
                    "list"));
        }
        if (Duke.taskList.size()==0) {
            Duke.ui.print("You don't have anything to do right now!");
        }
        else {
            StringBuilder toPrint = new StringBuilder();
            for (int i = 1; i < Duke.taskList.size()+1; i++) {
                toPrint.append(i).append(". ").append(Duke.taskList.get(i-1).toString());
                if (i < Duke.taskList.size()){
                    toPrint.append("\n");
                }
            }
            Duke.ui.print("Here are your tasks:\n" + toPrint);
        }
    }
}
