package com.android1.practice3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Myadapter myadapter;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        preferences=this.getSharedPreferences("Preference",MODE_PRIVATE);
        getMyList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                myadapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myadapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.sorting){
            sortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortDialog(){
        String[] options={"Ascending","Descending"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("Sort by");
        builder.setIcon(R.drawable.ic_sort_black_24dp);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("Sort","ascending");
                    editor.apply();
                    getMyList();
                }
                if (which==1){
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("Sort","descending");
                    editor.apply();
                    getMyList();
                }
            }
        });

        builder.create().show();
    }



    private void  getMyList(){
        ArrayList<Model>models=new ArrayList<>();

        Model model= new Model();
        model.setName("Apple Pie");
        model.setImage(R.drawable.food5);
        model.setPrice("5600");
        models.add(model);

        model= new Model();
        model.setName("Clam Chowder");
        model.setImage(R.drawable.food);
        model.setPrice("4500");
        models.add(model);

        model= new Model();
        model.setName("Bagel and Lox");
        model.setImage(R.drawable.background);
        model.setPrice("2500");
        models.add(model);


        model= new Model();
        model.setName("Deep-Dish Pizza");
        model.setImage(R.drawable.chivas);
        model.setPrice("1500");
        models.add(model);

        model= new Model();
        model.setName("Sausage Gravy");
        model.setImage(R.drawable.d1);
        model.setPrice("6000");
        models.add(model);

        model= new Model();
        model.setName("Texas Barbecue");
        model.setImage(R.drawable.d2);
        model.setPrice("3000");
        models.add(model);

        model= new Model();
        model.setName("Water melon");
        model.setImage(R.drawable.d4);
        model.setPrice("9000");
        models.add(model);

        model= new Model();
        model.setName("snifter");
        model.setImage(R.drawable.d3);
        model.setPrice("1000");
        models.add(model);

        model= new Model();
        model.setName("Liquor");
        model.setImage(R.drawable.drinks);
        model.setPrice("2000");
        models.add(model);

        model= new Model();
        model.setName("Chicken");
        model.setImage(R.drawable.food3);
        model.setPrice("7000");
        models.add(model);

        model= new Model();
        model.setName("Fried Chicken");
        model.setImage(R.drawable.food2);
        model.setPrice("4000");
        models.add(model);

        String sortSetting =preferences.getString("Sort","ascending");

        if (sortSetting.equals("ascending")){
            Collections.sort(models,Model.By_Menu_Ascending);
        }else if (sortSetting.equals("descending")){
            Collections.sort(models,Model.By_Menu_Descending);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myadapter=new Myadapter(this,models);
        recyclerView.setAdapter(myadapter);



    }
}
