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

import net.isetjb.config.I18N;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import org.apache.log4j.Logger;

/**
 * Desktop class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class Desktop extends JFrame
{

    final static Logger log = Logger.getLogger(Desktop.class);

    JDesktopPane jDesktopPane = new JDesktopPane();
    JLabel jLabelFooterState = new JLabel(I18N.lang("desktop.jLabelFooterState") + System.getProperty("os.name") + " " + System.getProperty("os.version"));

    // internal frames :
    FrameAbout frameAbout = new FrameAbout();
    FrameGame frameGame;

    // menu :
    MenuBar menuBar = new MenuBar();

    /**
     * Constructor.
     */
    public Desktop()
    {
        log.debug("START constructor...");

        // init frame :
        setTitle(I18N.lang("desktop.title"));
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, screenSize.width, screenSize.height);

        // init desktop :
        getContentPane().add(jDesktopPane, BorderLayout.CENTER);
        getContentPane().add(jLabelFooterState, BorderLayout.SOUTH);

        // add internal frames to desktop :
        jDesktopPane.add(frameAbout);

        // add the menu bar :
        setJMenuBar(menuBar);

        // menu listeners :
        // jMenuItemQuit :
        menuBar.jMenuItemQuit.addActionListener((ActionEvent ev) ->
        {
            log.debug("ActionEvent on " + ev.getActionCommand());

            if (confirmBeforeExit())
            {
                System.exit(0);
            }
        });

        // jMenuItemFrameAbout :
        menuBar.jMenuItemFrameAbout.addActionListener((ActionEvent ev) ->
        {
            log.debug("ActionEvent on " + ev.getActionCommand());

            frameAbout.setVisible(true);
        });

        // jMenuItemFrameGame :
        menuBar.jMenuItemFrameGame.addActionListener((ActionEvent ev) ->
        {
            log.debug("ActionEvent on " + ev.getActionCommand());

            // get category from menu :
            int category = 0;
            if (menuBar.jMenuItemCategory0.isSelected())
            {
                category = 0;
            } else if (menuBar.jMenuItemCategory1.isSelected())
            {
                category = 1;
            } else if (menuBar.jMenuItemCategory2.isSelected())
            {
                category = 2;
            } else if (menuBar.jMenuItemCategory3.isSelected())
            {
                category = 3;
            } else if (menuBar.jMenuItemCategory4.isSelected())
            {
                category = 4;
            }

            // get level from menu :
            int level = 0;
            if (menuBar.jMenuItemLevel0.isSelected())
            {
                level = 0;
            } else if (menuBar.jMenuItemLevel1.isSelected())
            {
                level = 1;
            } else if (menuBar.jMenuItemLevel2.isSelected())
            {
                level = 2;
            } else if (menuBar.jMenuItemLevel3.isSelected())
            {
                level = 3;
            }

            frameGame = new FrameGame(category, level);
            jDesktopPane.add(frameGame);
            frameGame.setVisible(true);
        });

        // window closing event :
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent ev)
            {
                log.debug("WindowEvent on " + ev.paramString());

                if (confirmBeforeExit())
                {
                    System.exit(0);
                }
            }
        });

        setVisible(true);

        log.debug("End of constructor.");
    }

    /**
     * Show confirm dialog before closing the window.
     *
     * @return boolean true user answer Yes.
     */
    public boolean confirmBeforeExit()
    {
        log.debug("Display confirm dialog...");

        if (JOptionPane.showConfirmDialog(this, I18N.lang("desktop.confirmbeforeexitdialog.text"), I18N.lang("desktop.confirmbeforeexitdialog.title"), JOptionPane.YES_NO_OPTION) == 0)
        {
            log.debug("User answer YES.");
            return true;
        }

        log.debug("User answer NO.");
        return false;
    }
}
