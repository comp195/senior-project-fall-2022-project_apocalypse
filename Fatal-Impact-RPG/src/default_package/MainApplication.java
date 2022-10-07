package default_package;


public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private HowToPlayPane howToPlay;
	private PlayerSelectionPane chooseCharacter;
	private MainMenuPane menu;
	private ScreenDisplayPane display; //IMPLEMENT LATER
	private GameOverPane gameEnd;
	private boolean closeRangeCharacter;
	private boolean audioOn;
	private boolean playerWin;
	private int count;
	

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		howToPlay = new HowToPlayPane(this);
		chooseCharacter = new PlayerSelectionPane(this);
		menu = new MainMenuPane(this);
		display = new ScreenDisplayPane(this); //IMPLEMENT LATER
		gameEnd = new GameOverPane(this);
		audioOn = true; // audio is on by default
		setupInteractions();
		switchToMenu();
		 
	}

	public void switchToMenu() {
		count++;
		switchToScreen(menu);
	}

	public void switchTo(int n) {
		if (n == 0) {
			switchToScreen(chooseCharacter);
		}
		else if (n == 1){ // n == 1
			switchToScreen(howToPlay);
		}
		else if (n == 2) {
			switchToScreen(display);
		}
		else if (n == 3) {
			switchToScreen(gameEnd);
		}
	}
	//IMPLEMENT LATER
	/*
	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}
	*/
	public static void main(String[] args) {
		new MainApplication().start();
	}

	public boolean isCloseRangeCharacter() {
		return closeRangeCharacter;
	}

	public void setCloseRangeCharacter(boolean closeRangeWeapon) {
		this.closeRangeCharacter = closeRangeWeapon;
	}

	public boolean isAudioOn() {
		return audioOn;
	}

	public void setAudioOn(boolean audioOn) {
		this.audioOn = audioOn;
	}

	public boolean isPlayerWin() {
		return playerWin;
	}

	public void setPlayerWin(boolean playerWin) {
		this.playerWin = playerWin;
	}
}