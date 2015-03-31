/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

/**
 *
 * @author DH
 */
public class ExportToExcelPanel extends javax.swing.JPanel {

    /**
     * Creates new form ExportToExcelPanel
     */
    public ExportToExcelPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        AreaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArea = new javax.swing.JTable();
        CentrePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCentre = new javax.swing.JTable();
        EmployeePanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblEmployee = new javax.swing.JTable();
        btnExportToExcel = new javax.swing.JButton();

        tabPanel.setName(""); // NOI18N
        tabPanel.setPreferredSize(new java.awt.Dimension(492, 385));

        tblArea.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblArea);

        javax.swing.GroupLayout AreaPanelLayout = new javax.swing.GroupLayout(AreaPanel);
        AreaPanel.setLayout(AreaPanelLayout);
        AreaPanelLayout.setHorizontalGroup(
            AreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        AreaPanelLayout.setVerticalGroup(
            AreaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        tabPanel.addTab("Area", AreaPanel);

        tblCentre.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblCentre);

        javax.swing.GroupLayout CentrePanelLayout = new javax.swing.GroupLayout(CentrePanel);
        CentrePanel.setLayout(CentrePanelLayout);
        CentrePanelLayout.setHorizontalGroup(
            CentrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        CentrePanelLayout.setVerticalGroup(
            CentrePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        tabPanel.addTab("Centre", CentrePanel);

        tblEmployee.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblEmployee);

        javax.swing.GroupLayout EmployeePanelLayout = new javax.swing.GroupLayout(EmployeePanel);
        EmployeePanel.setLayout(EmployeePanelLayout);
        EmployeePanelLayout.setHorizontalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );
        EmployeePanelLayout.setVerticalGroup(
            EmployeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
        );

        tabPanel.addTab("Employee", EmployeePanel);

        btnExportToExcel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExportToExcel.setText("Export To Excel");
        btnExportToExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportToExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 855, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportToExcel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnExportToExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabPanel.getAccessibleContext().setAccessibleName("tab 1");
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportToExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportToExcelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportToExcelActionPerformed

    public JPanel getAreaPanel() {
        return AreaPanel;
    }

    public void setAreaPanel(JPanel AreaPanel) {
        this.AreaPanel = AreaPanel;
    }

    public JPanel getCentrePanel() {
        return CentrePanel;
    }

    public void setCentrePanel(JPanel CentrePanel) {
        this.CentrePanel = CentrePanel;
    }

    public JPanel getEmployeePanel() {
        return EmployeePanel;
    }

    public void setEmployeePanel(JPanel EmployeePanel) {
        this.EmployeePanel = EmployeePanel;
    }

    public JTabbedPane getTabPanel() {
        return tabPanel;
    }

    public void setTabPanel(JTabbedPane tabPanel) {
        this.tabPanel = tabPanel;
    }

    public JTable getTblArea() {
        return tblArea;
    }

    public void setTblArea(JTable tblArea) {
        this.tblArea = tblArea;
    }

    public JTable getTblCentre() {
        return tblCentre;
    }

    public void setTblCentre(JTable tblCentre) {
        this.tblCentre = tblCentre;
    }

    public JTable getTblEmployee() {
        return tblEmployee;
    }

    public void setTblEmployee(JTable tblEmployee) {
        this.tblEmployee = tblEmployee;
    }

    public JButton getBtnExportToExcel() {
        return btnExportToExcel;
    }
    public void setBtnExportToExcel(JButton btnExportToExcel) {
        this.btnExportToExcel = btnExportToExcel;
    }
    public void addBtnExportToExcelListener(ActionListener log) {
        btnExportToExcel.addActionListener(log);
    }        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AreaPanel;
    private javax.swing.JPanel CentrePanel;
    private javax.swing.JPanel EmployeePanel;
    private javax.swing.JButton btnExportToExcel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JTable tblArea;
    private javax.swing.JTable tblCentre;
    private javax.swing.JTable tblEmployee;
    // End of variables declaration//GEN-END:variables
}
