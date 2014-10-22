package me.mateass.srg2srcgui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame 
{
	/**
	 * @author mateass
	 */
	private static final long serialVersionUID = 1L;
	private File srcDir = new File(".");
	private File libDir = new File(".");
	private File srgDir = new File(".");
	private File outDir = new File(".");
	
	public MainFrame() {
		super("Srg2Source GUI");
		this.setSize(new Dimension(800, 600));
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildGui(this);
		this.setVisible(true);
	}
	
	private void buildGui(JFrame window) {
		JPanel p = new JPanel();
		//SrcDir inputs
		JPanel srcDirPanel = new JPanel();
		JLabel srcDirLabel = new JLabel("Source Dir: ", 4);
		final JTextField srcDirTextField = new JTextField(40);
		srcDirTextField.setEditable(false);
		JButton srcDirBrowseButton = new JButton("Browse...");
		srcDirBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSrcDirDialog(srcDirTextField);
			}
		});
		srcDirPanel.add(srcDirLabel);
		srcDirPanel.add(srcDirTextField);
		srcDirPanel.add(srcDirBrowseButton);
		//SrcDir end
		
		//LibDir inputs
		JPanel libDirPanel = new JPanel();
		JLabel libDirLabel = new JLabel("Library Dir: ", 4);
		final JTextField libDirTextField = new JTextField(40);
		libDirTextField.setEditable(false);
		JButton libDirBrowseButton = new JButton("Browse...");
		libDirBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showLibDirDialog(libDirTextField);
			}
		});
		libDirPanel.add(libDirLabel);
		libDirPanel.add(libDirTextField);
		libDirPanel.add(libDirBrowseButton);
		//LibDir end
		
		//SrgDir inputs
		JPanel srgDirPanel = new JPanel();
		JLabel srgDirLabel = new JLabel("Srg File Dir: ", 4);
		final JTextField srgDirTextField = new JTextField(40);
		srgDirTextField.setEditable(false);
		JButton srgDirBrowseButton = new JButton("Browse...");
		srgDirBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showSrgDirDialog(srgDirTextField);
			}
		});
		srgDirPanel.add(srgDirLabel);
		srgDirPanel.add(srgDirTextField);
		srgDirPanel.add(srgDirBrowseButton);
		//SrgDir end
		
		//OutDir inputs
		JPanel outDirPanel = new JPanel();
		JLabel outDirLabel = new JLabel("Output Dir: ", 4);
		final JTextField outDirTextField = new JTextField(40);
		outDirTextField.setEditable(false);
		JButton outDirBrowseButton = new JButton("Browse...");
		outDirBrowseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showOutDirDialog(outDirTextField);
			}
		});
		outDirPanel.add(outDirLabel);
		outDirPanel.add(outDirTextField);
		outDirPanel.add(outDirBrowseButton);
		//OutDir end
		
		//Process Button
		JButton process = new JButton("Remap");
		process.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doRemap();
			}
		});
		//Process Button end
		
		
		p.add(srcDirPanel);
		p.add(libDirPanel);
		p.add(srgDirPanel);
		p.add(outDirPanel);
		p.add(process, "South");
		window.add(p, "Center");
	}
	
	private void doRemap() {
		try {
			Caller.callRangeExtractor(srcDir, libDir, outDir);
			Caller.callRangeApplyer(srcDir, srgDir, outDir);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showSrcDirDialog(JTextField tf) {
		final JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			this.srcDir = fc.getSelectedFile();
			tf.setText(this.srcDir.toString());
		}
	}
	
	private void showLibDirDialog(JTextField tf) {
		final JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			this.libDir = fc.getSelectedFile();
			tf.setText(this.libDir.toString());
		}
	}
	
	private void showSrgDirDialog(JTextField tf) {
		final JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			this.srgDir = fc.getSelectedFile();
			tf.setText(this.srgDir.toString());
		}
	}
	
	private void showOutDirDialog(JTextField tf) {
		final JFileChooser fc = new JFileChooser(".");
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int result = fc.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION) {
			this.outDir = fc.getSelectedFile();
			tf.setText(this.outDir.toString());
		}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new MainFrame();
	}
}
