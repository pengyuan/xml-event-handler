package demo; 
import java.awt.Button; 
import java.awt.FlowLayout; 
import java.awt.Frame; 
import java.awt.TextField; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class FrameDemo extends Frame implements ActionListener{ 
	private static final long serialVersionUID = 1L;
	private TextField input; 
	private Button calculate; 
	private TextField show; 
	private void init(){ 
		input = new TextField(10); 
		calculate = new Button("square"); 
		show = new TextField(10); 
	} 
	public FrameDemo(){ 
		this.init(); 
		show.setEditable(false); 
		calculate.addActionListener(this); 
		this.setLayout(new FlowLayout()); 
		this.add(input); 
		this.add(calculate); 
		this.add(show); 
		this.setSize(300,80); 
		this.setTitle("FrameDemo"); 
		this.setVisible(true); 
	} 
	public void actionPerformed(ActionEvent event) { 
		if(event.getSource()==calculate){ 
			String numStr = input.getText(); 
			double num = Double.parseDouble(numStr); 
			String result = String.valueOf(num * num); 
			show.setText(result); 
		} 
	} 
	public static void main(String args[]){ 
		new FrameDemo(); 
	} 
}