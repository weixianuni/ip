import java.util.Scanner;


public class Yappy {


    public static void main(String[] args) {

        Task[] library = new Task[100];
        int numOfItems = 0;


        System.out.println("____________________________________________________________");
        System.out.println("\tHello! I'm Yappy\n" + "\tWhat can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            if (inputLine.equals("bye")) {
                break;
            } else if (inputLine.equals("list")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < numOfItems; i++) {
                    int num = i + 1;
                    Task task = library[i];
                    System.out.println("\t" + num + "." + task.toString());
                }
                System.out.println("\t____________________________________________________________");
                continue;
            } else if (inputLine.startsWith("mark")) {
                int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
                library[index].setCompleted();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tAlrighty I have marked this task as completed: " + "\n\t\s\s" + library[index].toString());
                System.out.println("\t____________________________________________________________");
            } else if (inputLine.startsWith("unmark")) {
                int index = Integer.parseInt(inputLine.split(" ")[1]) - 1;
                library[index].setUncompleted();
                System.out.println("\t____________________________________________________________");
                System.out.println("\tAlrighty I have marked this task as not completed yet: " + "\n\t\s\s" + library[index].toString());
                System.out.println("\t____________________________________________________________");
            } else {
                library[numOfItems] = new Task(inputLine);
                System.out.println("\t____________________________________________________________");
                System.out.println("\tAdded the following task: " + inputLine);
                System.out.println("\tNow you have " + numOfItems + " tasks in your list.");
                System.out.println("\t____________________________________________________________");
                numOfItems++;
            }
        }

        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }
}
