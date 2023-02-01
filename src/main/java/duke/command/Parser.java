package duke.command;
import duke.exception.MissingContentException;

/**
 * Makes sense of what users say
 */
public class Parser {

    /**
     * Makes sense of adding to do command from users
     * @param arr array of original array
     * @return full string command for users
     */
    public String toDo(String[] arr) {
        String remaining = "";
        for (int j = 1; j < arr.length; j++) {
            //remaining += " ";
            remaining += arr[j];
            remaining += " ";
        }
        return remaining;
    }

    /**
     * Returns the detail/content of deadline.
     * Given that the detail is not empty.
     *
     * @param arr an array of the input.
     * @return the String detail.
     * @throws duke.MissingContentException if arr is empty.
     */
    public String deadlineDetail(String[] arr) throws MissingContentException {
        String detail = "";
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/by")) {
                break;
            }
            detail += arr[j];
            detail += " ";
        }
        return detail;

    }

    /**
     * Returns index of the input array where indicates time of the deadline.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return the index of deadline time.
     * @throws duke.MissingContentException if arr is empty.
     */
    public int deadlineTimeIndex(String[] arr) throws MissingContentException {
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        int pointer = 0;
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/by")) {
                pointer = j + 1;
                break;
            }
        }
        if (pointer == 0) {
            throw new MissingContentException();
        }
        return pointer;
    }

    /**
     * Returns index of the input array where indicates starting time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return the index of starting time.
     * @throws duke.MissingContentException if arr is empty.
     */
    public int getEventStartTimeIndex(String[] arr) throws MissingContentException {
        int startIndex = 0;
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/from")) {
                startIndex = j + 1;
                break;
            }
        }
        if (startIndex == 0) {
            throw new MissingContentException();
        }
        return startIndex;
    }

    /**
     * Returns event detail from input array.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @return event detail.
     * @throws duke.MissingContentException if arr is empty.
     */
    public String getEventDetail(String[] arr) throws MissingContentException {
        if (arr.length <= 1) {
            throw new MissingContentException();
        }
        String detail = "";
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/from")) {
                break;
            }
            detail += arr[j];
            detail += " ";
        }
        return detail;
    }

    /**
     * Returns index of the input array where indicates ending time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @param startIndex starting index where indicates starting time of event.
     * @return the index of ending time.
     */
    public int getEventEndTimeIndex(String[] arr, int startIndex) throws MissingContentException {
        int endIndex = 0;
        for (int j = startIndex; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/to")) {
                endIndex = j + 1;
                break;
            }
        }
        if (endIndex == 0) {
            throw new MissingContentException();
        }
        return endIndex;
    }

    /**
     * Returns index of the input array where indicates ending time of the event.
     * Given that the arr is not empty.
     *
     * @param arr an array of the input.
     * @param startIndex starting index where indicates starting time of event.
     * @param endIndex ending index where indicates ending time of event.
     * @return an array consists of event start time and event ent time specifically in String.
     */
    public String[] getEventTime(String[] arr, int startIndex, int endIndex) {
        String[] eventTime = new String[2];
        String start = "";
        String end = "";
        for (int j = startIndex; j < endIndex - 1; j++) {
            if (String.valueOf(arr[j]).equals("/")) {
                start += "-";
            } else {
                start += arr[j];
            }
            if (j != endIndex - 2) {
                start += " ";
            }
        }
        for (int k = endIndex; k < arr.length; k++) {
            if (String.valueOf(arr[k]).equals("/")) {
                end += "-";
            } else {
                end += arr[k];
            }
            if (k != arr.length - 1) {
                end += " ";
            }
        }
        eventTime[0] = start;
        eventTime[1] = end;
        return eventTime;
    }
}