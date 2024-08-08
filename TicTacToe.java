package TicTacToeGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TicTacToe extends JFrame implements ActionListener {
	private Random random = new Random();
	private JPanel topic, button_panel;
	private JLabel wordsonTopic;
	private JButton[] button;
	private boolean first_player;

	TicTacToe() {
		Border border = BorderFactory.createLineBorder(new Color(68, 86, 145), 3);
		button = new JButton[9];
		topic = new JPanel();
		topic.setPreferredSize(new Dimension(100, 100));
		topic.setBackground(new Color(88, 99, 169));
		topic.setLayout(new BorderLayout());
		topic.setBorder(border);

		wordsonTopic = new JLabel("TIC-TAC-TOE");
		wordsonTopic.setBackground(new Color(205, 222, 223));
		wordsonTopic.setFont(new Font("Calibri", Font.BOLD, 30));
		wordsonTopic.setForeground(new Color(68, 86, 145));
		wordsonTopic.setHorizontalAlignment(JLabel.CENTER);
		wordsonTopic.setOpaque(true);
		topic.add(wordsonTopic, BorderLayout.CENTER); 

		button_panel = new JPanel();
		button_panel.setBackground(new Color(255, 250, 255));
		button_panel.setLayout(new GridLayout(3, 3, 5, 5));
		button_panel.setBorder(border);

		for (int i = 0; i < 9; i++) {
			button[i] = new JButton();
			button_panel.add(button[i]);
			button[i].setFont(new Font("Calibri", Font.BOLD, 120));
			button[i].setForeground(new Color(68, 86, 145));
			button[i].setFocusable(false);
			button[i].addActionListener(this);
		}
		
	 
		firstturndecision();
		
		
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setTitle("Tic Tac Toe");
		this.setVisible(true);
		this.setBackground(new Color(212, 221, 222));
		this.setLayout(new BorderLayout());
		this.add(button_panel);

		this.setLocationRelativeTo(null);
		this.add(topic, BorderLayout.NORTH);
	}
	
	private void firstturndecision() {
		if(random.nextInt(2) == 0) {
			first_player = true;
			wordsonTopic.setText("X's turn");
		}
		else {
			first_player = false;
			wordsonTopic.setText("O's turn");
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 9; i++) {
			if(e.getSource() == button[i]){
				if(button[i].getText().equals("")) {
					if(first_player) {
						button[i].setText("X");
						first_player = false;
						wordsonTopic.setText("O's turn");
					}
					else {
						button[i].setText("O");
						first_player = true;
						wordsonTopic.setText("X's turn");
					}
					check();
				}
				
				
			}
		}
	}
	
	private void check() { 
		 boolean hasWinner = false;
		 
		    if (isWinner(0, 1, 2) || isWinner(3, 4, 5) || isWinner(6, 7, 8) || 
		        isWinner(0, 3, 6) || isWinner(1, 4, 7) || isWinner(2, 5, 8) || 
		        isWinner(0, 4, 8) || isWinner(2, 4, 6)) {
		        hasWinner = true;
		    }
		    
		    if(!hasWinner) {
		    	boolean isDraw = true;
		    	for(int i = 0; i < 9; i++) {
		    		if(button[i].getText().equals("")) {
		    			isDraw = false;
		    			break;
		    		}
		    	}
		    	if(isDraw) {
		    		int response = JOptionPane.showOptionDialog(null, "It's a draw! Do you want to play again", "Game Over", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			    	if(response == JOptionPane.YES_OPTION) {
						reset();
					}
					else {
						System.exit(0);
					}
		    	}
		    }
	}
	
	public boolean isWinner(int a, int b, int c) {
		if(button[a].getText().equals(button[b].getText()) && button[b].getText().equals(button[c].getText() ) && !button[a].getText().equals("")) {
			findwinner(a, b, c);
			return true;
		}
		return false;
	}

	public void findwinner(int a, int b, int c) {
		button[a].setBackground(Color.green);
		button[b].setBackground(Color.green);
		button[c].setBackground(Color.green);
		
		for(int i = 0; i < 9; i++) {
			button[i].setEnabled(false);
		}
		
		if(first_player) {
			wordsonTopic.setText("O win");
		}
		else {
			wordsonTopic.setText("X win");
		}
		
		int response = JOptionPane.showOptionDialog(null, "Do you want to play again?", "Game over", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if(response == JOptionPane.YES_OPTION) {
			reset();
		}
		else {
			System.exit(0);
		}
	}
	
	private void reset() {
		for(int i = 0; i < 9; i++) {
			button[i].setText("");
			button[i].setBackground(null);
			button[i].setEnabled(true);
		}
	}
	

}
