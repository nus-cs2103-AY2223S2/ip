import java.util.ArrayList;

public class Record {
    private ArrayList<Task> list;
    public Record() {
        this.list = new ArrayList<>();
    }
    public void add(String input) {
        Task t;
        StringBuilder sb = new StringBuilder();
        switch(input.charAt(0)) {
            case 'd':
                String[] dArr = input.split(" ");
                int i = 1;

                while(!dArr[i].equals("/by")) {
                    sb.append(dArr[i]);
                    sb.append(" ");
                    i++;
                }
                String descriptionD = sb.toString().substring(0, sb.length()-1);
                sb.setLength(0);

                while (i < dArr.length) {
                    sb.append(dArr[i]);
                    sb.append(" ");
                    i++;
                }
                String deadline = sb.toString().substring(4, sb.length()-1);

                t = new Deadline(descriptionD, deadline);
                break;
            case 'e':
                String[] eArr = input.split(" ");
                int j = 1;

                while(!eArr[j].equals("/from")) {
                    sb.append(eArr[j]);
                    sb.append(" ");
                    j++;
                }
                String descriptionE = sb.toString().substring(0, sb.length()-1);
                sb.setLength(0);

                while(!eArr[j].equals("/to")) {
                    sb.append(eArr[j]);
                    sb.append(" ");
                    j++;
                }
                String start = sb.toString().substring(6, sb.length()-1);
                sb.setLength(0);

                while(j < eArr.length) {
                    sb.append(eArr[j]);
                    sb.append(" ");
                    j++;
                }
                String end = sb.toString().substring(4, sb.length()-1);

                t = new Event(descriptionE, start, end);
                break;
            default:
                t = new ToDo(input.substring(5));
                break;
        }
        this.list.add(t);
    }
    public void mark(int index) {
        this.list.get(index).mark();
    }
    public void unmark(int index) {
        this.list.get(index).unmark();
    }
    public String taskToString(int index) {
        return this.list.get(index).toString();
    }
    public String latestTaskToString() {
        return this.list.get(list.size()-1).toString();
    }
    public int getUncompletedSize() {
        int count = 0;
        for (int i = 0; i < this.list.size(); i++) {
            if (!this.list.get(i).isComplete()) {
                count++;
            }
        }
        return count;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append("\n    ");
            }
            sb.append((i+1) + ". " + this.taskToString(i));
        }
        return sb.toString();
    }
}