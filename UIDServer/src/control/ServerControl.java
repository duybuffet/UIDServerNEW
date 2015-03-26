/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import common.model.Area;
import common.model.Centre;
import common.model.Employee;
import dao.AreaDAO;
import dao.CentreDAO;
import dao.EmployeeDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.AreaPanel;
import view.CentreFrame;
import view.EmployeePanel;
import view.LoginPanel;
import view.MenuPanel;
import view.RequestPanel;
import view.ServerFrame;
import view.UserPanel;

/**
 *
 * @author Duy Buffet
 */
public class ServerControl {

    private ServerFrame serverFrame;
    private CentreFrame centreFrame;
    private AreaPanel areaPanel;
    private EmployeePanel employeePanel;
    private LoginPanel loginPanel;
    private RequestPanel requestPanel;
    private MenuPanel menuPanel;
    private UserPanel userPanel;

    private AreaDAO areaDAO;
    private String areaCode, areaName;
    private String areaCodeTemp;

    private CentreDAO centreDAO;
    private String centreName;
    private int centreID;

    private EmployeeDAO empDao;
    private String userName, pass, cpass;
    private int empId, gender;
    

    public ServerControl(ServerFrame serverFrame) {
        initComponents(serverFrame);
    }

    private void initComponents(ServerFrame serverFrame) {
        // init all components
        this.serverFrame = serverFrame;
        loginPanel = new LoginPanel();
        areaPanel = new AreaPanel();
        employeePanel = new EmployeePanel();
        requestPanel = new RequestPanel();
        menuPanel = new MenuPanel();

        // set login panel first when main frame is opened
        this.serverFrame.getMainSplitPane().setRightComponent(loginPanel);
        this.serverFrame.getMainSplitPane().setLeftComponent(menuPanel);

        // add action listener
        loginPanel.addBtnLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = "", pass = "";
            Employee employee = new Employee();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            userName = loginPanel.getTxtUName().getText().trim();
            pass = loginPanel.getTxtPass().getText().trim();

            employee.setUsername(userName);
            employee.setPass(pass);

//            try {
//                if (employeeDAO.login(employee)) {
//                    serverFrame.showMessage("Login successfully!");
            serverFrame.getMainSplitPane().setRightComponent(employeePanel);
            menuPanel.addBtnMenuListener(new MenuListener());
            serverFrame.addBtnControlListener(new ControlServerListener());
//                } else {
//                    serverFrame.showMessage("Login unsuccessfully. Please reinput username or password");
//                }
//            } catch (SQLException ex) {
//                serverFrame.showMessage("Server error! Sorry for this unconvenient.");
//            }
        }

    }

    class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn == menuPanel.getBtnArea()) {
                serverFrame.getMainSplitPane().setRightComponent(areaPanel);
                areaPanel.addBtnAreaListener(new AreaListener());
                new AreaListener().showAllArea();
            } else if (btn == menuPanel.getBtnEmp()) {
                serverFrame.getMainSplitPane().setRightComponent(employeePanel);
                employeePanel.addBtnEmployeeListener(new EmployeeListener());
            } else if (btn == menuPanel.getBtnRequest()) {
                serverFrame.getMainSplitPane().setRightComponent(requestPanel);
            } else if (btn == menuPanel.getBtnUser()) {
                serverFrame.getMainSplitPane().setRightComponent(userPanel);
                userPanel.addBtnUserListener(new UserListener());
            }
        }

    }

    class ControlServerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class AreaListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            // add
            if (btn == areaPanel.getBtnAdd()) {
                // check JTextFiel areaCode and areaName
                if (testTxtInput() != false) {
                    insertArea();
                }

            }
            // show all
            if (btn == areaPanel.getBtnAll()) {
                showAllArea();
            }
            // edit
            if (btn == areaPanel.getBtnEdit()) {
                if (testTxtInput() != false) {
                    updateArea();
                }
            }
            // Delete
            if (btn == areaPanel.getBtnDel()) {
                if (testTxtInput() != false) {
                    deleteArea();
                }
            }
            // Search
            if (btn == areaPanel.getBtnSearch()) {
                String search = JOptionPane.showInputDialog(areaPanel, "Input Key Search ");
                if (search != null) {
                    showSearchArea(search);
                }
            }
            // Go to centre
            if (btn == areaPanel.getBtnGoto()) {
                gotoCentre();
//                centreFrame.dispose();
//                serverFrame.getMainSplitPane().setRightComponent(centrePanel);

            }
        }

        // Insert Area
        private void insertArea() {
            int result = JOptionPane.showConfirmDialog(areaPanel, "AreCode : " + areaCode + "\nAreaName : " + areaName, "Are you update?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                areaDAO = new AreaDAO();
                try {
                    areaDAO.insert(new Area(areaCode, areaName));
                    showMessageDialog("Insert Success!");
                    areaPanel.getTxtCode().setText("");
                    areaPanel.getTxtName().setText("");
                    showAllArea();
                } catch (SQLException ex) {
                    showMessageDialog("Insert wrong!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Test input area code and area name
        private boolean testTxtInput() {
            int countTest = 0;
            boolean test = false;
            areaCode = areaPanel.getTxtCode().getText().trim();
            areaName = areaPanel.getTxtName().getText().trim();

            if (areaCode.isEmpty() || areaCode.length() != 2) {
                countTest += 1;
            }
            if (areaName.isEmpty()) {
                countTest += 2;
            }
            switch (countTest) {
                case 0:
                    test = true;
                    break;
                case 1:
                    showMessageDialog("Request:\nAreaCode not null and AreaCode not equals 2 key!");
                    break;
                case 2:
                    showMessageDialog("Request:\nAreaName not Null!");
                    break;
                case 3:
                    showMessageDialog("Request:\nAreaCode not null and AreaCode not equals 2 key!\nAreaName not Null!");
                    break;
            }
            return test;
        }

        private void updateArea() {
            System.out.println(areaCodeTemp);
            System.out.println(areaCode);
            
            int result = JOptionPane.showConfirmDialog(areaPanel, "AreCode : " + areaCode + "\nAreaName : " + areaName, "Are you insert?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                if(!areaCodeTemp.equals(areaCode)){
                    showMessageDialog(" You not change Area Code!");
                }
                else{
                    areaDAO = new AreaDAO();
                    try {
                        areaDAO.update(new Area(areaCode, areaName));
                        showMessageDialog("Update Success!");
                        showAllArea(); 
                    } catch (SQLException ex) {
                        showMessageDialog("Update wrong!");
                        Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        }

        private void showAllArea() {
            areaDAO = new AreaDAO();
            ArrayList<Area> listArae = new ArrayList<>();
            try {
                listArae = areaDAO.selectAll();
                Vector tblRecords = new Vector();
                Vector tblTitle = new Vector();
                tblTitle.add("Area Code");
                tblTitle.add("Area Name");

                for (Area ls : listArae) {
                    Vector record = new Vector();
                    record.add(ls.getAreaCode());
                    record.add(ls.getAreaName());
                    tblRecords.add(record);
                }

                areaPanel.getTblArea().setModel(new DefaultTableModel(tblRecords, tblTitle));
                eventTableArea();
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void deleteArea() {
            int result = JOptionPane.showConfirmDialog(areaPanel, "AreCode : " + areaCode + "\nAreaName : " + areaName, "Are you delete?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                areaDAO = new AreaDAO();
                try {
                    areaDAO.delete(new Area(areaCode, areaName));
                    showMessageDialog("Delete Success!");
                    JOptionPane.showMessageDialog(areaPanel, "Delete Success!");
                    showAllArea();
                } catch (SQLException ex) {
                    showMessageDialog("Delete wrong!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void eventTableArea() {
            areaPanel.getTblArea().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = areaPanel.getTblArea().getSelectedRow();

                    areaPanel.getTxtCode().setText((String) areaPanel.getTblArea().getValueAt(row, 0));
                    areaPanel.getTxtName().setText((String) areaPanel.getTblArea().getValueAt(row, 1));
                    areaCodeTemp = (String) areaPanel.getTblArea().getValueAt(row, 0);
//                    areaPanel.getTxtCode().setEditable(false);
                }
            });

        }

        private void showSearchArea(String areaCode) {
            areaDAO = new AreaDAO();
            Area area = new Area();
            try {
                area = areaDAO.selectAreaByCode(areaCode);
                if (area != null) {
                    Vector tblRecords = new Vector();
                    Vector tblTitle = new Vector();
                    tblTitle.add("Area Code");
                    tblTitle.add("Area Name");

                    Vector record = new Vector();
                    record.add(area.getAreaCode());
                    record.add(area.getAreaName());
                    tblRecords.add(record);

                    areaPanel.getTblArea().setModel(new DefaultTableModel(tblRecords, tblTitle));
                    eventTableArea();
                } else {
                    showMessageDialog("Enter key search Incorrect!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void showMessageDialog(String message) {
            JOptionPane.showMessageDialog(areaPanel, message);
        }

        private void gotoCentre() {
            areaCode = areaPanel.getTxtCode().getText().trim();
            if (!areaCode.isEmpty()) {
                centreFrame = new CentreFrame();
                centreFrame.setVisible(true);
                centreFrame.addBtnCentreListener(new CentreListener());
                centreFrame.getTxtAreaCode().setText(areaCode);
                new CentreListener().showAllCentre();
            } else {
                showMessageDialog("Not Choice row in table !");
            }
        }
    }

    class CentreListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn == centreFrame.getBtnAdd()) {
                // check JTextFiel areaCode and areaName
                if (testTxtInput() != false) {
                    insertCentre();
                }

            }
            // show all
            if (btn == centreFrame.getBtnAll()) {
                showAllCentre();
            }
            // edit
            if (btn == centreFrame.getBtnEdit()) {
                if (testTxtInput() != false) {
                    updateCentre();
                }
            }
            // Delete
            if (btn == centreFrame.getBtnDel()) {
                if (testTxtInput() != false) {
                    deleteCentre();
                }
            }
            // Search
            if (btn == centreFrame.getBtnSearch()) {
                try {
                    int search = Integer.parseInt(JOptionPane.showInputDialog(centreFrame, "Input Key Search "));
                    if (search != 0) {
                        showSearchCentre(search);
                    }
                } catch (NumberFormatException ex) {
                }
            }
        }

        // Insert Area
        private void insertCentre() {
            int result = JOptionPane.showConfirmDialog(centreFrame, "CentreName : " + centreName + "\nArea Code : " + areaCode, "Are you insert?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                centreDAO = new CentreDAO();
                try {
                    centreDAO.insert(centreName, areaCode);
                    showMessageDialog("Insert Success!");
                    centreFrame.getTxtId().setText("");
                    centreFrame.getTxtName().setText("");
                    showAllCentre();
                } catch (SQLException ex) {
                    showMessageDialog("Insert wrong!");
//                    showMessageDialog(ex.toString());
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Test input area code and area name
        private boolean testTxtInput() {
            int countTest = 0;
            boolean test = false;
            centreName = centreFrame.getTxtName().getText().trim();

            if (centreName.isEmpty()) {
                countTest += 2;
            }
            switch (countTest) {
                case 0:
                    test = true;
                    break;
                case 2:
                    showMessageDialog("Request:\nCentre Name is Null!");
                    break;
            }
            return test;
        }

        private void updateCentre() {
            int result = JOptionPane.showConfirmDialog(centreFrame, "CentreID : " + centreID + "\nCentreName : " + centreName, "Are you insert?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                centreDAO = new CentreDAO();
                try {
                    centreDAO.update(new Centre(centreID, centreName));
                    showMessageDialog("Update Success!");
                    showAllCentre();
                } catch (SQLException ex) {
                    showMessageDialog("Update wrong!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void showAllCentre() {
            centreDAO = new CentreDAO();
            ArrayList<Centre> listCentre = new ArrayList<>();
            try {
                listCentre = centreDAO.selectAllByAreaCode(areaCode);
                Vector tblRecords = new Vector();
                Vector tblTitle = new Vector();
                tblTitle.add("Centre ID");
                tblTitle.add("Centre Name");

                for (Centre lc : listCentre) {
                    Vector record = new Vector();
                    record.add(lc.getCentreId());
                    record.add(lc.getCentreName());
                    tblRecords.add(record);
                }

                centreFrame.getTblCentre().setModel(new DefaultTableModel(tblRecords, tblTitle));
                eventTableCentre();
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void deleteCentre() {
            int result = JOptionPane.showConfirmDialog(centreFrame, "Centre ID : " + centreID + "\nCentre Name : " + centreName, "Are you delete?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {

                centreDAO = new CentreDAO();
                try {
                    centreDAO.delete(new Centre(centreID, centreName));
                    showMessageDialog("Delete Success!");
                    showAllCentre();
                } catch (SQLException ex) {
                    showMessageDialog("Delete wrong!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void eventTableCentre() {
            centreFrame.getTblCentre().addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int row = centreFrame.getTblCentre().getSelectedRow();

                    centreFrame.getTxtId().setText((String) centreFrame.getTblCentre().getValueAt(row, 0).toString());
                    centreFrame.getTxtName().setText((String) centreFrame.getTblCentre().getValueAt(row, 1));
                    centreID = Integer.parseInt(centreFrame.getTblCentre().getValueAt(row, 0).toString());
//                    areaPanel.getTxtCode().setEditable(false);
                }
            });

        }

        private void showSearchCentre(int centreID) {
            centreDAO = new CentreDAO();
            Centre centre = new Centre();
            try {
                centre = centreDAO.selectCentreById(centreID);
                if (centre != null) {
                    Vector tblRecords = new Vector();
                    Vector tblTitle = new Vector();
                    tblTitle.add("Centre ID");
                    tblTitle.add("Centre Name");

                    Vector record = new Vector();
                    record.add(centre.getCentreId());
                    record.add(centre.getCentreName());
                    tblRecords.add(record);

                    centreFrame.getTblCentre().setModel(new DefaultTableModel(tblRecords, tblTitle));
                    eventTableCentre();
                } else {
                    showMessageDialog("Enter key search Incorrect!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void showMessageDialog(String message) {
            JOptionPane.showMessageDialog(centreFrame, message);
        }
    }

    class RequestListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    class EmployeeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            //Add
            if (btn == employeePanel.getBtnAdd()) {
                if (inputCheck()) {
                    insertEmployee();
                }
            }
            //Show All
            if (btn == employeePanel.getBtnAll()) {
                showAllEmployee();
            }

            //Edit
            if (btn == employeePanel.getBtnEdit()) {
                if (inputCheck()) {
                    editEmp();
                }
            }

            //Delete
            if (btn == employeePanel.getBtnDel()) {
                if (inputCheck()) {
                    deleteEmp();
                }
            }

            //Search
            if (btn == employeePanel.getBtnSearch()) {
                searchEmp(empId);
            }

        }

        //Insert
        private void insertEmployee() {
            int result = JOptionPane.showConfirmDialog(employeePanel, "Add this employee?", "", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                empDao = new EmployeeDAO();

                try {
                    empDao.insert(new Employee(empId, userName, pass, gender));
                    showMessageDialog("Insert Success!");
                    employeePanel.getTxtUName().setText("");
                    employeePanel.getTxtPass().setText("");
                    employeePanel.getTxtCPass().setText("");
                    employeePanel.getBtnGroupGender().clearSelection();
                } catch (SQLException ex) {
                    showMessageDialog("Insert Failed!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //Check input information
        private boolean inputCheck() {
            boolean check = false;
            String err = "";

//            empId = Integer.parseInt(employeePanel.getTxtId().getText().trim());
            userName = employeePanel.getTxtUName().getText().trim();
            pass = employeePanel.getTxtPass().getText().trim();
            cpass = employeePanel.getTxtCPass().getText().trim();
            if (employeePanel.getRdoMale().isSelected()) {
                gender = 0;
            }
            if (employeePanel.getRdoFemale().isSelected()) {
                gender = 1;
            }

//            if (empId.length() == 0) {
//                err += "Employee ID must not be empty\n";
//            }
            if (userName.length() == 0) {
                err += "Username must not be empty\n";
            }
            if (pass.length() == 0) {
                err += "Password must not be empty\n";
            } else {
                if (!pass.equals(cpass)) {
                    err += "Passwords do not match\n";
                }
            }
            if (!employeePanel.getRdoMale().isSelected() && !employeePanel.getRdoFemale().isSelected()) {
                err += "Please choose your gender\n";
            }

            if (err.length() == 0) {
                check = true;
            } else {
                showMessageDialog(err);
            }
            return check;
        }

        private void showAllEmployee() {
            empDao = new EmployeeDAO();
            ArrayList<Employee> listEmp = new ArrayList<>();

            try {
                listEmp = empDao.selectAllEmployee();
                Vector tblTitles = new Vector();
                Vector tblRow = new Vector();
                tblTitles.add("Id");
                tblTitles.add("Username");
                tblTitles.add("Gender");

                for (Employee list : listEmp) {
                    Vector v = new Vector();
                    v.add(list.getId());
                    v.add(list.getUsername());
                    v.add(list.getGender());
                    tblRow.add(v);
                }

                employeePanel.getTblEmp().setModel(new DefaultTableModel(tblRow, tblTitles));
                tblEvent();
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void editEmp() {
            int result = JOptionPane.showConfirmDialog(employeePanel, "Are you sure?", "", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                empDao = new EmployeeDAO();

                try {
                    empDao.update(new Employee(empId, userName, pass, gender));
                    showMessageDialog("Update Success!");
                    employeePanel.getTxtUName().setText("");
                    employeePanel.getTxtPass().setText("");
                    employeePanel.getTxtCPass().setText("");
                    employeePanel.getBtnGroupGender().clearSelection();
                } catch (SQLException ex) {
                    showMessageDialog("Update Failed!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void deleteEmp() {
            int result = JOptionPane.showConfirmDialog(employeePanel, "Are you sure?", "", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {
                empDao = new EmployeeDAO();

                try {
                    empDao.delete(new Employee(userName, pass, gender));
                    showMessageDialog("Delete Sucess!");
                    employeePanel.getTxtUName().setText("");
                    employeePanel.getTxtPass().setText("");
                    employeePanel.getTxtCPass().setText("");
                    employeePanel.getBtnGroupGender().clearSelection();
                } catch (SQLException ex) {
                    showMessageDialog("Delete Failed!");
                    Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void searchEmp(int Id) {
//            employeePanel.getTxtId().setEnabled(true);
//            employeePanel.getTxtUName().setEnabled(false);
//            employeePanel.getTxtPass().setEnabled(false);
//            employeePanel.getTxtCPass().setEnabled(false);
//            employeePanel.getRdoMale().setEnabled(false);
//            employeePanel.getRdoFemale().setEnabled(false);
            empDao = new EmployeeDAO();
            Employee emp = new Employee();

            try {
                emp = empDao.selectEmployeeByID(Id);

                if (emp != null) {
                    Vector tblTitles = new Vector();
                    Vector tblRow = new Vector();
                    tblTitles.add("Id");
                    tblTitles.add("Username");
                    tblTitles.add("Gender");

                    Vector v = new Vector();
                    v.add(emp.getId());
                    v.add(emp.getUsername());
                    v.add(emp.getGender());
                    tblRow.add(v);

                    employeePanel.getTblEmp().setModel(new DefaultTableModel(tblRow, tblTitles));
                    tblEvent();
                } else {
                    showMessageDialog("No result found!");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        private void showMessageDialog(String message) {
            JOptionPane.showMessageDialog(employeePanel, message);
        }

        private void tblEvent() {
            employeePanel.getTblEmp().addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        employeePanel.getBtnDel().setEnabled(true);
                        employeePanel.getBtnEdit().setEnabled(true);
                        int row = employeePanel.getTblEmp().getSelectedRow();

                        employeePanel.getTxtId().setText((String) employeePanel.getTblEmp().getValueAt(row, 0));
                        employeePanel.getTxtUName().setText((String) employeePanel.getTblEmp().getValueAt(row, 1));
                        String gender = (String) employeePanel.getTblEmp().getValueAt(row, 2);
                        if (gender.equals(employeePanel.getRdoMale().getText())) {
                            employeePanel.getRdoMale().setSelected(true);
                        }
                        if (gender.equals(employeePanel.getRdoFemale().getText())) {
                            employeePanel.getRdoFemale().setSelected(true);
                        }
                    }
                }
            });
        }
    }

    class UserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPass = "", newPass = "", cPass="";
            Employee employee = new Employee();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            oldPass = userPanel.getTxtOPass().getPassword().toString();
            newPass = userPanel.getTxtNPass().getPassword().toString();
            cPass = userPanel.getTxtCNPass().getPassword().toString();

            employee.setUsername(userName);
            employee.setPass(pass);

//            try {
//                if (employeeDAO.login(employee)) {
//                    serverFrame.showMessage("Login successfully!");
            serverFrame.getMainSplitPane().setRightComponent(employeePanel);
            menuPanel.addBtnMenuListener(new MenuListener());
            serverFrame.addBtnControlListener(new ControlServerListener());
//                } else {
//                    serverFrame.showMessage("Login unsuccessfully. Please reinput username or password");
//                }
//            } catch (SQLException ex) {
//                serverFrame.showMessage("Server error! Sorry for this unconvenient.");
//            }
        }

    }
}
