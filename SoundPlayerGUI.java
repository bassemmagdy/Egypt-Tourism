import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SoundPlayerGUI extends JApplet implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	public static Clip clip;
	private Clip myclip;
	JPanel a;
	JButton play;
	JButton pause;
	JButton stop;
	JButton loop;
	JButton mute;
	boolean select;

	public void init()
	{
		setSize(335, 50);
		a = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 10));
		a.setSize(900, 300);
		play = new JButton("play");
		pause = new JButton("pause");
		stop = new JButton("stop");
		loop = new JButton("loop");
		mute = new JButton("mute");
		play.setEnabled(true);
		stop.setEnabled(true);
		this.add(a);
		a.add(play);
		a.add(pause);
		a.add(stop);
		a.add(loop);
		a.add(mute);
		play.addActionListener(this);
		play.setActionCommand("play");
		stop.addActionListener(this);
		stop.setActionCommand("stop");
		pause.addActionListener(this);
		pause.setActionCommand("pause");
		loop.addActionListener(this);
		loop.setActionCommand("loop");
		mute.addActionListener(this);
		mute.setActionCommand("mute");
		setBackground(Color.BLACK);
		setLayout(new BorderLayout());
		a.setBackground(Color.GRAY);
		setVisible(true);
		try {
			File s = new File("C:\\Users\\hp\\Desktop\\Egyptian Tourguide\\Anthem.wav");
			if (s.exists()) {
			AudioInputStream sound = AudioSystem.getAudioInputStream(s);
			myclip = AudioSystem.getClip();
			myclip.open(sound);
			} 
			else {
				throw new RuntimeException("Sound file not found in: "	+	"C:\\Users\\hp\\Desktop\\Egyptian Tourguide\\Anthem.wav");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Sound: Line Unavailable Exception Error: " + e);}}
	public void play() {
		myclip.loop(0);
		myclip.start();
	}

	public void stop() {
		myclip.stop();
		myclip.setFramePosition(0);
	}
	public void pause() {
		myclip.stop();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("play")) {
			play();
			play.setEnabled(false);
			stop.setEnabled(true);
		}
		if (e.getActionCommand().equals("pause")) {
			pause();
			play.setEnabled(true);
		}

		if (e.getActionCommand().equals("stop")) {
			stop();
			play.setEnabled(true);
			stop.setEnabled(false);
		}
		if (e.getActionCommand().equals("loop")) {

			myclip.loop(Clip.LOOP_CONTINUOUSLY);
			stop.setEnabled(true);
		}

		if (e.getActionCommand().equals("mute")) {
			BooleanControl bc = (BooleanControl) myclip
					.getControl(BooleanControl.Type.MUTE);
			if (bc != null) {
				if (bc.getValue() == true) {
					bc.setValue(false);
				} else {
					bc.setValue(true);}}}}
}
