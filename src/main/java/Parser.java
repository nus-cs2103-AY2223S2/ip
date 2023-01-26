public class Parser {
    public String toDo(String[] arr) {
        String remaining = "";
        for (int j = 1; j < arr.length; j++) {
            //remaining += " ";
            remaining += arr[j];
            remaining += " ";
        }
        return remaining;
    }

    public String deadlineDetail(String[] arr) {
        String detail = "";
        for (int j = 1 ; j < arr.length ; j++) {
                if (String.valueOf(arr[j]).equals("/by")) {
                    break;
            }
            detail += arr[j];
            detail += " ";
        }
        return detail;
    }

    public int deadlineTimeIndex(String[] arr) {
        int pointer = 0;
        for (int j = 1 ; j < arr.length ; j++) {
            if (String.valueOf(arr[j]).equals("/by")) {
                pointer = j + 1;
                break;
            }
        }
        return pointer;
    }

    public int getEventStartTimeIndex(String[] arr) {
        int startIndex = 0;
        for (int j = 1; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/from")) {
                startIndex = j + 1;
                break;
            }
        }
        return startIndex;
    }

    public String getEventDetail(String[] arr) {
        String detail = "";
        for (int j = 1; j < arr.length; j++){
            if (String.valueOf(arr[j]).equals("/from")) {
                break;
            }
            detail += arr[j];
            detail += " ";
        }
        return detail;
    }

    public int getEventEndTimeIndex(String[] arr, int startIndex) {
        int endIndex = 0;
        for (int j = startIndex; j < arr.length; j++){
            if (String.valueOf(arr[j]).equals("/to")) {
                endIndex = j + 1;
                break;
            }
        }
        return endIndex;
    }

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
        for (int j = endIndex; j < arr.length; j++) {
            if (String.valueOf(arr[j]).equals("/")) {
                end += "-";
            } else {
                end += arr[j];
            }
            if (j != arr.length - 1) {
                end += " ";
            }
        }
        eventTime[0] = start;
        eventTime[1] = end;
        return eventTime;
    }
}