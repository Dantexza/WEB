package com.example.bgabr.web;


import android.graphics.Bitmap;

import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;





public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    WebView web;
    boolean loadingFinished = true;
    boolean redirect = false;
    ProgressBar bar;
    ImageView toolimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolimg = (ImageView) findViewById(R.id.imageView5);
        bar = (ProgressBar) findViewById(R.id.progressBar2) ;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Glide.with(getApplicationContext()).asGif().load(R.drawable.logo_animado).into(toolimg);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        web = (WebView) findViewById(R.id.web);
        //YoYo.with(Techniques.FadeIn).duration(6000).playOn(web);
        web.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(
                    WebView view, WebResourceRequest request) {
                if (!loadingFinished) {
                    redirect = true;
                }

                loadingFinished = false;
                web.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageStarted(
                    WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingFinished = false;
                //YoYo.with(Techniques.Pulse).duration(7000).repeat(1000).playOn(toolimg);
                YoYo.with(Techniques.FadeOut).duration(1000).playOn(web);
                bar.setVisibility(View.VISIBLE);
                //SHOW LOADING IF IT ISNT ALREADY VISIBLE
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!redirect) {
                    loadingFinished = true;

                    web.loadUrl("javascript:(function() { " +
                            "var head = document.getElementsByTagName('center')[0].style.display='none'; " +
                            "})()");
                    web.loadUrl("javascript:(function() { " +
                            "var head = document.getElementById('masthead').style.display='none'; " +
                            "})()");
                    web.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('br')[0].style.display='none'; " +
                            "})()");
                    web.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('br')[1].style.display='none'; " +
                            "})()");
                    web.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('br')[2].style.display='none'; " +
                            "})()");
                    web.loadUrl("javascript:(function() { " +
                            "document.getElementsByTagName('br')[3].style.display='none'; " +
                            "})()");



                }

                if (loadingFinished && !redirect) {
                    //HIDE LOADING IT HAS FINISHED
                    view.scrollTo(0, 0);

                    YoYo.with(Techniques.FadeIn).duration(2000).playOn(web);
                    bar.setVisibility(View.GONE);
                } else {
                    redirect = false;
                }
            }
        });
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setUseWideViewPort(true);//setting wide view
        web.getSettings().setLoadWithOverviewMode(true);//setting default zoomed out view
        web.setInitialScale(1);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);//setting zoom controls
        web.getSettings().setDisplayZoomControls(false);
        web.loadUrl("https://eorzeabrasil.com/");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            web.goBack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        ImageView nav_img = (ImageView) findViewById(R.id.imageView) ;
       // YoYo.with(Techniques.Pulse).duration(7000).repeat(1000).playOn(findViewById(R.id.imageView));
        Glide.with(getApplicationContext()).asGif().load(R.drawable.logo_animado).into(nav_img);
        //  THIS

        //getMenuInflater().inflate(R.menu.main2, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            web.loadUrl("https://eorzeabrasil.com");

        } else if (id == R.id.nav_patchs) {
            web.loadUrl("https://eorzeabrasil.com/patchs");

        } else if (id == R.id.nav_videos) {
            web.loadUrl("https://eorzeabrasil.com/videos");

        } else if (id == R.id.nav_events) {
            web.loadUrl("https://eorzeabrasil.com/eventos");

        } else if (id == R.id.nav_class) {
            web.loadUrl("https://eorzeabrasil.com/classes");

        } else if (id == R.id.nav_jobs) {
            web.loadUrl("https://eorzeabrasil.com/jobs");

        } else if (id == R.id.nav_craft) {
            web.loadUrl("https://eorzeabrasil.com/crafting");

        } else if (id == R.id.nav_Gather) {
            web.loadUrl("https://eorzeabrasil.com/gathering");

        } else if (id == R.id.nav_duty) {
            web.loadUrl("https://eorzeabrasil.com/duties");

        } else if (id == R.id.nav_log) {
            web.loadUrl("https://eorzeabrasil.com/logs");

        }else if (id == R.id.nav_guias) {
            web.loadUrl("https://eorzeabrasil.com/guias");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

  }

