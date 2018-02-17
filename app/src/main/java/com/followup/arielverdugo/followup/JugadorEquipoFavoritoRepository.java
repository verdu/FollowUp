package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by arielverdugo on 16/2/18.
 */

public class JugadorEquipoFavoritoRepository {

    private static JugadorEquipoFavoritoRepository instance;

    private Dao<JugadorEquipoFavorito, Integer> dao;


    public static JugadorEquipoFavoritoRepository getInstance(Context context) {
        if(instance == null)
            instance = new JugadorEquipoFavoritoRepository((Context) context);
        return instance;
    }

    private JugadorEquipoFavoritoRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(JugadorEquipoFavorito.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JugadorEquipoFavorito> getJugadorEquipoFavorito(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addJugadorEquipoFavorito(JugadorEquipoFavorito c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JugadorEquipoFavorito findJugadorEquipoFavoritoById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteJugadorEquipoFavoritoById(int id){
        try {
            dao.deleteById(id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<JugadorEquipoFavorito> findWhere(String fieldName,Object value){
        try {
            return dao.queryForEq(fieldName, value);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateJugadorEquipoFavorito(JugadorEquipoFavorito a)
    {
        try {
            dao.update(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteJugadorEquipoFavoritoByParams(Map<String, String> params){
        try {
            DeleteBuilder<JugadorEquipoFavorito,Integer> deleteBuilder = dao.deleteBuilder();
            Where<JugadorEquipoFavorito, Integer> where = deleteBuilder.where();

            for(Map.Entry<String, String> param: params.entrySet()) {
                where.eq(param.getKey(), param.getValue());
            }
            where.and(params.size());
            PreparedDelete<JugadorEquipoFavorito> preparedDelete = deleteBuilder.prepare();
            dao.delete(preparedDelete);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
