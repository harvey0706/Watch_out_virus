public class Character extends MovingObject {
	private int life;
	private int power;

	// 依選擇角色不同做變化
	public Character(String chosen) {
		x = 250;
		y = 600;

		if (chosen.equals("譚德賽")) {
			image = GamePage.character1;
			life = 3;
			power = 1;
		} else if (chosen.equals("川普")) {
			image = GamePage.character2;
			life = 5;
			power = 2;
		} else if (chosen.equals("陳時中")) {
			image = GamePage.character3;
			life = 8;
			power = 4;
		} else if (chosen.equals("周子瑜")) {
			image = GamePage.character4;
			life = 1;
			power = 1;
		}

		width = image.getWidth();
		height = image.getHeight();

	}

	public void moveTo(int x) {
		this.x = x - width / 2;
	}

	// 角色射擊
	public Bullet shoot() {
		Bullet b = new Bullet(x + this.width / 2, y - this.height / 2);
		return b;
	}

	// 角色受到傷害
	public void damaged() {
		life = life - 1;
	}

	// 角色獲得生命道具
	public void heal() {
		life = life + 1;
	}

	// 角色獲得疫苗道具
	public void strengthen() {
		power = power + 1;
	}

	// 檢查道具與角色碰撞
	public boolean buffed(MovingObject obj) {
		int objX = obj.getX();
		int objY = obj.getY();
		return objX > x - obj.getWidth() / 2 && objX < x + width && objY > y - obj.getHeight() && objY < y + height;
	}

	public int getLife() {
		return life;
	}

	public int getPower() {
		return power;
	}
}
