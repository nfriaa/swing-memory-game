/*
 * The MIT License
 *
 * Copyright 2017 Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.isetjb.bestscore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.isetjb.dao.DAOConnection;
import net.isetjb.dao.Repository;
import org.apache.log4j.Logger;

/**
 * The BestScore Repository implementation.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class BestScoreRepository implements Repository<BestScoreBean>
{
    final static Logger log = Logger.getLogger(BestScoreRepository.class);

    /**
     * Find one item by id.
     *
     * @param id
     * @return
     */
    @Override
    public BestScoreBean find(long id)
    {
        log.debug("Start method...");

        BestScoreBean obj = null;

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    "SELECT * FROM best_scores WHERE id=?");

            prepared.setLong(1, id);

            ResultSet result = prepared.executeQuery();

            if (result.first())
            {
                obj = map(result);
            }

        } catch (SQLException e)
        {
            log.error("Error finding product : " + e);
        }

        log.debug("End method.");

        return obj;
    }

    /**
     * Find all items.
     *
     * @return
     */
    @Override
    public ArrayList<BestScoreBean> findAll()
    {
        log.debug("Start method...");

        ArrayList<BestScoreBean> bestScores = new ArrayList<>();

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    "SELECT * FROM best_scores");

            ResultSet result = prepared.executeQuery();

            while (result.next())
            {
                bestScores.add(map(result));
            }

        } catch (SQLException e)
        {
            log.error("Error finding best_scores : " + e);
        }

        log.debug("End method.");

        return bestScores;
    }

    /**
     * Create new Object and return this new Object if success. Run only on
     * tables with auto_increment id column.
     *
     * @param obj
     * @return
     */
    @Override
    public BestScoreBean create(BestScoreBean obj)
    {
        log.debug("Start method...");

        BestScoreBean objectToReturn = null;

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    " INSERT INTO best_scores (player_name, game_date, game_category, game_level, game_score) "
                    + " VALUES(?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);

            prepared.setString(1, obj.getPlayerName());
            prepared.setObject(2, obj.getGameDate());
            prepared.setInt(3, obj.getGameCategory());
            prepared.setInt(4, obj.getGameLevel());
            prepared.setInt(5, obj.getGameScore());

            // execute query and get the affected rows number :
            int affectedRows = prepared.executeUpdate();
            if (affectedRows != 0)
            {
                // get the latest inserted id :
                ResultSet generatedKeys = prepared.getGeneratedKeys();
                if (generatedKeys.next())
                {
                    log.debug("Inserted id : " + generatedKeys.getLong(1));
                    objectToReturn = this.find(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e)
        {
            log.error("Error creating new best_score : " + e);
        }

        log.debug("End method.");

        return objectToReturn;
    }

    /**
     * Update a record.
     *
     * @param obj
     * @return
     */
    @Override
    public BestScoreBean update(BestScoreBean obj)
    {
        log.debug("Start method...");

        BestScoreBean objectToReturn = null;

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    " UPDATE best_scores "
                    + " SET player_name=?, "
                    + " game_date=?, "
                    + " game_category=?, "
                    + " game_level=?, "
                    + " game_score=? "
                    + " WHERE id=? ");

            prepared.setString(1, obj.getPlayerName());
            prepared.setObject(2, obj.getGameDate());
            prepared.setInt(3, obj.getGameCategory());
            prepared.setInt(4, obj.getGameLevel());
            prepared.setInt(5, obj.getGameScore());
            prepared.setLong(6, obj.getId());

            // execute query and get the affected rows number :
            int affectedRows = prepared.executeUpdate();
            if (affectedRows != 0)
            {
                log.debug("Updated id : " + obj.getId());
                objectToReturn = this.find(obj.getId());
            }

        } catch (SQLException e)
        {
            log.error("Error updating best_score : " + e);
        }

        log.debug("End method.");

        return objectToReturn;
    }

    /**
     * Delete a single record.
     *
     * @param id
     * @return the number of affected rows.
     */
    @Override
    public int delete(long id)
    {
        log.debug("Start method...");

        int affectedRows = 0;

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    " DELETE FROM best_scores "
                    + " WHERE id=? ");

            prepared.setLong(1, id);

            // execute query and get the affected rows number :
            affectedRows = prepared.executeUpdate();

        } catch (SQLException e)
        {
            log.error("Error deleteing best_score : " + e);
        }

        log.debug("End method.");

        return affectedRows;
    }

    /**
     * Map the current row of the given ResultSet to an Object.
     *
     * @param resultSet
     * @return The mapped Object from the current row of the given ResultSet.
     * @throws SQLException If something fails at database level.
     */
    private static BestScoreBean map(ResultSet resultSet) throws SQLException
    {
        BestScoreBean obj = new BestScoreBean();

        obj.setId(resultSet.getLong("id"));
        obj.setPlayerName(resultSet.getString("player_name"));
        obj.setGameDate(resultSet.getDate("game_date"));
        obj.setGameCategory(resultSet.getInt("game_category"));
        obj.setGameLevel(resultSet.getInt("game_level"));
        obj.setGameScore(resultSet.getInt("game_score"));

        return obj;
    }

    /**
     * Find the score min for given category and level.
     *
     * @param category
     * @param level
     * @return
     */
    public int getMinScore(int category, int level)
    {
        log.debug("Start method...");

        int minScore = 0;

        try
        {
            PreparedStatement prepared = DAOConnection.getInstance().prepareStatement(
                    "SELECT MIN(game_score) AS min_score FROM best_scores WHERE game_category=? AND game_level=? ");

            prepared.setInt(1, category);
            prepared.setInt(2, level);

            ResultSet result = prepared.executeQuery();

            if (result.first())
            {
                minScore = result.getInt("min_score");
            }

        } catch (SQLException e)
        {
            log.error("Error finding min score by category and level : " + e);
        }

        log.debug("End method.");

        return minScore;

    }
}
