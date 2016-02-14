package script;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import simulate.SwarmMain;

public class CodeFrame extends JFrame implements ActionListener{
	
	private JButton compile;
	private JTextArea codeArea;
	
	public CodeFrame()
	{
		compile = new JButton("Compile");
		compile.addActionListener(this);
		codeArea = new JTextArea();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 500);
		this.getContentPane().add(compile, BorderLayout.PAGE_START);
		this.getContentPane().add(codeArea, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public String getCode()
	{
		return codeArea.getText();
	}

	//button has been pressed
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		SwarmMain.compileFlag = true;
	}

}
