package yappy.task;

/**
 * Represents a task.
 * A <code>Task</code> object has a <code>isCompleted</code> field to
 * represent whether a task is completed and
 * a description field for the description of the task.
 *
 */
public class Task {
    boolean isCompleted;
    String description;

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
    public Boolean completed() {
        return this.isCompleted;
    }
}