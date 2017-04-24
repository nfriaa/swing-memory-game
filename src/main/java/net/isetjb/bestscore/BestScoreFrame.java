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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.isetjb.config.I18N;
import org.apache.log4j.Logger;

/**
 * Class used to display best scores list.
 *
 * @author Nafaa Friaa (nafaa.friaa@isetjb.rnu.tn)
 */
public class BestScoreFrame extends JInternalFrame
{
    final static Logger log = Logger.getLogger(BestScoreFrame.class);

    JPanel jPanelHeader = new JPanel();
    //JLabel jLabel1 = new JLabel(I18N.lang("bestscoreframe.jLabel1"));

    JComboBox filterCategory = new JComboBox();
    JComboBox filterLevel = new JComboBox();

    public JTable jTable1;

    /**
     * Constructor.
     */
    public BestScoreFrame()
    {
        log.debug("START constructor...");

        setTitle(I18N.lang("bestscoreframe.title"));
        setLocation(new Random().nextInt(100) + 100, new Random().nextInt(100));
        setSize(550, 350);
        setVisible(false);
        setClosable(true);
        setIconifiable(true);
        //setMaximizable(false);
        //setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        // header :
        jPanelHeader.setBorder(BorderFactory.createTitledBorder(I18N.lang("bestscoreframe.jPanelHeader")));
        jPanelHeader.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // filters :
        filterCategory.addItem("- " + I18N.lang("menubar.jMenuCategory") + " -");
        filterCategory.addItem(I18N.lang("menubar.jMenuItemCategory0"));
        filterCategory.addItem(I18N.lang("menubar.jMenuItemCategory1"));
        filterCategory.addItem(I18N.lang("menubar.jMenuItemCategory2"));
        filterCategory.addItem(I18N.lang("menubar.jMenuItemCategory3"));
        filterCategory.addItem(I18N.lang("menubar.jMenuItemCategory4"));

        jPanelHeader.add(filterCategory);

        filterCategory.addItemListener((ItemEvent ev) ->
        {
            log.debug("ItemEvent on " + ev.getItem().toString());

            jTable1.setModel(this.getData(filterCategory.getSelectedIndex() - 1, filterLevel.getSelectedIndex() - 1));

        });

        filterLevel.addItem("- " + I18N.lang("menubar.jMenuLevel") + " -");
        filterLevel.addItem(I18N.lang("menubar.jMenuItemLevel0"));
        filterLevel.addItem(I18N.lang("menubar.jMenuItemLevel1"));
        filterLevel.addItem(I18N.lang("menubar.jMenuItemLevel2"));
        filterLevel.addItem(I18N.lang("menubar.jMenuItemLevel3"));

        jPanelHeader.add(filterLevel);

        filterLevel.addItemListener((ItemEvent ev) ->
        {
            log.debug("ItemEvent on " + ev.getItem().toString());

            jTable1.setModel(this.getData(filterCategory.getSelectedIndex() - 1, filterLevel.getSelectedIndex() - 1));

        });

        getContentPane().add(jPanelHeader, BorderLayout.NORTH);

        // Table :
        jTable1 = new JTable(this.getData(-1, -1));
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);

        getContentPane().add(new JScrollPane(jTable1), BorderLayout.CENTER);

        log.debug("End of constructor.");
    }

    /**
     * Method to get the data from Repository and return it in DefaultTableModel
     * object. Very useful for refreshing JTable after data modification.
     *
     * @return
     */
    public DefaultTableModel getData(int category, int level)
    {
        log.debug("Start method...");

        // Comumns :
        String[] columns = new String[]
        {
            I18N.lang("bestscoreframe.jtable1.coloumn.playerName"),
            I18N.lang("bestscoreframe.jtable1.coloumn.gameDate"),
            I18N.lang("bestscoreframe.jtable1.coloumn.gameCategory"),
            I18N.lang("bestscoreframe.jtable1.coloumn.gameLevel"),
            I18N.lang("bestscoreframe.jtable1.coloumn.gameScore")
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // get data rows :
        BestScoreRepository bsRepository = new BestScoreRepository();
        ArrayList<BestScoreBean> items = bsRepository.findAll();

        // transform ArrayList<> to Object[][] :
        for (BestScoreBean item : items)
        {
            // filters :
            if (category != -1 && category != item.getGameCategory())
            {
                continue;
            }
            if (level != -1 && level != item.getGameLevel())
            {
                continue;
            }

            model.addRow(new Object[]
            {
                item.getPlayerName(),
                item.getGameDate(),
                I18N.lang("menubar.jMenuItemCategory" + item.getGameCategory()),
                I18N.lang("menubar.jMenuItemLevel" + item.getGameLevel()),
                item.getGameScore()
            });
        }

        log.debug("End method.");

        return model;
    }

}
