import javax.swing.JFrame;

public class GameViewer {
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 800;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("小心疫疫");
		LogInPage front = new LogInPage(frame);
		frame.add(front);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
