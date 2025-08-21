import java.util.Scanner;


public class Yappy {
    public static void main(String[] args) {

        String[] library = new String[100];
        int numOfItems = 0;


        System.out.println("____________________________________________________________");
        System.out.println("\tHello! I'm Yappy\n" + "\tWhat can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner input = new Scanner(System.in);
        while (true) {
            String inputLine = input.nextLine();
            if (inputLine.equals("bye")) {
                break;
            }
            if (inputLine.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < numOfItems; i++) {
                    int num = i + 1;
                    System.out.println("\t" + num + ". " + library[i]);
                }
                System.out.println("____________________________________________________________");
                continue;
            }
            library[numOfItems] = inputLine;
            System.out.println("____________________________________________________________");
            System.out.println("\tadded: " + inputLine);
            System.out.println("____________________________________________________________");
            numOfItems++;
        }

        System.out.println("____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

    }
}
