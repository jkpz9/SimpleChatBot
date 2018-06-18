import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatBot extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	
	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(20,50);
	JTextArea input = new JTextArea(1, 50);
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	
	String[][] chatBot = {
	  // standard greeting
			{"hi", "hello", "hey", "hi there", "hola"},
			{"hi", "hello", "hey"},
	  // question greetings
			{"how are you", "how r you", "how r u", "how are u"},
			{"good", "doing well"},
	  //
			{"yes"},
			{"no", "NO", "NO!!!!!!"},
	  // default
			{"shut up","you're bad","noob","stop talking",
			"(michael is unavailable, due to LOL)"}
	};
	
	
	public ChatBot()
	{
		super("Chat Bot");
		super.setSize(600, 400);
		super.setResizable(false);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		dialog.setEditable(false);
		input.addKeyListener(this);
		
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(52,152,219));
		
		super.add(p);
		
		super.setLocationRelativeTo(null);
		
		super.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ChatBot();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);
			
			String speech = input.getText();
			input.setText("");
			addText("\n-->You:\t" + speech);	
			
			speech = speech.trim();
			
			int length = speech.length();
			
			while(length> 0 && (speech.charAt(length-1) == '!' || speech.charAt(length-1) == '.' || speech.charAt(length-1) == '?'))
			{
					speech = speech.substring(0, length-1);
					length = speech.length();
			}
			
			if (length < 1) return;
			
			byte response = 0;
			
			int j = 0;
			
			while(response == 0) {
				if (inArray(speech, chatBot[j*2]))
				{
					response  = 2;
					
					int r = (int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					
					addText("\n-->Bot\t" + chatBot[(j*2)+1][r]);
				}
				
				j++;
				
				if (j*2 == chatBot.length - 1 && response == 0) {
					response = 1;
				}
				
				if (response == 1) {
					int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
					addText("\n-->Bot\t"+chatBot[chatBot.length-1][r]);
				}
				
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			input.setEditable(true);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public void addText(String str)
	{
		dialog.setText(dialog.getText() + str);
	}
	
	public boolean inArray(String target, String [] src)
	{
		for(int i=0; i<src.length; i++)
		{
			if (src[i].equals(target.toLowerCase()))
					return true;
		}
		
		return false;
	}

}
