
public class Vaccine extends MovingObject{
	public Vaccine(int x, int y) {
		this.x = x;
		this.y = y;
		image = GamePage.vaccine;
		width = image.getWidth();
		height = image.getHeight();
	}
	public void move() {
		y = y + 1;
	}
	public boolean outOfBound() {
		return y > GameViewer.FRAME_HEIGHT;
	}
}
