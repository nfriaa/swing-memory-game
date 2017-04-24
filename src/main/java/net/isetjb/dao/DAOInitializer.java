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
package net.isetjb.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import net.isetjb.bestscore.BestScoreBean;
import net.isetjb.bestscore.BestScoreRepository;
import net.isetjb.config.PROP;
import org.apache.log4j.Logger;
import org.h2.tools.Server;

/**
 * Class for database initialization.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class DAOInitializer
{

    final static Logger log = Logger.getLogger(DAOInitializer.class);

    /**
     * Empty private constructor.
     */
    private DAOInitializer()
    {
    }

    /**
     * Initialize the database.
     */
    public static void init()
    {
        if ("true".equalsIgnoreCase(PROP.getProperty("db.createTables")))
        {
            DAOInitializer.createTables();
        }

        if ("true".equalsIgnoreCase(PROP.getProperty("db.startH2Console")))
        {
            DAOInitializer.startH2WebConsole();
        }
    }

    /**
     * Create db console server to access it in web browser.
     */
    private static void startH2WebConsole()
    {
        log.info("Trying to start H2 Console Web Server...");

        int defaultPort = 2020;
        int port;

        try
        {
            port = Integer.parseInt(PROP.getProperty("db.h2ConsolePort"));
        } catch (NumberFormatException e)
        {
            log.error("Error parsing port number in properties file : " + e);
            log.info("Default port " + defaultPort + " used.");
            port = defaultPort;
        }

        try
        {
            Server h2Server = Server.createWebServer("-webPort", "" + port).start();
            log.info("H2 Console Web Server started and connection is open.");
            log.info("URL: " + h2Server.getURL());
        } catch (SQLException e)
        {
            log.error("Error starting H2 Console Web Server : " + e);
        }
    }

    /**
     * Create tables in the database.
     */
    private static void createTables()
    {
        // products table :
        String createQuery = "CREATE TABLE IF NOT EXISTS best_scores("
                + "id INT PRIMARY KEY AUTO_INCREMENT,"
                + "player_name VARCHAR(255),"
                + "game_date DATE,"
                + "game_category TINYINT(1),"
                + "game_level TINYINT(1),"
                + "game_score INT"
                + ")";

        log.info("Creating table best_scores...");

        try
        {
            PreparedStatement ps = DAOConnection.getInstance().prepareStatement(createQuery);
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0)
            {
                log.debug("Table best_scores created. Query affectedRows : " + affectedRows);
            } else
            {
                log.debug("Table best_scores already exist. Query affectedRows : " + affectedRows);
            }

        } catch (SQLException e)
        {
            log.error("Error creating table best_scores : " + e);
        }
    }

}
