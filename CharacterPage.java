import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.SQLException;

public class CharacterPage extends JPanel {

	private JFrame frame;
	private CharacterPage charPage = this;
	private SetGetters SG;
	private String userAccount;

	private JLabel charLabel;
	private JComboBox characters;
	private JButton confirmBtn;
	private JLabel imgLabel;

	private static ImageIcon trump;
	private static ImageIcon tzuyu;
	private static ImageIcon tedros;
	private static ImageIcon clock;

	static {
		try {
			URL trumpUrl = new URL("https://i.imgur.com/2pjafzi.jpg");
			trump = new ImageIcon(ImageIO.read(trumpUrl));
			URL tzuyuUrl = new URL("https://i.imgur.com/TV37eRJ.jpg");
			tzuyu = new ImageIcon(ImageIO.read(tzuyuUrl));
			URL tedrosUrl = new URL("https://i.imgur.com/O9hqMSp.jpg");
			tedros = new ImageIcon(ImageIO.read(tedrosUrl));
			URL clockUrl = new URL("https://i.imgur.com/kTCyBNg.jpg");
			clock = new ImageIcon(ImageIO.read(clockUrl));
		} catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	private String chosen;

	// Constructor
	public CharacterPage(JFrame f, String userAccount) {
		frame = f;
		this.userAccount = userAccount;
		SG = new SetGetters(userAccount);

		setSize(600, 800);
		setLayout(null);

		createTitle();
		createCharComboBox();
		createCharImage();
		createConfirmBtn();
	}

	// 標題
	public void createTitle() {
		charLabel = new JLabel("角色");
		charLabel.setBounds(205, 72, 198, 117);
		charLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 99));
		add(charLabel);
	}

	// 選單
	public void createCharComboBox() {
		characters = new JComboBox();
		characters.setBounds(131, 230, 362, 55);
		characters.addItem("譚德賽");
		characters.addItem("川普");
		characters.addItem("陳時中");
		characters.addItem("周子瑜");
		chosen = "譚德賽";
		add(characters);
		class switchToCharListener implements ItemListener {
			public void itemStateChanged(ItemEvent swi) {
				if (swi.getSource() == characters) {
					if (characters.getSelectedItem().equals("譚德賽")) {
						imgLabel.setIcon(tedros);
						chosen = "譚德賽";
						confirmBtn.setEnabled(true);
					} else if (characters.getSelectedItem().equals("川普")) {
						try {
							if (SG.getTrump() == 0) {
								confirmBtn.setEnabled(false);
							} else {
								confirmBtn.setEnabled(true);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						imgLabel.setIcon(trump);
						chosen = "川普";
					} else if (characters.getSelectedItem().equals("陳時中")) {
						try {
							if (SG.getClock() == 0) {
								confirmBtn.setEnabled(false);
							} else {
								confirmBtn.setEnabled(true);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						imgLabel.setIcon(clock);
						chosen = "陳時中";
					} else if (characters.getSelectedItem().equals("周子瑜")) {
						try {
							if (SG.getTzuyu() == 0) {
								confirmBtn.setEnabled(false);
							} else {
								confirmBtn.setEnabled(true);
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						imgLabel.setIcon(tzuyu);
						chosen = "周子瑜";
					}

				}
			}
		}
		ItemListener switchToCharListener = new switchToCharListener();
		characters.addItemListener(switchToCharListener);
	}

	// 圖片
	public void createCharImage() {
		imgLabel = new JLabel();
		imgLabel.setBounds(85, 299, 446, 278);
		imgLabel.setIcon(tedros);
		add(imgLabel);
	}

	// 確認鍵
	public void createConfirmBtn() {
		confirmBtn = new JButton("選擇");
		confirmBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 58));
		confirmBtn.setBounds(181, 600, 256, 65);
		add(confirmBtn);

		class ConfirmBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ItemPage itemPage = new ItemPage(frame, charPage, userAccount);
				frame.remove(charPage);
				frame.add(itemPage);
				frame.setVisible(true);
			}
		}
		ActionListener confirmBtnListener = new ConfirmBtnListener();
		confirmBtn.addActionListener(confirmBtnListener);
	}

	public String getChosen() {
		return chosen;
	}

}