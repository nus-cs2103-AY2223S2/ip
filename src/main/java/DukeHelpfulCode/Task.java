package DukeHelpfulCode;

public class Task {
    /**
     * Task class for things added to the UserList
     * Methods:
     *      markAsDone -> mark Task as done if not done and vice versa.
     */

    private static String LINEBREAK = "_________________________________________________________________\n";
    private String name;
    private Boolean isDone = false;

    public Task (String name) {
        this.name = name;
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

    public void mark() {
        /**
         * Marks Task as done if not done and vice versa.
         */
        if (isDone) {
            System.out.println(LINEBREAK + "OK, '" + this.name + "' has been marked as Not Done.\n");
            isDone = false;
        } else {
            System.out.println(LINEBREAK + "OK, '" + this.name + "' has been marked as Done.\n");
            isDone = true;
        }
    }

}