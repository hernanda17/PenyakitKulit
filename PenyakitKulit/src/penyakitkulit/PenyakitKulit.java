/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package penyakitkulit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Hernanda
 */
public class PenyakitKulit {

    private static ArrayList <String[]> keterangan = new ArrayList<String[]>();
    private static ArrayList <String[]> tree = new ArrayList<String[]>();
    private static ArrayList <String[]> jalur = new ArrayList<String[]>();
    public void run(){
        getdata();
        //mulai pertanyaan
        boolean akhir=false;
        int i = 0;
        int simpan=1;
        while(!akhir)
        {
             try{
                String[] d=cari(String.valueOf(simpan));
                System.out.println(d[0]);
                if(!d[2].equalsIgnoreCase("e"))
                {
                    int simpan2 = cariKet(String.valueOf(d[0]));
                    String [] ket= keterangan.get(simpan2)[1].split("[|]");
                    int res2 = JOptionPane.showConfirmDialog (null,ket[0] +" ?","Konfrimasi",0);  
                    if(res2==0)
                    {
                       jalur.add(ket);
                       simpan = Integer.valueOf(d[1]);
                    }else
                    {
                       simpan = Integer.valueOf(d[2]);
                    }
                }else
                {
                    int simpan2 = cariKet(String.valueOf(d[0]));
                  //  System.out.println("");
                    String penyakit;
                    if(simpan2!=100)
                       penyakit = keterangan.get(simpan2)[1];
                    else
                       penyakit= "Tidak Diketahui";
                    frame.txtPenyakit.setText(penyakit);
                    akhir =true;
                }
             }catch(NullPointerException ex)
             {
                 System.out.println("err");
                 akhir = true;
             }
        }
        // Hitung Jalur
        float infrensi=0;
        for(int l = 0; l<jalur.size();l++)
        {
            
            System.out.print(jalur.get(l)[0]+" ");
            System.out.println(Float.valueOf(jalur.get(l)[1]));
            frame.txtGejala.append(jalur.get(l)[0]+"\n");
            infrensi = (infrensi+Float.valueOf(jalur.get(l)[1]));
        }
        infrensi = infrensi/jalur.size();
        System.out.println("Hasil "+infrensi);
        infrensi = infrensi*100;
        frame.txtProb.setText(String.valueOf(infrensi)+"%");
    }
    
    public static String[] cari(String cari)
    {
        
        for(int i=0;i<tree.size();i++)
        {
            if(tree.get(i)[0].equalsIgnoreCase(cari))
            {
               return tree.get(i);
            }
        }
        return null;
    }
    
     public static int cariKet(String cari)
    {
        
        for(int i=0;i<keterangan.size();i++)
        {
            if(keterangan.get(i)[0].equalsIgnoreCase(cari))
            {
               return i;
            }
        }
        return 100;
    }
    
    public static void getdata()
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("config.txt"));
            try {
                String line = br.readLine();
                String [] split;
                int i = 0;
                do{
                    //System.out.println(line);
                    split = line.split(":");
                    keterangan.add(split);
                    line = br.readLine();
                }
                while (line != null&& !line.equalsIgnoreCase("end")) ;
                line = br.readLine();
                do{
                    //System.out.println(line);
                    split = line.split("[|]");
                   // System.out.println(split[1]);
                    tree.add(split);
                    line = br.readLine();
                }
                while (line != null&& !line.equalsIgnoreCase("end")) ;
            } catch (IOException ex) {
                 Logger.getLogger(PenyakitKulit.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(PenyakitKulit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PenyakitKulit.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(PenyakitKulit.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
