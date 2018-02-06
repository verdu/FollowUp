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

public class EntrenamientoRepository {

    private static EntrenamientoRepository instance;

    private Dao<Entrenamiento, Integer> dao;

    public static EntrenamientoRepository getInstance(Context context) {
        if(instance == null)
            instance = new EntrenamientoRepository(context);
        return instance;
    }

    private EntrenamientoRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(Entrenamiento.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entrenamiento> getEntrenamientos(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEntrenamiento(Entrenamiento c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Entrenamiento findEntrenamientoById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteEntrenamientoById(int id){
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Entrenamiento> findWhere(String fieldName,Object value){
        try {
            return dao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
