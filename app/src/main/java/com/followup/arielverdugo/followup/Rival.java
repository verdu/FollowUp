package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by arielverdugo on 8/2/18.
 */

@DatabaseTable(tableName = "rival")
public class Rival {
    @DatabaseField(generatedId = true)
    //private int id;
    public int id;


    @DatabaseField
    private String nombre;


    public Rival() {}

    public Rival(String nombre) {
        this.nombre = nombre;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
