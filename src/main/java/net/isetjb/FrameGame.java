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
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JInternalFrame;
import javax.swing.JToggleButton;
import org.apache.log4j.Logger;

/**
 * FrameJeux class.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class FrameGame extends JInternalFrame
{
    final static Logger log = Logger.getLogger(FrameGame.class);

    // icones :
    ImageIcon icone0 = new ImageIcon(getClass().getClassLoader().getResource("images/icone0.png"));

    ImageIcon icone1 = new ImageIcon(getClass().getClassLoader().getResource("images/icone1.png"));
    ImageIcon icone2 = new ImageIcon(getClass().getClassLoader().getResource("images/icone2.png"));
    ImageIcon icone3 = new ImageIcon(getClass().getClassLoader().getResource("images/icone3.png"));
    ImageIcon icone4 = new ImageIcon(getClass().getClassLoader().getResource("images/icone4.png"));
    ImageIcon icone5 = new ImageIcon(getClass().getClassLoader().getResource("images/icone5.png"));
    ImageIcon icone6 = new ImageIcon(getClass().getClassLoader().getResource("images/icone6.png"));
    ImageIcon icone7 = new ImageIcon(getClass().getClassLoader().getResource("images/icone7.png"));
    ImageIcon icone8 = new ImageIcon(getClass().getClassLoader().getResource("images/icone8.png"));

    ImageIcon icone9 = new ImageIcon(getClass().getClassLoader().getResource("images/icone1.png"));
    ImageIcon icone10 = new ImageIcon(getClass().getClassLoader().getResource("images/icone2.png"));
    ImageIcon icone11 = new ImageIcon(getClass().getClassLoader().getResource("images/icone3.png"));
    ImageIcon icone12 = new ImageIcon(getClass().getClassLoader().getResource("images/icone4.png"));
    ImageIcon icone13 = new ImageIcon(getClass().getClassLoader().getResource("images/icone5.png"));
    ImageIcon icone14 = new ImageIcon(getClass().getClassLoader().getResource("images/icone6.png"));
    ImageIcon icone15 = new ImageIcon(getClass().getClassLoader().getResource("images/icone7.png"));
    ImageIcon icone16 = new ImageIcon(getClass().getClassLoader().getResource("images/icone8.png"));

    ImageIcon icones[] =
    {
        icone1, icone2, icone3, icone4, icone5, icone6, icone7, icone8, icone9, icone10, icone11, icone12, icone13, icone14, icone15, icone16
    };

    // boutons :
    JToggleButton jButton1 = new JToggleButton(icone0);
    JToggleButton jButton11 = new JToggleButton(icone0);
    JToggleButton jButton2 = new JToggleButton(icone0);
    JToggleButton jButton22 = new JToggleButton(icone0);
    JToggleButton jButton3 = new JToggleButton(icone0);
    JToggleButton jButton33 = new JToggleButton(icone0);
    JToggleButton jButton4 = new JToggleButton(icone0);
    JToggleButton jButton44 = new JToggleButton(icone0);

    JToggleButton jButton5 = new JToggleButton(icone0);
    JToggleButton jButton55 = new JToggleButton(icone0);
    JToggleButton jButton6 = new JToggleButton(icone0);
    JToggleButton jButton66 = new JToggleButton(icone0);
    JToggleButton jButton7 = new JToggleButton(icone0);
    JToggleButton jButton77 = new JToggleButton(icone0);
    JToggleButton jButton8 = new JToggleButton(icone0);
    JToggleButton jButton88 = new JToggleButton(icone0);

    JToggleButton buttons[] =
    {
        jButton1, jButton11, jButton2, jButton22, jButton3, jButton33, jButton4, jButton44, jButton5, jButton55, jButton6, jButton66, jButton7, jButton77, jButton8, jButton88
    };

    /**
     * Constructor.
     */
    public FrameGame()
    {
        log.debug("START constructor...");

        setTitle(I18N.lang("framegame.title"));
        setLocation(new Random().nextInt(120) + 20, new Random().nextInt(120) + 20);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // names :
        jButton1.setName("1");
        jButton11.setName("1");
        jButton2.setName("2");
        jButton22.setName("2");
        jButton3.setName("3");
        jButton33.setName("3");
        jButton4.setName("4");
        jButton44.setName("4");
        jButton5.setName("5");
        jButton55.setName("5");
        jButton6.setName("6");
        jButton66.setName("6");
        jButton7.setName("7");
        jButton77.setName("7");
        jButton8.setName("8");
        jButton88.setName("8");

        //add compnent to the frame :
        getContentPane().setLayout(new GridLayout(4, 4));

        for (JToggleButton button : buttons)
        {
            // the hidden icone :
            button.setSelectedIcon(icones[Integer.parseInt(button.getName())]);

            getContentPane().add(button);

            button.addActionListener((ActionEvent ev) ->
            {
                log.debug("ActionEvent on icone" + button.getName());

                log.debug("count selected buttons : " + countSelectedButtons());

                // ne pas permettre le retour à false :
                if (!button.isSelected())
                {
                    button.setSelected(true);
                }

                if (countSelectedButtons() == 3)
                {
                    unSelectAllButtons();
                    button.setSelected(true);
                }

                verify();
            });
        }

        pack();
        setVisible(false);

        log.debug("End of constructor.");
    }

    public int countSelectedButtons()
    {
        int count = 0;

        for (JToggleButton button : buttons)
        {
            if (button.isSelected() && button.isEnabled())
            {
                count++;
            }
        }

        return count;
    }

    public void unSelectAllButtons()
    {
        for (JToggleButton button : buttons)
        {
            if (button.isSelected() && button.isEnabled())
            {
                button.setSelected(false);
            }
        }
    }

    public void verify()
    {
        ArrayList<JToggleButton> selection = new ArrayList<>();

        for (JToggleButton button : buttons)
        {
            if (button.isSelected() && button.isEnabled())
            {
                selection.add(button);
            }
        }

        if (selection.size() == 2)
        {
            log.debug("First selected : " + selection.get(0).getName());
            log.debug("Second selected : " + selection.get(1).getName());

            if (selection.get(0).getName() == selection.get(1).getName())
            {
                selection.get(0).setEnabled(false);
                selection.get(1).setEnabled(false);
                log.debug("Good Attempt !");
            } else
            {
                log.debug("Wrong Attempt !");
            }
        }
    }

}
