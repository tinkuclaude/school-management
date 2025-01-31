package com.guimotech.gui.swing;

import com.guimotech.config.HelperService;
import com.guimotech.gui.swing.model.MyTableModelMap;
import com.guimotech.service.TrimestreService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class LstTrimestre extends JFrame {

    private static final TrimestreService trimestreService = TrimestreService.getInstance();
    private JPanel jContentPane = null;

    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private JPanel jPanelHeaders = null;

    private  JScrollPane jScrollPane = null;
    private JTable jTable = null;

    private static LstTrimestre instance = null;
    public static LstTrimestre getInstance(JFrame parent) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new LstTrimestre(parent);
        return instance;
    }

    private LstTrimestre(JFrame parent) {
        super();
        initialize();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void initialize() {
        this.setSize(500, 300);
        this.setContentPane(getJContentPane());
        this.setTitle("Liste des trimestres");

//        setFields();
    }

    private void setFields() {
        String[] vHeader = new String[] {"numero", "intitulé"};
        List<HashMap<String, Object>> data;
        try {
            data = trimestreService.findAllMap();
            System.out.println(data);
        } catch (SQLException e) {
            HelperService.showMessage(e);
            return;
        }

        jTable.setModel(new MyTableModelMap(data, vHeader));
        ((MyTableModelMap)jTable.getModel()).setWidth(null);
    }

    void loadGrid(){
        List<HashMap<String, Object>> data;
        try {
            data = trimestreService.findAllMap();
            System.out.println(data);
        } catch (SQLException e) {
            HelperService.showMessage(e);
            return;
        }
        if (data != null && jTable.getModel() instanceof MyTableModelMap) {
            ((MyTableModelMap)jTable.getModel()).setData(data);
        }
    }

//    private int[] getTableHeaderSize() {
//
//    }

    private JPanel getJContentPane() {
        if(jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getJPanelHeaders(), BorderLayout.NORTH);
            jContentPane.add(getJPanelContents(), BorderLayout.CENTER);
            jContentPane.add(getJPanelButtons(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    public JPanel getJPanelContents() {
        if(jPanelContents == null) {
            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));

            jPanelContents.add(getJScrollPane());
//            jPanelContents.add(getJTable());
        }
        return jPanelContents;
    }

    public JPanel getJPanelButtons() {
        if(jPanelButtons == null) {
            jPanelButtons = new JPanel();
            jPanelButtons.setLayout(new FlowLayout());
            jPanelButtons.setBorder(new LineBorder(new Color(25, 100, 250), 2));

            jPanelButtons.add(getJButtonNew());
        }
        return jPanelButtons;
    }

    public JPanel getJPanelHeaders() {
        if(jPanelHeaders == null) {
            jPanelHeaders = new JPanel();
            jPanelHeaders.setLayout(null);
            jPanelHeaders.setBorder(new LineBorder(new Color(25, 100, 250), 2));
            jPanelHeaders.setPreferredSize(new Dimension(30, 50));
        }
        return jPanelHeaders;
    }

    public JScrollPane getJScrollPane() {
        if(jScrollPane == null) {
            jScrollPane = new JScrollPane();
            jScrollPane.setAutoscrolls(true);
            jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane.setViewportView(getJTable());

            jScrollPane.setColumnHeader(new JViewport() {
                private static final long serialVersionUID = 1L;
                @Override
                public Dimension getPreferredSize() {
                    Dimension d = super.getPreferredSize();
                    d.height = 60;
                    return d;
                }
            });

        }
        return jScrollPane;
    }

    public JTable getJTable() {
        if(jTable == null ) {

            Object[][] data = {
                    {"John", "Doe", 29},
                    {"Jane", "Smith", 34}
            };
            String[] columnNames = {"First Name", "Last Name", "Age"};

            jTable = new JTable(data, columnNames);
            jTable.setShowHorizontalLines(false);
            jTable.setAutoscrolls(true);
            jTable.setRowHeight(25);
//            jTable.setBackground(Color.white);
            jTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount() == 2) {
                        modifier();
                    }
                }
            });
        }
        return jTable;
    }

    private void modifier() {
        System.out.println("modifier ----------------");
        if (jTable.getSelectedRowCount() != 1) return ;
        try{
            int selectedrow = jTable.getSelectedRow();
            Integer numero = (Integer) ((MyTableModelMap)jTable.getModel()).
                    getRow(selectedrow).get("numero");

            SglTrimestre.getInstance(instance, true, numero).setVisible(true);
            loadGrid();
        } catch (Exception e) {
            HelperService.showMessage(e);
        }
    }

    private JButton jButtonNew = null;
    public JButton getJButtonNew() {
        if(jButtonNew == null) {
            jButtonNew = new JButton();
            jButtonNew.setText("Nouveau");
            jButtonNew.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nouveau();
                }
            });
        }
        return jButtonNew;
    }

    private void nouveau() {
        System.out.println("nouveau ----------------");
        SglTrimestre.getInstance(instance, true, null).setVisible(true);
        loadGrid();
    }
}
