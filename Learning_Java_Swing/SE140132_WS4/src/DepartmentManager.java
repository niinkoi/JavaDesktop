
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khoi Nguyen
 */
public class DepartmentManager extends javax.swing.JFrame {
    
    private final String FILE_NAME = "C:\\Users\\Khoi Nguyen\\OneDrive\\Documents\\NetBeansProjects\\BookStore\\SE140132_WS4\\employees.txt";
    private final String SAVED_SUCCESS = "Data are saved to the file " + FILE_NAME;
    
    private final String BEFORE_REMOVE_DEP_MSG = "Remove all employees before deleting a department.";
    private final String CONFIRM_DEL_DEP = "Deleting this department- OK?";
    private final String CONFIRM_DEL_EMP = "Deleting this employee- OK?";
    
    private final String INVALID_FORMAT_CODE = "Invalid format code";
    private final String DUPLICATED_CODE = "Code is duplicated";
    private final String REQUIRED_NAME = "Name is required";
    private final String VALID_SALARY = "Salary is an integer";
    
    private final String DEP_CODE_REG = "[A-Z]{2}";
    private final String EMP_CODE_REG = "^E\\d{3}$";
    private final String SAL_REG = "^\\d+$";
    
    DefaultMutableTreeNode root = null;
    DefaultMutableTreeNode curDepNode = null;
    DefaultMutableTreeNode curEmpNode = null;
    
    boolean addNewDep = false;
    boolean addNewEmp = false;
    
    private List<Department> deptList = new ArrayList<>();
    private List<Employee> empList = new ArrayList<>();
    
    /**
     * Creates new form DepartmentManager
     */
    public DepartmentManager() {
        initComponents();
        root = (DefaultMutableTreeNode) (this.treeDep.getModel().getRoot());
        loadData();
        TreePath path = new TreePath(root);
        treeDep.expandPath(path);
    }
    
    private void loadData() {
        String line = "";
        StringTokenizer stringTokenizer;
        
        try (
                FileReader reader = new FileReader(FILE_NAME);
                BufferedReader bufferReader = new BufferedReader(reader);) {
            
            while ((line = bufferReader.readLine()) != null) {
                line = line.trim();
                boolean isDept = (line.charAt(line.length() - 1) == ':');
                stringTokenizer = new StringTokenizer(line, "-:,");
                String code = stringTokenizer.nextToken().trim();
                String name = stringTokenizer.nextToken().trim();
                
                if (isDept) {
                    Department current = new Department(code, name);
                    curDepNode = new DefaultMutableTreeNode(current);
                    root.add(curDepNode);
                    this.deptList.add(current);
                } else {
                    int salary = Integer.parseInt(stringTokenizer.nextToken().trim());
                    Employee current = new Employee(code, name, salary);

                    curEmpNode = new DefaultMutableTreeNode(current);
                    curDepNode.add(curEmpNode);
                    this.empList.add(current);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void viewDeptAndEmp() {
        Department curDep = null;
        Employee curEmp = null;
        
        if (curDepNode != null) {
            curDep = (Department) (curDepNode.getUserObject());
        }
        if (curEmpNode != null) {
            curEmp = (Employee) (curEmpNode.getUserObject());
        }
        
        this.fdDeptCode.setText(curDep != null ? curDep.getCode() : "");
        this.fdDeptName.setText(curDep != null ? curDep.getName() : "");
        this.fdEmCode.setText(curEmp != null ? curEmp.getCode() : "");
        this.fdEmpName.setText(curEmp != null ? curEmp.getName() : "");
        this.fdEmpSalary.setText("" + (curEmp != null ? curEmp.getSalary() : ""));
        
        this.fdDeptCode.setEditable(false);
        this.fdEmCode.setEditable(false);
    }
    
    private boolean checkDuplicate(List<? extends AbstractEntity> list, JTextField field) {
        return list.stream()
                       .anyMatch(el -> el.getCode().equals(field.getText()));
    }
    
    private boolean validDetails(String regex, JTextField... fields) {
        String line;
        List tempList = (List<Department>) this.deptList;
        if (addNewEmp || addNewDep) {
            line = fields[0].getText().trim().toUpperCase();
            fields[0].setText(line);
            
            if (!line.matches(regex)) {
                JOptionPane.showMessageDialog(this, INVALID_FORMAT_CODE);
                return false;
            }
           
            if (addNewEmp) {
               tempList = (List<Employee>) this.empList;
            }
            
            boolean isDuplicate = checkDuplicate(tempList, fields[0]);
            if (isDuplicate) {
                JOptionPane.showMessageDialog(this, DUPLICATED_CODE);
                fields[0].requestFocus();
                return false;
            }
        }
        
        line = fields[1].getText().trim();
        if (line.length() == 0) {
            JOptionPane.showMessageDialog(this, REQUIRED_NAME);
            return false;
        }
        
        if (addNewEmp) {
            line = fields[2].getText().trim();
            if (!line.matches(SAL_REG)) {
                JOptionPane.showMessageDialog(this, VALID_SALARY);
                return false;
            }
        }
        
        return true;
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
        treeDep = new javax.swing.JTree();
        btnSaveToFile = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fdDeptCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fdDeptName = new javax.swing.JTextField();
        btnDeptNew = new javax.swing.JButton();
        btnDeptRem = new javax.swing.JButton();
        btnDeptSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnEmpRem = new javax.swing.JButton();
        fdEmpName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        fdEmCode = new javax.swing.JTextField();
        btnEmpSave = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnEmpNew = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fdEmpSalary = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Departments");
        treeDep.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeDep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeDepMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(treeDep);

        btnSaveToFile.setText("Save to file");
        btnSaveToFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveToFileActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(51, 51, 255))); // NOI18N

        jLabel1.setText("Dept. code");

        jLabel2.setText("Dept. name");

        btnDeptNew.setText("New");
        btnDeptNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeptNewActionPerformed(evt);
            }
        });

