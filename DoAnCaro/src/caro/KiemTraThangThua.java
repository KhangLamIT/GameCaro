/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caro;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.JButton;

/**
 *
 * @author Administrator
 */
public class KiemTraThangThua {
    private JButton cell[][];
    private String thang;
    public KiemTraThangThua(JButton cell[][], String thang){
        this.cell = cell;
        this.thang = thang;
    }
    
    public boolean ktThangThua(){
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(cell[i][j].getText() != ""){
                    //kiểm tra ngang
                    if(kiemTraNgang(i, j) == true)
                        return true;
                    
                    //kiểm tra dọc
                    if(kiemTraDoc(i, j) == true)
                        return true;
                    
                    //kiểm tra chéo
                    if(kiemTraChieo(i, j) == true)
                        return true;
                }
            }
        }
        return false;
    }
    private boolean kiemTraNgang(int i, int j) {
        int count = 0, k = 0;
        int jj = j+1;//vì kt các ô sau ô hiện tại
        //k<4 vì chỉ kiểm tra 4 ô kế tiếp ô hiện tại
        while(k<4){
            //mảng bắt đầu từ 0-19 nên nếu jj(cột) = 20 thì sẽ không kiểm tra
            if(jj >= 20)
                break;
            if(cell[i][j].getText() == cell[i][jj].getText()){
                count++;
                if(count>=4){
                    for (int x = 0; x <= 4; x++) {
                        cell[i][j+x].setFont(fontGachNgang());
                    }
                    this.thang = cell[i][j].getText();
                    return true;
                }
            }
            jj++;
            k++;
        }
        return false;
    }
    private boolean kiemTraDoc(int i, int j) {
        int count = 0, k = 0;
        int ii = i+1;//kiểm tra các ô dòng dưới
        //k<4 vì chỉ kiểm tra 4 ô kế tiếp ô hiện tại
        while(k<4){
            //mảng bắt đầu từ 0-19 nên nếu ii(dòng) = 20 thì sẽ không kiểm tra
            if(ii >= 20)
                break;
            if(cell[i][j].getText() == cell[ii][j].getText()){
                count++;
                if(count>=4){
                    for (int x = 0; x <= 4; x++) {
                        cell[i+x][j].setFont(fontGachNgang());
                    }
                    this.thang = cell[i][j].getText();
                    return true;
                }
            }
            ii++;
            k++;
        }
        return false;
    }
    private boolean kiemTraChieo(int i, int j) {
        //kiểm tra các đường chéo chính 
        int count = 0, k = 0;
        int jj = j+1, ii = i+1;
        //k<4 vì chỉ kiểm tra 4 ô kế tiếp ô hiện tại
        while(k<4){
            //mảng bắt đầu từ 0-19 nên nếu jj(cột) = 20 hoặc ii(dòng) = 20 thì sẽ không kiểm tra
            if(jj >= 20 || ii>=20)
                break;
            if(cell[i][j].getText() == cell[ii][jj].getText()){
                count++;
                if(count>=4){
                    for (int x = 0; x <= 4; x++) {
                        cell[i+x][j+x].setFont(fontGachNgang());
                    }
                    this.thang = cell[i][j].getText();
                    return true;
                }
            }
            jj++;
            ii++;
            k++;
        }
        
        //kiểm tra các đường chéo phụ
        count = 0;
        k = 0;
        jj = j-1;
        ii = i+1;
        //k<4 vì chỉ kiểm tra 4 ô kế tiếp ô hiện tại
        while(k<4){
            //mảng bắt đầu từ 0-19 nên nếu jj(cột) = 20 hoặc ii(dòng) = 20 thì sẽ không kiểm tra
            if(jj < 0 || ii>=20)
                break;
            if(cell[i][j].getText() == cell[ii][jj].getText()){
                count++;
                if(count>=4){
                    for (int x = 0; x <= 4; x++) {
                        cell[i+x][j-x].setFont(fontGachNgang());
                    }
                    this.thang = cell[i][j].getText();
                    return true;
                }
            }
            jj--;
            ii++;
            k++;
        }
        return false;
    }
    private Font fontGachNgang(){
        Font fnt = new Font("", Font.BOLD, 25);
        Map fontAttr = fnt.getAttributes();
        fontAttr.put(TextAttribute.STRIKETHROUGH,TextAttribute.STRIKETHROUGH_ON);
        Font myFont = new Font(fontAttr);
        return myFont;
    }

    /**
     * @return the thang
     */
    public String getThang() {
        return this.thang;
    }
}
