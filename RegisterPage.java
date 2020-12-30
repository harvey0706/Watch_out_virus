import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterPage extends JPanel {

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
	private JLabel confirmPasswordLabel;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton registerBtn;
	private JButton cancelBtn;
	private JFrame f;

	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG09";
	private String config = "?useUnicode=true&characterEncoding=utf8";
	private String url = server + database + config;
	private String username = "TG09";
	private String password = "u73e9x";
	private Connection conn = null;
	private SetGetters SG = new SetGetters();

	private RegisterPage registerPage = this;

	public RegisterPage(JFrame f) {

		this.f = f;

		setLayout(null);

		createTitle();
		createAccount();
		createPassword();
		createConfirmPassword();
		createRegisterBtn();
		createCancelBtn();
	}

	public void createTitle() {
		title = new JLabel();
		title.setIcon(titleIcon);
		title.setBounds(70, 60, 550, 100);
		add(title);
	}

	public void createAccount() {
		accountLabel = new JLabel("建立新帳號");
		accountLabel.setBounds(30, 210, 200, 100);
		accountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(accountLabel);

		accountField = new JTextField();
		accountField.setBounds(250, 225, 300, 80);
		accountField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(accountField);
	}

	public void createPassword() {
		passwordLabel = new JLabel("輸入密碼");
		passwordLabel.setBounds(50, 320, 200, 100);
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(250, 335, 300, 80);
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(passwordField);
	}

	public void createConfirmPassword() {
		confirmPasswordLabel = new JLabel("確認密碼");
		confirmPasswordLabel.setBounds(50, 430, 200, 100);
		confirmPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(confirmPasswordLabel);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(250, 445, 300, 80);
		confirmPasswordField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(confirmPasswordField);
	}

	public void createRegisterBtn() {
		registerBtn = new JButton("註冊");
		registerBtn.setBounds(200, 550, 180, 70);
		registerBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(registerBtn);

		class registerBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String getAccountField = accountField.getText();
				String getPasswordField = new String(passwordField.getPassword());
				String getConfirmPasswordField = new String(confirmPasswordField.getPassword());
				ResultSet rs = null;
				PreparedStatement ps = null;
				try {
					conn = DriverManager.getConnection(url, username, password);
					String query = "SELECT * FROM Watch_Out_Virus WHERE User_Account='" + getAccountField + "'";
					ps = conn.prepareStatement(query);
					rs = ps.executeQuery();

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "帳號已存在");
						passwordField.setText("");
						confirmPasswordField.setText("");
					} else {
						if (getPasswordField.equals(getConfirmPasswordField)) {
							try {
								SG.register(getAccountField, getPasswordField);
								JOptionPane.showMessageDialog(null, "註冊完成");
								LogInPage logInPage = new LogInPage(f);
								f.remove(registerPage);
								f.add(logInPage);
								f.setVisible(true);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "密碼不相同");
							passwordField.setText("");
							confirmPasswordField.setText("");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		registerBtnListener registerBtnListener = new registerBtnListener();
		this.registerBtn.addActionListener(registerBtnListener);
	}

	public void createCancelBtn() {
		cancelBtn = new JButton("返回");
		cancelBtn.setBounds(200, 650, 180, 70);
		cancelBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(cancelBtn);

		class CancelBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				LogInPage logInPage = new LogInPage(f);
				f.remove(registerPage);
				f.add(logInPage);
				f.setVisible(true);
			}
		}

		ActionListener cancelBtnListener = new CancelBtnListener();
		cancelBtn.addActionListener(cancelBtnListener);
	}
}
