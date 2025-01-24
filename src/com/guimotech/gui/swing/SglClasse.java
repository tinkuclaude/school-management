package com.guimotech.gui.swing;

import com.guimotech.dao.dto.ClasseDTO;
import com.guimotech.dao.dto.NiveauDTO;
import com.guimotech.service.ClasseService;
import com.guimotech.service.NiveauService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Callable;

public class SglClasse extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private ClasseService classeService = ClasseService.getInstance();

    private JTextField jTextFieldCode = null;
    private JTextField jTextFieldIntitule = null;
    private JTextField jTextFieldNiveau =null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private static SglClasse instance = null;

    private ClasseDTO classeDTO = new ClasseDTO();

//    public SglNiveau(JFrame parent, boolean modal) {
//    }

    public static SglClasse getInstance(JFrame parent, boolean modal, Long id) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglClasse(parent, modal, id);
        return instance;
    }

    private SglClasse(JFrame parent, boolean modal, Long id) {
        super(parent, modal);
        initialize(id);
    }

    void initialize(Long id) {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("Gestion d'une classe");
        //this.setIconImage();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });

        if(id == null) {
            classeDTO = new ClasseDTO();
        }
        else {
            try {
                classeDTO = classeService.getClasse(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        setFields();
    }

    private void setFields() {
        jTextFieldCode.setText(classeDTO.getCode());
        jTextFieldIntitule.setText(classeDTO.getIntitule());
        if(classeDTO.getNiveau() == null)
            jTextFieldNiveau.setText("");
        else jTextFieldNiveau.setText(String.valueOf(classeDTO.getNiveau()));
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

            JLabel jLabelNiveau = new JLabel("Niveau.");
            jLabelNiveau.setBounds(10, 90, 100, 25);


            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
//            jPanelContents.add(jLabelId);
//            jPanelContents.add(getJTextFieldId());
            jPanelContents.add(jLabelCode);
            jPanelContents.add(getJTextFieldCode());
            jPanelContents.add(jLabelIntitule);
            jPanelContents.add(getJTextFieldIntitule());
            jPanelContents.add(jLabelNiveau);
            jPanelContents.add(getjTextFieldNiveau());

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
    public JTextField getjTextFieldNiveau() {
        if(jTextFieldNiveau == null) {
            jTextFieldNiveau = new JTextField();
            jTextFieldNiveau.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldNiveau.setBounds(120, 90, 100, 25);
        }
        return jTextFieldNiveau;
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
                    "Entrer le code de la classe",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldCode.requestFocus();
            jTextFieldCode.selectAll();
            return;
        }

        String intitule = jTextFieldIntitule.getText();
        if (intitule.trim().equals("")){
            JOptionPane.showMessageDialog (this,
                    "Entrer le code de la classe",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldIntitule.requestFocus();
            jTextFieldIntitule.selectAll();
            return;
        }

        String niv = jTextFieldNiveau.getText();
        String niveau = null;

        if(!niv.trim().equals("")) {
            try {
//                niveau = String.parseInt(niv.trim());
                if(niveau.trim().equals(""))  {
                    JOptionPane.showMessageDialog (this,
                            "Entrer une classe valide",
                            "School Management", JOptionPane.INFORMATION_MESSAGE);
                    jTextFieldNiveau.requestFocus();
                    jTextFieldNiveau.selectAll();
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog (this,
                        "Entrer une classe valide",
                        "School Management", JOptionPane.INFORMATION_MESSAGE);
                jTextFieldNiveau.requestFocus();
                jTextFieldNiveau.selectAll();
                return;
            }
        }

        classeDTO.setCode(code);
        classeDTO.setIntitule(intitule);
        classeDTO.setNiveau(niveau);

        try {
            classeDTO =classeService.save(classeDTO);
            JOptionPane.showMessageDialog (this,
                    "Classe enregistrée avec succèss.",
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
        classeDTO = new ClasseDTO();
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
            ClasseService.delete(key);
            JOptionPane.showMessageDialog (this,
                    "Classe supprimé avec succèss.",
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



