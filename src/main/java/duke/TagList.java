package duke;

import java.util.ArrayList;

/**
 * Class to store tags associated with each task in Duke chatbot.
 */
public class TagList {
    protected ArrayList<String> listOfTags;

    /**
     * Constructor for an instance of TagList.
     *
     * @param listOfTags Arraylist containing Strings which specify the tag names.
     */
    public TagList(ArrayList<String> listOfTags) {
        this.listOfTags = listOfTags;
    }

    /**
     * Function to set the tag name at specified index with given tag name.
     *
     * @param index Index specified by user.
     * @param newTag Name of new tag given by user.
     * @return String representation of confirmation that the tag name has been changed.
     */
    public String setTag(int index, String newTag) {
        Integer indexInString = index;
        this.listOfTags.set(index, newTag);
        indexInString++;
        String toReturn = "Ugh, do I really need change the tag for you? Fine...\n"
                + " The task at index " + indexInString.toString()
                + " has successfully been tagged with " + newTag + ".\n"
                + "Getting my hands dirty just for an orc....";
        return toReturn;
    }

    /**
     * Function to add a tag name upon first adding a task.
     *
     * @param tagName The tag name of the task to be set.
     */
    public void addTag(String tagName) {
        this.listOfTags.add(tagName);
    }

    public ArrayList<String> getTagListFull() {
        return this.listOfTags;
    }

    /**
     * Function to return a list of tags without their associated task details.
     *
     * @return String representation of the list of tags.
     */
    public String getStringFormOfList() {
        String toPrint = "Here are the tags for each index, without any of the details:\n";
        for (int i = 0; i < listOfTags.size(); i++) {
            Integer currIndex = i + 1;
            String currTag = listOfTags.get(i);
            String toUse = currIndex.toString() + ". " + currTag + "\n";
            toPrint += toUse;
        }
        return toPrint;
    }

    public String getTagAtIndex(int index) {
        return listOfTags.get(index);
    }

    /**
     * Function to delete tag name at specified index
     *
     * @param index Specified index at which to delete task.
     */
    public void deleteTagAtIndex(int index) {
        listOfTags.remove(index);
    }

}
