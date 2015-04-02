/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import common.model.PersonDetails;
import control.ServerControl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS_PC
 */
public class RequestPanel extends javax.swing.JPanel {

    private DefaultTableModel model;

    /**
     * Creates new form RequestPanel
     *
     * @throws java.sql.SQLException
     */
    public RequestPanel() throws SQLException {
        initComponents();
        model = (DefaultTableModel) tblRequest.getModel();
        
        dao.PersonDetailsDAO re;
        re = new dao.PersonDetailsDAO();

        ArrayList<PersonDetails> list;
        list = re.selectNewRequest();
        for (PersonDetails list1 : list) {
            Object fullname = list1.getFirstName() + " " + list1.getMiddleName() + " " + list1.getLastName();
            model.addRow(new Object[]{list1.getRequestedId(), fullname, list1.getDob(), (list1.getGender() == 1) ? "Male" : "Female", list1.getAddress(), list1.getOccupation(), (list1.getMarried() == 1) ? "Married" : "Single"});
        }
        this.getTblRequest().setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRequest = new javax.swing.JTable();
        btnVerify = new javax.swing.JButton();
        btnDeny = new javax.swing.JButton();

        tblRequest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Request ID", "Full Name", "Date of Birth", "Gender", "Address", "Occupation", "Marital Status"
            }
        ));
        jScrollPane1.setViewportView(tblRequest);

        btnVerify.setText("Verify");
        btnVerify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerifyActionPerformed(evt);
            }
        });

        btnDeny.setText("Deny");
        btnDeny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(btnVerify)
                .addGap(41, 41, 41)
                .addComponent(btnDeny)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerify)
                    .addComponent(btnDeny))
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private String getValueRow() {
        int row = tblRequest.getSelectedRow();
        String data = (String) tblRequest.getValueAt(row, 1);
        return data;
    }
    private void btnVerifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerifyActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showConfirmDialog(btnDeny, evt);
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (dialogButton == JOptionPane.YES_OPTION) {
            try {
                dao.PersonDetailsDAO re;
                re = new dao.PersonDetailsDAO();
                int row = tblRequest.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(tblRequest.getValueAt(row, 0)));
                if (re.verify(1, id)) {
                    showMessage("Verify successful!!");
                    model.removeRow(row);
                    this.getTblRequest().setModel(model);
                    ServerControl.establishLabelRequest();
                } else {
                    showMessage("Verify Failed!!");
                }
            } catch (SQLException ex) {
                showMessage("There was an error. Sorry for this inconvenience");
            }
        }
    }//GEN-LAST:event_btnVerifyActionPerformed

    private void btnDenyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenyActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "Are you sure?");
        if (dialogButton == JOptionPane.YES_OPTION) {
            try {
                dao.PersonDetailsDAO re;
                re = new dao.PersonDetailsDAO();
                int row = tblRequest.getSelectedRow();
                int id = Integer.parseInt(String.valueOf(tblRequest.getValueAt(row, 0)));
                if (re.verify(1, id)) {
                    showMessage("Deny successful!!");
                    model.removeRow(row);
                    this.getTblRequest().setModel(model);
                    ServerControl.establishLabelRequest();
                } else {
                    showMessage("Deny Failed!!");
                }
            } catch (SQLException ex) {
                showMessage("There was an error. Sorry for this inconvenience");
            }
        }
    }//GEN-LAST:event_btnDenyActionPerformed

    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public JTable getTblRequest() {
        return tblRequest;
    }

    public void setTblRequest(JTable tblRequest) {
        this.tblRequest = tblRequest;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeny;
    private javax.swing.JButton btnVerify;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRequest;
    // End of variables declaration//GEN-END:variables
}
