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
     * @param str
     * @return
     */
    public boolean isDuplicate(String str) {
        boolean result = false;
        if(tl.length() > 0) {
            for (int i = 0; i < tl.length(); i++) {
                Task curr = (Task) tl.get(i);
                if (curr.getContent().equals(str)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
