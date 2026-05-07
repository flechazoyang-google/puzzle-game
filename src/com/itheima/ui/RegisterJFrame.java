package com.itheima.ui;

import com.itheima.domain.User;
import com.itheima.util.CodeUtil;
import com.itheima.util.UserUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RegisterJFrame extends JFrame implements MouseListener {

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmField = new JPasswordField();
    JTextField codeField = new JTextField();
    JLabel codeLabel = new JLabel();

    JButton registerButton = new JButton();
    JButton backButton = new JButton();

    String rightCode = CodeUtil.getCode();

    private ImageIcon res(String path) {
        return new ImageIcon(getClass().getResource("/" + path));
    }

    public RegisterJFrame() {
        initJFrame();
        initRegister();
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(488, 500);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setTitle("注册");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
    }

    private void initRegister() {
        JLabel titleLabel = new JLabel("用户注册");
        titleLabel.setBounds(190, 30, 100, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(titleLabel);

        JLabel usernameLabel = new JLabel(res("image/login/用户名.png"));
        usernameLabel.setBounds(80, 90, 47, 17);
        this.getContentPane().add(usernameLabel);
        usernameField.setBounds(150, 85, 220, 25);
        this.getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel(res("image/login/密码.png"));
        passwordLabel.setBounds(80, 145, 47, 17);
        this.getContentPane().add(passwordLabel);
        passwordField.setBounds(150, 140, 220, 25);
        this.getContentPane().add(passwordField);

        JLabel confirmLabel = new JLabel("确认密码");
        confirmLabel.setBounds(80, 200, 60, 17);
        this.getContentPane().add(confirmLabel);
        confirmField.setBounds(150, 195, 220, 25);
        this.getContentPane().add(confirmField);

        JLabel codeLabelText = new JLabel(res("image/login/验证码.png"));
        codeLabelText.setBounds(80, 255, 47, 17);
        this.getContentPane().add(codeLabelText);
        codeField.setBounds(150, 250, 120, 25);
        this.getContentPane().add(codeField);

        codeLabel.setText(rightCode);
        codeLabel.setBounds(290, 250, 100, 25);
        codeLabel.addMouseListener(this);
        this.getContentPane().add(codeLabel);

        registerButton = new JButton("注册");
        registerButton.setBounds(100, 320, 128, 47);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        backButton = new JButton("返回登录");
        backButton.setBounds(250, 320, 128, 47);
        backButton.addMouseListener(this);
        this.getContentPane().add(backButton);

        JLabel bg = new JLabel(res("image/login/background.png"));
        bg.setBounds(0, 0, 470, 460);
        this.getContentPane().add(bg);

        this.getContentPane().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o == registerButton) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            String code = codeField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showJDialog("用户名或密码不能为空");
            } else if (!password.equals(confirm)) {
                showJDialog("两次密码输入不一致");
            } else if (!code.equalsIgnoreCase(rightCode)) {
                showJDialog("验证码输入错误");
            } else if (UserUtil.userExists(username)) {
                showJDialog("用户名已存在");
            } else {
                UserUtil.saveUser(new User(username, password));
                showJDialog("注册成功");
                this.setVisible(false);
                new LoginJFrame();
            }

            rightCode = CodeUtil.getCode();
            codeLabel.setText(rightCode);
        } else if (o == backButton) {
            this.setVisible(false);
            new LoginJFrame();
        } else if (o == codeLabel) {
            rightCode = CodeUtil.getCode();
            codeLabel.setText(rightCode);
        }
    }

    private void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
