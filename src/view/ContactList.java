package view;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ContactList extends javax.swing.JPanel {

    public ContactList() {
        initComponents();
    }

    public JButton getjButtonAdd() {
        return jButtonAdd;
    }

    public JButton getjButtonDelete() {
        return jButtonDelete;
    }

    public JButton getjButtonEdit() {
        return jButtonEdit;
    }

    public JTable getjTableList() {
        return jTableList;
    }

    public JTextField getjTextFieldSearch() {
        return jTextFieldSearch;
    }

    public JButton getjButtonExport() {
        return jButtonExport;
    }

    public JButton getjButtonImport() {
        return jButtonImport;
    }

    public JProgressBar getjProgressBar() {
        return jProgressBar;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitle = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonImport = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jPanelTable = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jTableList = new javax.swing.JTable();
        jProgressBar = new javax.swing.JProgressBar();

        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanelTitle.setPreferredSize(new java.awt.Dimension(800, 100));

        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonAdd.setText("Agregar");

        jButtonEdit.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonEdit.setText("Editar");

        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonDelete.setText("Eliminar");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Lista de contactos");

        jTextFieldSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldSearch.setName("nombre"); // NOI18N

        jButtonImport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonImport.setText("Importar");

        jButtonExport.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButtonExport.setText("Exportar");

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTitleLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTitleLayout.createSequentialGroup()
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))
                    .addGroup(jPanelTitleLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jButtonExport, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonImport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(71, 71, 71)))
                .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jLabel1)
                    .addComponent(jButtonImport)
                    .addComponent(jButtonExport))
                .addGap(18, 18, 18)
                .addGroup(jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonEdit)
                    .addComponent(jButtonDelete)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanelTable.setPreferredSize(new java.awt.Dimension(800, 400));

        jScrollPane.setPreferredSize(new java.awt.Dimension(800, 400));

        jTableList.setAutoCreateRowSorter(true);
        jTableList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Apellido", "Telefono", "Email", "Categoría", "Favorito"
            }
        ));
        jTableList.setPreferredSize(new java.awt.Dimension(800, 400));
        jScrollPane.setViewportView(jTableList);

        jProgressBar.setStringPainted(true);

        javax.swing.GroupLayout jPanelTableLayout = new javax.swing.GroupLayout(jPanelTable);
        jPanelTable.setLayout(jPanelTableLayout);
        jPanelTableLayout.setHorizontalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTableLayout.createSequentialGroup()
                    .addGap(327, 327, 327)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(327, Short.MAX_VALUE)))
        );
        jPanelTableLayout.setVerticalGroup(
            jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTableLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
            .addGroup(jPanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelTableLayout.createSequentialGroup()
                    .addGap(182, 182, 182)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(182, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
            .addComponent(jPanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonImport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelTable;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTableList;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
