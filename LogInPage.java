import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class LogInPage extends JPanel {

	private static ImageIcon titleIcon;
	static {
		try {
			URL titleUrl = new URL("https://i.imgur.com/rmbJhM5.png");
			titleIcon = new ImageIcon(ImageIO.read(titleUrl));
		} catch (IOException e) {
			e.printStackTrace();// TODO: handle exception
		}
	}

	private JLabel title;
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JButton logInBtn;
	private JButton registerBtn;
	private JFrame f;

	// SQL database
	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG09";
	private String config = "?useUnicode=true&characterEncoding=utf8";
	private String url = server + database + config;
	private String username = "TG09";
	private String password = "u73e9x";
	private Connection conn = null;
	private String userAccount;
	private SetGetters SG = new SetGetters();

	private LogInPage logInPage = this;

	public LogInPage(JFrame f) {

		this.f = f;
		setLayout(null);

		createTitle();
		createAccount();
		createPassword();
		createLogInBtn();
		createRegisterBtn();
	}

	// 建立標題
	public void createTitle() {
		title = new JLabel();
		title.setIcon(titleIcon);
		title.setBounds(70, 60, 550, 100);
		add(title);
	}

	// 建立帳號相關物件
	public void createAccount() {
		accountLabel = new JLabel("帳號");
		accountLabel.setBounds(50, 210, 200, 100);
		accountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(accountLabel);

		accountField = new JTextField();
		accountField.setBounds(200, 225, 300, 80);
		accountField.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(accountField);
	}

	// 建立密碼相關物件
	public void createPassword() {
		passwordLabel = new JLabel("密碼");
		passwordLabel.setBounds(50, 340, 200, 100);
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 355, 300, 80);
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(passwordField);
	}

	// 建立登入按鍵
	public void createLogInBtn() {
		logInBtn = new JButton("登入");
		logInBtn.setBounds(200, 480, 180, 70);
		logInBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(logInBtn);

		class LogInBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String getAccountField = accountField.getText();
				String getPasswordField = String.valueOf(passwordField.getPassword());
				ResultSet rs = null;
				PreparedStatement ps = null;
				try {
					conn = DriverManager.getConnection(url, username, password);
					String query = "SELECT * FROM Watch_Out_Virus WHERE User_Account='" + getAccountField + "'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();

					// 確認帳號與密碼無誤，登入帳號
					if (rs.next()) {
						if (getPasswordField.equals(SG.getUserPassWord(getAccountField))) {
							userAccount = getAccountField;
							StartPage startPage = new StartPage(f, userAccount);
							f.remove(logInPage);
							f.add(startPage);
							f.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "密碼錯誤");
						}
					} else {
						JOptionPane.showMessageDialog(null, "無此帳號");
					}

				} catch (Exception exc) {
					exc.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					if (ps != null) {
						try {
							ps.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}

		ActionListener logInBtnListener = new LogInBtnListener();
		logInBtn.addActionListener(logInBtnListener);
	}

	// 建立註冊帳號按鍵
	public void createRegisterBtn() {
		registerBtn = new JButton("註冊新帳號");
		registerBtn.setBounds(200, 600, 180, 70);
		registerBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(registerBtn);

		// 跳到註冊頁面
		class RegisterBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				RegisterPage registerPage = new RegisterPage(f);
				f.remove(logInPage);
				f.add(registerPage);
				f.setVisible(true);
			}
		}

		ActionListener registerBtnListener = new RegisterBtnListener();
		registerBtn.addActionListener(registerBtnListener);
	}

}
