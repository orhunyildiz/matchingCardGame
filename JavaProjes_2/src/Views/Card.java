package Views;

import Logic.Controls;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card extends JLabel implements MouseListener {

    Icon faceIcon;
    Icon backIcon;
    boolean faceUp = false;
    int num;
    int iconWidthHalf, iconHeightHalf;
    boolean mousePressedOnMe = false;
    private final Controls controls;

    public Card(Controls controls, Icon face, Icon back, int num) {
        super(back);
        this.faceIcon = face;
        this.backIcon = back;
        this.num = num;
        this.addMouseListener(this);
        this.iconHeightHalf = back.getIconHeight() / 2;
        this.iconWidthHalf = face.getIconWidth() / 2;
        this.controls = controls;
    }

    public int getNum() {
        return num;
    }

    private boolean overIcon(int x, int y) {

        int distX = Math.abs(x - getWidth() / 2);
        int distY = Math.abs(y - getHeight() / 2);

        if (distX > iconHeightHalf || distY > iconWidthHalf) {
            return false;
        }

        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (overIcon(e.getX(), e.getY())) {
            turnUp();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (overIcon(e.getX(), e.getY())) {
            mousePressedOnMe = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.mousePressedOnMe) {
            mousePressedOnMe = false;
            mouseClicked(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePressedOnMe = false;
    }

    private void turnUp() {
        if (faceUp) {
            return;
        }
        faceUp = false; // kartlara basıldıgı zaman ters cevırır.
        faceUp = controls.turnUp(this);
        if (faceUp) {
            setIcon(faceIcon);
        }
    }

    public void turnDown() {
        if (!faceUp) {
            return;
        }
        setIcon(backIcon);
        faceUp = false;
    }
}
