import java.util.Scanner;

public class Connor {

    private static String getCommand(String input) {
        if (input.indexOf(' ') == -1) {
            return input.toUpperCase();
        } else {
            return input.substring(0, input.indexOf(' ')).toUpperCase();
        }
    }

    private static String getDirective(String input) {
        return input.substring(input.indexOf(' ') + 1, input.length());
    }

    private static String[] getDirectiveTimePair(String input) {
        int slashIndex = input.indexOf('/');
        String[] pair = new String[2];
        pair[0] = input.substring(0, slashIndex - 1);
        pair[1] = input.substring(slashIndex + 1, input.length());
        return pair;
    }

    private static String[] getStartEndTime(String input) {
        int slashIndex = input.indexOf('/');
        String[] pair = new String[2];
        pair[0] = input.substring(0, slashIndex - 1);
        pair[1] = input.substring(slashIndex + 1, input.length());
        return pair;
    }

    public static void main(String[] args) {
        String name = "Connor";
        System.out.println("        Hello! I'm " + name + ", the android sent by Cyberlife");
        System.out.println("        Please type in your command below.");
        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String command = getCommand(input);
            if (command.equals("HI")) {
                Response.greetings("HI");
            } else if (command.equals("BYE")) {
                Response.greetings("BYE");
                sc.close();
                break;
            } else if (command.equals("MARK")) {
                String numberMark = getDirective(input);
                list.markDone(Integer.valueOf(numberMark));
            } else if (command.equals("UNMARK")) {
                String numberUnmark = getDirective(input);
                list.markUndone(Integer.valueOf(numberUnmark));
            } else if (command.equals("LIST")) {
                list.getList();
            } else if (command.equals("TODO")) {
                TODO todo = new TODO(getDirective(input));
                list.addTask(todo);
            } else if (command.equals("DEADLINE")) {
                String[] pair = getDirectiveTimePair(getDirective(input));
                String taskName = pair[0];
                String time = pair[1];
                String exactTime = time.substring(time.indexOf(' ') + 1, time.length());
                Deadline deadline = new Deadline(taskName, exactTime);
                list.addTask(deadline);
            } else if (command.equals("EVENT")){
                String[] pair = getDirectiveTimePair(getDirective(input));
                String taskName = pair[0];
                String[] timePair = getStartEndTime(pair[1]);
                String startTime = timePair[0];
                String endTime = timePair[1];
                String exactStartTime = startTime.substring(startTime.indexOf(' ') + 1, startTime.length());
                String exactEndTime = endTime.substring(endTime.indexOf(' ') + 1, endTime.length());
                Event event = new Event(taskName, exactStartTime, exactEndTime);
                list.addTask(event);
            } else {
                Response.printMessage("Sorry, My program is unable to process your request");
            }
        }
    }
}
