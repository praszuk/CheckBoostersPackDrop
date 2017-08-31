package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GUI {
	private static final int height = 300;
	private static final int width = 200;
	private static final String windowTitle = "Booster packs";

	private JFrame jf = new JFrame();
	private JLabel label = new JLabel("",SwingConstants.CENTER);


	public GUI() {
		SwingUtilities.invokeLater(() -> createGUI());
	}

	private void createGUI() {
		JScrollPane jsp = new JScrollPane(label);

		jf.setLayout(new BorderLayout());
		jf.add(jsp, BorderLayout.CENTER);

		jf.setTitle(windowTitle);
		jf.setPreferredSize(new Dimension(height,width));
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	// setter
	public void setText(String text) { label.setText(text); }
}
