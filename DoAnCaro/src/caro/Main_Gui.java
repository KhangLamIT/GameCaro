/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class Main_Gui extends JFrame{
    private JButton btnNguoiVsNguoi, btnNguoiVsMay, btnHuongDan, btnVeChungToi, btnAmThanh, btnThoat;
    //kiểm tra xem âm thanh đã bật hay chưa
    private boolean amThanh;
    private Music music;
    private Caro_Gui caro_Gui_NguoiVsMay, caro_Gui_NguoiVsNguoi;
    
    public Main_Gui(){
        caro_Gui_NguoiVsMay = new Caro_Gui(this, true, false);
        caro_Gui_NguoiVsNguoi = new Caro_Gui(this, true, true);
        Main_GUI();
        events();
    }
    
    private void Main_GUI() {
        this.setTitle("Game Caro");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        File file = new File("");
        String currentDirectory = file.getAbsolutePath();
        
        music = new Music(currentDirectory + "\\src\\caro\\Music\\Holly Dazed - RKVC.wav");
        
        ImagePanel pnBackground = new ImagePanel();
        pnBackground.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        pnBackground = new ImagePanel(currentDirectory+"\\src\\caro\\Image\\background_MainGui.jpg");
        
        ImagePanel pnLogo = new ImagePanel();
        pnLogo = new ImagePanel(currentDirectory+"\\src\\caro\\Image\\caro_Logo.png");
        pnLogo.setPreferredSize(new Dimension(200,150));
        pnBackground.add(pnLogo);
        
        //khởi tạo btn
        btnNguoiVsNguoi = createButton("Người vs Người");
        btnNguoiVsMay = createButton("Người vs Máy");
        btnHuongDan = createButton("Hướng Dẫn");
        btnVeChungToi = createButton("Về Chúng Tôi");
        btnAmThanh = createButton("Âm Thanh");
        btnThoat = createButton("Thoát");
        
        if(amThanh == true){
            btnAmThanh.setForeground(Color.blue);
            btnAmThanh.setBackground(Color.white);
            btnAmThanh.setBorder(BorderFactory.createLineBorder(Color.blue));
        }
        
        JPanel pnNguoiVsNguoi = new JPanel();  
        pnNguoiVsNguoi.setOpaque(false);
        btnNguoiVsNguoi.setPreferredSize(new Dimension(210, 35));
        pnNguoiVsNguoi.add(btnNguoiVsNguoi);
        pnBackground.add(pnNguoiVsNguoi);
        
        JPanel pnNguoiVsMay = new JPanel();
        pnNguoiVsMay.setOpaque(false);
        btnNguoiVsMay.setPreferredSize(new Dimension(210, 35));
        pnNguoiVsMay.add(btnNguoiVsMay);
        pnBackground.add(pnNguoiVsMay);
        
        JPanel pnThongTin = new JPanel();
        btnHuongDan.setPreferredSize(new Dimension(103, 26));
        btnVeChungToi.setPreferredSize(new Dimension(103, 26));
        pnThongTin.setOpaque(false);
        pnThongTin.add(btnHuongDan);
        pnThongTin.add(btnVeChungToi);
        pnBackground.add(pnThongTin);
        
        JPanel pnCaiDat = new JPanel();
        pnCaiDat.setOpaque(false);
        btnAmThanh.setPreferredSize(new Dimension(btnHuongDan.getPreferredSize().width, btnHuongDan.getPreferredSize().height));
        pnCaiDat.add(btnAmThanh);
        btnThoat.setPreferredSize(new Dimension(btnVeChungToi.getPreferredSize().width, btnVeChungToi.getPreferredSize().height));
        pnCaiDat.add(btnThoat);
        pnBackground.add(pnCaiDat);
        
        this.add(pnBackground);
    }

    private JButton createButton(String tieuDe) {
        JButton btn = new JButton(tieuDe);
        btn.setForeground(Color.red);
        btn.setBackground(Color.white);
        btn.setBorder(BorderFactory.createLineBorder(Color.red));
        return btn;
    }

    private void events() {
        btnHuongDan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Trên bàn cờ 20x20 ô vuông. Một người đi X, một người đi O.\n" +
                                "Khi đến lượt mình, người chơi phải tích vào một ô trên bàn cờ.\n"
                                + "Người chơi phải tìm cách tích đủ 5 ô liên tiếp theo chiều dọc \n"
                                + " hoặc chiều ngang hoặc đường chéo thì sẽ thắng.",
                                "Hướng dẫn", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tl = JOptionPane.showConfirmDialog(null,"Bạn Có Muốn Thoát Chương Trình?", "Thoát", JOptionPane.YES_NO_OPTION);
                if(tl == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        
        btnVeChungToi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Lâm Chí Khang - mssv: 2001190592\n" 
                                                    +"Y Don Rbăm - mssv: 2001190776\n"
                                                    +"Huỳnh Lê Công Thành - mssv: 2001190815",
                                                            "Giới Thiệu", JOptionPane.PLAIN_MESSAGE);
            }
        });
        
        btnAmThanh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(amThanh == false){
                    amThanh = true;
                    btnAmThanh.setForeground(Color.blue);
                    btnAmThanh.setBackground(Color.white);
                    btnAmThanh.setBorder(BorderFactory.createLineBorder(Color.blue));
                    try {
                        music.player_start();
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Main_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Main_Gui.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    amThanh = false;
                    music.player_stop();
                    
                    btnAmThanh.setForeground(Color.red);
                    btnAmThanh.setBackground(Color.white);
                    btnAmThanh.setBorder(BorderFactory.createLineBorder(Color.red));
                }
                
            }
        });
        
        btnNguoiVsNguoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caro_Gui_NguoiVsNguoi.clear();
                caro_Gui_NguoiVsNguoi.setVisible(true);
            }
        });
        
        btnNguoiVsMay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                caro_Gui_NguoiVsMay.clear();
                caro_Gui_NguoiVsMay.setVisible(true);
            }
        });
    }

}
