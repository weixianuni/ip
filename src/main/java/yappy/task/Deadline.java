package yappy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Deadline extends Task {
    LocalDateTime by;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String description, Boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String fileString() {
        return "D|" + this.isCompleted + "|" + this.description + "|" + this.by.format(formatter) + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    public LocalDateTime getByDate() {
        return this.by;
    }
}
