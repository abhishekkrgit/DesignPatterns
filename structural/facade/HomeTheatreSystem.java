package structural.facade;

class Amplifier {
    private int volLevel;
    public Amplifier(){
        volLevel = 5;
    }

    public void on(){
        System.out.println("Amplifier is powering on");
    }

    public void off() {
        System.out.println("Amplifier is powering off");
    }

    public void setVolume(int level){
        this.volLevel = level;
        System.out.println("Volume is set to: " + volLevel);
    }
}

class DvdPlayer {
    private String movie = null;
    public void on() { 
        System.out.println("DVD Player: Powering on."); 
    }

    public void off() {
         System.out.println("DVD Player: Shutting down.");
    }

    public void play(String movie) { 
        System.out.println("DVD Player: Playing '" + movie + "'."); 
    }
    public void stop() {
         System.out.println("DVD Player: Stopped."); 
    }
}

class Projector {
    public void on() {
        System.out.println("Projector: Warming up.");
    }

    public void off() { 
        System.out.println("Projector: Cooling down.");
    }

    public void wideScreenMode() {
         System.out.println("Projector: Widescreen mode enabled."); 
    }
}

class StreamingService {
    public void connect() {
         System.out.println("Streaming: Connected to service."); 
    }

    public void disconnect() {
         System.out.println("Streaming: Disconnected.");
    }

    public void stream(String movie) {
         System.out.println("Streaming: Now streaming '" + movie + "'."); 
    }
}

class HomeTheaterFacade {
    private Amplifier amplifier;
    private Projector projector;
    private DvdPlayer dvdPlayer;
    private StreamingService streamingService;

    public HomeTheaterFacade(Amplifier amplifier, Projector projector,
        StreamingService streamingService, DvdPlayer dvdPlayer) {
        this.amplifier = amplifier;
        this.projector = projector;
        this.streamingService = streamingService;
        this.dvdPlayer = dvdPlayer;
    }

    public void watchMovie(String movie) {
        System.out.println("\n--- Preparing to watch: " + movie + " ---");
        amplifier.on();
        amplifier.setVolume(20);
        dvdPlayer.on();
        projector.on();
        streamingService.connect();
        streamingService.stream(movie);
        projector.wideScreenMode();
        System.out.println("\n--- Enjoy the movie: " + movie + " ---");

    }

    public void endMovie() {
        System.out.println("\n--- Shutting down home theater ---");
        streamingService.disconnect();
        amplifier.off();
        dvdPlayer.off();
        projector.off();
        System.out.println("--- Home theater off ---\n");
    }

}

public class HomeTheatreSystem {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(
            new Amplifier(), new Projector(), new StreamingService(), new DvdPlayer());
        homeTheaterFacade.watchMovie("Dhurandhar");

        try {
            Thread.sleep(10000); // Changed to 10 seconds        
        } catch (InterruptedException ex){
            Thread.currentThread().interrupt(); 
            System.err.println("Sleep was interrupted!");
        }

        homeTheaterFacade.endMovie();    
    }
}
