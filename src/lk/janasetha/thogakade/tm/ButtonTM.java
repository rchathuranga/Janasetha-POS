package lk.janasetha.thogakade.tm;

import javafx.scene.control.Button;
import lk.janasetha.thogakade.dto.QueryDTO;

public class ButtonTM extends Button {
    private QueryDTO item;

    public ButtonTM(String text) {
        super(text);
    }

    public QueryDTO getItem() {
        return item;
    }

    public void setItem(QueryDTO item) {
        this.item = item;
    }
}
