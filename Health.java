
public class Health extends MovingObject{
	public Health(int x, int y) {
		this.x=x;
		this.y=y;
		image=GamePage.health;
		width = image.getWidth();
		height = image.getHeight();
	}
	public void move() {
		y=y+1;
	}
	public boolean outOfBound() {
		return y>GameViewer.FRAME_HEIGHT;
	}
}
