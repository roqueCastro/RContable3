package com.example.roque.rcontable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    // Se crean todas las variables se unicializan

    WebView wv;
    View v;
    String WEB_ROOT;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        // Variable que lleva la Url
        WEB_ROOT = getResources().getString(R.string.web_root);

        //webview a una pagina internet

        // Se renombra la variable del LayoutRefresh con su ID
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);

        // Esto se cumple cuando se ejecuta el Refresh
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //Se carga la Url por  medio del String " WebActiong "  Que viene del publib void
                WebAction();
            }
        });

        WebAction();


        //TOLL BAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                conSemana();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

           prueba2(v);
           salir();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    //WEB FUNCIONE
    public void WebAction(){

        wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        //wv.loadUrl((String.valueOf(Html.fromHtml(WEB_ROOT))));
        wv.loadUrl(WEB_ROOT);
        swipe.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wv.loadUrl("file:///android_asset/error.html");


            }


            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
            }

        });

    }

    //WEB FUNCIONE
    public void conSemana(){

        wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        //wv.loadUrl((String.valueOf(Html.fromHtml(WEB_ROOT))));
        wv.loadUrl("http://rcontable.000webhostapp.com/CONSULTAS/ConsultarGastos.php");
        swipe.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wv.loadUrl("file:///android_asset/error.html");


            }


            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
            }

        });

    }

    //WEB FUNCIONE
    public void salir(){

        wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAppCacheEnabled(true);
        //wv.loadUrl((String.valueOf(Html.fromHtml(WEB_ROOT))));
        wv.loadUrl("http://rcontable.000webhostapp.com/salir.php");
        swipe.setRefreshing(true);
        wv.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                wv.loadUrl("file:///android_asset/error.html");


            }


            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                swipe.setRefreshing(false);
            }

        });

    }

    public void prueba2(View v) {
        Toast toast = Toast.makeText(this, "Cerrando Sesion...", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    //COMPRUEBA SI HAY CONEXION
    @Override
    public void onBackPressed(){

        if (wv.canGoBack()){
            wv.goBack();
        }else {
            finish();
        }
    }


//CIERRA TODOO
}

