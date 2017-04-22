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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import net.isetjb.config.I18N;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

    JPanel jPanelGrid = new JPanel();
    JPanel jPanelFooter = new JPanel();
    JLabel jLabelFooter = new JLabel();

    ImageIcon hiddenIcone = new ImageIcon(getClass().getClassLoader().getResource("images/hiddenIcone.png"));

    int score = 0;
    final int category;
    final int level;
    final int MAX_ICONES_NUMBER = 18;
    final int ICONES_NUMBER;
    final int GRID_WIDTH;
    final int GRID_HEIGHT;

    ArrayList<ImageIcon> icones = new ArrayList<>();
    ArrayList<JToggleButton> buttons = new ArrayList<>();

    /**
     * Constructor.
     */
    public FrameGame(int category, int level)
    {
        log.debug("START constructor...");

        this.category = category;
        this.level = level;

        switch (level)
        {
            // beginner
            case 0:
                ICONES_NUMBER = 4;
                GRID_HEIGHT = 2;
                GRID_WIDTH = 4;
                break;

            // medium
            case 1:
                ICONES_NUMBER = 8;
                GRID_HEIGHT = 4;
                GRID_WIDTH = 4;
                break;

            // intermediate
            case 2:
                ICONES_NUMBER = 9;
                GRID_HEIGHT = 3;
                GRID_WIDTH = 6;
                break;

            // advanced
            case 3:
                ICONES_NUMBER = 18;
                GRID_HEIGHT = 6;
                GRID_WIDTH = 6;
                break;

            default:
                ICONES_NUMBER = 4;
                GRID_HEIGHT = 2;
                GRID_WIDTH = 4;
                break;
        }

        setTitle(I18N.lang("framegame.title") + " [ " + I18N.lang("menubar.jMenuItemCategory" + category) + " - " + I18N.lang("menubar.jMenuItemLevel" + level) + " ]");
        setLocation(new Random().nextInt(120) + 20, new Random().nextInt(120) + 20);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(false);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initIcones();
        initButtons();

        jPanelGrid.setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH));
        getContentPane().add(jPanelGrid, BorderLayout.CENTER);

        jPanelFooter.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanelFooter.add(jLabelFooter);
        getContentPane().add(jPanelFooter, BorderLayout.SOUTH);

        updatejLabelFooterText(false);

        // events :
        for (JToggleButton button : buttons)
        {
            button.setSelectedIcon(icones.get(Integer.parseInt(button.getName())));

            jPanelGrid.add(button);

            button.addActionListener((ActionEvent ev) ->
            {
                log.debug("ActionEvent on icone" + button.getName());

                log.debug("count selected buttons : " + countSelectedButtons());

                score++;
                updatejLabelFooterText(false);

                // dont permit hide icone :
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

    public void updatejLabelFooterText(boolean success)
    {
        // construct footer text :
        String temp = "<html>";

        temp += "[ " + I18N.lang("menubar.jMenuItemCategory" + category);
        temp += " - " + I18N.lang("menubar.jMenuItemLevel" + level);
        temp += " ] ";

        if (success)
        {
            temp += "<strong style='color: white; background-color: green;'> &nbsp;";
        }

        temp += I18N.lang("framegame.jLabelFooter.score") + ": " + score;

        if (success)
        {
            temp += " </strong>";
        }

        temp += "</html>";

        jLabelFooter.setText(temp);
    }

    public void initIcones()
    {
        for (int i = 0; i < MAX_ICONES_NUMBER; i++)
        {
            icones.add(new ImageIcon(getClass().getClassLoader().getResource("images/category" + category + "/icone" + i + ".png")));
        }

        Collections.shuffle(icones);
    }

    public void initButtons()
    {
        // first half :
        for (int i = 0; i < ICONES_NUMBER; i++)
        {
            JToggleButton tempButton = new JToggleButton(hiddenIcone);
            tempButton.setName("" + i);
            buttons.add(tempButton);
        }

        // second half :
        for (int i = 0; i < ICONES_NUMBER; i++)
        {
            JToggleButton tempButton = new JToggleButton(hiddenIcone);
            tempButton.setName("" + i);
            buttons.add(tempButton);
        }

        Collections.shuffle(buttons);
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

    public int countRemainingButtons()
    {
        int count = 0;

        for (JToggleButton button : buttons)
        {
            if (!button.isSelected() && button.isEnabled())
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

            if (selection.get(0).getName().equals(selection.get(1).getName()))
            {
                selection.get(0).setEnabled(false);
                selection.get(1).setEnabled(false);
                log.debug("Good Attempt !");
                log.debug("Remaining buttons : " + countRemainingButtons());
                if (countRemainingButtons() == 0)
                {
                    log.debug("Congratulation, game successfully completed !");
                    updatejLabelFooterText(true);
                }
            } else
            {
                log.debug("Wrong Attempt !");
            }
        }
    }

}
