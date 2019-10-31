package e196244_r176519.ft.unicamp.br.aula03;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import e196244_r176519.ft.unicamp.br.aula03.DataBase.DatabaseFragment;
import e196244_r176519.ft.unicamp.br.aula03.Internet.InternetFragment;
import e196244_r176519.ft.unicamp.br.aula03.Jogo3.AssetsGame3Fragments;
import e196244_r176519.ft.unicamp.br.aula03.Jogo3.jogo3Fragment;
import e196244_r176519.ft.unicamp.br.aula03.Puzzle.PuzzleFragment;
import e196244_r176519.ft.unicamp.br.aula03.Puzzle2.Assets;
import e196244_r176519.ft.unicamp.br.aula03.Puzzle2.NameFragment;
import e196244_r176519.ft.unicamp.br.aula03.alunos.AlunosFragment;
import e196244_r176519.ft.unicamp.br.aula03.kotlin.kotlinActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (savedInstanceState == null) {
            e196244_r176519.ft.unicamp.br.aula03.autorFragment f1 = new e196244_r176519.ft.unicamp.br.aula03.autorFragment();
            fragmentTransaction.add(R.id.frame, f1, "f1_tag");
            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Context contexto = getApplicationContext();
        Toast toast = Toast.makeText(contexto, "null", Toast.LENGTH_SHORT);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_contato) {
            toast = Toast.makeText(contexto, "mail", Toast.LENGTH_SHORT);
            toast.show();
            Fragment mailFragment = fragmentManager.findFragmentByTag("mail");
            if (mailFragment == null)
                mailFragment = new mailFragment();
            replaceFragment(mailFragment, "mail");
        } else if (id == R.id.Assets) {
            Toast.makeText(contexto, "Assets", Toast.LENGTH_SHORT).show();
            Fragment assets = (Assets) fragmentManager.findFragmentByTag("Assets");
            if (assets == null) {
                assets = new Assets();
            }
            replaceFragment(assets, "Assets");
        } else if (id == R.id.AssetsGame3) {
            Toast.makeText(contexto, "Assets", Toast.LENGTH_SHORT).show();
            Fragment assets = (AssetsGame3Fragments) fragmentManager.findFragmentByTag("Assets3");
            if (assets == null) {
                assets = new AssetsGame3Fragments();
            }
            replaceFragment(assets, "Assets");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Context contexto = getApplicationContext();
        Toast toast = Toast.makeText(contexto, "null", Toast.LENGTH_SHORT);
        if (id == R.id.autores) {

            toast = Toast.makeText(contexto, "Autores", Toast.LENGTH_SHORT);
            Fragment autorFragment = fragmentManager.findFragmentByTag("f1_tag");
            if (autorFragment == null)
                autorFragment = new autorFragment();

            replaceFragment(autorFragment, "f1_tag");
        } else if (id == R.id.nav_alunos) {

            toast = Toast.makeText(contexto, "Alunos", Toast.LENGTH_SHORT);
            Fragment alunosFragment = fragmentManager.findFragmentByTag("alunos");
            if (alunosFragment == null)
                alunosFragment = new AlunosFragment();
            replaceFragment(alunosFragment, "alunos");

        } else if (id == R.id.biografia) {

            toast = Toast.makeText(contexto, "biografias", Toast.LENGTH_SHORT);
            Fragment biografiaGragment = fragmentManager.findFragmentByTag("biografias");
            if (biografiaGragment == null)
                biografiaGragment = new BiografiasFragment();
            replaceFragment(biografiaGragment, "biografias");

        } else if (id == R.id.jogo1) {

            toast = Toast.makeText(contexto, "Jogo 1", Toast.LENGTH_SHORT);
            Fragment puzzleFragment = fragmentManager.findFragmentByTag("puzzle");
            if (puzzleFragment == null)
                puzzleFragment = new PuzzleFragment();
            replaceFragment(puzzleFragment, "alunos");

        } else if (id == R.id.jogo2) {
            toast = Toast.makeText(contexto, "Jogo 2", Toast.LENGTH_SHORT);
            NameFragment nameFragment = (NameFragment) fragmentManager.findFragmentByTag("name");
            if (nameFragment == null) {
                nameFragment = new NameFragment();
            }
            replaceFragment(nameFragment, "name");
        }else if (id == R.id.newAct) {
            toast = Toast.makeText(contexto, "new Act", Toast.LENGTH_SHORT);
            Intent intent = new Intent(this, kotlinActivity.class);
            startActivity(intent);
        } else if (id == R.id.database) {
            toast = Toast.makeText(contexto, "Database", Toast.LENGTH_SHORT);
            Fragment bd = fragmentManager.findFragmentByTag("db");
            if (bd == null)
                bd = new DatabaseFragment();
            replaceFragment(bd, "db");
        } else if (id == R.id.internet) {
            toast = Toast.makeText(contexto, "Internet", Toast.LENGTH_SHORT);
            Fragment bd = fragmentManager.findFragmentByTag("Internet");
            if (bd == null)
                bd = new InternetFragment();
            replaceFragment(bd, "Internet");
        } else if (id == R.id.jogo3) {
            toast = Toast.makeText(contexto, "Jogo3", Toast.LENGTH_SHORT);
            Fragment bd = fragmentManager.findFragmentByTag("Jogo3");
            if (bd == null)
                bd = new jogo3Fragment();
            replaceFragment(bd, "Jogo3");
        }
        toast.show();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void doSomething(String msg) {
        e196244_r176519.ft.unicamp.br.aula03.autorFragment autor;
        autor = (e196244_r176519.ft.unicamp.br.aula03.autorFragment) fragmentManager.findFragmentByTag("f1_tag");
        if (autor == null) {
            autor = new e196244_r176519.ft.unicamp.br.aula03.autorFragment();
        }
        autor.setText(msg);
        replaceFragment(autor, "f1_tag");
    }

    public void showBiografia(int position) {

        BiografiasFragment biografiasFragment = (BiografiasFragment) fragmentManager.findFragmentByTag("biografias");

        if (biografiasFragment == null) {
            biografiasFragment = new BiografiasFragment();
        }

        biografiasFragment.setPosition(position);
        replaceFragment(biografiasFragment, "biografias");

    }
}
