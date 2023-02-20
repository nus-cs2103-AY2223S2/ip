package DukeHelpfulCode.Tasks;

public class Task {
    /**
     * Task class for things added to the TaskList
     * Methods:
     *      markAsDone -> mark Task as done if not done and vice versa.
     */

    private static String LINEBREAK = "_________________________________________________________________\n";
    private String name;
    private Boolean isDone;
    private String type;

    public Task (String name, String type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
    }

    public Task (String name, String type, boolean isDone){
        this.name = name;
        this.type = type;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone(){
        return isDone;
    }

    public String toString() {
        /**
         * Returns "[done] Task"
         */
        char doneX = ' ';
        if (isDone) {
            doneX = 'X';
        }
        return "[" + doneX + "]" + " " + name;
    }

    public boolean equals(Object obj){
        if (obj instanceof Task) {
            Task objTask = (Task) obj;
            return objTask.name.equals(this.name);
        } else {
            return false;
        }
    }

    public String mark() {
        /**
         * Marks Task as done if not done and vice versa.
         */
        if (isDone) {
            isDone = false;
            return LINEBREAK + "OK, '" + this.name + "' has been marked as Not Done.\n";

        } else {
            isDone = true;
            return LINEBREAK + "OK, '" + this.name + "' has been marked as Done.\n";

        }
    }

}