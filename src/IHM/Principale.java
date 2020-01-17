/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IHM;

import SGBD.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fr108211
 */
public class Principale extends javax.swing.JFrame {

    ArrayList<Table> Tables;
    ArrayList<Index> Indexes;
    Index t;

    /**
     * Creates new form Principale
     */
    public Principale() {
        Tables = new ArrayList<Table>();
        Indexes = new ArrayList<Index>();
        for (int i = 0; i < 2; i++) {
            Tables.add(createTable(100, new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3"))));
            new Index(Tables.get(0), "Attr1");
        }
        Indexes.add(new Index(Tables.get(0), "Attr1"));
        initComponents();
        buildBox();
        buildTable(Tables.get(0));
    }

    private void buildBox() {
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        jComboBox3.removeAllItems();
        for (int i = 0; i < Tables.size(); i++) {
            jComboBox1.addItem("Table: " + i);
            jComboBox2.addItem("Table: " + i);
            jComboBox3.addItem("Table: " + i);
        }
        for (int i = 0; i < Indexes.size(); i++) {
            jComboBox1.addItem("Index: " + i);
            jComboBox2.addItem("Index: " + i);
            jComboBox3.addItem("Index: " + i);
        }
    }

    private void buildTable(Table t) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        for (String s : t.getFormat().getFormat()) {
            model.addColumn(s);
        }
        model.addColumn("BlockID");
        model.addColumn("LineID");
        for (Bloc b : t.getContent()) {
            for (Ligne l : b.getContent()) {
                model.addRow(buildArray(l, b.getBlockID()));
            }
        }
    }

    private void buildTable(Index t) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Valeur de " + t.getNAME());
        model.addColumn("BlocID");
        model.addColumn("LineID");

        for (IndexEntry ie : t.getContent()) {
            model.addRow(buildArray(ie));
        }
    }

    private static String[] buildArray(Ligne l, int bid) {
        ArrayList<Attribut> cnt = l.getContent();
        String[] str = new String[cnt.size() + 2];
        for (int i = 0; i < cnt.size(); i++) {
            str[i] = cnt.get(i).getDATA();
        }
        str[cnt.size()] = Integer.toString(bid);
        str[cnt.size() + 1] = Integer.toString(l.getLineID());
        return str;
    }

    private static String[] buildArray(IndexEntry ie) {
        String[] str = new String[3];
        str[0] = ie.getDATA();
        str[1] = Integer.toString(ie.getBlockID());
        str[2] = Integer.toString(ie.getLineID());
        return str;
    }

    /**
     * Génère une table de taille donnée
     *
     * @param nbL Nombre de lignes à générer
     * @param tf Format de la table.
     * @return Instance de la table générée.
     */
    public Table createTable(int nbL, TableFormat tf) {
        Table res = new Table(tf);
        res.createTable(nbL);
        return res;
    }

    private void buildRJ(ResultatJointure rj) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        String atrib = rj.getAttribut();
        TableFormat temp = new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3"));
        temp.getFormat().remove(atrib);
        model.addColumn(atrib);

        for (String s : temp.getFormat()) {
            model.addColumn(s);
        }
        model.addColumn("BlockID");
        model.addColumn("LineID");

        for (String s : temp.getFormat()) {
            model.addColumn(s + "'");
        }
        model.addColumn("BlockID");
        model.addColumn("LineID");

