package structural.facade;

class FlightBookingSystem {
    private int counter = 0;

    public String bookFlight(String src, String dest, String date){
        counter++;

        String confirmationId = "FL-" + counter;
        System.out.println("Flight has been booked for: "+ dest + " on date: "+ date +
            " with confirmation id: "+ confirmationId
        );

        return confirmationId;
    }

    public boolean cancelReservation(String confirmationID) {
        System.out.println("Flight: reservation with id: " + confirmationID +" has been cancelled.");
        return true;
    }
}

class HotelBookingSystem {
    private int counter = 0;

    public String bookHotel(String dest, String checkin, String checkout){
        counter++;
        String confirmationId = "HT-"+ counter;
        System.out.println("Hotel: Reserved in "+ dest + " from "+ checkin +
         " to "+ checkout + ". confirmation id: "+ confirmationId);

        return confirmationId;
    }

    public boolean cancelReservation(String confirmationID) {
        System.out.println("Hotel: reservation with id: " + confirmationID +" has been cancelled.");
        return true;
    }
}

class CarRentalSystem {
    private int counter = 0;
    public String bookCar(String location, String pickUpDate, String returnDate) {
        counter++;
        String confirmationId = "CR-"+ counter;
        System.out.println("Car: Rented in location "+ location+ " from " + pickUpDate +
         " with return date " + returnDate +". confirmation id: " + confirmationId);

        return confirmationId;
    }

    public boolean cancelReservation(String confirmationID) {
        System.out.println("Car: rental with id: " + confirmationID +" has been cancelled.");
        return true;
    }
}

class TravelBookingFacade {
    private CarRentalSystem carRental;
    private FlightBookingSystem flightBooking;
    private HotelBookingSystem hotelBooking;
    private String arrivalFlightId;
    private String departFlightId;
    private String hotelId;
    private String carId;

    public TravelBookingFacade(CarRentalSystem carRentalSystem,
         FlightBookingSystem flightBookingSystem,
          HotelBookingSystem hotelBookingSystem) {
        
        this.hotelBooking = hotelBookingSystem;
        this.flightBooking = flightBookingSystem;
        this.carRental = carRentalSystem;
    }

    public void bookTrip(String src, String dest, String startDate, String endDate){
        // booking flight
        departFlightId= flightBooking.bookFlight(src, dest, startDate);
        arrivalFlightId = flightBooking.bookFlight(dest, src, endDate);

        // booking hotel
        hotelId = hotelBooking.bookHotel(dest, startDate, endDate);

        // car Rental
        carId = carRental.bookCar(dest, startDate, endDate);

        System.out.println("Trip has been booked with below details: \nDepart flight id: " + departFlightId + "\n" + "Arrival flight id: " + arrivalFlightId +
            "\nhotel booking id: " + hotelId +
            "\ncar booking id: " + carId 
        );
        return;
    }

    public void cancelBooking(){
        flightBooking.cancelReservation(arrivalFlightId);
        flightBooking.cancelReservation(departFlightId);

        hotelBooking.cancelReservation(hotelId);
        carRental.cancelReservation(carId);
        System.out.println(" Trip has been cancelled");
    }
}


public class TravelBookingSystem {
    public static void main(String[] args) {
        TravelBookingFacade travelbookingManager = new TravelBookingFacade( new CarRentalSystem(),
        new FlightBookingSystem(),
        new HotelBookingSystem());

        travelbookingManager.bookTrip("Bangalore", "Varanasi", "20-01-26", "25-01-26");

        try {
            Thread.sleep(10000);
        } catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
        System.out.println(" \n");

        travelbookingManager.cancelBooking();
    }
    
}
