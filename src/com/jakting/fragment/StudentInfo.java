package com.jakting.fragment;

import com.jakting.utils.DbProcess;
import com.jakting.utils.TableAdjust;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentInfo extends JFrame implements ActionListener {
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSStudentID = null;//学号
    JLabel jLSName = null;//姓名
    JLabel jLSSex = null;//性别
    JLabel jLSClass = null;//班级
    JLabel jLSDepartment = null;//学院
    JLabel jLSBirthday = null;//生日
    JLabel jLSNativePlace = null;//籍贯

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFSStudentID = null;//学号
    JTextField jTFSName = null;//姓名
    JTextField jTFSSex = null;//性别
    JTextField jTFSClass = null;//班级
    JTextField jTFSDepartment = null;//学院
    JTextField jTFSBirthday = null;//生日
    JTextField jTFSNativePlace = null;//籍贯

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBInsert = null;//插入
    JButton jBUpdate = null;//更新
    JButton jBDeleteCurrentRecord = null;//删除当前记录
    JButton jBDeleteAllRecords = null;//删除所有记录

    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JPanel jP1, jP2, jP3, jP4, jP5, jP6 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;
    private static DbProcess dbProcess;
    String SelectQueryFieldStr = "学号";

    //构造函数
    public StudentInfo() {
        jLStudentInfoTable = new JLabel("学生信息统计表");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSStudentID = new JLabel("学号");
        jLSName = new JLabel("姓名");
        jLSSex = new JLabel("性别");
        jLSClass = new JLabel("班级");
        jLSDepartment = new JLabel("学院");
        jLSBirthday = new JLabel("生日");
        jLSNativePlace = new JLabel("籍贯");

        jTFQueryField = new JTextField(10);//查询字段
        jTFSStudentID = new JTextField(10);//学号
        jTFSName = new JTextField(10);//姓名
        jTFSSex = new JTextField(10);//性别
        jTFSClass = new JTextField(10);//班级
        jTFSDepartment = new JTextField(10);//学院
        jTFSBirthday = new JTextField(10);//年龄
        jTFSNativePlace = new JTextField(10);//籍贯

        jBQuery = new JButton("查询");

        jBQueryAll = new JButton("查询所有记录");
        jBInsert = new JButton("插入");
        jBUpdate = new JButton("更新");
        jBDeleteCurrentRecord = new JButton("删除当前记录");
        jBDeleteAllRecords = new JButton("删除所有记录");

        studentVector = new Vector();
        titleVector = new Vector();

        // 定义表头
        titleVector.add("学号");
        titleVector.add("姓名");
        titleVector.add("性别");
        titleVector.add("班级");
        titleVector.add("学院");
        titleVector.add("生日");
        titleVector.add("籍贯");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(800, 250));
        int[] i = {0, 1, 2, 3, 4, 5, 6};
        TableAdjust.setSomeColumnSize(studentJTable, i, 52, 52, 52);
        TableAdjust.setOneColumnSize(studentJTable, 0, 110, 110, 52);
        TableAdjust.setOneColumnSize(studentJTable, 1, 70, 70, 52);
        TableAdjust.setOneColumnSize(studentJTable, 3, 200, 200, 52);
        TableAdjust.setOneColumnSize(studentJTable, 4, 172, 172, 52);
        TableAdjust.setOneColumnSize(studentJTable, 5, 100, 100, 52);
        TableAdjust.setOneColumnSize(studentJTable, 6, 70, 70, 52);
        studentJScrollPane = new JScrollPane(studentJTable);
        //分别设置水平和垂直滚动条自动出现
        studentJScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        studentJScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        //为表格添加监听器
        studentJTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
                System.out.println("mouseClicked(). row = " + row);
                Vector v = new Vector();
                v = (Vector) studentVector.get(row);

                jTFSStudentID.setText(Integer.toString((int) v.get(0)));// 学号
                jTFSStudentID.setEditable(false);
                jTFSName.setText((String) v.get(1));// 姓名
                jTFSSex.setText((String) v.get(2));// 性别
                jTFSClass.setText((String) v.get(3));// 班级
                jTFSDepartment.setText((String) v.get(4));// 学院
                jTFSBirthday.setText((String) v.get(5));// 生日
                jTFSNativePlace.setText((String) v.get(6));// 籍贯
            }
        });


        // 设置监听
        jBQuery.addActionListener(this);
        jBQueryAll.addActionListener(this);
        jBInsert.addActionListener(this);
        jBUpdate.addActionListener(this);
        jBDeleteCurrentRecord.addActionListener(this);
        jBDeleteAllRecords.addActionListener(this);

        jCBSelectQueryField = new JComboBox<String>();//查询字段
        jCBSelectQueryField.addItem("学号");
        jCBSelectQueryField.addItem("姓名");
        jCBSelectQueryField.addItem("性别");
        jCBSelectQueryField.addItem("班级");
        jCBSelectQueryField.addItem("学院");
        jCBSelectQueryField.addItem("生日");
        jCBSelectQueryField.addItem("籍贯");
        jCBSelectQueryField.addItemListener(new ItemListener() {//下拉框事件监听
            public void itemStateChanged(ItemEvent event) {
                switch (event.getStateChange()) {
                    case ItemEvent.SELECTED:
                        SelectQueryFieldStr = (String) event.getItem();
                        System.out.println("选中：" + SelectQueryFieldStr);
                        break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中：" + event.getItem());
                        break;
                }
            }
        });

        jP1 = new JPanel();
        jP2 = new JPanel();
        jP3 = new JPanel();
        jP4 = new JPanel();
        jP5 = new JPanel();
        jP6 = new JPanel();
        jPTop = new JPanel();
        jPBottom = new JPanel();

        //jP0.add(jToolBar, );

        jP1.add(jLStudentInfoTable);
        jP2.add(studentJScrollPane);
        jP2.setPreferredSize(new Dimension(1000, 1000));

        jP3.add(jLSelectQueryField);
        jP3.add(jCBSelectQueryField);
        jP3.add(jLEqual);
        jP3.add(jTFQueryField);
        jP3.add(jBQuery);
        jP3.add(jBQueryAll);
        jP3.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP3.setPreferredSize(new Dimension(50, 50));

        jP4.add(jLSStudentID);
        jP4.add(jTFSStudentID);
        jP4.add(jLSName);
        jP4.add(jTFSName);
        jP4.add(jLSSex);
        jP4.add(jTFSSex);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(50, 50));

        jP5.add(jLSClass);
        jP5.add(jTFSClass);
        jP5.add(jLSDepartment);
        jP5.add(jTFSDepartment);
        jP5.add(jLSBirthday);
        jP5.add(jTFSBirthday);
        jP5.add(jLSNativePlace);
        jP5.add(jTFSNativePlace);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(50, 50));

        jP6.add(jBInsert);
        jP6.add(jBUpdate);
        jP6.add(jBDeleteCurrentRecord);
        jP6.add(jBDeleteAllRecords);
        jP6.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP6.setPreferredSize(new Dimension(50, 50));

        jPTop.add(jP1);
        jPTop.add(jP2);
        jPTop.setLayout(new BoxLayout(jPTop, BoxLayout.Y_AXIS));
        jPBottom.setLayout(new GridLayout(5, 1));
        jPBottom.add(jP3);
        jPBottom.add(jP4);
        jPBottom.add(jP5);
        jPBottom.add(jP6);
        //jPBottom.setLayout(new BoxLayout(jPBottom, BoxLayout.Y_AXIS));


        dbProcess = new DbProcess();
    }

    public JPanel addAndSelect(JPanel jStu) {
        JPanel jStuA = jStu;
        jStuA.add(jPTop);
        jStuA.add(jPBottom);
        jStuA.setLayout(new BoxLayout(jStuA, BoxLayout.Y_AXIS));
        //jStu.setPreferredSize(new Dimension(700,480));
        {
            //点击窗体时取消表格的选中状态
            jStuA.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    studentJTable.clearSelection();
                    jTFSStudentID.setText("");
                    jTFSName.setText("");
                    jTFSSex.setText("");
                    jTFSClass.setText("");
                    jTFSDepartment.setText("");
                    jTFSBirthday.setText("");
                    jTFSNativePlace.setText("");
                    jTFSStudentID.setEditable(true);
                }
            });
        }
        return jStuA;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("查询")
                && !jTFQueryField.getText().isEmpty()) {
            System.out.println("actionPerformed(). 查询");
            String sQueryField = jTFQueryField.getText().trim();
            queryProcess(sQueryField);
            jTFQueryField.setText("");
        } else if (e.getActionCommand().equals("查询所有记录")) {
            System.out.println("actionPerformed(). 查询所有记录");
            queryAllProcess();
        } else if (e.getActionCommand().equals("插入")
                && !jTFSStudentID.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBirthday.getText().isEmpty()
                && !jTFSNativePlace.getText().isEmpty()) {
            System.out.println("actionPerformed(). 插入");
            insertProcess();
        } else if (e.getActionCommand().equals("更新")
                && !jTFSStudentID.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSClass.getText().isEmpty()
                && !jTFSDepartment.getText().isEmpty()
                && !jTFSBirthday.getText().isEmpty()
                && !jTFSNativePlace.getText().isEmpty()) {
            System.out.println("actionPerformed(). 更新");
            updateProcess();
        } else if (e.getActionCommand().equals("删除当前记录")) {
            System.out.println("actionPerformed(). 删除当前记录");
            deleteCurrentRecordProcess();
        } else if (e.getActionCommand().equals("删除所有记录")) {
            System.out.println("actionPerformed(). 删除所有记录");
            deleteAllRecordsProcess();
        }
    }

    public void queryProcess(String sQueryField) {
        try {
            // 建立查询条件
            String sql = "select * from student inner join class on class.cID = student.Class inner join department on department.dID = student.Department where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

            if (queryFieldStr.equals("StudentID")) {
                sql = sql + queryFieldStr;
                sql = sql + " = " + sQueryField;
            } else {
                sql = sql + queryFieldStr;
                sql = sql + " = ";
                sql = sql + "'" + sQueryField + "';";
            }

            System.out.println("queryProcess(). sql = " + sql);

            dbProcess.connect();
            ResultSet rs = dbProcess.executeQuery(sql);

            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            studentVector.clear();
            while (rs.next()) {
                Vector v = new Vector();
                v.add(Integer.valueOf(rs.getInt("StudentID")));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sSex"));
                v.add(rs.getString("class.cClassName"));
                v.add(rs.getString("department.dDepName"));
                v.add(rs.getString("sBirthday"));
                v.add(rs.getString("sNativePlace"));
                studentVector.add(v);
            }

            studentJTable.updateUI();

            dbProcess.disconnect();
        } catch (SQLException sqle) {
            System.out.println("sqle = " + sqle);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void queryAllProcess() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 建立查询条件
                    String sql = "select * from student inner join class on class.cID = student.Class inner join department on department.dID = student.Department;";
                    System.out.println("queryAllProcess(). sql = " + sql);

                    dbProcess.connect();
                    ResultSet rs = dbProcess.executeQuery(sql);

                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    studentVector.clear();
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(Integer.valueOf(rs.getInt("StudentID")));
                        v.add(rs.getString("sName"));
                        v.add(rs.getString("sSex"));
                        v.add(rs.getString("class.cClassName"));
                        v.add(rs.getString("department.dDepName"));
                        v.add(rs.getString("sBirthday"));
                        v.add(rs.getString("sNativePlace"));
                        studentVector.add(v);
                    }


                    studentJTable.updateUI();
                    dbProcess.disconnect();
                } catch (SQLException sqle) {
                    System.out.println("sqle = " + sqle);
                    JOptionPane.showMessageDialog(null,
                            "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void insertProcess() {
        String StudentID = jTFSStudentID.getText().trim();
        String sName = jTFSName.getText().trim();
        String sSex = jTFSSex.getText().trim();
        String Class = jTFSClass.getText().trim();
        String Department = jTFSDepartment.getText().trim();
        String sBirthday = jTFSBirthday.getText().trim();
        String sNativePlace = jTFSNativePlace.getText().trim();
        int trueClass = 0,trueDept=0;

        String getClassDepartment = "select class.cID,department.dID from student,class,department where class.cClassName = '"+Class+"' and department.dDepName='"+Department+"';";
        System.out.println("getClassDepartment. sql = " + getClassDepartment);
        dbProcess.connect();
        ResultSet rs = dbProcess.executeQuery(getClassDepartment);
        try {
            trueClass = rs.getInt("cID");
            trueDept =  rs.getInt("dID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbProcess.disconnect();

        // 建立插入条件
        String sql = "insert into student values(";
        sql = sql + StudentID + ",'";
        sql = sql + sName + "','";
        sql = sql + sSex + "',";
        sql = sql + trueClass + ",";
        sql = sql + trueDept + ",'";
        sql = sql + sBirthday + "','";
        sql = sql + sNativePlace + "');";

        System.out.println("insertProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("insertProcess(). insert database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();
    }


    public void updateProcess() {
        String StudentID = jTFSStudentID.getText().trim();
        String sName = jTFSName.getText().trim();
        String sSex = jTFSSex.getText().trim();
        String Class = jTFSClass.getText().trim();
        String Department = jTFSDepartment.getText().trim();
        String sBirthday = jTFSBirthday.getText().trim();
        String sNativePlace = jTFSNativePlace.getText().trim();
        int trueClass = 0,trueDept=0;
        String getClassDepartment = "select class.cID,department.dID from student,class,department where class.cClassName = '"+Class+"' and department.dDepName='"+Department+"';";
        System.out.println("getClassDepartment. sql = " + getClassDepartment);
        dbProcess.connect();
        ResultSet rs = dbProcess.executeQuery(getClassDepartment);
        try {
            while (rs.next()) {
                trueClass = rs.getInt("cID");
                trueDept = rs.getInt("dID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbProcess.disconnect();
        // 建立更新条件
        String sql = "update student set sName = '";
        sql = sql + sName + "', sSex = '";
        sql = sql + sSex + "', Class = ";
        sql = sql + trueClass + ", Department = ";
        sql = sql + trueDept + ", sBirthday = '";
        sql = sql + sBirthday + "', sNativePlace = '";
        sql = sql + sNativePlace + "'";
        sql = sql + " WHERE StudentID = " + StudentID + ";";
        System.out.println("updateProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("updateProcess(). update database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public void deleteCurrentRecordProcess() {

        String StudentID = jTFSStudentID.getText().trim();

        // 建立删除条件
        String sql = "delete from student where StudentID = " + StudentID + ";";
        System.out.println("deleteCurrentRecordProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteCurrentRecordProcess(). delete database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public void deleteAllRecordsProcess() {
        // 建立删除条件
        String sql = "delete from student;";
        System.out.println("deleteAllRecordsProcess(). sql = " + sql);
        try {
            if (dbProcess.executeUpdate(sql) < 1) {
                System.out.println("deleteAllRecordsProcess(). delete database failed.");
            }
        } catch (Exception e) {
            System.out.println("e = " + e);
            JOptionPane.showMessageDialog(null,
                    "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
        queryAllProcess();

    }

    public String jCBSelectQueryFieldTransfer(String InputStr) {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if (InputStr.equals("学号")) {
            outputStr = "StudentID";
        } else if (InputStr.equals("姓名")) {
            outputStr = "sName";
        } else if (InputStr.equals("性别")) {
            outputStr = "sSex";
        } else if (InputStr.equals("班级")) {
            outputStr = "Class";
        } else if (InputStr.equals("学院")) {
            outputStr = "Department";
        } else if (InputStr.equals("生日")) {
            outputStr = "sBirthday";
        } else if (InputStr.equals("籍贯")) {
            outputStr = "sNativePlace";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
