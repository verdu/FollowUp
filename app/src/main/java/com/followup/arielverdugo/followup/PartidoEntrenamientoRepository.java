package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by arielverdugo on 6/2/18.
 */

public class PartidoEntrenamientoRepository {

    private static PartidoEntrenamientoRepository instance;

    private Dao<PartidoEntrenamiento, Integer> dao;

    public static PartidoEntrenamientoRepository getInstance(Context context) {
        if(instance == null)
            instance = new PartidoEntrenamientoRepository((Context) context);
        return instance;
    }

    private PartidoEntrenamientoRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(PartidoEntrenamiento.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PartidoEntrenamiento> getEstadisticaPartidoEntrenamiento(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEstadisticaPartidoEntrenamiento(PartidoEntrenamiento c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PartidoEntrenamiento findEstadisticaPartidoEntrenamientoById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteEstadisticaPartidoEntrenamientoById(int id){
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<PartidoEntrenamiento> findWhere(String fieldName, Object value){
        try {
            return dao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