        for (Paire p : rj.getContent()) {
            model.addRow(buildArray(p, atrib));
        }
    }

    private static String[] buildArray(Paire p, String s) {
        ArrayList<String> tmp1 = new ArrayList<String>();
        ArrayList<String> tmp2 = new ArrayList<String>();
        String[] str = new String[9];

        for (Attribut a : p.getContent().get(0).getContent()) {
            if (a.getNAME().equals(s)) {
                str[0] = a.getDATA();
            }
        }

        for (Attribut a : p.getContent().get(0).getContent()) {
            tmp1.add(a.getNAME());
        }
        tmp1.remove(s);

        for (Attribut a : p.getContent().get(0).getContent()) {
            if (!a.getNAME().equals(s)) {
                tmp2.add(a.getDATA());
            }
        }
        for (Attribut a : p.getContent().get(1).getContent()) {
            if (!a.getNAME().equals(s)) {
                tmp2.add(a.getDATA());
            }
        }

        str[1] = tmp2.get(0);
        str[2] = tmp2.get(1) + "";
        str[3] = p.getBlocID1() + "";
        str[4] = p.getLineID1() + "";
        str[5] = tmp2.get(2);
        str[6] = tmp2.get(3);
        str[7] = p.getBlocID2() + "";
        str[8] = p.getLineID2() + "";

        return str;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SGBD - RIGLET - LARDI");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setPreferredSize(new java.awt.Dimension(150, 273));
        jPanel3.setLayout(new java.awt.GridLayout(6, 1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jPanel3.add(jComboBox1);

        jPanel6.setLayout(new java.awt.GridLayout(2, 0));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nb Blocs:");
        jPanel6.add(jLabel2);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("jLabel3");
        jPanel6.add(jLabel3);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nb Lignes:");
        jPanel6.add(jLabel4);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("jLabel5");
        jPanel6.add(jLabel5);

        jPanel3.add(jPanel6);

        jButton1.setText("Ajouter un tuple");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jButton3.setText("Ajouter Index");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        jButton2.setText("Ajouter une table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);

        jPanel5.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(Integer.toString(jSlider1.getValue()));
        jPanel5.add(jLabel1);

        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });
        jPanel5.add(jSlider1);

        jPanel3.add(jPanel5);

        jPanel1.add(jPanel3, java.awt.BorderLayout.WEST);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Gestion des tables", jPanel1);

        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel10.setPreferredSize(new java.awt.Dimension(150, 273));
        jPanel10.setLayout(new java.awt.GridLayout(10, 1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Table 1");
        jPanel10.add(jLabel11);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(jComboBox2);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Table 2");
        jPanel10.add(jLabel6);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel10.add(jComboBox3);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Jointure");
        jPanel10.add(jLabel7);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Produit Cartésien", "Sort-Merge", "Hash", "Key-lookup" }));
        jPanel10.add(jComboBox4);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Attribut");
        jPanel10.add(jLabel8);

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Attr1", "Attr2", "Attr3" }));
        jPanel10.add(jComboBox5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel2);

        jButton4.setText("Lancer la jointure");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton4);

        jPanel9.add(jPanel10, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable2.setEnabled(false);
        jScrollPane2.setViewportView(jTable2);

        jPanel13.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel13, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Jointure", jPanel9);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getItemCount() > 0) {
            int ind = jComboBox1.getSelectedIndex();
            if (ind < Tables.size()) {
                buildTable(Tables.get(ind));
                jLabel3.setText(Integer.toString(Tables.get(ind).blocsSize()));
                jLabel5.setText(Integer.toString(Tables.get(ind).linesSize()));
                jButton1.setEnabled(true);
                jButton3.setEnabled(true);

            } else {
                buildTable(Indexes.get(ind - Tables.size()));
                jLabel3.setText("-");
                jLabel5.setText("-");
                jButton1.setEnabled(false);
                jButton3.setEnabled(false);

            }

        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        jLabel1.setText(Integer.toString(jSlider1.getValue()));
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Tables.add(createTable(jSlider1.getValue(), new TableFormat(Arrays.asList("Attr1", "Attr2", "Attr3"))));
        this.buildBox();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int ind;
            int cl;
            if ((ind = jComboBox1.getSelectedIndex()) < Tables.size() && (cl = jTable1.getSelectedColumn()) < 3 && cl >= 0) {
                Index tmp = new Index(Tables.get(ind), "Attr" + (cl + 1));
                Indexes.add(tmp);
                buildBox();
                this.setTitle("SGBD - RIGLET - LARDI");
            } else {
                this.setTitle("Selectionnez une table puis un attribut dans le tableau.");
            }
        } catch (Exception e) {
            this.setTitle("Selectionnez une table puis un attribut dans le tableau.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buildTupleDialog btd = new buildTupleDialog(this, true, Tables.get(jComboBox1.getSelectedIndex()).getFormat());
        btd.setVisible(true);
        Tables.get(jComboBox1.getSelectedIndex()).ajouterLigne(btd.l);
        jLabel3.setText(Integer.toString(Tables.get(jComboBox1.getSelectedIndex()).blocsSize()));
        jLabel5.setText(Integer.toString(Tables.get(jComboBox1.getSelectedIndex()).linesSize()));
        buildTable(Tables.get(jComboBox1.getSelectedIndex()));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ResultatJointure rj = null;
        Table a = null, b = null;
        Index ia = null, ib = null;
        int inda = jComboBox2.getSelectedIndex();
        int indb = jComboBox3.getSelectedIndex();

        if (inda < Tables.size()) {
            a = Tables.get(inda);
        } else {
            ia = Indexes.get(inda - Tables.size());
        }
        if (indb < Tables.size()) {
            b = Tables.get(indb);
        } else {
            ib = Indexes.get(indb - Tables.size());
        }
        boolean doubleT = a != null && b != null;
        boolean doubleI = ia != null && ib != null;
        boolean both = (a != null ^ b != null) && (ia != null ^ ib != null);
        /*Produit Cartésien
        Sort-Merge
        Hash
        Key-lookup
        Double Index*/
        Index sel = (ia != null) ? ia : ib;
        Table ta = (a != null) ? a : b;
        int choice = jComboBox4.getSelectedIndex();
        if (doubleT) {
            switch (choice) {
                case 0:
                    rj = Jointure.produitCartesien(a, b, (String) jComboBox5.getSelectedItem());
                    break;
                case 1:
                    rj = Jointure.Sort_Merg(a, b, (String) jComboBox5.getSelectedItem());
                    break;
                case 2:
                    rj = Jointure.Hashage(a, b, (String) jComboBox5.getSelectedItem());
                    break;
            }
        } else if (doubleI) {
            if (choice == 4) {
                //  Jointure.DoubleIndex();
            }
        } else {
            if (choice == 3) {
                rj = Jointure.keyLookup(ta, sel.getTable(), sel);
            }
        }
        if (rj != null)
            buildRJ(rj);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principale().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}