        btnDeptRem.setText("Remove");
        btnDeptRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeptRemActionPerformed(evt);
            }
        });

        btnDeptSave.setText("Save");
        btnDeptSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeptSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnDeptNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeptRem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeptSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fdDeptName)
                            .addComponent(fdDeptCode))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(fdDeptCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fdDeptName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeptNew)
                    .addComponent(btnDeptRem)
                    .addComponent(btnDeptSave))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Employee Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(0, 51, 255))); // NOI18N

        btnEmpRem.setText("Remove");
        btnEmpRem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpRemActionPerformed(evt);
            }
        });

        jLabel3.setText("Emp. code");

        btnEmpSave.setText("Save");
        btnEmpSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpSaveActionPerformed(evt);
            }
        });

        jLabel4.setText("Emp. name");

        btnEmpNew.setText("New");
        btnEmpNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpNewActionPerformed(evt);
            }
        });

        jLabel5.setText("Salary");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnEmpNew, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpRem, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEmpSave, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fdEmpSalary)
                    .addComponent(fdEmCode)
                    .addComponent(fdEmpName))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fdEmCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fdEmpName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fdEmpSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmpNew)
                    .addComponent(btnEmpRem)
                    .addComponent(btnEmpSave))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(btnSaveToFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSaveToFile))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeDepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeDepMouseClicked
        treeDep.cancelEditing();
        
        TreePath path = treeDep.getSelectionPath();
        if (path == null) return;
        
        DefaultMutableTreeNode selectedNode = null;
        selectedNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
        
        Object selectedObject = selectedNode.getUserObject();
        if (selectedNode == root) {
            this.curDepNode = this.curEmpNode = null;
        } else {
            if (selectedObject instanceof Department) {
                this.curDepNode = selectedNode;
                this.curEmpNode = null;
            } else if (selectedObject instanceof Employee) {
                this.curEmpNode = selectedNode;
                this.curDepNode = (DefaultMutableTreeNode) (selectedNode.getParent());
            }
        }
        viewDeptAndEmp();
    }//GEN-LAST:event_treeDepMouseClicked

    private void btnDeptNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeptNewActionPerformed
        this.addNewDep = true;
        
        this.fdDeptCode.setText("");
        this.fdDeptName.setText("");
        this.fdEmCode.setText("");
        this.fdEmpName.setText("");
        this.fdEmpSalary.setText("");
        
        this.fdDeptCode.setEditable(true);
        this.fdDeptCode.requestFocus();
    }//GEN-LAST:event_btnDeptNewActionPerformed

    private void btnDeptRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeptRemActionPerformed
        if (this.curDepNode.getChildCount() > 0) {
            String message = BEFORE_REMOVE_DEP_MSG;
            JOptionPane.showMessageDialog(this, message);
        } else {
            int response = JOptionPane.showConfirmDialog(this, CONFIRM_DEL_DEP);
            if (response == JOptionPane.OK_OPTION) {
                root.remove(this.curDepNode);
                treeDep.updateUI();
            }
        }
    }//GEN-LAST:event_btnDeptRemActionPerformed

    private void btnDeptSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeptSaveActionPerformed
        String code = this.fdDeptCode.getText().trim().toUpperCase();
        fdDeptCode.setText(code);
        
        String name = this.fdDeptName.getText().trim();
        fdDeptName.setText(name);
        
        if (!validDetails(DEP_CODE_REG, this.fdDeptCode, this.fdDeptName)) {
            return;
        }
        if (addNewDep) {
            Department newDep = new Department(code, name);
            root.add(new DefaultMutableTreeNode(newDep));
        } else {
            ((Department) curDepNode.getUserObject()).setName(name);
        }
        this.treeDep.updateUI();
        this.addNewDep = false;
    }//GEN-LAST:event_btnDeptSaveActionPerformed

    private void btnEmpNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpNewActionPerformed
        this.addNewEmp = true;
        this.fdEmCode.setText("");
        this.fdEmpName.setText("");
        this.fdEmpSalary.setText("");
        this.fdEmCode.setEditable(true);
        this.fdEmCode.requestFocus();
    }//GEN-LAST:event_btnEmpNewActionPerformed

    private void btnEmpRemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpRemActionPerformed
        if (this.curEmpNode != null) {
            int response = JOptionPane.showConfirmDialog(this, CONFIRM_DEL_EMP);
            if (response == JOptionPane.OK_OPTION) {
                curDepNode.remove(this.curEmpNode);
                treeDep.updateUI();
            }
        }
    }//GEN-LAST:event_btnEmpRemActionPerformed

    private void btnEmpSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpSaveActionPerformed
                
        if (!validDetails(EMP_CODE_REG, this.fdEmCode, this.fdEmpName, this.fdEmpSalary)) {
            return;
        }
        
        String code = this.fdEmCode.getText().trim().toUpperCase();
        fdEmCode.setText(code);
        
        String name = this.fdEmpName.getText().trim();
        fdEmpName.setText(name);
        
        String salaryStr = this.fdEmpSalary.getText().trim();
        fdEmpSalary.setText(salaryStr);
        
        int salary = Integer.parseInt(salaryStr);

        
        if (addNewEmp) {
            Employee emp = new Employee(code, name, salary);
            curDepNode.add(new DefaultMutableTreeNode(emp));
        } else {
            Employee emp = (Employee) (curEmpNode.getUserObject());
            emp.setName(name);
            emp.setSalary(salary);
        }
        
        this.treeDep.updateUI();
        this.addNewEmp = false;
    }//GEN-LAST:event_btnEmpSaveActionPerformed

    private void btnSaveToFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveToFileActionPerformed
        if (root.getChildCount() == 0) return;
        String str = "";
        
        try(
                FileWriter file = new FileWriter(FILE_NAME);
                PrintWriter printWriter = new PrintWriter(file);) {
            
                Enumeration depts = root.children();
                while (depts.hasMoreElements()) {
                    DefaultMutableTreeNode depNode = (DefaultMutableTreeNode) depts.nextElement();
                    Department dep = (Department) (depNode.getUserObject());
                    str = dep.getCode() + "-" + dep.getName() + ":";
                    printWriter.println(str);
                    
                    Enumeration emps = depNode.children();
                    while (emps.hasMoreElements()) {
                        DefaultMutableTreeNode empNode = (DefaultMutableTreeNode) emps.nextElement();
                        Employee emp = (Employee) (empNode.getUserObject());
                        str = emp.getCode() + "," + emp.getName() + "," + emp.getSalary();
                        printWriter.println(str);
                    }
                }
                
                JOptionPane.showMessageDialog(this, SAVED_SUCCESS);
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSaveToFileActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeptNew;
    private javax.swing.JButton btnDeptRem;
    private javax.swing.JButton btnDeptSave;
    private javax.swing.JButton btnEmpNew;
    private javax.swing.JButton btnEmpRem;
    private javax.swing.JButton btnEmpSave;
    private javax.swing.JButton btnSaveToFile;
    private javax.swing.JTextField fdDeptCode;
    private javax.swing.JTextField fdDeptName;
    private javax.swing.JTextField fdEmCode;
    private javax.swing.JTextField fdEmpName;
    private javax.swing.JTextField fdEmpSalary;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeDep;
    // End of variables declaration//GEN-END:variables
}
