package e196244_r176519.ft.unicamp.br.aula03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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

import e196244_r176519.ft.unicamp.br.aula03.transacoes.Transacao;
import e196244_r176519.ft.unicamp.br.aula03.transacoes.TransacaoCreation;
import e196244_r176519.ft.unicamp.br.aula03.transacoes.Transacoes;
import e196244_r176519.ft.unicamp.br.aula03.transacoes.TransacoesFragment;
import e196244_r176519.ft.unicamp.br.aula03.transacoes.graph;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(savedInstanceState == null){
            TransacoesFragment f1 = new TransacoesFragment();
            fragmentTransaction.add(R.id.frame, f1, "transaction_list");
            fragmentTransaction.commit();
        }
    }

    public void replaceFragment(Fragment fragment, String tag){
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ShowToast")
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Context contexto = getApplicationContext();

        if (id == R.id.lista_transacoes) {
            Fragment alunosFragment = fragmentManager.findFragmentByTag("lista_transacoes");
            if(alunosFragment == null)
                alunosFragment = new TransacoesFragment();
            replaceFragment(alunosFragment,"lista_transacoes");

        } else if (id == R.id.criar_transacao) {
            Fragment alunosFragment = fragmentManager.findFragmentByTag("criar_transacao");
            if(alunosFragment == null)
                alunosFragment = new TransacaoCreation();
            replaceFragment(alunosFragment,"criar_transacao");

        } else if (id == R.id.grafico) {
            Fragment alunosFragment = fragmentManager.findFragmentByTag("grafico");
            if(alunosFragment == null)
                alunosFragment = new graph();
            replaceFragment(alunosFragment,"grafico");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showTransaction(int position){

        TransacaoCreation creation = (TransacaoCreation) fragmentManager.findFragmentByTag("criar_transacao");
        Transacao transaction = Transacoes.getTransacao(position);

        if(creation == null){
            creation = new TransacaoCreation(transaction.getNome(), transaction.getDefinicao(), transaction.getData(), transaction.getValor(), transaction.getId());
        }
        replaceFragment(creation, "criar_transacao");

    }
}
