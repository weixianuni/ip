import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class Event extends Task {

    LocalDateTime from;
    LocalDateTime to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Event(String description, Boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String fileString() {
        return "E|" + this.isCompleted + "|" + this.description + "|" + this.from.format(formatter) + "|" + this.to.format(formatter) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}

