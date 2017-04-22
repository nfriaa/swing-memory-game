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
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;

/**
 * MenuBar class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class MenuBar extends JMenuBar
{
    final static Logger log = Logger.getLogger(MenuBar.class);

    // file :
    JMenu jMenuFile = new JMenu(I18N.lang("menubar.jMenuFile"));
    JMenuItem jMenuItemFrameGame = new JMenuItem(I18N.lang("menubar.jMenuItemFrameGame"));
    JMenuItem jMenuItemQuit = new JMenuItem(I18N.lang("menubar.jMenuItemQuit"));

    // settings :
    JMenu jMenuSettings = new JMenu(I18N.lang("menubar.jMenuSettings"));

    // category :
    JMenu jMenuCategory = new JMenu(I18N.lang("menubar.jMenuCategory"));
    JRadioButtonMenuItem jMenuItemCategory0 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemCategory0"), true);
    JRadioButtonMenuItem jMenuItemCategory1 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemCategory1"));
    JRadioButtonMenuItem jMenuItemCategory2 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemCategory2"));
    JRadioButtonMenuItem jMenuItemCategory3 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemCategory3"));
    JRadioButtonMenuItem jMenuItemCategory4 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemCategory4"));

    // level :
    JMenu jMenuLevel = new JMenu(I18N.lang("menubar.jMenuLevel"));
    JRadioButtonMenuItem jMenuItemLevel0 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemLevel0"), true);
    JRadioButtonMenuItem jMenuItemLevel1 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemLevel1"));
    JRadioButtonMenuItem jMenuItemLevel2 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemLevel2"));
    JRadioButtonMenuItem jMenuItemLevel3 = new JRadioButtonMenuItem(I18N.lang("menubar.jMenuItemLevel3"));

    // help :
    JMenu jMenuHelp = new JMenu(I18N.lang("menubar.jMenuHelp"));
    JMenuItem jMenuItemFrameAbout = new JMenuItem(I18N.lang("menubar.jMenuItemFrameAbout"));

    /**
     * Constructor.
     */
    public MenuBar()
    {
        log.debug("START constructor...");

        // file :
        add(jMenuFile);
        jMenuFile.setMnemonic(KeyEvent.VK_F);

        jMenuItemFrameGame.setAccelerator(KeyStroke.getKeyStroke('J', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuFile.add(jMenuItemFrameGame);

        jMenuFile.addSeparator();

        jMenuItemQuit.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuFile.add(jMenuItemQuit);

        // settings :
        add(jMenuSettings);

        // category :
        jMenuSettings.add(jMenuCategory);
        jMenuCategory.add(jMenuItemCategory0);
        jMenuCategory.add(jMenuItemCategory1);
        jMenuCategory.add(jMenuItemCategory2);
        jMenuCategory.add(jMenuItemCategory3);
        jMenuCategory.add(jMenuItemCategory4);

        ButtonGroup radioGroup1 = new ButtonGroup();
        radioGroup1.add(jMenuItemCategory0);
        radioGroup1.add(jMenuItemCategory1);
        radioGroup1.add(jMenuItemCategory2);
        radioGroup1.add(jMenuItemCategory3);
        radioGroup1.add(jMenuItemCategory4);

        // level :
        jMenuSettings.add(jMenuLevel);
        jMenuLevel.add(jMenuItemLevel0);
        jMenuLevel.add(jMenuItemLevel1);
        jMenuLevel.add(jMenuItemLevel2);
        jMenuLevel.add(jMenuItemLevel3);

        ButtonGroup radioGroup2 = new ButtonGroup();
        radioGroup2.add(jMenuItemLevel0);
        radioGroup2.add(jMenuItemLevel1);
        radioGroup2.add(jMenuItemLevel2);
        radioGroup2.add(jMenuItemLevel3);

        // help :
        add(jMenuHelp);
        jMenuHelp.setMnemonic(KeyEvent.VK_H);

        jMenuItemFrameAbout.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        jMenuHelp.add(jMenuItemFrameAbout);

        log.debug("End of constructor.");
    }
}
