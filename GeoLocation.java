import java.util.Random;

// Klasė GeoLocation
public class GeoLocation {
    private double lat;
    private double lon;
    private static int numLocations = 0;

    // Konstruktorius be parametrų
    public GeoLocation() {
        Random random = new Random();
        this.lat = Math.round((random.nextDouble() * 180 - 90) * 1_000_000) / 1_000_000.0;
        this.lon = Math.round((random.nextDouble() * 360 - 180) * 1_000_000) / 1_000_000.0;
        numLocations++;
    }

    // Konstruktorius su dviem parametrais
    public GeoLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
        numLocations++;
    }

    // Kopijavimo konstruktorius
    public GeoLocation(GeoLocation location) {
        Random random = new Random();
        this.lat = location.lat + (random.nextDouble() * 0.2 - 0.1);
        this.lon = location.lon + (random.nextDouble() * 0.2 - 0.1);
        this.lat = Math.round(this.lat * 1_000_000) / 1_000_000.0;
        this.lon = Math.round(this.lon * 1_000_000) / 1_000_000.0;
        numLocations++;
    }

    // Print metodas
    public void print() {
        System.out.println("[" + this.lat + ", " + this.lon + "]");
    }

    public static int getNumLocations() {
        return numLocations;
    }

    // Atstumo kilometrais skaiciavimo metodas
    public static double distance(GeoLocation loc1, GeoLocation loc2) {
        final int R = 6371;
        double lat1Rad = Math.toRadians(loc1.lat);
        double lat2Rad = Math.toRadians(loc2.lat);
        double deltaLat = Math.toRadians(loc2.lat - loc1.lat);
        double deltaLon = Math.toRadians(loc2.lon - loc1.lon);

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = R * c;
        return Math.round(distance * 10) / 10.0;
    }

    public static void main(String[] args) {
        GeoLocation someLocation = new GeoLocation();
        GeoLocation vilnius = new GeoLocation(54.683333, 25.283333);
        GeoLocation kaunas = new GeoLocation(54.897222, 23.886111);
        GeoLocation nearVilnius = new GeoLocation(vilnius);

        someLocation.print();
        vilnius.print();
        nearVilnius.print();

        System.out.println("Number of locations: " + GeoLocation.getNumLocations());

        System.out.println("From Vilnius to Kaunas: " + GeoLocation.distance(vilnius, kaunas));
        System.out.println("From Vilnius to random location: " + GeoLocation.distance(vilnius, someLocation));
        System.out.println("From Vilnius to random neighbour: " + GeoLocation.distance(vilnius, nearVilnius));
    }
}
