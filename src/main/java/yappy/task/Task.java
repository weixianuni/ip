package yappy.task;

/**
 * Represents a task.
 * A <code>Task</code> object has a <code>isCompleted</code> field to
 * represent whether a task is completed and
 * a description field for the description of the task.
 *
 */
public class Task {
    private boolean isCompleted;
    private final String description;

    /**
     * Constructs a new <code>Task</code> task with the given description.
     * @param description Textual description of the task.
     * @param isCompleted <code>true</code> if the task has been completed,
     *                    <code>false</code>> otherwise.
     */
    public Task(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * Set the isCompleted field to true.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Set the isCompleted field to false.
     */
    public void setUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Returns string to be written to disk.
     *
     * @return An empty string
     */
    public String toFileString() {
        return "";
    }

    public String toString() {
        return "[" + (this.isCompleted ? "X" : " ") + "] " + this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean for whether the task is completed or not.
     *
     * @return isCompleted field.
     */
    public Boolean isCompleted() {
        return this.isCompleted;
    }
}
