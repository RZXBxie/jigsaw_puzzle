package com.itheima.ui;

import javax.swing.*;

import java.io.File;
import java.util.Random;

import static com.itheima.ui.Const.GAME_WINDOW_HEIGHT;
import static com.itheima.ui.Const.GAME_WINDOW_WIDTH;
import static com.itheima.ui.Const.GAME_PICTURE_WIDTH;
import static com.itheima.ui.Const.GAME_PICTURE_HEIGHT;

public class GameJFrame extends JFrame {
	// 新建一个游戏界面
	public GameJFrame() {
		initJFrame();
		initJMenuBar();

		// 初始化图片
		initImage();

		// 让界面显示出来
		this.setVisible(true);
	}

	private void initImage() {
		String[] pictures = new String[16];
		for (int i = 1; i <= 16; i++) {
			String filename = "E:\\JigsawPuzzle\\jigsawgame\\image\\girl\\girl1\\" + i + ".jpg";
			pictures[i - 1] = filename;
		}
		pictures[15] = null;
		for (int i = 0; i < 15; ++i) {
			Random rand = new Random();
			int index = rand.nextInt(15);
			swap(pictures, index, i);
		}

		// 将图片添加到JFrame中
		int number = 1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 1.新建一个ImageIcon对象，它用来存储图片
				String filename = pictures[i * 4 + j];
				pictures[number - 1] = filename;
				ImageIcon icon = new ImageIcon(filename);

				// 2.新建一个JLabel对象，他可以管理图片和文字
				JLabel label = new JLabel(icon);

				// 3.设置图片的位置
				label.setBounds(j * GAME_PICTURE_WIDTH, i * GAME_PICTURE_HEIGHT, GAME_PICTURE_WIDTH, GAME_PICTURE_HEIGHT);

				// 4.将图片添加到JFrame
				this.getContentPane().add(label);
				number++;
			}
		}
	}

	public void swap(String[] arr, int x, int y) {
		String tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}

	private void initJMenuBar() {
		// 初始化菜单
		// 创建整个菜单对象
		JMenuBar menuBar = new JMenuBar();

		// 创建菜单上面的两个选项的对象（功能、关于我们）
		JMenu functionJMenu = new JMenu("功能");
		JMenu aboutJMenu = new JMenu("关于我们");

		// 创建功能列表
		JMenuItem restartItem = new JMenuItem("重新开始");
		JMenuItem reLoginItem = new JMenuItem("重新登录");
		JMenuItem closeItem = new JMenuItem("关闭游戏");
		JMenuItem accountItem = new JMenuItem("公众号");

		// 将相应功能添加到菜单中
		functionJMenu.add(restartItem);
		functionJMenu.add(reLoginItem);
		functionJMenu.add(closeItem);
		aboutJMenu.add(accountItem);
		menuBar.add(functionJMenu);
		menuBar.add(aboutJMenu);
		this.setJMenuBar(menuBar);
	}

	private void initJFrame() {
		// 设置界面的宽高
		this.setSize(GAME_WINDOW_WIDTH, GAME_WINDOW_HEIGHT);
		// 设置界面标题
		this.setTitle("拼图单机版 v1.0");
		// 设置界面置顶
		this.setAlwaysOnTop(true);
		// 设置界面居中
		this.setLocationRelativeTo(null);
		// 关闭游戏后直接结束虚拟机
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 取消默认布局，否则图片会被加载到正中间
		this.setLayout(null);
	}
}
