import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GamePage extends JPanel {
	// 建立固定物件
	private int score = 0;
	private int money = 0;
	private int damagedCount = 0;
	private Timer timer;
	private JFrame frame;
	private ItemPage itemPage;
	private CharacterPage charPage;
	private String userAccount;

	// Timer延遲10毫秒
	private static final int INTERVEL = 10;

	// 建立遊戲物件圖
	public static BufferedImage background;
	public static BufferedImage life;
	public static BufferedImage character1;
	public static BufferedImage character2;
	public static BufferedImage character3;
	public static BufferedImage character4;
	public static BufferedImage bullet;
	public static BufferedImage virus1;
	public static BufferedImage virus2;
	public static BufferedImage virus3;
	public static BufferedImage health;
	public static BufferedImage vaccine;

	// 建立移動物件
	private Character character;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Virus> virusEnemy = new ArrayList<Virus>();
	private ArrayList<Health> healthItem = new ArrayList<Health>();
	private ArrayList<Vaccine> vaccineItem = new ArrayList<Vaccine>();

	// 讀入圖片
	static {
		try {
			background = ImageIO.read(new URL("https://i.imgur.com/MdmY4hx.jpg"));
			life = ImageIO.read(new URL("https://i.imgur.com/b34lJh1.png"));
			character1 = ImageIO.read(new URL("https://i.imgur.com/OrI2kk2.png"));
			character2 = ImageIO.read(new URL("https://i.imgur.com/faYGpVk.png"));
			character3 = ImageIO.read(new URL("https://i.imgur.com/sCIf3ho.png"));
			character4 = ImageIO.read(new URL("https://i.imgur.com/xE7g7SB.png"));
			bullet = ImageIO.read(new URL("https://i.imgur.com/s2WcvQs.png"));
			virus1 = ImageIO.read(new URL("https://i.imgur.com/xSNT0Xh.png"));
			virus2 = ImageIO.read(new URL("https://i.imgur.com/f65aKyD.png"));
			virus3 = ImageIO.read(new URL("https://i.imgur.com/knTvbyF.png"));
			health = ImageIO.read(new URL("https://i.imgur.com/1hMMIMq.png"));
			vaccine = ImageIO.read(new URL("https://i.imgur.com/FuMUAet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// 繪製所有物件
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintLife(g);
		paintCharacter(g);
		paintBullet(g);
		paintVirus(g);
		paintHealth(g);
		paintVaccine(g);
		paintScore(g);
		paintLifeAmount(g);
	}

	public void paintLife(Graphics g) {
		g.drawImage(life, 10, 5, null);
	}

	public void paintCharacter(Graphics g) {
		g.drawImage(character.getImage(), character.getX(), character.getY(), null);
	}

	public void paintBullet(Graphics g) {
		for (Bullet bs : bullets) {
			g.drawImage(bs.getImage(), bs.getX() - bs.getWidth() / 2, bs.getY(), null);
		}
	}

	public void paintVirus(Graphics g) {
		for (Virus v : virusEnemy) {
			g.drawImage(v.getImage(), v.getX(), v.getY(), null);
		}
	}

	public void paintHealth(Graphics g) {
		for (Health h : healthItem) {
			g.drawImage(h.getImage(), h.getX(), h.getY(), null);
		}
	}

	public void paintVaccine(Graphics g) {
		for (Vaccine vac : vaccineItem) {
			g.drawImage(vac.getImage(), vac.getX(), vac.getY(), null);
		}
	}

	public void paintScore(Graphics g) {
		String yourScore = String.format("%010d", score);
		g.setFont(new Font("Dialog", 1, 40));
		g.drawString(yourScore, 300, 50);
	}

	public void paintLifeAmount(Graphics g) {
		g.setFont(new Font("Dialog", 1, 40));
		g.drawString("X" + character.getLife(), 80, 50);
	}

	// 建立GamePage，傳入選擇的角色與道具
	public GamePage(JFrame f, ItemPage itemPage, CharacterPage charPage, String userAccount) {
		this.frame = f;
		this.itemPage = itemPage;
		this.charPage = charPage;
		character = new Character(this.charPage.getChosen());
		this.userAccount = userAccount;

		// 執行遊戲
		this.action();
	}

	public void action() {
		// 使角色移動和射擊
		MouseAdapter m = new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				character.moveTo(x);
			}

			public void mouseClicked(MouseEvent e) {
				Bullet b = character.shoot();
				bullets.add(b);
			}
		};
		this.addMouseMotionListener(m);
		this.addMouseListener(m);

		// 用timer控制所有遊戲進行
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				bulletMove();
				createVirus();
				virusMove();
				itemMove();
				bangAction();
				checkVirusLife();
				getItem();
				deleteOutOfBound();
				repaint();
				checkGameOver();
			}
		}, INTERVEL, INTERVEL);
	}

	public void bulletMove() {
		for (Bullet bs : bullets) {
			bs.move();
		}
	}

	public void virusMove() {
		for (Virus v : virusEnemy) {
			if (score < 15000) {
				v.move(1);
			} else if (score >= 15000) {
				v.move(2);
			}
		}
	}

	// 獎勵掉落速度較慢
	int itemIndex = 0;

	public void itemMove() {
		itemIndex++;
		for (Health h : healthItem) {
			if (itemIndex % 2 == 0) {
				h.move();
			}
		}
		for (Vaccine vac : vaccineItem) {
			if (itemIndex % 2 == 0) {
				vac.move();
			}
		}
	}

	// 固定時間生成新病毒
	int virusIndex = 0;

	public void createVirus() {
		virusIndex++;
		if (score < 5000) {
			if (virusIndex % 200 == 0) {
				Virus v1 = new Virus(1);
				clothesEffect(v1);
				virusEnemy.add(v1);
			}
		} else if (score >= 5000 && score < 15000) {
			if (virusIndex % 150 == 0) {
				Virus v2 = new Virus(2);
				clothesEffect(v2);
				virusEnemy.add(v2);
			}
		} else if (score >= 15000) {
			if (virusIndex % 100 == 0) {
				Virus v3 = new Virus(3);
				clothesEffect(v3);
				virusEnemy.add(v3);
			}
		}
	}

	// 檢查子彈與病毒碰撞
	public void bangAction() {
		for (Virus v : virusEnemy) {
			bang(v);
		}
	}

	// 檢查病毒是否被消滅
	public void checkVirusLife() {
		ArrayList<Virus> virusLive = new ArrayList<Virus>();
		for (Virus v : virusEnemy) {
			if (v.getLife() > 0) {
				virusLive.add(v);
			} else {
				score = score + v.getScore();
				money = money + v.getMoney();
				itemFall(v.getX(), v.getY());
			}
		}
		virusEnemy = virusLive;
	}

	// 獲得道具
	public void getItem() {
		ArrayList<Health> healthLive = new ArrayList<Health>();
		ArrayList<Vaccine> vaccineLive = new ArrayList<Vaccine>();
		for (Health h : healthItem) {
			if (character.buffed(h)) {
				character.heal();
			} else {
				healthLive.add(h);
			}
		}
		for (Vaccine vac : vaccineItem) {
			if (character.buffed(vac)) {
				character.strengthen();
			} else {
				vaccineLive.add(vac);
			}
		}
		healthItem = healthLive;
		vaccineItem = vaccineLive;
	}

	// 刪除超出畫面的物件
	// 病毒超出畫面則扣生命
	public void deleteOutOfBound() {
		ArrayList<Virus> virusLive = new ArrayList<Virus>();
		for (Virus v : virusEnemy) {
			if (!v.outOfBound()) {
				virusLive.add(v);
			} else {
				// 檢查是否有使用口罩道具
				if (itemPage.getMaskBoolean()) {
					double maskProtectIndex = Math.random();
					if (maskProtectIndex > 0.25) {
						character.damaged();
						// 檢查是否有使用酒精道具
						damagedCount++;
						if (damagedCount == 1) {
							if (itemPage.getAlcoholBoolean()) {
								for (Virus vs : virusEnemy) {
									score = score + vs.getScore();
									money = money + vs.getMoney();
								}
								virusEnemy.clear();
								break;
							}
						}
					}
				} else {
					character.damaged();
					// 檢查是否有使用酒精道具
					damagedCount++;
					if (damagedCount == 1) {
						if (itemPage.getAlcoholBoolean()) {
							for (Virus vs : virusEnemy) {
								score = score + vs.getScore();
								money = money + vs.getMoney();
							}
							virusEnemy.clear();
							break;
						}
					}
				}
			}
		}
		virusEnemy = virusLive;

		ArrayList<Bullet> bulletLive = new ArrayList<Bullet>();
		for (Bullet b : bullets) {
			if (!b.outOfBound()) {
				bulletLive.add(b);
			}
		}
		bullets = bulletLive;

		ArrayList<Health> healthLive = new ArrayList<Health>();
		for (Health h : healthItem) {
			if (!h.outOfBound()) {
				healthLive.add(h);
			}
		}
		healthItem = healthLive;

		ArrayList<Vaccine> vaccineLive = new ArrayList<Vaccine>();
		for (Vaccine vac : vaccineItem) {
			if (!vac.outOfBound()) {
				vaccineLive.add(vac);
			}
		}
		vaccineItem = vaccineLive;
	}

	// 檢查角色生命值是否歸零，歸零則跳到GameOverPage
	public void checkGameOver() {
		if (character.getLife() == 0) {
			GameOverPage gPanel = new GameOverPage(frame, this, userAccount);
			frame.remove(this);
			frame.add(gPanel);
			frame.setVisible(true);
			timer.cancel();
		}
	}

	// 檢查病毒與子彈碰撞，並移除子彈，使病毒扣血
	public void bang(Virus v) {
		for (Bullet b : bullets) {
			if (b.shot(v)) {
				bullets.remove(b);
				v.damaged(character);
				break;
			}
		}
	}

	// 控制道具掉落
	public void itemFall(int x, int y) {
		// 隨機掉落生命(機率為5%)
		double index = Math.random();
		if (index < 0.05) {
			Health h = new Health(x, y);
			healthItem.add(h);
		}
		// 一定分數會掉落疫苗
		if (score == 5000 || score == 15000) {
			Vaccine vac = new Vaccine(x, y);
			vaccineItem.add(vac);
		}
	}

	// 檢查是否有使用防護衣
	public void clothesEffect(Virus v) {
		if (character.getLife() == 1) {
			if (itemPage.getClothesBoolean()) {
				v.clothesEffect();
			}
		}
	}

	public int getMoney() {
		return money;
	}

	public int getScore() {
		return score;
	}
}
