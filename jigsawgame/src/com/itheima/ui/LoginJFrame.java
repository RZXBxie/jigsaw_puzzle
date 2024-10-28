package com.itheima.ui;

import javax.swing.*;

import java.util.HashMap;
import static com.itheima.ui.Const.*;

public class LoginJFrame extends JFrame {
	// 用于存储用户名和对应的密码
	HashMap<String, String> userInfo = new HashMap<>();

	// 新建一个登录的界面
	public LoginJFrame() {
		initJFrame();
		initView();

		// 让界面显示出来
		this.setVisible(true);
	}

	private void initView() {
		// 添加用户名图片
		JLabel usernameText = new JLabel(new ImageIcon("jigsawgame\\image\\login\\用户名.png"));
		usernameText.setBounds(145, 141, USERNAME_IMAGE_WIDTH, USERNAME_IMAGE_HEIGHT);
		this.getContentPane().add(usernameText);

		// 添加用户名输入框
		JTextField username = new JTextField();
		username.setBounds(195, 135, 200, 30);
		this.getContentPane().add(username);

		// 添加密码图片
		JLabel passwordText = new JLabel(new ImageIcon("jigsawgame\\image\\login\\密码.png"));
		passwordText.setBounds(160, 202, PASSWORD_IMAGE_WIDTH, PASSWORD_IMAGE_HEIGHT);
		this.getContentPane().add(passwordText);

		// 添加密码输入框
		JTextField password = new JTextField();
		password.setBounds(195, 195, 200, 30);
		this.getContentPane().add(password);

		// 添加验证码图片
		JLabel codeText = new JLabel(new ImageIcon("jigsawgame\\image\\login\\验证码.png"));
		codeText.setBounds(142, 256, CODE_IMAGE_WIDTH, CODE_IMAGE_HEIGHT);
		this.getContentPane().add(codeText);

		// 添加验证码输入框
		JTextField code = new JTextField();
		code.setBounds(195, 256, 100, 30);
		this.getContentPane().add(code);


	}

	private void initJFrame() {
		this.setSize(LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
		// 设置界面标题
		this.setTitle("登录界面");
		// 设置界面置顶
		this.setAlwaysOnTop(true);
		// 设置界面居中
		this.setLocationRelativeTo(null);
		// 关闭游戏后直接结束虚拟机
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
