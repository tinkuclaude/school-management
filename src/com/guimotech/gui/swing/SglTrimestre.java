package com.guimotech.gui.swing;

import com.guimotech.dao.dto.TrimestreDTO;
import com.guimotech.service.TrimestreService;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SglTrimestre extends JDialog {
    // JFrame:
    // JDialog:

    // service
    private TrimestreService trimService = TrimestreService.getInstance();

    private JTextField jTextFieldNumero = null;
    private JTextField jTextFieldIntitule = null;

    private JButton jButtonValidate = null;
    private JButton jButtonNew = null;
    private JButton jButtonDelete = null;

    private JPanel jContentPane = null;
    // JPanel for contents in center of frame
    private JPanel jPanelContents = null;
    // JPanel for button on south of frame
    private JPanel jPanelButtons = null;

    private static SglTrimestre instance = null;
    public static SglTrimestre getInstance(JFrame parent, boolean modal) {
        if(instance != null) {
            // supprimer instance existante
            instance.dispose();
            instance = null;
        }
        instance = new SglTrimestre(parent, modal);
        return instance;
    }

    private SglTrimestre(JFrame parent, boolean modal) {
        super(parent, modal);
        initialize();
    }

    void initialize() {
        this.setSize(300, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("Gestion d' un trimestre");
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

            JLabel jLabelNumero = new JLabel("Numéro");
            jLabelNumero.setBounds(10, 10, 100, 25);

            JLabel jLabelIntitule = new JLabel("Intitulé");
            jLabelIntitule.setBounds(10, 50, 100, 25);

            jPanelContents = new JPanel();
            jPanelContents.setLayout(null);
            jPanelContents.setBorder(new LineBorder(new Color(25, 100, 250), 2));
            jPanelContents.add(jLabelNumero);
            jPanelContents.add(getJTextFieldNumero());
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

    public JTextField getJTextFieldNumero() {
        if(jTextFieldNumero == null) {
            jTextFieldNumero = new JTextField();
            jTextFieldNumero.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldNumero.setBounds(120, 10, 100, 25);
        }
        return jTextFieldNumero;
    }

    public JTextField getJTextFieldIntitule() {
        if(jTextFieldIntitule == null) {
            jTextFieldIntitule = new JTextField();
            jTextFieldIntitule.setBorder(new LineBorder(new Color(0, 0, 0), 1));
            jTextFieldIntitule.setBounds(120, 50, 100, 25);
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

        TrimestreDTO dto = new TrimestreDTO();
        int numero;
        try {
            numero = Integer.parseInt(jTextFieldNumero.getText());
            dto.setNumero(numero);
        } catch (Exception e) {
            JOptionPane.showMessageDialog (this,
                    "Entrer un entier valide",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldNumero.requestFocus();
            jTextFieldNumero.selectAll();
            return;
        }
        dto.setIntitule(jTextFieldIntitule.getText());

        try {
            trimService.save(dto);
            JOptionPane.showMessageDialog (this,
                    "Trimestre enregistré avec succèss.",
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
        jTextFieldNumero.setText("");
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

        String numeroStr = jTextFieldNumero.getText();
        int key;
        try {
            key = Integer.parseInt(numeroStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog (this,
                    "Entrer un entier valide",
                    "School Management", JOptionPane.INFORMATION_MESSAGE);
            jTextFieldNumero.requestFocus();
            jTextFieldNumero.selectAll();
            return;
        }


        try {
            trimService.delete(key);
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
