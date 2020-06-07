package lk.janasetha.thogakade.tm;

import javafx.scene.control.Button;

public class ButtonTM extends Button {
    private ItemTM item;

    public ButtonTM(String text) {
        super(text);
    }

    public ItemTM getItem() {
        return item;
    }
    public void setItem(ItemTM item) {
        this.item = item;
    }
}
