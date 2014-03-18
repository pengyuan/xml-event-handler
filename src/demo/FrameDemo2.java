package demo;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextField;

import org.pengyuan.util.Session;
import org.pengyuan.util.XMLReader;

public class FrameDemo2 extends Frame{
	private static final long serialVersionUID = 1L;
	private TextField input = new TextField(10);
    private Button calculate = new Button("square");
    private TextField show = new TextField(10);

    public TextField getInput() {
        return input;
    }

    public void setInput(TextField input) {
        this.input = input;
    }

    public Button getCalculate() {
        return calculate;
    }

    public void setCalculate(Button calculate) {
        this.calculate = calculate;
    }

    public TextField getShow() {
        return show;
    }

    public void setShow(TextField show) {
        this.show = show;
    }

    public FrameDemo2(){
        show.setEditable(false);
        this.setLayout(new FlowLayout());
        this.add(input);
        this.add(calculate);
        this.add(show);
        this.setSize(300,80);
        this.setTitle("FrameDemo2");
        this.setVisible(true);
    }

    public static void main(String args[]){
        XMLReader xr = new XMLReader("src/demo/event-config.xml");
        FrameDemo2 frame = (FrameDemo2)xr.getBean("frame");
        Session.setAttribute("frame", frame);
    }
}