/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ImagePanel extends JPanel{
    private Image img;

    public ImagePanel(){
        
    }
    public ImagePanel(String img) {
      this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
      this.img = img;
    }

    public void paintComponent(Graphics g) {
      g.drawImage(img, 0, 0, null);
    }
}
