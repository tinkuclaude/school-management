package com.guimotech.gui.swing;

import com.guimotech.config.HelperService;
import com.guimotech.dao.dto.EleveDTO;
import com.guimotech.service.EleveService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

public class SglEleve extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private EleveService eleveService = EleveService.getInstance();

    private JTextField jTextFieldMatricule = null;
    private JTextField jTextFieldNom = null;
    private JTextField jTextFieldPrenom = null;
    private JComboBox<String> jComboBoxSexe = null;
    private JTextField jTextFieldDatenaiss = null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private EleveDTO eleveDTO;
    private static SglEleve instance = null;
    public static SglEleve getInstance(JFrame parent, boolean modal, Long id) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglEleve(parent, modal, id);
        return instance;
    }

    private SglEleve(JFrame parent, boolean modal, Long id) {
        super(parent, modal);
        initialize(id);
        setLocationRelativeTo(parent);
    }

    void initialize(Long id) {
        this.setSize(300, 350);
        this.setContentPane(getJContentPane());
        this.setTitle("Gestion d' un eleve");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                close();
//            }
//        });

        if(id == null) eleveDTO = new EleveDTO();
        else {
            try {
                eleveDTO = eleveService.getEleve(id);
            } catch (Exception e) {
                e.printStackTrace();
                eleveDTO = new EleveDTO();
            }
        }

        setFields();
    }

    private void setFields() {
        jTextFieldMatricule.setText(eleveDTO.getMatricule());
        jTextFieldNom.setText(eleveDTO.getNom());
        jTextFieldPrenom.setText(eleveDTO.getPrenom());
        if(eleveDTO.getSexe() == null)
            jComboBoxSexe.setSelectedIndex(-1);
        else jComboBoxSexe.setSelectedIndex(eleveDTO.getSexe());
        if(eleveDTO.getDatenaiss() == null)
            jTextFieldDatenaiss.setText("");
        else jTextFieldDatenaiss.setText(HelperService.dateToString(eleveDTO.getDatenaiss()));
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

            JLabel jLabelSexe = new JLabel("Sexe");
            jLabelSexe.setBounds(10, 130, 100, 25);

            JLabel jLabelDatenaiss = new JLabel("Datenaiss");
            jLabelDatenaiss.setBounds(10, 170, 100, 25);

            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
            jPanelContents.add(jLabelMatricule);
            jPanelContents.add(getJTextFieldMatricule());
            jPanelContents.add(jLabelNom);
            jPanelContents.add(getJTextFieldNom());
            jPanelContents.add(jLabelPrenom);
            jPanelContents.add(getJTextFieldPrenom());
            jPanelContents.add(jLabelSexe);
            jPanelContents.add(getJComboBoxSexe());
            jPanelContents.add(jLabelDatenaiss);
            jPanelContents.add(getJTextFieldDatenaiss());


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

    public JComboBox<String> getJComboBoxSexe() {
        if(jComboBoxSexe == null) {
            jComboBoxSexe = new JComboBox<>();
            jComboBoxSexe.addItem("Masculin");
            jComboBoxSexe.addItem("Feminin");
            jComboBoxSexe.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jComboBoxSexe.setBounds(120, 130, 100, 25);
        }
        return jComboBoxSexe;
    }

    public JTextField getJTextFieldDatenaiss() {
        if(jTextFieldDatenaiss == null) {
            jTextFieldDatenaiss = new JTextField();
            jTextFieldDatenaiss.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldDatenaiss.setBounds(120, 170, 100, 25);
        }
        return jTextFieldDatenaiss;
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
        Integer sexe = jComboBoxSexe.getSelectedIndex();
        String dateStr = jTextFieldDatenaiss.getText();

        Date dateNaiss = null;

        if(!dateStr.trim().equals(""))
            try {
                dateNaiss = HelperService.stringToDate(dateStr);
            } catch (Exception e) {
                JOptionPane.showMessageDialog (this,
                        "Entrer une date au bon format",
                        "School Management", JOptionPane.INFORMATION_MESSAGE);
                jTextFieldMatricule.requestFocus();
                jTextFieldMatricule.selectAll();
                return;
            }

        eleveDTO.setMatricule(matricule);
        eleveDTO.setNom(nom);
        eleveDTO.setPrenom(prenom);
        eleveDTO.setSexe(sexe);
        eleveDTO.setDatenaiss(dateNaiss);

        try {
            eleveDTO = eleveService.save(eleveDTO);
            JOptionPane.showMessageDialog (this,
                    "Eleve enregistré avec succèss.",
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
        eleveDTO = new EleveDTO();
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
            eleveService.delete(key);
            JOptionPane.showMessageDialog (this,
                    "Eleve supprimé avec succèss.",
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

