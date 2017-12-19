package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by arielverdugo on 13/10/17.
 */

public class JugadorRepository
{
    private static JugadorRepository instance;

    private Dao<Jugador, Integer> dao;

    public static JugadorRepository getInstance(Context context) {
        if(instance == null)
            instance = new JugadorRepository((Context) context);
        return instance;
    }

    private JugadorRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(Jugador.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Jugador> getJugadores(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Jugador> getJugadoresFavoritos(){
        return findWhere("favorito",1);
    }

    public void addJugador(Jugador c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public Jugador findJugadorById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteJugadorById(int id){
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Jugador> findWhere(String fieldName,Object value){
        try {
            return dao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void updateJugador(Jugador a)
    {
        try {
            dao.update(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
