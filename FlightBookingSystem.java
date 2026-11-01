
import java.util.ArrayList;
import java.util.Scanner;

// Main Class
public class FlightBookingSystem {

    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int bookingCounter = 1001;

    Scanner sc = new Scanner(System.in);

    // Constructor
    public FlightBookingSystem() {
        flights.add(new Flight("PN101", "Kiran Air", "Pune",
                "Delhi", 20, 4000));

        flights.add(new Flight("MB202", "Air India", "Mumbai",
                "Bengaluru", 25, 3500));

        flights.add(new Flight("PN303", "IndiGo", "Pune",
                "Hyderabad", 30, 4500));
    }

    // Display Flights
    public void displayFlights() {
        System.out.println("\n===== AVAILABLE FLIGHTS =====");

        for (Flight f : flights) {
            f.displayFlight();
        }
    }

    // Find Flight
    public Flight findFlight(String code) {
        for (Flight f : flights) {
            if (f.getFlightCode().equalsIgnoreCase(code)) {
                return f;
            }
        }
        return null;
    }

    // Book Flight
    public void bookFlight() {

        displayFlights();

        System.out.print("\nEnter flight code: ");
        String code = sc.nextLine().trim();

        Flight flight = findFlight(code);

        if (flight == null) {
            System.out.println("Invalid flight code!");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Passenger name cannot be blank!");
            return;
        }

        int age;

        try {
            System.out.print("Enter age: ");
            age = Integer.parseInt(sc.nextLine());

            if (age <= 0) {
                System.out.println("Age must be greater than zero!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.println("Passenger Type:");
        System.out.println("1. Regular");
        System.out.println("2. Student");

        int type;

        try {
            System.out.print("Choose passenger type: ");
            type = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid passenger type!");
            return;
        }

        Passenger passenger;

        if (type == 1) {
            passenger = new RegularPassenger(name, age);
        } else if (type == 2) {
            passenger = new StudentPassenger(name, age);
        } else {
            System.out.println("Invalid passenger type!");
            return;
        }

        int tickets;

        try {
            System.out.print("Enter number of tickets: ");
            tickets = Integer.parseInt(sc.nextLine());

            if (tickets <= 0) {
                System.out.println("Ticket count must be greater than zero!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ticket count!");
            return;
        }

        // Check Seat Availability
        if (tickets > flight.getAvailableSeats()) {
            System.out.println("Booking failed: Not enough seats available.");
            System.out.println("No booking was created.");
            return;
        }

        // Calculate Fare
        double subtotal = flight.getBaseFare() * tickets;
        double discount = passenger.calculateDiscount(subtotal);
        double totalFare = subtotal - discount;

        // Confirm Booking
        System.out.println("\n===== FARE DETAILS =====");
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Discount: Rs." + discount);
        System.out.println("Final Fare: Rs." + totalFare);

        System.out.println("\n1. Confirm Booking");
        System.out.println("2. Cancel Booking");

        int confirm;

        try {
            System.out.print("Enter choice: ");
            confirm = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice. Booking cancelled.");
            return;
        }

        if (confirm != 1) {
            System.out.println("Booking cancelled.");
            return;
        }

        // Update Seats Only After Confirmation
        flight.reserveSeats(tickets);

        // Create Booking
        String bookingId = "BK" + bookingCounter++;

        Booking booking = new Booking(
                bookingId, flight, passenger,
                tickets, subtotal, discount, totalFare
        );

        bookings.add(booking);

        System.out.println("\nBooking confirmed successfully!");
        booking.displaySummary();
    }

    // View Latest Booking
    public void viewBooking() {

        if (bookings.isEmpty()) {
            System.out.println("\nNo booking available.");
            return;
        }

        System.out.println("\n===== LATEST BOOKING =====");

        Booking latest = bookings.get(bookings.size() - 1);
        latest.displaySummary();
    }

    // Main Menu
    public void start() {

        while (true) {

            System.out.println("\n===== KIRAN AIR BOOKING =====");
            System.out.println("1. View Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. View Booking");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");

            String input = sc.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Enter a number.");
                continue;
            }

            if (choice == 1) {
                displayFlights();

            } else if (choice == 2) {
                bookFlight();

            } else if (choice == 3) {
                viewBooking();

            } else if (choice == 4) {
                System.out.println(
                    "Thank you for using Kiran Air Booking!"
                );
                break;

            } else {
                System.out.println("Invalid choice! Please select 1 to 4.");
            }
        }

        sc.close();
    }

    public static void main(String[] args) {
        FlightBookingSystem system = new FlightBookingSystem();
        system.start();
    }
}


// Flight Class
class Flight {

    private String flightCode;
    private String airline;
    private String source;
    private String destination;
    private int availableSeats;
    private double baseFare;

    public Flight(String flightCode, String airline,
                  String source, String destination,
                  int availableSeats, double baseFare) {

        this.flightCode = flightCode;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.baseFare = baseFare;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void reserveSeats(int tickets) {
        availableSeats -= tickets;
    }

    public void displayFlight() {
        System.out.println("\nFlight Code: " + flightCode);
        System.out.println("Airline: " + airline);
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Base Fare: Rs." + baseFare);
    }

    public String getRoute() {
        return source + " to " + destination;
    }

    public String getAirline() {
        return airline;
    }
}


// Abstract Passenger Class
abstract class Passenger {

    private String name;
    private int age;

    public Passenger(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Abstraction
    public abstract double calculateDiscount(double subtotal);

    public abstract String getPassengerType();
}


// Regular Passenger
class RegularPassenger extends Passenger {

    public RegularPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.0;
    }

    @Override
    public String getPassengerType() {
        return "Regular";
    }
}


// Student Passenger
class StudentPassenger extends Passenger {

    public StudentPassenger(String name, int age) {
        super(name, age);
    }

    @Override
    public double calculateDiscount(double subtotal) {
        return subtotal * 0.10;
    }

    @Override
    public String getPassengerType() {
        return "Student";
    }
}


// Booking Class
class Booking {

    private String bookingId;
    private Flight flight;
    private Passenger passenger;
    private int tickets;
    private double subtotal;
    private double discount;
    private double totalFare;

    public Booking(String bookingId, Flight flight,
                   Passenger passenger, int tickets,
                   double subtotal, double discount,
                   double totalFare) {

        this.bookingId = bookingId;
        this.flight = flight;
        this.passenger = passenger;
        this.tickets = tickets;
        this.subtotal = subtotal;
        this.discount = discount;
        this.totalFare = totalFare;
    }

    public void displaySummary() {

        System.out.println("\n===== BOOKING SUMMARY =====");
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Flight Code: " + flight.getFlightCode());
        System.out.println("Airline: " + flight.getAirline());
        System.out.println("Route: " + flight.getRoute());
        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Age: " + passenger.getAge());
        System.out.println("Passenger Type: " + passenger.getPassengerType());
        System.out.println("Number of Tickets: " + tickets);
        System.out.println("Base Fare per Ticket: Rs." + flight.getBaseFare());
        System.out.println("Subtotal: Rs." + subtotal);
        System.out.println("Discount: Rs." + discount);
        System.out.println("Final Amount: Rs." + totalFare);
    }
}


