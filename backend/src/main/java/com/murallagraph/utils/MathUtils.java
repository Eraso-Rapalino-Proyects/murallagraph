package com.murallagraph.utils;

public class MathUtils {

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371e3;
        double phi1 = degreesToRadians(lat1);
        double phi2 = degreesToRadians(lat2);
        double deltaPhi = degreesToRadians(lat2 - lat1);
        double deltaLambda = degreesToRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public static double degreesToRadians(double degrees) {
        return Math.toRadians(degrees);
    }

    public static double[][] calculateEdgeDistance(double[] startPoint, double[] endPoint, double arrowLength) {
        double lineDirection = Math.atan2(endPoint[1] - startPoint[1], endPoint[0] - startPoint[0]);

        double[] middlePoint = {
                (startPoint[0] + endPoint[0]) / 2,
                (startPoint[1] + endPoint[1]) / 2
        };

        double[] arrowBaseMiddle = {
                middlePoint[0] + arrowLength * 0.5 * Math.cos(lineDirection),
                middlePoint[1] + arrowLength * 0.5 * Math.sin(lineDirection)
        };

        double angleOffset = Math.PI / 6;

        double[] arrowPoint1 = {
                arrowBaseMiddle[0] - arrowLength * Math.cos(lineDirection - angleOffset),
                arrowBaseMiddle[1] - arrowLength * Math.sin(lineDirection - angleOffset)
        };

        double[] arrowPoint2 = {
                arrowBaseMiddle[0] - arrowLength * Math.cos(lineDirection + angleOffset),
                arrowBaseMiddle[1] - arrowLength * Math.sin(lineDirection + angleOffset)
        };

        return new double[][] { middlePoint, arrowPoint1, arrowPoint2 };
    }
}
