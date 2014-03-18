package event;

import java.awt.TextField;
import org.pengyuan.util.Session;
import demo.FrameDemo2;

public class OnClick {
    public void onClick(){
        FrameDemo2 frame = (FrameDemo2)Session.getAttribute("frame");
        TextField input = frame.getInput();
        TextField show = frame.getShow();
        String numStr = input.getText();
        double num = Double.parseDouble(numStr);
        String result = String.valueOf(num * num);
        show.setText(result);
    }
}