package edu.upc.eseiaat.pma.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DjManko on 9/10/17.
 */

public class WebReader {
    public static String getUrl(String surl){
        String error = "Error: ";
        try {
            URL url = new URL(surl);    //hacer catch
            //  HttpURLConnection conn = url.openConnection(); -> hacer cast después del catch
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //crear conexión a URL
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) { //ver si no se ha podido conectar al URL
                return error + conn.getResponseMessage();
            }
            InputStream in = conn.getInputStream();   //inputstream=flujo de in, hemos de ponerlo en
            // flujo out asociado a byte y ahí coger el string
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            copyBytes(in,out);  //crear método.
            out.close();       //primero hay que cerrar el flujo

            return out.toString();
        } catch (MalformedURLException e) {
            return error + e.toString(); // devuelve el error convertido en string.
        } catch (IOException e) {
            return error + e.toString();
        }
    }

    private static void copyBytes(InputStream in, ByteArrayOutputStream out) throws IOException { //privado porque fuera no tiene sentido
        byte[] bytes = new byte[1024];  //leemos de 1024 en 1024 y lo vamos pasando al array
        int nbytes = in.read(bytes);  //para ir leyendo, nos dirá cuantos hemos leído, añadir excepció
        //porque irá arriba y arriba saltará el error
        while (nbytes > 0){
            out.write(bytes,0,nbytes);// el byte b , int off, int len porque este escribe lo que lea, si solo es byte
            // lee 1024 y no siempre tendremos 1024 by.
            nbytes = in.read(bytes);    //actualizar cuantos bytes hemos leído
        }
    }

    /*public static o no metodo o atributo es de objeto cuando necesito tener un objeto de esa clase para
     saber su valor y puede cambiar su valor, de clase si solo necesito uno para toda la clase y el
      valor es el mismo, una clase sin atributo es static*/
}
