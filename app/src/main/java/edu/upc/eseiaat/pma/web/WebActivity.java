package edu.upc.eseiaat.pma.web;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    private EditText editTextURL;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        editTextURL = (EditText) findViewById(R.id.EditTextURL);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
    }

    public void conect(View view) {
        String url = editTextURL.getText().toString();       // para poder acceder a la red
        WebReaderTask wrTask = new WebReaderTask();        //?
        wrTask.execute(url);                              //¿ejecutar la url?
    }

    private class WebReaderTask extends AsyncTask<String, Void, String>{//subclase, si se tiene que
        // ejecutar en background, implementar métodos
        @Override
        protected String doInBackground(String... url) {
            return WebReader.getUrl(url[0]); //como hay más de un string hay que indicar cual es,
            // en este caso es el primero
        }

        @Override
        protected void onPostExecute(String s) { //sobreescribir el método (code->override->onpost
            textViewResult.setText(s);  //?

        }
    }
}

// para dar permisos de acceder a red hay que editar el androidmanifest