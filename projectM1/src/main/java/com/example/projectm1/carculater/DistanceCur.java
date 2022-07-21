package com.example.projectm1.carculater;

public class DistanceCur {
    /**
     * 두 지점간의 거리 계산
     * @param lat1 지점 1 위도
     * @param lnt1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lnt2 지점 2 경도
     */
    public static double distance(double lat1, double lnt1, double lat2, double lnt2) {

        double theta = lnt1 - lnt2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;

        return (dist);
    }

    // This function converts decimal degrees to radians
    public static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    public static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
