package com.followup.arielverdugo.followup;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by arielverdugo on 16/2/18.
 */

public class JugadorEquipoFavorito {

    @DatabaseField(generatedId = true)
    private int id;


    // This is a foreign object which just stores the id from the User object in this table.
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "id_jugador_equipo", maxForeignAutoRefreshLevel = 5)
    JugadorEquipo jugadorEquipo;

    // This is a foreign object which just stores the id from the Post object in this table.
    @DatabaseField(canBeNull = false)
    private int favorito;

    public JugadorEquipoFavorito(JugadorEquipo jugadorEquipo,int favorito)
    {
        this.jugadorEquipo = jugadorEquipo;
        this.favorito = favorito;
    }

    public JugadorEquipoFavorito()
    {

    }

    public int isFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }

    public boolean isFavourite()
    {
        if(favorito == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public JugadorEquipo getJugadorEquipo() {
        return jugadorEquipo;
    }

    public void setJugadorEquipo(JugadorEquipo jugadorEquipo) {
        this.jugadorEquipo = jugadorEquipo;
    }
}


