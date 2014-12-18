package testethread;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 *
 * @author z
 */
public class MultiT implements Runnable {
    
    private String urlT, id, nome="leg";

    public MultiT(String urlT, String id) {
        this.urlT = urlT;
        this.id = id;
    }
    
    public String getUrlT(){
        return this.urlT;
    }
    
    
    
    public boolean checaLink(String urlString) throws MalformedURLException, IOException {

        URL u = new URL(urlString);
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection();
        huc.setRequestMethod("HEAD");
        
   
        huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
        huc.connect();
        if(huc.getResponseCode() == HttpURLConnection.HTTP_OK){
            System.out.print("\nLink encontrado! Baixando...");
            nome = u.getFile().substring(3, 29);
            return true;
        }else{
            System.out.print("...");
            huc.disconnect();
            return false;
        }
    }

    public void tenta(String hue, String id){

        System.out.print("\nVerificando o link: " + hue);
        boolean wot=false;
        try{
            wot = checaLink(hue);
        }catch(IOException vish){
           System.out.println(vish);
           //System.exit(1);
        }
        if(wot){
            try{
                URL link = new URL(hue);
                InputStream in = new BufferedInputStream(link.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                
                while (-1!=(n=in.read(buf))){
                       out.write(buf, 0, n);
                       System.out.print(".");
                }
                
                out.close();
                in.close();
                
                byte[] response = out.toByteArray();

                FileOutputStream fos = new FileOutputStream(nome);
                fos.write(response);
                fos.close();
                JOptionPane.showMessageDialog(null,"\nLegenda " + nome + " baixada. Obrigado");
                
                //System.exit(0);
            }catch(IOException exce){
                   System.out.println(exce);
            }
        }

    }

    @Override
    public void run() {
        tenta(getUrlT(),this.id);
    }
}
