public class TrainStation {
    public static void main(String[] args) {

        // I create a new train object
        Train newtrain = new Train();

        // I add compartments to the train with unique compartment numbers, types, and seat capacities
        newtrain.addcompartment(1, "AC", 5);
        newtrain.addcompartment(2, "non-AC", 6);

        // I check for available AC compartments
        Compartments AC = newtrain.available("AC");

        // If an AC compartment is available, I add passengers to it
        if (AC != null) {
            AC.addPassenger("Rahul");
            AC.addPassenger("Sushma");
        }

        // I check for available Non-AC compartments
        Compartments NonAC = newtrain.available("non-AC");

        // If a Non-AC compartment is available, I add passengers to it
        if (NonAC != null) {
            NonAC.addPassenger("Shankar");
            NonAC.addPassenger("Latha");
        }

        // I display the entire train structure with compartments and passengers
        newtrain.display();
    }
}

class Train {

    Compartments headCompartment;

    // Constructor initializes the headCompartment to null when the train is created
    public Train() {
        this.headCompartment = null;
    }

    // I add a new compartment to the train
    public void addcompartment(int compartmentNumber, String compartmentname, int totalseats) {
        Compartments newCompartment = new Compartments(compartmentNumber, compartmentname, totalseats);

        // If the train has no compartments, I set the new compartment as the head
        if (headCompartment == null) {
            headCompartment = newCompartment;
        } else {
            // Otherwise, I traverse to the end and add the new compartment
            Compartments current = headCompartment;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newCompartment;
        }

        // I print a message confirming that the compartment has been added
        System.out.println("Added Compartment: " + compartmentNumber + " compartment Type: " + compartmentname);
    }

    // I check if a compartment of a specific type is available
    public Compartments available(String name) {
        Compartments current = headCompartment;

        // I traverse the compartments and return the first available one
        while (current != null) {
            if (current.compartmentname.equals(name) && current.availableseats > 0) {
                return current; 
            }
            current = current.next;
        }
        
        // If no available compartment is found, I print a message
        System.out.println("No available compartment of type: " + name);
        return null; 
    }

    // I display the entire train structure with its compartments and passengers
    public void display() {
        if (headCompartment == null) {
            System.out.println("No compartments in the train.");
            return;
        }

        Compartments current = headCompartment;

        // I traverse and display all compartments and their passengers
        while (current != null) {
            System.out.print("Compartment: " + current.compartmentname + " -> ");
            current.displayPassengers(); 
            current = current.next;
        }
        System.out.println("null");
    }
}

class Compartments {

    int compartmentNumber;
    String compartmentname;
    int totalseats;
    int availableseats;
    passengers passengersHead;
    Compartments next;

    // Constructor initializes the compartment details
    public Compartments(int compartmentNumber, String compartmentname, int totalseats) {
        this.compartmentNumber = compartmentNumber;
        this.compartmentname = compartmentname;
        this.totalseats = totalseats;
        this.availableseats = totalseats;
        this.passengersHead = null;
        this.next = null;
    }

    // I add a passenger to the compartment if there are available seats
    public void addPassenger(String name) {
        if (availableseats <= 0) {
            System.out.println("No seats are available in " + compartmentname + " compartment.");
            return;
        }

        // I create a new passenger with a seat number and confirm the ticket
        passengers newPassenger = new passengers(name, totalseats - availableseats + 1, "Confirmed");

        // If no passengers exist, I set the new passenger as the head
        if (passengersHead == null) {
            passengersHead = newPassenger;
        } else {
            // Otherwise, I traverse to the end and add the new passenger
            passengers current = passengersHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newPassenger;
        }

        // I reduce the available seats and print a confirmation message
        availableseats--;
        System.out.println(name + " added to the passenger list of compartment " + compartmentNumber);
    }

    // I display all the passengers in the compartment
    public void displayPassengers() {
        if (passengersHead == null) {
            System.out.print("No passengers in this compartment. ");
            return;
        }

        passengers current = passengersHead;
        System.out.print("Passengers: ");
        // I traverse through the list of passengers and display their details
        while (current != null) {
            System.out.print(current.name + "(Seat: " + current.seatnumber + "), ");
            current = current.next;
        }
    }
}

class passengers {

    String name;
    int seatnumber;
    String ticketStatus;
    passengers next;

    // Constructor initializes the passenger details
    public passengers(String name, int seatnumber, String ticketStatus) {
        this.name = name;
        this.seatnumber = seatnumber;
        this.ticketStatus = ticketStatus;
        this.next = null;
    }
}
