package com.guimotech.gui.swing;

import com.guimotech.config.HelperService;
import com.guimotech.dao.dto.EnseignantDTO;
import com.guimotech.service.EnseignantService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class SglEnseignant extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private EnseignantService enseignantService = EnseignantService.getInstance();

    private JTextField jTextFieldMatricule = null;
    private JTextField jTextFieldNom = null;
    private JTextField jTextFieldPrenom = null;
    private JComboBox<String> jComboBoxCivilite = null;
    private JTextField jTextFieldDatenaiss = null;
    private JTextField jTextFieldTelephone = null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private EnseignantDTO enseignantDTO;
    private static SglEnseignant instance = null;
    public static SglEnseignant getInstance(JFrame parent, boolean modal, Long id) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglEnseignant(parent, modal, id);
        return instance;
    }

    private SglEnseignant(JFrame parent, boolean modal, Long id) {
        super(parent, modal);
        initialize(id);
        setLocationRelativeTo(parent);
    }

    void initialize(Long id) {
        this.setSize(300, 450);
        this.setContentPane(getJContentPane());
        this.setTitle("Gestion d' un enseiggnant");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //this.setIconImage();

//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//
//                close();
//            }
//        });

        if(id == null) enseignantDTO = new EnseignantDTO();
        else {
            try {
                enseignantDTO = enseignantService.getEnseignant(id);
            } catch (Exception e) {
                e.printStackTrace();
                enseignantDTO = new EnseignantDTO();
            }
        }

        setFields();
    }

    private void setFields() {
        jTextFieldMatricule.setText(enseignantDTO.getMatricule());
        jTextFieldNom.setText(enseignantDTO.getNom());
        jTextFieldPrenom.setText(enseignantDTO.getPrenom());
        if(enseignantDTO.getCivilite() == null)
            jComboBoxCivilite.setSelectedIndex(-1);
        else jComboBoxCivilite.setSelectedIndex(enseignantDTO.getCivilite());
        if(enseignantDTO.getDatenaiss() == null)
            jTextFieldDatenaiss.setText("");
        else jTextFieldDatenaiss.setText(HelperService.dateToString(enseignantDTO.getDatenaiss()));
        jTextFieldTelephone.setText(enseignantDTO.getTelephone());

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

            JLabel jLabelMatricule = new JLabel("Matricule");
            jLabelMatricule.setBounds(10, 10, 100, 25);

            JLabel jLabelNom = new JLabel("Nom");
            jLabelNom.setBounds(10, 50, 100, 25);

            JLabel jLabelPrenom = new JLabel("Prenom");
            jLabelPrenom.setBounds(10, 90, 100, 25);

            JLabel jLabelCivilite = new JLabel("Civilite");
            jLabelCivilite.setBounds(10, 130, 100, 25);

            JLabel jLabelDatenaiss = new JLabel("Datenaiss");
            jLabelDatenaiss.setBounds(10, 170, 100, 25);

            JLabel jLabelTelephone = new JLabel("Telephone");
            jLabelTelephone.setBounds(10, 210, 100, 25);


            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
            jPanelContents.add(jLabelMatricule);
            jPanelContents.add(getJTextFieldMatricule());
            jPanelContents.add(jLabelNom);
            jPanelContents.add(getJTextFieldNom());
            jPanelContents.add(jLabelPrenom);
            jPanelContents.add(getJTextFieldPrenom());
            jPanelContents.add(jLabelCivilite);
            jPanelContents.add(getJComboBoxCivilite());
            jPanelContents.add(jLabelDatenaiss);
            jPanelContents.add(getJTextFieldDatenaiss());
            jPanelContents.add(jLabelTelephone);
            jPanelContents.add(getJTextFieldTelphone());


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

//    private Component getJButtonDelete() {
//    }

    public JTextField getJTextFieldMatricule() {
        if(jTextFieldMatricule == null) {
            jTextFieldMatricule = new JTextField();
            jTextFieldMatricule.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldMatricule.setBounds(120, 10, 100, 25);
        }
        return jTextFieldMatricule;
    }

    public JTextField getJTextFieldNom() {
        if(jTextFieldNom == null) {
            jTextFieldNom = new JTextField();
            jTextFieldNom.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldNom.setBounds(120, 50, 100, 25);
        }
        return jTextFieldNom;
    }

    public JTextField getJTextFieldPrenom() {
        if(jTextFieldPrenom == null) {
            jTextFieldPrenom = new JTextField();
            jTextFieldPrenom.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldPrenom.setBounds(120, 90, 100, 25);
        }
        return jTextFieldPrenom;
    }

    public JComboBox<String> getJComboBoxCivilite() {
        if(jComboBoxCivilite == null) {
            jComboBoxCivilite = new JComboBox<>();
            jComboBoxCivilite.addItem("Monsieur");
            jComboBoxCivilite.addItem("Madame");
            jComboBoxCivilite.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jComboBoxCivilite.setBounds(120, 130, 100, 25);
        }
        return jComboBoxCivilite;
    }

    public JTextField getJTextFieldDatenaiss() {
        if(jTextFieldDatenaiss == null) {
            jTextFieldDatenaiss = new JTextField();
            jTextFieldDatenaiss.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldDatenaiss.setBounds(120, 170, 100, 25);
        }
        return jTextFieldDatenaiss;
    }

    public JTextField getJTextFieldTelphone() {
        if(jTextFieldTelephone == null) {
            jTextFieldTelephone = new JTextField();
            jTextFieldTelephone.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldTelephone.setBounds(120, 210, 100, 25);
        }
        return jTextFieldTelephone;
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

        String matricule = jTextFieldMatricule.getText();
        String nom = jTextFieldNom.getText();
        String prenom = jTextFieldPrenom.getText();
        int civilite = jComboBoxCivilite.getSelectedIndex();
//        if (civilite==-1) {
//            JOptionPane.showMessageDialog(this, "Entrer la civilite de l'enseignant","School Management", JOptionPane.INFORMATION_MESSAGE);
//        }
        String dateStr = jTextFieldDatenaiss.getText();
        String telephone = jTextFieldTelephone.getText();

        Date dateNaiss = null;

        if(!dateStr.trim().equals(""))
            try {
                dateNaiss = HelperService.stringToDate(dateStr);
            } catch (Exception e) {
//                JOptionPane.showMessageDialog (this,
//                        "Entrer une date au bon format",
//                        "School Management", JOptionPane.INFORMATION_MESSAGE);
//                HelperService.showMessage(e);
                HelperService.showMessageError("Entrer une date au bon format");
                jTextFieldMatricule.requestFocus();
                jTextFieldMatricule.selectAll();
                return;
            }

        enseignantDTO.setMatricule(matricule);
        enseignantDTO.setNom(nom);
        enseignantDTO.setPrenom(prenom);
        enseignantDTO.setCivilite(civilite);
        enseignantDTO.setDatenaiss(dateNaiss);
        enseignantDTO.setTelephone(telephone);

        try {
            enseignantDTO = enseignantService.save(enseignantDTO);
            JOptionPane.showMessageDialog (this,
                    "Enseignant enregistré avec succèss.",
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
        enseignantDTO = new EnseignantDTO();
        setFields();
        jTextFieldMatricule.requestFocus();
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

        String key = jTextFieldMatricule.getText();

        try {
            enseignantService.delete(key);
            JOptionPane.showMessageDialog (this,
                    "Enseignant supprimé avec succèss.",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
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
