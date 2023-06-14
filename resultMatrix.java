import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;

public class resultMatrix {
    private JPanel panel1;
    private JPanel panelGeneral;
    resultMatrix(int sizex,int sizey,int [][]arr,int []xy){
        createShape(arr,sizex,sizey,xy);
    }
    void createShape(int [][]arr,int sizex,int sizey,int []xy){
        panelGeneral.setPreferredSize(new Dimension(sizex,sizey));
        panelGeneral.setUI(new PanelUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                int x1=0,x2=0,y1=0,y2=0;
                for (int i = 0; i < sizex; i += 1) {
                    for (int j = 0; j < sizey; j += 1) {
                        if (arr[i][j]==1) {
                            if((i==xy[0]&&j == xy[1]))
                            {
                                g.setColor(Color.red);
                                x1=j*18;
                                y1=i*18;
                            }
                            else if((i==xy[2]&&j == xy[3])){
                                g.setColor(Color.red);
                                x2=j*18;
                                y2=i*18;
                            }
                            else
                                g.setColor(Color.blue);
                            g.fillRect(j*18, i*18, 20*sizex, 20*sizey);
                        } else {
                            g.setColor(Color.gray);
                            g.fillRect(j*18, i*18,  20*sizex, 20*sizey);
                        }
                    }
                }
                g.setColor(Color.GREEN);
                g.drawLine(x1,y1,x2,y2);
                super.paint(g, c);
            }
        });
    
    }
    public JPanel getpanel(){
        return panel1;
    }
}
