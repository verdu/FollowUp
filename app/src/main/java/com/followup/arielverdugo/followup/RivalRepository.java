package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by arielverdugo on 8/2/18.
 */

public class RivalRepository {
    private static RivalRepository instance;

    private Dao<Rival, Integer> dao;

    public static RivalRepository getInstance(Context context) {
        if(instance == null)
            instance = new RivalRepository(context);
        return instance;
    }

    private RivalRepository(Context context){
        OrmLiteSqliteOpenHelper helper =  OpenHelperManager.getHelper(context, DatabaseHelper.class);
        try {
            dao = helper.getDao(Rival.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rival> getRivales(){
        try {
            return dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addRival(Rival c){
        try {
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Rival findRivalById(int id){
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteRivalById(int id){
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Rival> findWhere(String fieldName,Object value){
        try {
            return dao.queryForEq(fieldName, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
