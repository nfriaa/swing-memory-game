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
package net.isetjb;

import javax.swing.UIManager;
import net.isetjb.config.I18N;
import net.isetjb.config.PROP;
import net.isetjb.dao.DAOInitializer;
import org.apache.log4j.Logger;

/**
 * Main Application class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class MemoryGame
{
    final static Logger log = Logger.getLogger(MemoryGame.class);

    public static void main(String[] args)
    {
        log.info("Initializing the application...");

        PROP.init();
        I18N.init();
        macosConfig();
        //lookF();
        DAOInitializer.init();

        log.info("Starting " + PROP.getProperty("app.finalName") + " Application...");

        // display the desktop frame :
        new Desktop();

        log.info("Application " + PROP.getProperty("app.finalName") + " started.");
    }

    /**
     * Special settting for macOS.
     */
    public static void macosConfig()
    {
        if (System.getProperty("os.name").contains("Mac"))
        {
            log.debug("Special settings for macOS users...");

            // take the menu bar off the jframe :
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
    }

    /**
     * Apply system default look and feel L&F.
     */
    public static void lookF()
    {
        log.debug("Trying to apply default system look and feel...");

        try
        {
            // Set to System L&F :
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

            log.debug("Success.");

        } catch (Exception e)
        {
            log.debug("Error setting system look an feel : " + e);
        }
    }
}
