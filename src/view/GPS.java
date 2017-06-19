/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import algorithms.GPSCoordinate;
import datastructures.KDData;

/**
 *
 * @author 631420067
 */
public class GPS implements KDData<Double> {

    GPSCoordinate coordinate;

    public GPS(GPSCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public int getDimensions() {
        return 2;
    }

    @Override
    public double distance(KDData<Double> other) {
        GPS o = (GPS) other;
        return coordinate.distance(o.coordinate);
    }

    @Override
    public int compare(KDData other, int dimension) {
        GPS o = (GPS) other;
        double ox = o.getValueForDimension(0);
        double oy = o.getValueForDimension(1);
        if(dimension == 0){
            return (int) (coordinate.latitude - ox);
        }return (int) (coordinate.longitude - oy);
        
    }

    @Override
    public Double getValueForDimension(int dimension) {
        if (dimension == 0) {
            return coordinate.latitude;
        } 
        return coordinate.longitude;
        
    }

    @Override
    public int compareTo(KDData<Double> other) {
        GPS o = (GPS) other;
        return (int) coordinate.distance(o.coordinate);
    }

}
