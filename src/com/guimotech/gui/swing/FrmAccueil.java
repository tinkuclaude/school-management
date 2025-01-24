package com.guimotech.gui.swing;

import com.guimotech.config.DBConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class FrmAccueil extends JFrame {
    private JMenuBar jMenuBarMain = null;
    private JMenu jMenuConfig = null;
    private JMenu jMenuStructure = null;


    private JMenu jMenuPersone = null;

    private JMenuItem jMenuItemTrimestre = null;
    private JMenuItem jMenuItemNiveau = null;
    private JMenuItem jMenuItemEleve = null;
    private JMenuItem jMenuItemEnseignant = null;

    private JPanel jContentPane = null;
    private JPanel jPanelCenter = null;
    private JPanel jPanelSouth = null;


    private static FrmAccueil instance = null;
    public static FrmAccueil getInstance() {
        if(instance == null) {
            instance = new FrmAccueil();
        }
        return instance;
    }

    private FrmAccueil() {
        super();
        initialize();
    }

    private void initialize() {
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setContentPane(getJContentPane());
        setJMenuBar(getJMenuBarMain());
        setTitle("School Management App for GT Learning Center 2025");

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    void close() {
        instance.dispose();
        instance = null;

        try {
            DBConfig.getInstance().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public JPanel getJContentPane() {
        if(jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanelCenter(), BorderLayout.CENTER);
            jContentPane.add(getJPanelSouth(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    public JPanel getJPanelCenter() {
        if(jPanelCenter == null) {
            jPanelCenter = new JPanel();
        }
        return jPanelCenter;
    }

    public JPanel getJPanelSouth() {
        if(jPanelSouth == null) {
            jPanelSouth = new JPanel();
            jPanelSouth.setBackground(Color.BLUE);
            jPanelSouth.setPreferredSize(new Dimension(10, 50));
        }
        return jPanelSouth;
    }

    public JMenuBar getJMenuBarMain() {
        if(jMenuBarMain == null) {
            jMenuBarMain = new JMenuBar();

            jMenuBarMain.add(getJMenuConfig());
            jMenuBarMain.add(getJMenuStructure());
            jMenuBarMain.add(getJMenuPersone());
        }
        return jMenuBarMain;
    }

    public JMenu getJMenuConfig() {
        if(jMenuConfig == null) {
            jMenuConfig = new JMenu();
            jMenuConfig.setText("Configuration");

            jMenuConfig.add(getJMenuItemTrimestre());

        }
        return jMenuConfig;
    }

    public JMenu getJMenuStructure() {
        if(jMenuStructure == null) {
            jMenuStructure = new JMenu();
            jMenuStructure.setText("School");

            jMenuStructure.add(getJMenuItemNiveau());
        }
        return jMenuStructure;
    }

    public JMenu getJMenuPersone() {
        if(jMenuPersone == null) {
            jMenuPersone = new JMenu("Tiers");

            jMenuPersone.add(getJMenuItemEleve());
            jMenuPersone.add(getjMenuItemEnseignant());
        }
        return jMenuPersone;
    }

    public JMenuItem getJMenuItemTrimestre() {
        if(jMenuItemTrimestre == null) {
            jMenuItemTrimestre = new JMenuItem();
            jMenuItemTrimestre.setText("Create term");

            jMenuItemTrimestre.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SglTrimestre.getInstance(instance, false).setVisible(true);
                }
            });
        }
        return jMenuItemTrimestre;
    }

    public JMenuItem getJMenuItemNiveau() {
        if(jMenuItemNiveau == null) {
            jMenuItemNiveau = new JMenuItem();
            jMenuItemNiveau.setText("Create level");

            jMenuItemNiveau.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SglNiveau.getInstance(instance, false, null).setVisible(true);
                }
            });
        }
        return jMenuItemNiveau;
    }

    public JMenuItem getJMenuItemEleve() {
        if(jMenuItemEleve == null) {
            jMenuItemEleve = new JMenuItem();
            jMenuItemEleve.setText("Create student");

            jMenuItemEleve.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SglEleve.getInstance(instance, false, null).setVisible(true);
                }
            });
        }
        return jMenuItemEleve;
    }

    public JMenuItem getjMenuItemEnseignant() {
        if(jMenuItemEnseignant == null) {
            jMenuItemEnseignant = new JMenuItem();
            jMenuItemEnseignant.setText("Create teacher");

            jMenuItemEnseignant.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SglEnseignant.getInstance(instance, false, null).setVisible(true);
                }
            });
        }
        return jMenuItemEnseignant;
    }

}
