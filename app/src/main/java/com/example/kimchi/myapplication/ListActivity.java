package com.example.kimchi.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private AppDataAdapter mAdapter;
    private List<AppData> appDataArrayList;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applist);

        appData();

        setUpRecyclerView();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener(){

            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(appDataArrayList.get(position).getApplink()));
                intent.setData(Uri.parse(appDataArrayList.get(position).getApplink()));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                //Toast.makeText(getActivity(), "길게누르기 " + appDataArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
    private void setUpRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),1));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AppDataAdapter(appDataArrayList,this);

        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu,menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
    private void appData() {
        appDataArrayList = new ArrayList<>();
        appDataArrayList.add(new AppData("Learn C++","market://details?id=com.sololearn.cplusplus","https://lh3.ggpht.com/yB8HooRMBe9S23VT2rW127FOGirb9X5_ErczTGMgfJ_3SfyjCj-w5yekHCPNmSxri-kf=s180-rw"));
        appDataArrayList.add(new AppData("Learn Java","market://details?id=com.sololearn.java","https://lh3.googleusercontent.com/_tcOdPaRc4TxibKjMqi7C6RvhWcErARv9-yzgDPu16aS3XZHyVjko9y_G91xvFzTjQ=s180-rw"));
        appDataArrayList.add(new AppData("Learn C#","market://details?id=com.sololearn.csharp","https://lh3.googleusercontent.com/cr3DPCyT9z7WQlRfPlfie-uQp-Y2N6pBWoV4S8nVklU1A49_1CPGN1TRkasDmPsvRpA=s180-rw"));
        appDataArrayList.add(new AppData("Learn Python","market://details?id=com.sololearn.python","https://lh3.googleusercontent.com/T1qRZmOss8w93Uaa5k87w7K30UsR_wG18FO79ZYfCWYxcppb1oQpdXh60Jo8lBoMFLQ=s180-rw"));
        appDataArrayList.add(new AppData("Learn SQL","market://details?id=com.sololearn.sql","https://lh3.ggpht.com/wPtGt0MEksYcFxP2U3C-ftugJY6ovHDkYqmvQwV6_KbLxt49XsZlN_PWjYWKB6HEeWc=s180-rw"));
        appDataArrayList.add(new AppData("CppDroid C/C++ IDE","https://play.google.com/store/apps/details?id=name.antonsmirnov.android.cppdroid","https://lh3.ggpht.com/o4GltADRUhPguEuuqx-KtK_PTuckc97OGdYNfR2YUZK17zFbkhEkkUk_xn6dpqHTF4c=s180-rw"));
        appDataArrayList.add(new AppData("Programming HUb:Learn to Code","https://play.google.com/store/apps/details?id=com.freeit.java","https://lh3.googleusercontent.com/eoFVtRz0VF42WVRm0lCYmm6U6kniHT96NKANsW1Cw7DHBfJOLtzR1VukOqm7_1ajWis=s180-rw"));
        appDataArrayList.add(new AppData("알고리즘 도감","https://play.google.com/store/apps/details?id=wiki.algorithm.algorithms","https://lh3.googleusercontent.com/7z4ah6ZM6DGhAr5AD47BNyDj9Vu8eB9pR56AkbbZGXOuF4jPGLy6v36WUZ-JZ277WA=s180-rw"));
        appDataArrayList.add(new AppData("스케치웨어 - 앱제작,앱만들기,코딩앱","https://play.google.com/store/apps/details?id=com.besome.sketch","https://lh3.googleusercontent.com/4NxgJVkh5jiAq3sNIV1Y7K02oet__EFCRV0pqc032UocsyxGfHrEECcISy6qgjoqruA=s180-rw"));
        appDataArrayList.add(new AppData("Learn HTML","https://play.google.com/store/apps/details?id=com.sololearn.htmltrial","https://lh3.googleusercontent.com/zwwddqxgFlP14DlucvBV52RUMA-cV3vRvmjf-iWqxuVhYVmB-l8XN9NDirb0687DSw=s180-rw"));
        appDataArrayList.add(new AppData("Learn PHP","https://play.google.com/store/apps/details?id=com.sololearn.php","https://lh3.ggpht.com/cRhcWANtEi6KHb8GjwYSFH0QDJ7O-zdT5Gu7MdtnnD-hp6D0yyBWxOJfXE7T0EwkIQft=s180-rw"));
        appDataArrayList.add(new AppData("Learn JavaScript","https://play.google.com/store/apps/details?id=com.sololearn.javascript","https://lh3.googleusercontent.com/VMhNdwD6dEukMpEoX9muTVHfdNdR61_isd0VWongciNOUQjQ5Zlm5habSTplvQM3J00=s180-rw"));
        appDataArrayList.add(new AppData("Learn CSS","https://play.google.com/store/apps/details?id=com.sololearn.csstrial","https://lh3.googleusercontent.com/UAylvT-lNL3zfIefMgP9kiUwrhEnJxJiwq8hC0kne0qvSaOGYH8BVlJMYnNWRneb5AI=s180-rw"));
        /*
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        appDataArrayList.add(new AppData());
        */

    }

}
