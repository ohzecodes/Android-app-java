package com.example.studentprogresstracking.UI.TERM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.studentprogresstracking.R;
import com.example.studentprogresstracking.UI.adpaters.TermAdpater;
import com.example.studentprogresstracking.database.Repository;
import com.example.studentprogresstracking.entity.OneTerm;

import java.util.ArrayList;


public class TermList extends AppCompatActivity {
    private Repository repo;
    ArrayList<OneTerm> TermList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_term);

        repo=new Repository(this.getApplication());
        inittermlist();

    }

    @Override
    protected void onResume() {
        super.onResume();
        inittermlist();
    }

    private void inittermlist() {
        TermList=  repo.getTermList();
        RecyclerView rv=findViewById(R.id.listOfTerms);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new TermAdpater(getApplicationContext(), TermList));

        findViewById(R.id.addatermFLB).setOnClickListener(v->
                startActivity(new Intent(this, AddATerm.class)));
    }

/*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater () ;
            inflater. inflate (R.menu.temp_menu , menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      if(item.getItemId()== R.id.deleteallterms){
          for (OneTerm T:TermList) {
              repo.DeleteTerm(T);
          }
      }
        return super.onOptionsItemSelected(item);
    }
*/

}