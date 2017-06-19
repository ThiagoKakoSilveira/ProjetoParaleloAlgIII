/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author 631420067
 */
public class Bike {
    private int id;
    private String nome;
    private double lat, lon;

    public Bike(int id, String nome, double lat, double lon) {
        this.id = id;
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
    }

    public Bike(String nome, double lat, double lon) {
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return "BikePoa {" + "id: " + id + ", nome: " + nome + ", latitude: " + lat + ", longitude: " + lon + "}";
    }
    
    
}
