package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by arielverdugo on 25/10/17.
 */

public class JugadorEquipoRepository
{
    private static JugadorEquipoRepository instance;

    private Dao<JugadorEquipo, Integer> dao;
    private Dao<Jugador,Integer>jugadorDao;
    private Dao<Equipo,Integer>equipoDao;

    public static JugadorEquipoRepository getInstance(Context context) {
        if(instance == null)
            instance = new JugadorEquipoRepository((Context) context);
        return instance;
    }

    private JugadorEquipoRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(JugadorEquipo.class);
            jugadorDao = helper.getDao(Jugador.class);
            equipoDao = helper.getDao(Equipo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JugadorEquipo> getJudadorEquipo(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addJugadorEquipo(JugadorEquipo c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JugadorEquipo findJugadorEquipoById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteJugadorEquipoById(int id){
        try {
            dao.deleteById(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<JugadorEquipo> findWhere(String fieldName,Object value){
        try {
            return dao.queryForEq(fieldName, value);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*public List<Jugador>findJugadorPorEquipoById(int id){
        try {
            QueryBuilder<Jugador, Integer> jugadorQb = jugadorDao.queryBuilder();
            QueryBuilder<Equipo, Integer> equipoQb = equipoDao.queryBuilder();

            //SEGUNDA OPCION PROBAR ESTO
            //equipoQb.where().eq("id_jugador", id);
            //return jugadorQb.orderByRaw("id").join(jugadorQb).query();

            //Primera opcion
            //sino anda probar sacando el orderByRaw

            equipoQb.join(jugadorQb).orderByRaw("id_equipo");
            return jugadorQb.where().eq("id_jugador", id).query();




        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }*/


}
