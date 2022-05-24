/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Caro_Gui extends JDialog{    
    private JMenu mnuThoat, mnuVanMoi;
    private static int n = 20;
    private JButton cell[][] = new JButton[n][n];
    private ArrayList<JButton> arrDanhLai = new ArrayList<>();
    private ArrayList<JButton> arrDanhTiep = new ArrayList<>();
    private JButton btnDiLai, btnDiTiep;
    private ImagePanel pnBC;
    //thang = false: chưa có người chiến thắng
    //kiemTraDanh = false: Tới lượt X đánh
    //kiemTraDanh = true: Tới lượt O đánh
    //cheDoChoi = true: nguoi voi nguoi
    //cheDoChoi = false: nguoi voi may
    private boolean thang = false, kiemTraDanh = false, cheDoChoi;
    
    public Caro_Gui(Frame parent, boolean modal, boolean cheDoChoi){
        super(parent, modal);
        this.cheDoChoi = cheDoChoi;
        Caro_GUI();
        XuLySuKien();
    }
    
    public void setCheDoChoi(boolean cheDoChoi) {
        this.cheDoChoi = cheDoChoi;
    }
    
    private void Caro_GUI() {
        this.setSize(610, 665);
        this.setLocationRelativeTo(null);
        this.setTitle("Game Caro");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        
        addMenuBar();
        addpnBanCo();
    }
    
    private void addMenuBar() {
        //tạo JMenuBar
        JMenuBar mnuCaro = new JMenuBar();
        //tạo 2 menu để chọn
        mnuVanMoi = new JMenu("Ván mới");
        mnuThoat = new JMenu("Thoát");
        //thêm 2 menu vào JMenuBar
        mnuCaro.add(mnuVanMoi);
        mnuCaro.add(mnuThoat);
        //thêm JMenuBar vào frame
        this.setJMenuBar(mnuCaro);
    }
    
    private void addpnBanCo() {
        File file = new File("");
        //lấy đường dẫn hiện tại của file
        String currentDirectory = file.getAbsolutePath();
        pnBC = new ImagePanel(currentDirectory+"\\src\\caro\\Image\\default.png");
        pnBC.setLayout(new BorderLayout());
        JPanel pnBanCo = new JPanel();
        pnBanCo.setOpaque(false);
        
        pnBanCo.setLayout(new GridLayout(20,20));
        pnBanCo.setPreferredSize(new Dimension(600, 600));
        
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++){
                //thêm btn vào cell
                cell[i][j] = createButton(i,j);
                //thêm cell vào bàn cờ
                pnBanCo.add(cell[i][j]);
            }
        }        
        if(cheDoChoi == false){
            JPanel pnXuLy = new JPanel();
            btnDiLai = new JButton("<< Đi lại");
            JPanel pnkhoangCach = new JPanel();
            btnDiTiep = new JButton("Đi tiếp >>");

            pnkhoangCach.setPreferredSize(new Dimension(50, 27));
            btnDiLai.setPreferredSize(new Dimension(110, 27));
            btnDiTiep.setPreferredSize(new Dimension(110, 27));

            btnDiLai.setEnabled(false);
            btnDiTiep.setEnabled(false);
            btnDiLai.setOpaque(false);
            btnDiLai.setContentAreaFilled(false);
            pnkhoangCach.setOpaque(false);
            btnDiTiep.setOpaque(false);
            btnDiTiep.setContentAreaFilled(false);
            pnXuLy.setOpaque(false);

            pnXuLy.add(btnDiLai);
            pnXuLy.add(pnkhoangCach);
            pnXuLy.add(btnDiTiep);
            pnBC.add(pnXuLy, BorderLayout.SOUTH);
        }
        pnBC.add(pnBanCo, BorderLayout.CENTER);
        this.add(pnBC);
    }
    
    private JButton createButton(int i, int j) {
        //tạo btn
        JButton btn = new JButton("");
        //cài đặt khoảng cách của text đối với viền
        btn.setMargin(new Insets(0, 0, 0, 0));
        //cài đặt font chữ
        btn.setFont(new Font("", Font.BOLD, 25));
        //cài đặt viền
        btn.setBorder(BorderFactory.createLineBorder(Color.black));
        //làm trong suốt button
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        //xử lý mỗi người là mỗi lượt đánh và kèm xử lý thắng thua
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyClickButton(i,j);
            }
        });
        return btn;
    }
    
    private void xuLyClickButton(int i, int j) {
        if(cheDoChoi == true)
            nguoiVSNguoi(i,j);
        else
            nguoiVSMay(i,j);
    }
    
    private void XuLySuKien() {
        mnuThoat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int tl = JOptionPane.showConfirmDialog(null,"Bạn Có Muốn Thoát Chương Trình?", "Thoát", JOptionPane.YES_NO_OPTION);
                if(tl == JOptionPane.YES_OPTION)
                {
                    setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        mnuVanMoi.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clear();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                }

            @Override
            public void mouseReleased(MouseEvent e) {
                }

            @Override
            public void mouseEntered(MouseEvent e) {
                }

            @Override
            public void mouseExited(MouseEvent e) {
                }
        });
        if(cheDoChoi == false){
        btnDiLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thang = false;
                kiemTraDanh = false;
                if(arrDanhLai.isEmpty() == false){
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if(cell[i][j].getText()!=""){
                                //cài đặt lại font, nếu không cài đặt sẽ có dấu gạch ngang giữa chữ
                                cell[i][j].setFont(new Font("", Font.BOLD, 25));
                            }
                        }
                    }
                    //nếu O là lần đánh cuối cùng thì xóa 2 quân cờ, ngược lại xóa 1 quân
                    if(arrDanhLai.get(arrDanhLai.size()-1).getText() == "O"){
                        //thêm quân cờ sắp xóa vào arrDanhTiep
                        arrDanhTiep.add(arrDanhLai.get(arrDanhLai.size()-1));
                        //xóa quân cờ
                        arrDanhLai.get(arrDanhLai.size()-1).setText("");
                        arrDanhLai.remove(arrDanhLai.size()-1);
                        
                        //thêm quân cờ sắp xóa vào arrDanhTiep
                        arrDanhTiep.add(arrDanhLai.get(arrDanhLai.size()-1));
                        //xóa quân cờ
                        arrDanhLai.get(arrDanhLai.size()-1).setText("");
                        arrDanhLai.remove(arrDanhLai.size()-1);
                    }
                    else{
                        //thêm quân cờ sắp xóa vào arrDanhTiep
                        arrDanhTiep.add(arrDanhLai.get(arrDanhLai.size()-1));
                        //xóa quân cờ
                        arrDanhLai.get(arrDanhLai.size()-1).setText("");
                        arrDanhLai.remove(arrDanhLai.size()-1);
                    }
                    btnDiTiep.setEnabled(true);
                }
                if(arrDanhLai.isEmpty() == true){
                    btnDiLai.setEnabled(false);
                }
            }
        });
        
        btnDiTiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(arrDanhTiep.isEmpty() == false){
                    //thêm lại quân cờ
                    if(arrDanhTiep.size()%2==0){
                        //thêm lại quân cờ X đã đánh trước đó vào bàn cờ
                        arrDanhTiep.get(arrDanhTiep.size()-1).setText("X");
                        //thêm vào arrDanhLai
                        arrDanhLai.add(arrDanhTiep.get(arrDanhTiep.size()-1));
                        //xóa quân cờ khỏi arrDanhTiep
                        arrDanhTiep.remove(arrDanhTiep.size()-1);
                        
                        //thêm lại quân cờ O đã đánh trước đó vào bàn cờ
                        arrDanhTiep.get(arrDanhTiep.size()-1).setText("O");
                        arrDanhLai.add(arrDanhTiep.get(arrDanhTiep.size()-1));
                        arrDanhTiep.remove(arrDanhTiep.size()-1);
                        showNguoiThang(cell,"O");
                    }
                    else{
                        //thêm lại quân cờ X đã đánh trước đó vào bàn cờ
                        arrDanhTiep.get(arrDanhTiep.size()-1).setText("X");
                        arrDanhLai.add(arrDanhTiep.get(arrDanhTiep.size()-1));
                        arrDanhTiep.remove(arrDanhTiep.size()-1);
                        showNguoiThang(cell,"X");
                    }
                    btnDiLai.setEnabled(true);
                }
                if(arrDanhTiep.isEmpty() == true)
                {
                    btnDiTiep.setEnabled(false);
                }
            }
        });
        }
    }

    private void nguoiVSNguoi(int i, int j) {        
        //nếu text là trống thì được quyền đánh và kiemTraDanh = false thì cho X đánh
        // và chưa có ai thắng thì được quyền đánh
        if(cell[i][j].getText()==""&& kiemTraDanh == false && thang == false){
            cell[i][j].setText("X");
            cell[i][j].setForeground(Color.BLUE);
            kiemTraDanh = true;
            showNguoiThang(cell,"X");
        }
        if(cell[i][j].getText()==""&& kiemTraDanh == true && thang == false){
            cell[i][j].setText("O");
            cell[i][j].setForeground(Color.RED);
            kiemTraDanh = false;
            showNguoiThang(cell,"O");
        }        
    }

    private void nguoiVSMay(int i, int j) {
        //dùng biến để kiểm tra xem người chơi đã đánh hay chưa
        //nếu đánh rồi thì cho máy đánh tiếp, ngược lại ko cho máy đánh
        if(cell[i][j].getText()==""&& kiemTraDanh == false && thang == false){
            cell[i][j].setText("X");
            cell[i][j].setForeground(Color.BLUE);
            arrDanhLai.add(cell[i][j]);
            kiemTraDanh = true;
            showNguoiThang(cell,"X");
        }
        if(kiemTraDanh == true && thang == false){
            AI ai = new AI(cell);
            int nextMoveX = 0 , nextMoveY = 0;
            int [] bestMove = ai.AI_TimDiemDanh(3);

            if (bestMove != null) {
                    nextMoveX = bestMove[0];
                    nextMoveY = bestMove[1];
            }

            cell[nextMoveX][nextMoveY].setText("O");
            cell[nextMoveX][nextMoveY].setForeground(Color.RED);  
            arrDanhLai.add(cell[nextMoveX][nextMoveY]);

            kiemTraDanh = false;
            showNguoiThang(cell,"O");
        }
        btnDiLai.setEnabled(true);
        
        arrDanhTiep.clear();
        btnDiTiep.setEnabled(false);
    }

    private void showNguoiThang(JButton[][] cell, String nguoiThang) {
        KiemTraThangThua thangThua = new KiemTraThangThua(cell, nguoiThang);
        if (thangThua.ktThangThua()==true) {
            thang = true;
            JOptionPane.showMessageDialog(null, nguoiThang + " đã chiến thắng!",
            "Kết Quả", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        //kiểm tra hòa
        int kt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(cell[i][j].getText() == ""){
                    kt++;
                    break;
                }
            }
            if(kt>0)
                break;
        }
        if (kt==0) {
            JOptionPane.showMessageDialog(null, "Hòa nhau rồi!",
            "Kết Quả", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
    public void clear(){
        thang = false;
        kiemTraDanh = false;
        if(cheDoChoi == false){
            arrDanhLai.clear();
            arrDanhTiep.clear();
            btnDiTiep.setEnabled(false);
            btnDiLai.setEnabled(false);
        }
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if(cell[i][j].getText()!=""){
                    cell[i][j].setText("");
                    //cài đặt lại font, nếu không cài đặt sẽ có dấu gạch ngang giữa chữ
                    cell[i][j].setFont(new Font("", Font.BOLD, 25));
                }
            }
        }
    }
}