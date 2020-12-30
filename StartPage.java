import java.awt.Font;
import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class StartPage extends JPanel {
	private static ImageIcon titleIcon;
	static {
		try {
			URL titleUrl = new URL("https://i.imgur.com/rmbJhM5.png");
			titleIcon = new ImageIcon(ImageIO.read(titleUrl));
		} catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	private static BufferedImage start_background;
	static {
		try {
			start_background = ImageIO.read(new URL("https://i.imgur.com/JugAyWY.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private JLabel title;
	private JButton shop;
	private JButton start;
	private JFrame f;

	private String userAccount;
	private StartPage startPage = this;

	// 建立開始頁面，傳入frame
	public StartPage(JFrame frame, String userAccount) {
		this.f = frame;
		this.userAccount = userAccount;

		setLayout(null);
		createTitle();
		createShop();
		createStart();
	}

	// 繪製背景
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(start_background, 0, 0, null);
	}

	// 建立遊戲標題
	public void createTitle() {
		title = new JLabel();
		title.setIcon(titleIcon);
		title.setBounds(70, 80, 500, 200);
		add(title);
	}

	// 建立前往商城JButton，連接到ShopPage
	public void createShop() {
		shop = new JButton("商店");
		shop.setBounds(150, 400, 300, 65);
		shop.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(shop);
		class shopActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ShopPage shopPage = new ShopPage(f, userAccount);
				f.remove(startPage);
				f.add(shopPage);
				f.setVisible(true);
			}
		}
		shop.addActionListener(new shopActionListener());
	}

	// 建立開始遊戲JButton，連接到CharacterPage
	public void createStart() {
		start = new JButton("開始");
		start.setBounds(150, 600, 300, 65);
		start.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(start);
		class startActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CharacterPage characterPage = new CharacterPage(f, userAccount);
				f.remove(startPage);
				f.add(characterPage);
				f.setVisible(true);
			}
		}
		start.addActionListener(new startActionListener());
	}
}
