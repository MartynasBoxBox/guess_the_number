package edu.ktu.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private RecyclerView recyclerView; //nuoroda i layout esanti sarasa duomenu, kuriuos noresim ideti
    private RecyclerView.Adapter adapter; //objektas, susieja sarasa su duomenim, kurie pateikiami adapteriui ir norimi atvaizduot
    private RecyclerView.LayoutManager layoutManager; //padeda su item'u pozicionavimu
    private ArrayList<PersonData> resultsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultsData = new ArrayList<>();
        prepareContent();

        recyclerView = findViewById(R.id.result_recycler_view); //fetchin'a pagal id
        layoutManager = new LinearLayoutManager(this); //visus duomenis isdelioja tiesiskai
        adapter = new CustomAdapter(resultsData); //kuria nauja adapteri

        recyclerView.setLayoutManager(layoutManager); //nustatom koki manageri naudot
        recyclerView.setAdapter(adapter); //nustatom koki adapteri naudoti

        adapter.notifyDataSetChanged(); //nurodo, kad pasikeite duomenu rinkinys ir reikia kazka daryt

    }

    private void prepareContent()
    {
        resultsData = new ArrayList<>();

        ResultsDatabaseHandler dbhandler = new ResultsDatabaseHandler(this);

        ArrayList<PersonData> data = dbhandler.getAllData();
        resultsData = data;
    }
}
