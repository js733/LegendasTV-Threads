package testethread;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author z
 */
public class TesteThread {
	
    public static void exemplo(){
        JFrame frame = new JFrame();
        ImageIcon icone = new ImageIcon("exemplo.jpg");
        JLabel imgLabel = new JLabel(icone);
        frame.add(imgLabel);
        frame.setTitle("EXEMPLO");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
	
	


    public static void main(String[] args) {

	String ano, mes, dia, hora, minuto, data;
        String[] testeLegenda = new String[60];
        
	exemplo();
        hora = JOptionPane.showInputDialog("Insira a hora em que a legenda foi postada");
        minuto = JOptionPane.showInputDialog("Insira o minuto em que a legenda foi postada");        
        dia = JOptionPane.showInputDialog("Insira o dia em que a legenda foi postada");
        mes = JOptionPane.showInputDialog("Insira o mes em que a legenda foi postada");
        ano = JOptionPane.showInputDialog("Insira o ano em que a legenda foi postada");

        System.out.println("Buscando primeira legenda com essas caracteristicas...\n\n[" + hora + ":" + minuto + " - " + dia + "/" + mes + "/" + ano + "]");

	
        for(int i=0;i<10;i++){
            data = ano+mes+dia+hora+minuto+0+i;
            testeLegenda[i] = "http://f.legendas.tv/l/legendas_tv_"+data+".rar";
        }
        for(int i=10;i<60;i++){
            data = ano+mes+dia+hora+minuto+i;
            testeLegenda[i] = "http://f.legendas.tv/l/legendas_tv_"+data+".rar";
        }

        for(int j=0;j<60;j+=20){
            for(int i=j;i<j+20;i++){
                MultiT proc = new MultiT(testeLegenda[i]);
                new Thread(proc).start();
            }
            
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ex){
                Logger.getLogger(MultiT.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

    }
}