package com.example.bikeshared.helpers;

public class CalculateDistance {
    public static double calcularDistanciaHaversine(double latI, double lonI, double lat2, double lon2) {
        final int R = 6371; // Raio da Terra em quilômetros
        double latDistance = Math.toRadians(lat2 - latI);
        double lonDistance = Math.toRadians(lon2 - lonI);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latI)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distância em quilômetros
    }
}
