package com.jakting.fragment;

import com.jakting.utils.DbProcess;
import com.jakting.utils.AddClassFromSpec;
import com.jakting.utils.ClickAction;
import com.jakting.utils.TableAdjust;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.jakting.utils.ClickAction.clickInsertCheck;
import static com.jakting.utils.ClickAction.clickInsertDialog;

public class StudentInfo extends JFrame implements ActionListener {
    JLabel jLStudentInfoTable = null;//学生信息表
    JLabel jLSelectQueryField = null;//选择查询字段
    JLabel jLEqual = null;//=
    JLabel jLSNo = null;//学号
    JLabel jLSName = null;//姓名
    JLabel jLSSex = null;//性别
    JLabel jLSAge = null;//年龄
    JLabel jLSBirth = null;//生日
    JLabel jLSSchool = null;//学院
    JLabel jLSSpecialty = null;//专业
    JLabel jLSClass = null;//专业
    JLabel jLSNative = null;//籍贯
    JLabel jLSOrigin = null;//生源所在地

    JTextField jTFQueryField = null;//查询字段
    JTextField jTFSNo = null;//学号
    JTextField jTFSName = null;//姓名
    JTextField jTFSSex = null;//性别
    JTextField jTFSAge = null;//年龄
    JTextField jTFSBirth = null;//生日
    JTextField jTFSSchool = null;//学院
    JTextField jTFSSpecialty = null;//专业
    JTextField jTFSClass = null;//专业
    JTextField jTFSNative = null;//籍贯
    JTextField jTFSOrigin = null;//生源所在地

    JButton jBQuery = null;//查询
    JButton jBQueryAll = null;//查询所有记录
    JButton jBInsert = null;//插入
    JButton jBUpdate = null;//更新
    JButton jBDeleteCurrentRecord = null;//删除当前记录
    JButton jBDeleteAllRecords = null;//删除所有记录

    //JComboBox jCBSelectQueryField = null;
    JComboBox<String> jCBSelectQueryField = null;//查询字段
    JPanel jP1, jP2, jP3, jP4, jP5, jP6, jP7 = null;
    JPanel jPTop, jPBottom = null;
    DefaultTableModel studentTableModel = null;
    JTable studentJTable = null;
    JScrollPane studentJScrollPane = null;
    Vector studentVector = null;
    Vector titleVector = null;
    private static DbProcess dbProcess;
    String SelectQueryFieldStr = "学号";

