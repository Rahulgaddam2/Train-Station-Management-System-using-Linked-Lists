public class TrainStation {
    public static void main(String[] args) {

        Train newtrain = new Train();

        newtrain.addcompartment(1, "AC", 5);
        newtrain.addcompartment(2, "non-AC", 6);

        
        Compartments AC = newtrain.available("AC");

        if (AC != null) {
            AC.addPassenger("Rahul");
            AC.addPassenger("Sushma");
        }

        
        Compartments NonAC = newtrain.available("non-AC");

        if (NonAC != null) {
            NonAC.addPassenger("Shankar");
            NonAC.addPassenger("Latha");
        }

        
        newtrain.display();
    }
}

class Train {

    Compartments headCompartment;

    public Train() {
        this.headCompartment = null;
    }

    public void addcompartment(int compartmentNumber, String compartmentname, int totalseats) {
        Compartments newCompartment = new Compartments(compartmentNumber, compartmentname, totalseats);

        if (headCompartment == null) {
            headCompartment = newCompartment;
        } else {
            Compartments current = headCompartment;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newCompartment;
        }

        System.out.println("Added Compartment: " + compartmentNumber + " compartment Type: " + compartmentname);
    }

    public Compartments available(String name) {
        Compartments current = headCompartment;

        while (current != null) {
            if (current.compartmentname.equals(name) && current.availableseats > 0) {
                return current; 
            }
            current = current.next;
        }
        System.out.println("No available compartment of type: " + name);
        return null; 
    }

    public void display() {
        if (headCompartment == null) {
            System.out.println("No compartments in the train.");
            return;
        }

        Compartments current = headCompartment;

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

    public Compartments(int compartmentNumber, String compartmentname, int totalseats) {
        this.compartmentNumber = compartmentNumber;
        this.compartmentname = compartmentname;
        this.totalseats = totalseats;
        this.availableseats = totalseats;
        this.passengersHead = null;
        this.next = null;
    }

    public void addPassenger(String name) {
        if (availableseats <= 0) {
            System.out.println("No seats are available in " + compartmentname + " compartment.");
            return;
        }

        passengers newPassenger = new passengers(name, totalseats - availableseats + 1, "Confirmed");

        if (passengersHead == null) {
            passengersHead = newPassenger;
        } else {
            passengers current = passengersHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newPassenger;
        }

        availableseats--;
        System.out.println(name + " added to the passenger list of compartment " + compartmentNumber);
    }

    public void displayPassengers() {
        if (passengersHead == null) {
            System.out.print("No passengers in this compartment. ");
            return;
        }

        passengers current = passengersHead;
        System.out.print("Passengers: ");
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

    public passengers(String name, int seatnumber, String ticketStatus) {
        this.name = name;
        this.seatnumber = seatnumber;
        this.ticketStatus = ticketStatus;
        this.next = null;
    }
}
