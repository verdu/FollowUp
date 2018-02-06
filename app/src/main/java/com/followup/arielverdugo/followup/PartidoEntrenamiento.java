package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by arielverdugo on 6/2/18.
 */

public class PartidoEntrenamiento {

    @DatabaseField(generatedId = true)
    private int id;


    // This is a foreign object which just stores the id from the User object in this table.
    @DatabaseField(foreign = true, columnName = "id_jugador_equipo")
    JugadorEquipo jugadorEquipo;

    // This is a foreign object which just stores the id from the Post object in this table.
    @DatabaseField(foreign = true, columnName = "id_partido")
    Partido partido;

    // This is a foreign object which just stores the id from the Post object in this table.
    @DatabaseField(foreign = true, columnName = "id_entrenamiento")
    Entrenamiento entrenamiento;

    public PartidoEntrenamiento(JugadorEquipo jugadorEquipo, Partido partido, Entrenamiento entrenamiento)
    {
        this.jugadorEquipo = jugadorEquipo;
        this.partido = partido;
        this.entrenamiento = entrenamiento;
    }

    public PartidoEntrenamiento()
    {

    }
}
