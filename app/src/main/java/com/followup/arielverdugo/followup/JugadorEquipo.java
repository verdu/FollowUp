package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by arielverdugo on 25/10/17.
 */

//@DatabaseTable(tableName = "jugador_por_equipo")
public class JugadorEquipo {
    @DatabaseField(generatedId = true)
    private int id;


    // This is a foreign object which just stores the id from the User object in this table.
    @DatabaseField(foreign = true, columnName = "id_equipo")
    Equipo equipo;

    // This is a foreign object which just stores the id from the Post object in this table.
    @DatabaseField(foreign = true, columnName = "id_jugador")
    Jugador jugador;

    public JugadorEquipo(Equipo equipo,Jugador jugador)
    {
        this.equipo = equipo;
        this.jugador = jugador;
    }

    public JugadorEquipo()
    {

    }

}
