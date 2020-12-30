import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.sql.SQLException;

public class ShopPage extends JPanel {
	private JFrame f;
	private SetGetters SG;
	private String userAccount;
	private ShopPage shopPage = this;

	private int moneyLeft;
	private JLabel moneyLabel;
	private JButton exitButton;
	private JLabel titleLabel;
	private JComboBox comboBox_category;
	private JPanel goodsPanel;
	private JPanel itemsPanel;
	private JPanel charPanel;

	// Mask instance variables
	private JPanel panel_mask;
	private JButton buyMaskButton;
	private JLabel maskImgLabel;
	private JLabel maskPriceLabel;
	private JLabel maskAmountLabel;
	private JLabel maskUtilLabel;
	private int maskPrice = 299;

	// alcohol instance variables
	private JPanel panel_alcohol;
	private int alcoholPrice = 599;
	private JLabel alcoholPriceLabel;
	private JButton buyalcoholButton;
	private JLabel alcoholAmountLabel;
	private JLabel alcoholImgLabel;
	private JLabel alcoholUtilLabel;

	// clothes instance variables
	private JPanel panel_clothes;
	private int clothesPrice = 349;
	private JLabel clothesAmountLabel;
	private JButton buyClothesButton;
	private JLabel clothesPriceLabel;
	private JLabel clothesImgLabel;
	private JLabel clothesUtilLabel;

	// trump instance variables
	private JPanel panel_trump;
	private JButton buytrumpButton;
	private JLabel trumpImgLabel;
	private JLabel trumpPriceLabel;
	private JLabel trumpAmountLabel;
	private JLabel trumpUtilLabel;
	private int trumpPrice = 5000;
	private String trumpAmount = "未擁有";

	// clock instance variables
	private JPanel panel_clock;
	private String clockAmount = "未擁有";
	private int clockPrice = 8000;
	private JLabel clockPriceLabel;
	private JButton buyclockButton;
	private JLabel clockAmountLabel;
	private JLabel clockImgLabel;
	private JLabel clockUtilLabel;

	// tzuyu instance variables
	private JPanel panel_tzuyu;
	private String tzuyuAmount = "未擁有";
	private int tzuyuPrice = 10000;
	private JLabel tzuyuAmountLabel;
	private JButton buytzuyuButton;
	private JLabel tzuyuPriceLabel;
	private JLabel tzuyuImgLabel;
	private JLabel tzuyuUtilLabel;

	// images
	private static ImageIcon tzuyuShop;
	private static ImageIcon trumpShop;
	private static ImageIcon clockShop;
	private static ImageIcon maskShop;
	private static ImageIcon alcoholShop;
	private static ImageIcon clothesShop;

	static {
		try {
			URL tzuyuShopUrl = new URL("https://i.imgur.com/jxC4wMN.jpg");
			URL trumpShopUrl = new URL("https://i.imgur.com/HV6fLnY.jpg");
			URL clockShopUrl = new URL("https://i.imgur.com/eWXkS0e.jpg");
			URL maskShopUrl = new URL("https://i.imgur.com/ihyJQel.jpg");
			URL alcoholShopUrl = new URL("https://i.imgur.com/f3NLIlC.jpg");
			URL clothesShopUrl = new URL("https://i.imgur.com/YMi5Mqn.jpg");
			tzuyuShop = new ImageIcon(ImageIO.read(tzuyuShopUrl));
			trumpShop = new ImageIcon(ImageIO.read(trumpShopUrl));
			clockShop = new ImageIcon(ImageIO.read(clockShopUrl));
			maskShop = new ImageIcon(ImageIO.read(maskShopUrl));
			alcoholShop = new ImageIcon(ImageIO.read(alcoholShopUrl));
			clothesShop = new ImageIcon(ImageIO.read(clothesShopUrl));
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	/**
	 * Create the application.
	 */
	public ShopPage(JFrame f, String userAccount) {
		this.f = f;
		this.userAccount = userAccount;
		SG = new SetGetters(userAccount);

		setBounds(100, 100, 600, 800);
		setLayout(null);
		createMoneyLabel();
		createExitBtn();
		createTitle();
		createCategory();
		createGoodsPanel();
		createItemsPanel();
		createCharPanel();
	}

	// 餘額
	public void createMoneyLabel() {
		try {
			moneyLabel = new JLabel("$" + SG.getMoney());
		} catch (SQLException e) {
		}
		moneyLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
		moneyLabel.setBounds(376, 23, 202, 49);
		add(moneyLabel);
	}

	// 退出鍵
	public void createExitBtn() {
		exitButton = new JButton("離開");
		exitButton.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		exitButton.setBounds(24, 23, 117, 49);
		class exitListener implements ActionListener {
			public void actionPerformed(ActionEvent exit) {
				StartPage front = new StartPage(f, userAccount);
				f.remove(shopPage);
				f.add(front);
				f.setVisible(true);
			}
		}
		exitButton.addActionListener(new exitListener());
		add(exitButton);
	}

	// 標題
	public void createTitle() {
		titleLabel = new JLabel("商城");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 120));
		titleLabel.setBounds(179, 99, 250, 114);
		add(titleLabel);
	}

