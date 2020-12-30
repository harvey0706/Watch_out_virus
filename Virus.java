public class Virus extends MovingObject {

	private int life;
	private int score;
	private int money;

	// 建立三種不同等級病毒
	public Virus(int level) {
		// 隨機生成位置
		x = (int) (Math.random() * 500);
		y=0;

		if (level == 1) {
			life = 3;
			score = 100;
			money = 1;
			image = GamePage.virus1;
		}

		else if (level == 2) {
			life = 10;
			score = 200;
			money = 3;
			image = GamePage.virus2;
		}

		else if (level == 3) {
			life = 25;
			score = 400;
			money = 10;
			image = GamePage.virus3;
		}

		width = image.getWidth();
		height = image.getHeight();

	}

	public void move(int i) {
		this.y = y + i;
	}

	// 病毒受到傷害
	public void damaged(Character c) {
		life = life - c.getPower();
	}

	// 病毒受防護衣影響
	public void clothesEffect() {
		life = life - 1;
	}

	// 檢查出界
	public boolean outOfBound() {
		return y > GameViewer.FRAME_HEIGHT;
	}

	public int getLife() {
		return life;
	}

	public int getScore() {
		return score;
	}

	public int getMoney() {
		return money;
	}
}
