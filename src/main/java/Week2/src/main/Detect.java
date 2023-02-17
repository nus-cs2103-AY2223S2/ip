package Week2.src.main;

import Commands.Task;

/**
 * It detects if the entered input already exists in the tasklist
 */
public class Detect {
    TaskList tl;

    /**
     * Constructor of the Detect class
     * It receives a tasklist to check.
     *
     * @param tl
     */
    public Detect(TaskList tl) {
        this.tl = tl;
    }

    /**
     * Returns true if the entered content already exists in the tasklist.
     * If not, false.
     * @param input String to check if it is in the list already.
     * @return boolean result that indicates whether it is duplicated or not.
     */
    public boolean isDuplicate(String input) {
        String str = input.replaceAll("\\s", "");
        boolean result = false;
        if(tl.length() > 0) {
            for (int i = 0; i < tl.length(); i++) {
                Task curr = (Task) tl.get(i);
                String currcon = curr.getContent().replaceAll("\\s", "");
                if (currcon.equals(str)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
