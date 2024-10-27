package com.itheima.ui;

import javax.swing.*;

import static com.itheima.ui.Const.LOGIN_WINDOW_HEIGHT;
import static com.itheima.ui.Const.LOGIN_WINDOW_WIDTH;

public class LoginJFrame extends JFrame {
	// 新建一个登录的界面
	public LoginJFrame() {
		this.setSize(LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
		// 设置界面标题
		this.setTitle("登录界面");
		// 设置界面置顶
		this.setAlwaysOnTop(true);
		// 设置界面居中
		this.setLocationRelativeTo(null);
		// 关闭游戏后直接结束虚拟机
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 让界面显示出来
		this.setVisible(true);
	}
}
