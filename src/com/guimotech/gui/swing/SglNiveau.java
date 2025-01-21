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

public class SglNiveau extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private NiveauService niveauService = NiveauService.getInstance();

    private JTextField jTextFieldCode = null;
    private JTextField jTextFieldIntitule = null;
    private JTextField jTextFieldFraisInscription =null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private static SglNiveau instance = null;

    private NiveauDTO niveauDTO = new NiveauDTO();

//    public SglNiveau(JFrame parent, boolean modal) {
//    }

    public static SglNiveau getInstance(JFrame parent, boolean modal, Long id) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglNiveau(parent, modal, id);
        return instance;
    }

    private SglNiveau(JFrame parent, boolean modal, Long id) {
        super(parent, modal);
        initialize(id);
    }

    void initialize(Long id) {
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

        if(id == null) {
            niveauDTO = new NiveauDTO();
        }
        else {
            try {
                niveauDTO = niveauService.getNiveau(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setFields();
    }

    private void setFields() {
        jTextFieldCode.setText(niveauDTO.getCode());
        jTextFieldIntitule.setText(niveauDTO.getIntitule());
        if(niveauDTO.getFrais_inscription() == null)
            jTextFieldFraisInscription.setText("");
        else jTextFieldFraisInscription.setText(String.valueOf(niveauDTO.getFrais_inscription()));
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

//            JLabel jLabelId = new JLabel("Id");
//            jLabelId.setBounds(10, 10, 100, 25);


            JLabel jLabelCode = new JLabel("Code");
            jLabelCode.setBounds(10, 10, 100, 25);

            JLabel jLabelIntitule = new JLabel("Intitulé");
            jLabelIntitule.setBounds(10, 50, 100, 25);

            JLabel jLabelFraisInscription = new JLabel("Frais Insc.");
            jLabelFraisInscription.setBounds(10, 90, 100, 25);


            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
//            jPanelContents.add(jLabelId);
//            jPanelContents.add(getJTextFieldId());
            jPanelContents.add(jLabelCode);
            jPanelContents.add(getJTextFieldCode());
            jPanelContents.add(jLabelIntitule);
            jPanelContents.add(getJTextFieldIntitule());
            jPanelContents.add(jLabelFraisInscription);
            jPanelContents.add(getjTextFieldFraisInscription());

        }
        return jPanelContents;
    }

//    private Component getJTextFieldCode() {
//    }

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
    public JTextField getjTextFieldFraisInscription() {
        if(jTextFieldFraisInscription == null) {
            jTextFieldFraisInscription = new JTextField();
            jTextFieldFraisInscription.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldFraisInscription.setBounds(120, 90, 100, 25);
        }
        return jTextFieldFraisInscription;
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

//        NiveauDTO dto = new NiveauDTO();

        String code = jTextFieldCode.getText();
        if (code.trim().equals("")){
            JOptionPane.showMessageDialog (this,
                    "Entrer le code du niveau",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldCode.requestFocus();
            jTextFieldCode.selectAll();
            return;
        }

        String intitule = jTextFieldIntitule.getText();
        if (intitule.trim().equals("")){
            JOptionPane.showMessageDialog (this,
                    "Entrer le code du niveau",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldIntitule.requestFocus();
            jTextFieldIntitule.selectAll();
            return;
        }

        String frais = jTextFieldFraisInscription.getText();
        Integer fraisInscription = null;

        if(!frais.trim().equals("")) {
            try {
                fraisInscription = Integer.parseInt(frais.trim());
                if(fraisInscription < 0) {
                    JOptionPane.showMessageDialog (this,
                            "Entrer un frais d'inscription valide",
                            "School Management", JOptionPane.INFORMATION_MESSAGE);
                    jTextFieldFraisInscription.requestFocus();
                    jTextFieldFraisInscription.selectAll();
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog (this,
                        "Entrer un momtant valide",
                        "School Management", JOptionPane.INFORMATION_MESSAGE);
                jTextFieldFraisInscription.requestFocus();
                jTextFieldFraisInscription.selectAll();
                return;
            }
        }

        niveauDTO.setCode(code);
        niveauDTO.setIntitule(intitule);
        niveauDTO.setFrais_inscription(fraisInscription);

        try {
            niveauDTO = niveauService.save(niveauDTO);
            JOptionPane.showMessageDialog (this,
                    "Niveau enregistré avec succèss.",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
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
        niveauDTO = new NiveauDTO();
        setFields();
        jTextFieldCode.requestFocus();
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

        String key = jTextFieldCode.getText();

        try {
            niveauService.delete(key);
            JOptionPane.showMessageDialog (this,
                    "Trimestre supprimé avec succèss.",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);

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
