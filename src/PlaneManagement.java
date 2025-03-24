import java.util.InputMismatchException;
import java.util.Scanner;

public class
PlaneManagement {
    static int[] RowA = new int[14];
    static int[] RowB = new int[12];
    static int[] RowC = new int[12];
    static int[] RowD = new int[14];
    static Ticket[] tickets = new Ticket[52];

    public static void main(String[] args) {
        // Prints the welcome message
        System.out.println("Welcome to Plane Management Systems");

        // Main loop for menu options
        while (true) {
            try {
                // Displays the menu options
                System.out.println("*************************************************");
                System.out.println("*            Menu Options                        *");
                System.out.println("*************************************************");
                System.out.println("\t1) Buy a seat\n\t2) Cancel a seat\n\t3) Find first available seat\n\t4) Show seating plan\n\t5) Print tickets information and total sales\n\t6) Search ticket\n\t0) Quit");
                System.out.println("*************************************************");
                System.out.println("Please select an option:");
                Scanner input = new Scanner(System.in);
                int x = input.nextInt();

                // Switch case to handle menu options
                switch (x) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_tickets();
                        break;
                    case 0:
                        System.out.println("Quit");
                        return;
                    default:
                        System.out.println("Invalid input. Please try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void buy_seat() {
        Scanner input = new Scanner(System.in);
        while (true) {
            // Asking for the seat row letter
            System.out.println("Give me a letter for my seat row");
            char Rowletter = input.nextLine().toUpperCase().charAt(0);
            int[] array;
            int seatlimit;
            // vaildating Row letter
            switch (Rowletter) {
                case 'A':
                    array = RowA;
                    seatlimit = RowA.length;
                    break;
                case 'B':
                    array = RowB;
                    seatlimit = RowB.length;
                    break;
                case 'C':
                    array = RowC;
                    seatlimit = RowC.length;
                    break;
                case 'D':
                    array = RowD;
                    seatlimit = RowD.length;
                    break;
                default:
                    System.out.println("Invalid Rowletter");
                    continue;
            }
            int seatnumber;
            while (true) {
                try {
                    // Asking for the seat number
                    System.out.println("Give me your seat number");
                    seatnumber = input.nextInt();
                    // Checking if the seat number is within the valid range
                    if (seatnumber - 1 >= 0 && seatnumber - 1 < seatlimit) {
                        // Checking if the seat is available
                        if (array[seatnumber - 1] == 0) {
                            array[seatnumber - 1] = 1;
                            // Asking for the person's information
                            System.out.println("Give me the person's name");
                            String name = input.next();
                            System.out.println("Give me the person's surname");
                            String surname = input.next();
                            System.out.println("Give me the person's email");
                            String email = input.next();

                            Person p1 = new Person(name, surname, email);

                            Ticket t1 = new Ticket();
                            // Setting the person, row, and seat information for the ticket
                            t1.setPerson(p1);
                            t1.setRow(Rowletter);
                            t1.setSeat(seatnumber);
                            // Setting the price based on the seat number
                            if (seatnumber >= 1 && seatnumber <= 5) {
                                t1.setPrice(200);
                            } else if (seatnumber >= 6 && seatnumber <= 9) {
                                t1.setPrice(180);
                            } else {
                                t1.setPrice(150);
                            }
                            // Adding the ticket to the tickets array
                            for (int i = 0; i < tickets.length; i++) {
                                if (tickets[i] == null) {
                                    tickets[i] = t1;
                                    break;
                                }
                            }
                            System.out.println("Your seat is successfully booked");
                            return;
                        } else {
                            System.out.println("This seat is already taken.");
                        }
                    } else {
                        System.out.println("Invalid seat number");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.next();
                }
            }
        }
    }

    public static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        while (true) {
            // Asking for the seat row letter
            System.out.println("Give me a letter for my seat row");
            char Rowletter = input.nextLine().toUpperCase().charAt(0);
            int[] array;
            switch (Rowletter) {
                // Determining the array based on the row letter
                case 'A':
                    array = RowA;
                    break;
                case 'B':
                    array = RowB;
                    break;
                case 'C':
                    array = RowC;
                    break;
                case 'D':
                    array = RowD;
                    break;
                default:
                    System.out.println("Invalid Rowletter");
                    continue;
            }
            int seatnumber;
            while (true) {
                try {
                    // Asking for the seat number
                    System.out.println("Give me your seat number");
                    seatnumber = input.nextInt();
                    // Checking if the seat number is within the valid range
                    if (seatnumber - 1 >= 0 && seatnumber < array.length) {
                        // Checking if the seat is already booked
                        if (array[seatnumber - 1] == 1) {
                            array[seatnumber - 1] = 0;

                            for (int i = 0; i < tickets.length; i++) {
                                if (tickets[i] != null && tickets[i].getRow() == Rowletter && tickets[i].getSeat() == seatnumber) {
                                    tickets[i] = null;
                                    break;
                                }
                            }
                            // Creating a new Ticket object to access the delete_file method
                            Ticket ticket = new Ticket();
                            ticket.delete_file(Rowletter, seatnumber); // Deleting ticket information from file
                            System.out.println("Your seat is successfully Cancelled.");
                            return;
                        } else {
                            System.out.println("This seat is already free.");
                            return;
                        }
                    } else {
                        System.out.println("Invalid seat number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.next();
                }
            }
        }
    }


    public static void find_first_available() {
        // Variable to track if any seat is available
        boolean condition = false;

        // Checking each seat in RowA
        for (int i = 0; i < RowA.length; i++) {
            if (RowA[i] == 0) { // If the seat is available
                System.out.println("Seat A" + (i + 1) + " is free."); // Print the seat information
                condition = true; // Update condition to true
                break;
            }
        }

        // If no available seat is found in RowA, check RowB
        if (!condition) {
            for (int i = 0; i < RowB.length; i++) {
                if (RowB[i] == 0) { // If the seat is available
                    System.out.println("Seat B" + (i + 1) + " is free."); // Print the seat information
                    condition = true; // Update condition to true
                    break;
                }
            }
        }

        // If no available seat is found in RowB, check RowC
        if (!condition) {
            for (int i = 0; i < RowC.length; i++) {
                if (RowC[i] == 0) { // If the seat is available
                    System.out.println("Seat C" + (i + 1) + " is free."); // Print the seat information
                    condition = true; // Update condition to true
                    break;
                }
            }
        }

        // If no available seat is found in RowC, check RowD
        if (!condition) {
            for (int i = 0; i < RowD.length; i++) {
                if (RowD[i] == 0) { // If the seat is available
                    System.out.println("Seat D" + (i + 1) + " is free."); // Print the seat information
                    condition = true; // Update condition to true
                    break;
                }
            }
        }

        // If all seats are booked, print a message indicating so
        if (!condition) {
            System.out.println("All the seats are purchased.");
        }
    }

    public static void show_seating_plan() {
        // Print the seating plan for each row
        System.out.println("Seating Plan:");
        System.out.print("Row A: ");
        for (int seat : RowA) {
            // Display 'O' for available seats and 'X' for sold seats
            if (seat == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println(); // Move to the next line for Row B
        System.out.print("Row B: ");
        for (int seat : RowB) {
            if (seat == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println(); // Move to the next line for Row C
        System.out.print("Row C: ");
        for (int seat : RowC) {
            if (seat == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println(); // Move to the next line for Row D
        System.out.print("Row D: ");
        for (int seat : RowD) {
            if (seat == 0) {
                System.out.print("O ");
            } else {
                System.out.print("X ");
            }
        }
        System.out.println(); // Move to the next line after printing the seating plan
    }

    public static void print_tickets_info() {
        double total_amount = 0;
        // Print information for each ticket and calculate total amount
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null) {
                tickets[i].printTicketInfo();
                total_amount += tickets[i].getPrice();
            }
        }
        System.out.println("Total amount =" + total_amount);
    }

    public static void search_tickets() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Give me a letter for my seat row");
            char Rowletter = input.nextLine().toUpperCase().charAt(0);
            int[] array;
            switch (Rowletter) {
                case 'A':
                    array = RowA;
                    break;
                case 'B':
                    array = RowB;
                    break;
                case 'C':
                    array = RowC;
                    break;
                case 'D':
                    array = RowD;
                    break;
                default:
                    System.out.println("Invalid Row letter");
                    continue;
            }
            int seatnumber;
            while (true) {
                try {
                    System.out.println("Give me your seat number");
                    seatnumber = input.nextInt();
                    if ((seatnumber - 1) >= 0 && (seatnumber - 1) < array.length) {
                        for (int i = 0; i < tickets.length; i++) {
                            if (tickets[i] != null && tickets[i].getRow() == Rowletter && tickets[i].getSeat() == seatnumber) {
                                tickets[i].printTicketInfo();
                                return;
                            }
                        }
                        System.out.println("This seat is free");
                        return;
                    } else {
                        System.out.println("Invalid input.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input.");
                    input.next();
                }
            }
        }
    }
}