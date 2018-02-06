package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by arielverdugo on 6/2/18.
 */

public class Entrenamiento {

    @DatabaseField(generatedId = true)
    //private int id;
    public int id;

    @DatabaseField
    private Integer fecha;

    @DatabaseField
    private Integer libres;

    @DatabaseField
    private Integer dobles;

    @DatabaseField
    private Integer triples;

    public Entrenamiento(){}

    public Entrenamiento(Integer fecha,Integer libres, Integer dobles, Integer triples)
    {
        this.fecha = fecha;
        this.libres = libres;
        this.dobles = dobles;
        this.triples = triples;
    }

    public Integer getFecha() {
        return fecha;
    }

    public void setFecha(Integer fecha) {
        this.fecha = fecha;
    }

    public Integer getLibres() {
        return libres;
    }

    public void setLibres(Integer libres) {
        this.libres = libres;
    }

    public Integer getDobles() {
        return dobles;
    }

    public void setDobles(Integer dobles) {
        this.dobles = dobles;
    }

    public Integer getTriples() {
        return triples;
    }

    public void setTriples(Integer triples) {
        this.triples = triples;
    }
}
