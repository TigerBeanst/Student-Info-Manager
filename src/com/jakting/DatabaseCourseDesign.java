package com.jakting;


import com.jakting.fragment.StatusChange;
import com.jakting.fragment.StudentInfo;
import com.jakting.utils.InitGlobalFont;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class DatabaseCourseDesign extends JFrame{
    // 定义组件
    private int count = 0;
    JTabbedPane jTabbedPane = null;

    JButton jToolbar_Student = null;//查询
    JButton jToolbar_Out = null;//查询
    JButton jToolbar_Great = null;//查询
    JButton jToolbar_Bad = null;//查询



    public static JPanel jStu, jOut, jGreat, jBad;
    // 构造函数
    public DatabaseCourseDesign() {
        // 创建组件
        jTabbedPane = new JTabbedPane();

        jToolbar_Student = new JButton("学生信息");
        jToolbar_Out = new JButton("学籍变更");
        jToolbar_Great = new JButton("获奖查询");
        jToolbar_Bad = new JButton("处分查询");

        jStu = new JPanel();
        jOut = new JPanel();
        jGreat = new JPanel();
        jBad = new JPanel();

        StudentInfo getjStu = new StudentInfo();
        jStu = getjStu.addAndSelect(jStu);
        StatusChange getjOut = new StatusChange();
        jOut = getjOut.addAndSelect(jOut);

        jTabbedPane.addTab("学生信息", jStu);
        jTabbedPane.addTab("学籍变更", jOut);
        jTabbedPane.addTab("获奖记录", jGreat);
        jTabbedPane.addTab("处分记录", jBad);
        jTabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // TODO Auto-generated method stub
                int n = jTabbedPane.getSelectedIndex();
                loadTab(n);
            }
        });
        loadTab(0);

        this.add(jTabbedPane, "Center");
        //this.add("North", jStu);

        //this.setLayout(new BoxLayout(jStu, BoxLayout.PAGE_AXIS));
        this.setTitle("学生管理系统");
        this.setSize(900, 700);
        this.setLocation(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        InitGlobalFont.InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 16));
        DatabaseCourseDesign getcon = new DatabaseCourseDesign();
    }

    private void loadTab(int n) {
        String title = jTabbedPane.getTitleAt(n);
        String countString = String.valueOf(count++);
        //String msg = "this is " + title + ", load at " + countString + " times";
        //jTabbedPane.setComponentAt(n, new JLabel(msg));
    }


}

