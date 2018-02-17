package com.followup.arielverdugo.followup;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arielverdugo on 25/10/17.
 */

public class JugadorEquipoRepository
{
    private static JugadorEquipoRepository instance;

    private Dao<JugadorEquipo, Integer> dao;
    private Dao<Jugador,Integer> jugadorDao;
    private Dao<Equipo,Integer> equipoDao;
    private Dao<JugadorEquipo,Integer> jugadorEquipoDao;

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

    public List<Jugador>findJugadorPorEquipoById(int id){
        try {
            QueryBuilder<JugadorEquipo, Integer> jugadorEquipoQb = jugadorEquipoDao.queryBuilder();
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
    }

    public List<Jugador> getJugadoresFavoritosPorEquipo(int idEquipo){
        try {
            GenericRawResults<Jugador> rawResults = dao.queryRaw(
                    "SELECT j.* FROM Jugador j WHERE idJugador in (SELECT idJugador FROM JugadorEquipo je " +
                            "INNER JOIN JugadorEquipoFavorito jef " +
                            "ON jef.idJugador = je.idJugador AND jef.favorito = 1" +
                            "WHERE je.idEquipo = " + idEquipo,
                    new RawRowMapper<Jugador>() {
                        // each result row is a single integer in the 0th column
                        public Jugador mapRow(String[] columnNames, String[] resultColumns) {
                            Jugador j = new Jugador();
                            j.setNombre("Ariel");
                            j.setApellido("Verdugo");
                            return j;
                        }}
            );

            return rawResults.getResults();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public Map<Integer, Boolean> getFavoritosPorJugadorEquipo(int idEquipo){
        Map<Integer, Boolean> valores = new HashMap<Integer, Boolean>();
        try {
            GenericRawResults<String[]> rawResults = dao.queryRaw(
                    "SELECT j.id FROM jugador j WHERE j.id in (SELECT je.id_jugador FROM jugadorequipo je " +
                            "INNER JOIN jugadorequipofavorito jef " +
                            "ON jef.id_jugador_equipo = je.id AND jef.favorito = 1 " +
                            "WHERE je.id_equipo = " + idEquipo + ")");



            List<String[]> favoritosRes  = rawResults.getResults();
            if(!favoritosRes.isEmpty()) {
                for(String[] result: favoritosRes) {
                    valores.put(Integer.parseInt(result[0]), true);
                }
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return valores;
    }

    public List<JugadorEquipo> findWhere(Map<String, String> params){
        try {
            QueryBuilder<JugadorEquipo, Integer> queryBuilder = dao.queryBuilder();
            Where<JugadorEquipo, Integer> where = queryBuilder.where();

            for(Map.Entry<String, String> param: params.entrySet()) {
                where.eq(param.getKey(), param.getValue());
            }

            where.and(params.size());
            PreparedQuery<JugadorEquipo> preparedQuery = queryBuilder.prepare();
            return dao.query(preparedQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
