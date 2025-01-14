package com.guimotech.gui.swing;

import com.guimotech.dao.dto.NiveauDTO;
import com.guimotech.service.NiveauService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SglNiveau  extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private NiveauService niveauService = NiveauService.getInstance();
    private JTextField JTextFieldId;
    private JTextField jTextFieldCode = null;
    private JTextField jTextFieldIntitule = null;
    private JTextField jTextFieldFrais_insciption = null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private static SglNiveau instance = null;
    private TextField jtextFielId;
    private Component JLabelId;

    public static SglNiveau getInstance(JFrame parent, boolean modal) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglNiveau(parent, modal);
        return instance;
    }

    private SglNiveau(JFrame parent, boolean modal) {
        super(parent, modal);
        initialize();
    }

    void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("Gestion d' un niveau");
        //this.setIconImage();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    private JPanel getJContentPane() {
        if(jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getjPanelContents(), BorderLayout.CENTER);
            jContentPane.add(getJPanelButtons(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    public JPanel getjPanelContents() {
        if(jPanelContents == null) {
            JLabel JLabelId=new JLabel("Id");
            JLabelId.setBounds(10 , 10, 100,25);


            JLabel jLabelCode = new JLabel("Code");
            jLabelCode.setBounds(10, 10, 100, 25);

            JLabel jLabelIntitule = new JLabel("Intitulé");
            jLabelIntitule.setBounds(10, 50, 100, 25);

            JLabel jLabelFrais_inscription=new JLabel("Frais-inscription");


            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
//            Component jlabelId = null;
//            jPanelContents.add(jlabelId);
//            Component getJtextFieldId = null;
//            jPanelContents.add(getJtextFieldId);
//            jPanelContents.add(JLabelId);
//            Component getjTextFieldId = null;
//            jPanelContents.add(getjTextFieldId);
            jPanelContents.add(jLabelCode);
            jPanelContents.add(getJTextFieldCode());
            jPanelContents.add(jLabelIntitule);
            jPanelContents.add(getJTextFieldIntitule());

        }
        return jPanelContents;
    }

    public JPanel getJPanelButtons() {
        if(jPanelButtons == null) {
            jPanelButtons = new JPanel();
            jPanelButtons.setLayout(new FlowLayout());
            jPanelButtons.setBorder(new LineBorder(new Color(25, 100, 250), 2));

            jPanelButtons.add(getJButtonDelete());
            jPanelButtons.add(getJButtonValidate());
            jPanelButtons.add(getJButtonNew());
        }
        return jPanelButtons;
    }

    public JTextField getJTextFieldCode() {
        if(jTextFieldCode == null) {
            jTextFieldCode = new JTextField();
            jTextFieldCode.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldCode.setBounds(120, 10, 100, 25);
        }
        return jTextFieldCode;
    }

    public JTextField getJTextFieldIntitule() {
        if(jTextFieldIntitule == null) {
            jTextFieldIntitule = new JTextField();
            jTextFieldIntitule.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldIntitule.setBounds(120, 50, 100, 25);
        }
        return jTextFieldIntitule;
    }
    public JTextField getjTextFieldFrais_insciption() {
        if(jTextFieldIntitule == null) {
            jTextFieldIntitule = new JTextField();
            jTextFieldIntitule.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldIntitule.setBounds(120, 90, 100, 25);
        }
        return jTextFieldIntitule;
    }

    public JButton getJButtonValidate() {
        if(jButtonValidate == null) {
            jButtonValidate = new JButton();
            jButtonValidate.setText("Valider");
            jButtonValidate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    validated();
                }
            });
        }
        return jButtonValidate;
    }

    void validated(){

        NiveauDTO dto = new NiveauDTO();
        String key;
        try {
            Object code;
            code = String.join(jTextFieldCode.getText());
            dto.setCode(code);
        } catch (Exception e) {
            JOptionPane.showMessageDialog (this,
                    "Entrer un entier valide",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldCode.requestFocus();
            jTextFieldCode.selectAll();
            return;
        }
        dto.setIntitule(jTextFieldIntitule.getText());

        try {
            niveauService.save(dto);
            JOptionPane.showMessageDialog (this,
                    "Niveau enregistré avec succèss.",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog (this,
                    e.getMessage(),
                    "School Management", JOptionPane.ERROR_MESSAGE);
        }
    }

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
        jtextFielId.setText("");
        jTextFieldCode.setText("");
        jTextFieldIntitule.setText("");
    }

    public JButton getJButtonDelete() {
        if(jButtonDelete == null) {
            jButtonDelete = new JButton();
            jButtonDelete.setText("Supprimer");
            jButtonDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    supprimer();
                }
            });
        }
        return jButtonDelete;
    }

    private void supprimer() {

        String codeStr = jTextFieldCode.getText();
        String key;
        try {
            key =(codeStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog (this,
                    "Entrer un entier valide",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldCode.requestFocus();
            jTextFieldCode.selectAll();
            return;
        }


        try {
            niveauService.delete(key);
            JOptionPane.showMessageDialog (this,
                    "Trimestre supprimé avec succèss.",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog (this,
                    e.getMessage(),
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    void close() {
        instance.dispose();
        instance = null;
    }
}