    //构造函数
    public StudentInfo(){
        jLStudentInfoTable = new JLabel("学生信息统计表");
        jLSelectQueryField = new JLabel("选择查询字段");
        jLEqual = new JLabel(" = ");
        jLSNo = new JLabel("学号");
        jLSName = new JLabel("姓名");
        jLSSex = new JLabel("性别");
        jLSAge = new JLabel("年龄");
        jLSBirth = new JLabel("生日");
        jLSSchool = new JLabel("学院");
        jLSSpecialty = new JLabel("专业");
        jLSClass = new JLabel("班级");
        jLSNative = new JLabel("籍贯");
        jLSOrigin = new JLabel("生源");

        jTFQueryField = new JTextField(10);//查询字段
        jTFSNo = new JTextField(10);//学号
        jTFSName = new JTextField(10);//姓名
        jTFSSex = new JTextField(10);//性别
        jTFSAge = new JTextField(10);//年龄
        jTFSBirth = new JTextField(10);//年龄
        jTFSSchool = new JTextField(10);//学院
        jTFSSpecialty = new JTextField(10);//专业
        jTFSClass = new JTextField(10);//班级
        jTFSNative = new JTextField(10);//籍贯
        jTFSOrigin = new JTextField(10);//生源所在地

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
        titleVector.add("年龄");
        titleVector.add("生日");
        titleVector.add("学院");
        titleVector.add("专业");
        titleVector.add("班级");
        titleVector.add("籍贯");
        titleVector.add("生源");
        //studentTableModel = new DefaultTableModel(tableTitle, 15);
        studentJTable = new JTable(studentVector, titleVector);
        studentJTable.setPreferredScrollableViewportSize(new Dimension(800, 250));
        int[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        TableAdjust.setSomeColumnSize(studentJTable, i, 52, 52, 52);
        TableAdjust.setOneColumnSize(studentJTable, 4, 100, 100, 52);
        TableAdjust.setOneColumnSize(studentJTable, 5, 168, 168, 52);
        TableAdjust.setOneColumnSize(studentJTable, 6, 135, 135, 52);
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

                jTFSNo.setText(Integer.toString((int) v.get(0)));// 学号
                jTFSName.setText((String) v.get(1));// 姓名
                jTFSSex.setText((String) v.get(2));// 性别
                jTFSAge.setText(Integer.toString((int) v.get(3)));// 年龄
                jTFSBirth.setText((String) v.get(4));// 生日
                jTFSSchool.setText((String) v.get(5));// 专业
                jTFSSpecialty.setText((String) v.get(6));// 专业
                jTFSClass.setText((String) v.get(7));// 班级
                jTFSNative.setText((String) v.get(8));// 籍贯
                jTFSOrigin.setText((String) v.get(9));// 生源所在地
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
        jCBSelectQueryField.addItem("年龄");
        jCBSelectQueryField.addItem("生日");
        jCBSelectQueryField.addItem("学院");
        jCBSelectQueryField.addItem("专业");
        jCBSelectQueryField.addItem("籍贯");
        jCBSelectQueryField.addItem("生源所在地");
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
        jP7 = new JPanel();
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

        jP4.add(jLSNo);
        jP4.add(jTFSNo);
        jP4.add(jLSName);
        jP4.add(jTFSName);
        jP4.add(jLSSex);
        jP4.add(jTFSSex);
        jP4.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP4.setPreferredSize(new Dimension(50, 50));

        jP5.add(jLSAge);
        jP5.add(jTFSAge);
        jP5.add(jLSBirth);
        jP5.add(jTFSBirth);
        jP5.add(jLSSchool);
        jP5.add(jTFSSchool);
        jP5.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP5.setPreferredSize(new Dimension(50, 50));

        jP7.add(jLSSpecialty);
        jP7.add(jTFSSpecialty);
        jP7.add(jLSNative);
        jP7.add(jTFSNative);
        jP7.add(jLSOrigin);
        jP7.add(jTFSOrigin);
        jP7.setLayout(new FlowLayout(FlowLayout.CENTER));
        jP7.setPreferredSize(new Dimension(50, 50));

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
        jPBottom.add(jP7);
        jPBottom.add(jP6);
        //jPBottom.setLayout(new BoxLayout(jPBottom, BoxLayout.Y_AXIS));


        dbProcess = new DbProcess();
    }

    public JPanel addAndSelect(JPanel jStu){
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
                    jTFSNo.setText("");
                    jTFSName.setText("");
                    jTFSSex.setText("");
                    jTFSAge.setText("");
                    jTFSBirth.setText("");
                    jTFSSchool.setText("");
                    jTFSSpecialty.setText("");
                    jTFSClass.setText("");
                    jTFSNative.setText("");
                    jTFSOrigin.setText("");
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
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSAge.getText().isEmpty()
                && !jTFSBirth.getText().isEmpty()
                && !jTFSSchool.getText().isEmpty()
                && !jTFSSpecialty.getText().isEmpty()
                && !jTFSNative.getText().isEmpty()
                && !jTFSOrigin.getText().isEmpty()) {
            System.out.println("actionPerformed(). 插入");
            insertProcess();
        } else if (e.getActionCommand().equals("更新")
                && !jTFSNo.getText().isEmpty()
                && !jTFSName.getText().isEmpty()
                && !jTFSSex.getText().isEmpty()
                && !jTFSAge.getText().isEmpty()
                && !jTFSBirth.getText().isEmpty()
                && !jTFSSchool.getText().isEmpty()
                && !jTFSSpecialty.getText().isEmpty()
                && !jTFSNative.getText().isEmpty()
                && !jTFSOrigin.getText().isEmpty()) {
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
            String sql = "select * from student where ";
            String queryFieldStr = jCBSelectQueryFieldTransfer(SelectQueryFieldStr);

            if (queryFieldStr.equals("sAge") || queryFieldStr.equals("sNo")) {//int sAge and sNo.
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
                v.add(Integer.valueOf(rs.getInt("sNo")));
                v.add(rs.getString("sName"));
                v.add(rs.getString("sSex"));
                v.add(Integer.valueOf(rs.getInt("sAge")));
                v.add(rs.getString("sBirth"));
                v.add(rs.getString("sSchool"));
                v.add(rs.getString("sSpecialty"));
                v.add(rs.getString("sClass"));
                v.add(rs.getString("sNative"));
                v.add(rs.getString("sOrigin"));
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
                    String sql = "select * from student;";
                    System.out.println("queryAllProcess(). sql = " + sql);

                    dbProcess.connect();
                    ResultSet rs = dbProcess.executeQuery(sql);

                    // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                    studentVector.clear();
                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(Integer.valueOf(rs.getInt("sNo")));
                        v.add(rs.getString("sName"));
                        v.add(rs.getString("sSex"));
                        v.add(Integer.valueOf(rs.getInt("sAge")));
                        v.add(rs.getString("sBirth"));
                        v.add(rs.getString("sSchool"));
                        v.add(rs.getString("sSpecialty"));
                        v.add(rs.getString("sClass"));
                        v.add(rs.getString("sNative"));
                        v.add(rs.getString("sOrigin"));
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
        String sNo = jTFSNo.getText().trim();
        String sName = jTFSName.getText().trim();
        String sSex = jTFSSex.getText().trim();
        String sAge = jTFSAge.getText().trim();
        String sBirth = jTFSBirth.getText().trim();
        String sSchool = jTFSSchool.getText().trim();
        String sSpecialty = jTFSSpecialty.getText().trim();
        String sNative = jTFSNative.getText().trim();
        String sOrigin = jTFSOrigin.getText().trim();

        if (!clickInsertCheck(sSpecialty)) {
            //如果没有在输入专业的时候输入班级
            clickInsertDialog();
        } else {
            String sClass = AddClassFromSpec.AddClassFromSpec(sSpecialty);
            sSpecialty = sSpecialty.substring(0, sSpecialty.length() - 2);
            // 建立插入条件
            String sql = "insert into student values(";
            sql = sql + sNo + ",'";
            sql = sql + sName + "','";
            sql = sql + sSex + "',";
            sql = sql + sAge + ",'";
            sql = sql + sBirth + "','";
            sql = sql + sSchool + "','";
            sql = sql + sSpecialty + "','";
            sql = sql + sClass + "','";
            sql = sql + sNative + "','";
            sql = sql + sOrigin + "');";

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
    }

    public void updateProcess() {
        String sNo = jTFSNo.getText().trim();
        String sName = jTFSName.getText().trim();
        String sSex = jTFSSex.getText().trim();
        String sAge = jTFSAge.getText().trim();
        String sBirth = jTFSBirth.getText().trim();
        String sSchool = jTFSSchool.getText().trim();
        String sSpecialty = jTFSSpecialty.getText().trim();
        String sNative = jTFSNative.getText().trim();
        String sOrigin = jTFSOrigin.getText().trim();

        if (!clickInsertCheck(sSpecialty)) {
            //如果没有在输入专业的时候输入班级
            clickInsertDialog();
        } else {
            // 建立更新条件
            String sClass = AddClassFromSpec.AddClassFromSpec(sSpecialty);
            sSpecialty = sSpecialty.substring(0, sSpecialty.length() - 2);
            String sql = "update student set sName = '";
            sql = sql + sName + "', sSex = '";
            sql = sql + sSex + "', sAge = ";
            sql = sql + sAge + ", sBirth = '";
            sql = sql + sBirth + "', sSchool = '";
            sql = sql + sSchool + "', sSpecialty = '";
            sql = sql + sSpecialty + "', sClass = '";
            sql = sql + sClass + "', sNative = '";
            sql = sql + sNative + "', sOrigin = '";
            sql = sql + sOrigin + "'";
            sql = sql + " WHERE sNo = " + sNo + ";";
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
    }

    public void deleteCurrentRecordProcess() {
        if (ClickAction.clickDeleteOne()) {
            String sNo = jTFSNo.getText().trim();

            // 建立删除条件
            String sql = "delete from student where sNo = " + sNo + ";";
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
    }

    public void deleteAllRecordsProcess() {
        if (ClickAction.clickDeleteAll()) {
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
    }

    public String jCBSelectQueryFieldTransfer(String InputStr) {
        String outputStr = "";
        System.out.println("jCBSelectQueryFieldTransfer(). InputStr = " + InputStr);

        if (InputStr.equals("学号")) {
            outputStr = "sNo";
        } else if (InputStr.equals("姓名")) {
            outputStr = "sName";
        } else if (InputStr.equals("性别")) {
            outputStr = "sSex";
        } else if (InputStr.equals("年龄")) {
            outputStr = "sAge";
        } else if (InputStr.equals("生日")) {
            outputStr = "sBirth";
        } else if (InputStr.equals("学院")) {
            outputStr = "sSchool";
        } else if (InputStr.equals("专业")) {
            outputStr = "sSpecialty";
        } else if (InputStr.equals("籍贯")) {
            outputStr = "sNative";
        } else if (InputStr.equals("生源所在地")) {
            outputStr = "sOrigin";
        }
        System.out.println("jCBSelectQueryFieldTransfer(). outputStr = " + outputStr);
        return outputStr;
    }
}
