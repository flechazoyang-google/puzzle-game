package com.itheima.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    //注册界面


    public RegisterJFrame() {
        this.setSize(488,500);

        //设置界面居中
        this.setLocationRelativeTo(null);

        //设置界面置顶
        this.setAlwaysOnTop(true);

        //设置界面标题
        this.setTitle("注册");

        //设置界面关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置界面显示
        this.setVisible(true);
    }
}