	// 道具＆角色切換
	public void createCategory() {
		comboBox_category = new JComboBox();
		comboBox_category.addItem("角色");
		comboBox_category.addItem("道具");
		comboBox_category.setBounds(222, 255, 172, 37);
		add(comboBox_category);
		class switchToCharListener implements ItemListener {
			public void itemStateChanged(ItemEvent swi) {
				if (swi.getSource() == comboBox_category) {
					if (comboBox_category.getSelectedItem().equals("道具")) {
						itemsPanel.setVisible(true);
						charPanel.setVisible(false);
					} else if (comboBox_category.getSelectedItem().equals("角色")) {
						itemsPanel.setVisible(false);
						charPanel.setVisible(true);
					}
				}
			}
		}
		ItemListener switchToCharListener = new switchToCharListener();
		comboBox_category.addItemListener(switchToCharListener);

	}

	public void createGoodsPanel() {
		goodsPanel = new JPanel();
		goodsPanel.setBounds(0, 0, 600, 800);
		add(goodsPanel);
		goodsPanel.setLayout(null);
	}

	// 道具版面
	public void createItemsPanel() {
		itemsPanel = new JPanel();
		itemsPanel.setBounds(51, 318, 500, 454);
		goodsPanel.add(itemsPanel);
		itemsPanel.setLayout(null);
		itemsPanel.setVisible(false);

		// 口罩
		panel_mask = new JPanel();
		panel_mask.setBounds(6, 6, 239, 219);
		itemsPanel.add(panel_mask);
		panel_mask.setBorder(BorderFactory.createTitledBorder(null, "口罩", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_mask.setLayout(null);

		buyMaskButton = new JButton("購買");
		try {
			if (SG.getMoney() < this.maskPrice) {
				buyMaskButton.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		buyMaskButton.setBounds(136, 134, 97, 29);
		panel_mask.add(buyMaskButton);

		// 購買mask listener
		class buyMaskListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - maskPrice);
					SG.setMasks(SG.getMaskAmount() + 1);
					moneyLabel.setText("$" + SG.getMoney());
					maskAmountLabel.setText("已擁有：  " + SG.getMaskAmount());
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clockPrice) {
						buyclockButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < trumpPrice) {
						buytrumpButton.setEnabled(false);
					}
					if (SG.getMoney() < tzuyuPrice) {
						buytzuyuButton.setEnabled(false);
					}
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		ActionListener buyMaskListener = new buyMaskListener();
		buyMaskButton.addActionListener(buyMaskListener);

		maskImgLabel = new JLabel();
		maskImgLabel.setBounds(6, 67, 134, 96);
		maskImgLabel.setIcon(maskShop);
		panel_mask.add(maskImgLabel);

		maskPriceLabel = new JLabel("$" + maskPrice);
		maskPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		maskPriceLabel.setBounds(163, 30, 70, 29);
		panel_mask.add(maskPriceLabel);

		try {
			maskAmountLabel = new JLabel("已擁有： " + SG.getMaskAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		maskAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		maskAmountLabel.setBounds(143, 91, 90, 31);
		panel_mask.add(maskAmountLabel);

		maskUtilLabel = new JLabel("整場遊戲25%機率可擋住攻擊");
		maskUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		maskUtilLabel.setBounds(6, 164, 227, 49);
		panel_mask.add(maskUtilLabel);

		// 酒精
		panel_alcohol = new JPanel();
		panel_alcohol.setBounds(257, 6, 237, 219);
		itemsPanel.add(panel_alcohol);
		panel_alcohol.setBorder(BorderFactory.createTitledBorder(null, "酒精", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_alcohol.setLayout(null);

		try {
			alcoholAmountLabel = new JLabel("已擁有： " + SG.getAlcoholAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		alcoholAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		alcoholAmountLabel.setBounds(145, 89, 86, 31);
		panel_alcohol.add(alcoholAmountLabel);

		buyalcoholButton = new JButton("購買");
		try {
			if (SG.getMoney() < this.alcoholPrice) {
				buyalcoholButton.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		buyalcoholButton.setBounds(134, 132, 97, 29);
		panel_alcohol.add(buyalcoholButton);
		// 購買listener
		class buyalcoholListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - alcoholPrice);
					SG.setAlcohol(SG.getAlcoholAmount() + 1);
					moneyLabel.setText("$" + SG.getMoney());
					alcoholAmountLabel.setText("已擁有：  " + SG.getAlcoholAmount());
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clockPrice) {
						buyclockButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < trumpPrice) {
						buytrumpButton.setEnabled(false);
					}
					if (SG.getMoney() < tzuyuPrice) {
						buytzuyuButton.setEnabled(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener buyalcoholListener = new buyalcoholListener();
		buyalcoholButton.addActionListener(buyalcoholListener);

		alcoholPriceLabel = new JLabel("$" + alcoholPrice);
		alcoholPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		alcoholPriceLabel.setBounds(161, 30, 70, 29);
		panel_alcohol.add(alcoholPriceLabel);

		alcoholImgLabel = new JLabel();
		alcoholImgLabel.setBounds(6, 65, 134, 96);
		alcoholImgLabel.setIcon(alcoholShop);
		panel_alcohol.add(alcoholImgLabel);

		alcoholUtilLabel = new JLabel("第一次受到傷害時，清除全部怪物");
		alcoholUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		alcoholUtilLabel.setBounds(4, 164, 227, 49);
		panel_alcohol.add(alcoholUtilLabel);

		// 防護衣
		panel_clothes = new JPanel();
		panel_clothes.setBounds(6, 229, 239, 219);
		itemsPanel.add(panel_clothes);
		panel_clothes.setBorder(BorderFactory.createTitledBorder(null, "防護衣", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_clothes.setLayout(null);

		try {
			clothesAmountLabel = new JLabel("已擁有： " + SG.getClothesAmount());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clothesAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		clothesAmountLabel.setBounds(146, 88, 87, 31);
		panel_clothes.add(clothesAmountLabel);

		buyClothesButton = new JButton("購買");
		try {
			if (SG.getMoney() < this.clothesPrice) {
				buyClothesButton.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		buyClothesButton.setBounds(136, 134, 97, 29);
		panel_clothes.add(buyClothesButton);

		// 購買listener
		class buyClothesListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - clothesPrice);
					SG.setClothes(SG.getClothesAmount() + 1);
					;
					moneyLabel.setText("$" + SG.getMoney());
					clothesAmountLabel.setText("已擁有： " + SG.getClothesAmount());
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clockPrice) {
						buyclockButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < trumpPrice) {
						buytrumpButton.setEnabled(false);
					}
					if (SG.getMoney() < tzuyuPrice) {
						buytzuyuButton.setEnabled(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener buyListener = new buyClothesListener();
		buyClothesButton.addActionListener(buyListener);

		clothesPriceLabel = new JLabel("$" + clothesPrice);
		clothesPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		clothesPriceLabel.setBounds(163, 30, 70, 29);
		panel_clothes.add(clothesPriceLabel);

		clothesImgLabel = new JLabel();
		clothesImgLabel.setBounds(6, 67, 134, 96);
		clothesImgLabel.setIcon(clothesShop);
		panel_clothes.add(clothesImgLabel);
		clothesUtilLabel = new JLabel("剩一滴血時，使所有病毒生命減少");
		clothesUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		clothesUtilLabel.setBounds(6, 164, 227, 49);
		panel_clothes.add(clothesUtilLabel);
	}

	// 角色
	public void createCharPanel() {
		charPanel = new JPanel();
		charPanel.setBounds(51, 318, 500, 454);
		goodsPanel.add(charPanel);
		charPanel.setLayout(null);
		charPanel.setVisible(true);

		// 川普
		panel_trump = new JPanel();
		panel_trump.setBounds(6, 6, 239, 219);
		charPanel.add(panel_trump);
		panel_trump.setBorder(BorderFactory.createTitledBorder(null, "川普", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_trump.setLayout(null);

		buytrumpButton = new JButton("購買");
		try {
			if (SG.getTrump() == 1) {
				trumpAmount = "已擁有";
				buytrumpButton.setEnabled(false);
			}
			if (SG.getMoney() < trumpPrice) {
				buytrumpButton.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		buytrumpButton.setBounds(136, 134, 97, 29);
		panel_trump.add(buytrumpButton);
		// 購買trump listener
		class buyTrumpListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - trumpPrice);
					SG.buyTrump();
					moneyLabel.setText("$" + SG.getMoney());
					trumpAmountLabel.setText("已擁有");
					buytrumpButton.setEnabled(false);
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clockPrice) {
						buyclockButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < tzuyuPrice) {
						buytzuyuButton.setEnabled(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener buyTrumpListener = new buyTrumpListener();
		buytrumpButton.addActionListener(buyTrumpListener);

		trumpImgLabel = new JLabel();
		trumpImgLabel.setBounds(6, 67, 134, 96);
		trumpImgLabel.setIcon(trumpShop);
		panel_trump.add(trumpImgLabel);

		trumpPriceLabel = new JLabel("$" + trumpPrice);
		trumpPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		trumpPriceLabel.setBounds(163, 30, 70, 29);
		panel_trump.add(trumpPriceLabel);

		trumpAmountLabel = new JLabel(trumpAmount);
		trumpAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		trumpAmountLabel.setBounds(143, 91, 90, 31);
		panel_trump.add(trumpAmountLabel);

		trumpUtilLabel = new JLabel("攻擊力：2 生命力：5");
		trumpUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		trumpUtilLabel.setBounds(6, 164, 227, 49);
		panel_trump.add(trumpUtilLabel);

		// 陳時中
		panel_clock = new JPanel();
		panel_clock.setBounds(257, 6, 237, 219);
		charPanel.add(panel_clock);
		panel_clock.setBorder(BorderFactory.createTitledBorder(null, "陳時中", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_clock.setLayout(null);

		buyclockButton = new JButton("購買");
		try {
			if (SG.getClock() == 1) {
				buyclockButton.setEnabled(false);
				clockAmount = "已擁有";
			}
			if (SG.getMoney() < clockPrice) {
				buyclockButton.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clockAmountLabel = new JLabel(clockAmount);
		clockAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		clockAmountLabel.setBounds(145, 89, 86, 31);
		panel_clock.add(clockAmountLabel);

		buyclockButton.setBounds(134, 132, 97, 29);
		panel_clock.add(buyclockButton);
		// 購買listener
		class buyclockListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - clockPrice);
					SG.buyClock();
					moneyLabel.setText("$" + SG.getMoney());
					clockAmount = "已擁有";
					clockAmountLabel.setText(clockAmount);
					buyclockButton.setEnabled(false);
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < trumpPrice) {
						buytrumpButton.setEnabled(false);
					}
					if (SG.getMoney() < tzuyuPrice) {
						buytzuyuButton.setEnabled(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener buyclockListener = new buyclockListener();
		buyclockButton.addActionListener(buyclockListener);

		clockPriceLabel = new JLabel("$" + clockPrice);
		clockPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		clockPriceLabel.setBounds(161, 30, 70, 29);
		panel_clock.add(clockPriceLabel);

		clockImgLabel = new JLabel();
		clockImgLabel.setBounds(6, 65, 134, 96);
		clockImgLabel.setIcon(clockShop);
		panel_clock.add(clockImgLabel);

		clockUtilLabel = new JLabel("攻擊力：4 生命力：8");
		clockUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		clockUtilLabel.setBounds(4, 164, 227, 49);
		panel_clock.add(clockUtilLabel);

		// 周子瑜
		panel_tzuyu = new JPanel();
		panel_tzuyu.setBounds(6, 229, 239, 219);
		charPanel.add(panel_tzuyu);
		panel_tzuyu.setBorder(BorderFactory.createTitledBorder(null, "周子瑜", TitledBorder.LEFT, TitledBorder.TOP,
				new Font("Lucida Grande", Font.PLAIN, 50)));
		panel_tzuyu.setLayout(null);

		// 購買listener
		buytzuyuButton = new JButton("購買");
		try {
			if (SG.getTzuyu() == 1) {
				tzuyuAmount = "已擁有";
				buytzuyuButton.setEnabled(false);
			}
			if (SG.getMoney() < tzuyuPrice) {
				buytzuyuButton.setEnabled(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		tzuyuAmountLabel = new JLabel(tzuyuAmount);
		tzuyuAmountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		tzuyuAmountLabel.setBounds(146, 88, 87, 31);
		panel_tzuyu.add(tzuyuAmountLabel);

		buytzuyuButton.setBounds(136, 134, 97, 29);
		panel_tzuyu.add(buytzuyuButton);
		class buytzuyuListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					SG.setMoney(SG.getMoney() - tzuyuPrice);
					SG.buyTzuyu();
					tzuyuAmount = "已擁有";
					moneyLabel.setText("$" + SG.getMoney());
					tzuyuAmountLabel.setText(tzuyuAmount);
					buytzuyuButton.setEnabled(false);
					if (SG.getMoney() < maskPrice) {
						buyMaskButton.setEnabled(false);
					}
					if (SG.getMoney() < alcoholPrice) {
						buyalcoholButton.setEnabled(false);
					}
					if (SG.getMoney() < clockPrice) {
						buyclockButton.setEnabled(false);
					}
					if (SG.getMoney() < clothesPrice) {
						buyClothesButton.setEnabled(false);
					}
					if (SG.getMoney() < trumpPrice) {
						buytrumpButton.setEnabled(false);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		ActionListener buyListener = new buytzuyuListener();
		buytzuyuButton.addActionListener(buyListener);

		tzuyuPriceLabel = new JLabel("$" + tzuyuPrice);
		tzuyuPriceLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		tzuyuPriceLabel.setBounds(163, 30, 70, 29);
		panel_tzuyu.add(tzuyuPriceLabel);

		tzuyuImgLabel = new JLabel();
		tzuyuImgLabel.setBounds(6, 67, 134, 96);
		tzuyuImgLabel.setIcon(tzuyuShop);
		panel_tzuyu.add(tzuyuImgLabel);

		tzuyuUtilLabel = new JLabel("攻擊力：??? 生命力：???");
		tzuyuUtilLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		tzuyuUtilLabel.setBounds(6, 164, 227, 49);
		panel_tzuyu.add(tzuyuUtilLabel);
	}

}
