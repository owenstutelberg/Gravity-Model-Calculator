package main;

public enum City {
    MINNEAPOLIS(427246, 44.9778, 93.2650),
    SAINT_PAUL(307284, 44.9537, 93.0900),
    BLOOMINGTON(88665, 44.8408, 93.2983),
    EDEN_PRAIRE(63051, 44.8547, 93.4708),
    MINNETONKA(53064, 44.9379, 93.4669),
    PLYMOUTH(79220, 45.0105, 93.4555),
    EAGAN(68000, 44.8041, 93.1669),
    EDINA(53262, 44.8897, 93.3499),
    SAINT_LOUIS_PARK(49594, 44.9597, 93.3702),
    MAPLE_GROVE(71173, 45.0725, 93.4558);



    public final int pop;
    public final double latitude;
    public final double longitude;

    City(int pop, double latitude, double longitude) {
        this.pop = pop;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getDistKM(double lat, double lon) {
        double R = 6371e3;
        double phiOne = latitude * Math.PI / 180;
        double phiTwo = lat * Math.PI / 180;
        double deltaPhi = (lat - latitude) * Math.PI / 180;
        double deltaLambda = (lon - longitude) * Math.PI / 180;

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) + 
                Math.cos(phiOne) * Math.cos(phiTwo) * 
                Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public double getGravity(City other) {
        // perform calculation in double to avoid integer overflow
        double numerator = (double) pop * other.pop;
        double distance = getDistKM(other.latitude, other.longitude);
        // distance is in kilometers; gravity proportional to 1 / distance^2
        return numerator / (distance * distance);
    }
}
