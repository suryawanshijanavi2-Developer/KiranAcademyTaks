
import java.util.Random;
import java.util.Scanner;

public class CabBookingChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        while (true) {

            System.out.println("\n===== CAB BOOKING CHATBOT =====");
            System.out.println("1. Book Ride");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            int choice = readChoice(sc);

            if (choice == 1) {

                // Cab Selection
                System.out.println("\n===== SELECT CAB TYPE =====");
                System.out.println("1. Auto - Rs.12/km");
                System.out.println("2. Sedan - Rs.18/km");
                System.out.println("3. SUV - Rs.25/km");

                System.out.print("Choose cab type: ");
                int cabChoice = readChoice(sc);

                String cabType;
                int rate;

                if (cabChoice == 1) {
                    cabType = "Auto";
                    rate = 12;
                } else if (cabChoice == 2) {
                    cabType = "Sedan";
                    rate = 18;
                } else if (cabChoice == 3) {
                    cabType = "SUV";
                    rate = 25;
                } else {
                    System.out.println("Invalid cab choice!");
                    continue;
                }

                // Route Selection
                System.out.println("\n===== SELECT ROUTE =====");
                System.out.println("1. Pune Station to Shivajinagar - 5 km");
                System.out.println("2. Swargate to Kothrud - 8 km");
                System.out.println("3. Hinjawadi to Wakad - 10 km");

                System.out.print("Choose route: ");
                int routeChoice = readChoice(sc);

                String source;
                String destination;
                int distance;

                if (routeChoice == 1) {
                    source = "Pune Station";
                    destination = "Shivajinagar";
                    distance = 5;
                } else if (routeChoice == 2) {
                    source = "Swargate";
                    destination = "Kothrud";
                    distance = 8;
                } else if (routeChoice == 3) {
                    source = "Hinjawadi";
                    destination = "Wakad";
                    distance = 10;
                } else {
                    System.out.println("Invalid route choice!");
                    continue;
                }

                // Fare Calculation
                int fare = rate * distance;

                System.out.println("\n===== BOOKING DETAILS =====");
                System.out.println("Cab Type: " + cabType);
                System.out.println("Source: " + source);
                System.out.println("Destination: " + destination);
                System.out.println("Distance: " + distance + " km");
                System.out.println("Rate: Rs." + rate + "/km");
                System.out.println("Total Fare: Rs." + fare);

                // Confirmation Before OTP
                System.out.println("\n1. Confirm & Share OTP");
                System.out.println("2. Cancel Ride");

                System.out.print("Enter your choice: ");
                int confirmChoice = readChoice(sc);

                if (confirmChoice == 2) {
                    System.out.println("Ride cancelled successfully!");
                    continue;
                } else if (confirmChoice != 1) {
                    System.out.println("Invalid choice! Booking cancelled.");
                    continue;
                }

                // Generate 4-Digit OTP
                int otp = random.nextInt(9000) + 1000;

                System.out.println("\nYour OTP is: " + otp);
                System.out.println("OTP shared successfully!");
                System.out.println("Booking Confirmed!");

                System.out.println("\nIMPORTANT RULE:");
                System.out.println("Once OTP is shared, ride cancellation is NOT allowed.");

            } else if (choice == 2) {

                System.out.println("\nThank you for using Cab Booking Chatbot!");
                System.out.println("Goodbye!");
                break;

            } else {
                System.out.println("Invalid menu choice! Please try again.");
            }
        }

        sc.close();
    }

    // Method to read valid integer input
    public static int readChoice(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}

    

