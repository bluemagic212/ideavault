package com.example.randomestudios.ideavaultapp1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;
    EditText title_editText;
    EditText idea_editText;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.menu_main);

        //text View to Font
        TextView fontTextView1 = (TextView)findViewById(R.id.button1);
        TextView fontTextView2 = (TextView) findViewById(R.id.button2);
        TextView fontTextView3 = (TextView) findViewById(R.id.button3);
        TextView fontTextView4 = (TextView) findViewById(R.id.button4);


        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        fontTextView1.setTypeface(font);
        fontTextView2.setTypeface(font);
        fontTextView3.setTypeface(font);
        fontTextView4.setTypeface(font);


        fontTextView1.setText("\uf0ca");
        fontTextView2.setText("\uf040");
        fontTextView3.setText("\uf130");
        fontTextView4.setText("\uf030");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Masonry Grid
        ArrayList<Model> list= getRecords();

/*
        ArrayList<Model> list= new ArrayList<>();
        list.add(new Model(Model.TEXT_TYPE,"Visit the Grand Canyon.",0));
        list.add(new Model(Model.IMAGE_TYPE, "Go Snorkeling in Los Roques Archipelago.", R.drawable.one));
        list.add(new Model(Model.AUDIO_TYPE, "Hey. Pressing the FAB button will playback an audio file on loop.", R.raw.sound));
        list.add(new Model(Model.IMAGE_TYPE, "Edge Walk at Toronto’s CN Tower.", R.drawable.two));
        list.add(new Model(Model.TEXT_TYPE,"Be able to hold a plank for a minute.",0));
        list.add(new Model(Model.IMAGE_TYPE, "Learn how to do a perfect push up.", R.drawable.three));
        list.add(new Model(Model.IMAGE_TYPE, "Swim in the World’s Largest Swimming Pool.", R.drawable.four));
        list.add(new Model(Model.TEXT_TYPE,"Use portion control for weight loss. ",0));
        list.add(new Model(Model.TEXT_TYPE,"Visit the Louvre- Dream come true.",0));
        list.add(new Model(Model.IMAGE_TYPE, "Run the New York Marathon.", R.drawable.five));
        list.add(new Model(Model.AUDIO_TYPE,"Hey. Pressing the FAB button will playback an audio file on loop.",R.raw.sound));
        list.add(new Model(Model.IMAGE_TYPE,"", R.drawable.five));*/


        myDb = new DatabaseHelper(this);
        title_editText = (EditText)findViewById(R.id.title_editText);
        idea_editText = (EditText)findViewById(R.id.idea_editText);
        submit_btn = (Button) findViewById(R.id.submit_button);
        AddData();



        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.masonry_grid);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == 1){
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
        else {
            mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        }

        MasonryAdapter adapter = new MasonryAdapter(list,this);
        mRecyclerView.setAdapter(adapter);
        SpaceItemDecoration decoration = new SpaceItemDecoration(5);
        mRecyclerView.addItemDecoration(decoration);

    }

    public void AddData(){
        submit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(title_editText.getText().toString(),idea_editText.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(MainActivity.this, "Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public ArrayList<Model> getRecords(){
        ArrayList<Model> data=new ArrayList<Model>();
        Cursor cursor = myDb.getAllData();
        while(cursor.moveToNext()){
            data.add(new Model(Model.TEXT_TYPE,cursor.getString(0),cursor.getString(1),0));
        }
        cursor.close();  // dont forget to close the cursor after operation done
        return data;
    }



    public void perform_action(View v)
    {
        TextView tv= (TextView) findViewById(R.id.EditText01);
        //alter text of textview widget
        Intent intent_editText = new Intent();
        intent_editText.setClass(this,EditText_Idea.class);
        startActivity(intent_editText);

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_actions, menu);
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

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
