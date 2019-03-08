package Views;

import Logic.Controls;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class games extends JPanel implements ActionListener {

    JFrame jf;
    private Container containPane;
    ImageIcon cardIcon[];
    int newgamecontrol = 1;
    JMenuItem a1, a2, a3, a4, a5, a6;

    public games() {
        jf = new JFrame("Eşleştirme Oyunu");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(500, 600);
        jf.setLayout(null);
        containPane = jf.getContentPane();
        containPane.setLayout(new BoxLayout(this.containPane, BoxLayout.PAGE_AXIS));

        JMenuBar menuBar = new JMenuBar();
        jf.setJMenuBar(menuBar);

        JMenu menu = new JMenu("Oyun");
        newMenuItem("Yeni Oyun", menu, this);
        newMenuItem("Çıkış", menu, this);

        JMenu settings = new JMenu("Ayarlar");

        JMenu computer = new JMenu("Bilgisayar");
        settings.add(computer);
        a1 = newMenuItem("4*4", computer, this);
        a2 = newMenuItem("6*4", computer, this);

        JMenu animal = new JMenu("Hayvan");
        settings.add(animal);
        a3 = newMenuItem("4*4", animal, this);
        a4 = newMenuItem("6*4", animal, this);

        JMenu emoji = new JMenu("Emoji");
        settings.add(emoji);
        a5 = newMenuItem("4*4", emoji, this);
        a6 = newMenuItem("6*4", emoji, this);

        menuBar.add(menu);
        menuBar.add(settings);

        JMenu aboutmenu = new JMenu("Hakkında");
        menuBar.add(aboutmenu);
        newMenuItem("Hakkında", aboutmenu, this);
        newMenuItem("Yardım", aboutmenu, this);
        cardIcon = loadCardIcon(0);
    }

    private JMenuItem newMenuItem(String yeni_Oyun, JMenu menu, ActionListener listener) {
        JMenuItem newItem = new JMenuItem(yeni_Oyun);
        newItem.setActionCommand(yeni_Oyun);
        newItem.addActionListener(listener);
        menu.add(newItem);
        return newItem;
    }

    public void newGame() {
        containPane.removeAll();//kart tekrarını onleme.
        containPane.add(makeCards(4, 4));
        jf.setSize(500, 600);
        jf.setVisible(true);

        newgamecontrol = 0;
    }

    public void newGame2() {
        containPane.removeAll();//kart tekrarını onleme.
        containPane.add(makeCards(6, 4));
        jf.setSize(600, 800);
        jf.setVisible(true);
        newgamecontrol = 1;
    }

    private ImageIcon[] loadCardIcon(int a) {
        ImageIcon[] icon = new ImageIcon[9];
        for (int i = 0; i < 8; i++) {
            ImageIcon image = new ImageIcon(getClass().getResource("/resimler/" + (i + a) + ".png"));
            icon[i] = image;
        }
        icon[8] = new ImageIcon(getClass().getResource("/resimler/" + (a + 12) + ".png"));
        return icon;
    }//4*4 için kart iconları 

    private ImageIcon[] loadCardIcon2(int a) {
        ImageIcon[] icon = new ImageIcon[13];
        for (int i = 0; i < 13; i++) {
            ImageIcon image = new ImageIcon(getClass().getResource("/resimler/" + (i + a) + ".png"));
            icon[i] = image;
        }
        return icon;
    }//6*4 için kart iconları

    public void abouts() {
        JOptionPane.showMessageDialog(null, "Hazırlayanlar:\n-Furkan Çetin\n-Orhun Yıldız");
    }

    public void help() {
        JOptionPane.showMessageDialog(null, "Kartların Eşini Bulun");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Yeni Oyun")) {
            if (newgamecontrol == 1) {
                newGame2();
            } else {
                newGame();
            }
        }
        if (e.getActionCommand().equals("Çıkış")) {
            System.exit(0);
        }
        if (e.getActionCommand().equals("Hakkında")) {
            abouts();
        }
        if (e.getActionCommand().equals("Yardım")) {
            help();
        }
        if (e.getSource() == a1) {
            cardIcon = loadCardIcon(0);
            newGame();
        }
        if (e.getSource() == a2) {
            cardIcon = loadCardIcon2(0);
            newGame2();
        }
        if (e.getSource() == a3) {
            cardIcon = loadCardIcon(13);
            newGame();
        }
        if (e.getSource() == a4) {
            cardIcon = loadCardIcon2(13);
            newGame2();
        }
        if (e.getSource() == a5) {
            cardIcon = loadCardIcon(26);
            newGame();
        }
        if (e.getSource() == a6) {
            cardIcon = loadCardIcon2(26);
            newGame2();
        }
    }

    public JPanel makeCards(int a, int b) {
        JPanel jp = new JPanel(new GridLayout(a, b));//4,4
        ImageIcon backIcon = cardIcon[2 * a];//8
        int[] cardsToAdd = new int[4 * a];//16
        Controls controls = new Controls();
        for (int i = 0; i < 2 * a; i++) {//8
            cardsToAdd[2 * i] = i;
            cardsToAdd[2 * i + 1] = i;
        }//numara ekleme
        randomizeCardArray(cardsToAdd);
        for (int i = 0; i < cardsToAdd.length; i++) {
            int num = cardsToAdd[i];
            Card newCard = new Card(controls, cardIcon[num], backIcon, num);
            jp.add(newCard);
        }
        return jp;
    }
    
    private void randomizeCardArray(int[] cardsToAdd) {

        Random r = new Random();

        for (int i = 0; i < cardsToAdd.length; i++) {
            int d = r.nextInt(cardsToAdd.length);
            int s = cardsToAdd[d];
            cardsToAdd[d] = cardsToAdd[i];
            cardsToAdd[i] = s;
        }
    }
}
