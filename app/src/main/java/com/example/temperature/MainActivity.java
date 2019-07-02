package com.example.temperature;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.temperature.CustomAdapter;
import com.example.temperature.DataModel;
import com.example.temperature.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {


    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    Double maxVal=0.0;
    Double minVal=100.0;
    Double avgVal=0.0;
    Double sumVal=0.0;

    private TextView maxText;
    private TextView minText;

    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRef=FirebaseDatabase.getInstance().getReference();


        listView=(ListView)findViewById(R.id.list);
        maxText=(TextView)findViewById(R.id.max);
        minText=(TextView)findViewById(R.id.min);

        final Integer icon_thermometer= R.drawable.download_1;
        dataModels= new ArrayList<>();




        adapter= new CustomAdapter(dataModels,getApplicationContext());
        listView.setAdapter(adapter);



        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                String roomNo=dataSnapshot.getKey();
                if(!roomNo.equals("FCM_Device_Tokens")) {
                    String temp = dataSnapshot.getValue(String.class);

                    Log.d("MainActivity", "onChildAdded: " + roomNo + " " + temp);

                    DataModel newData = new DataModel(roomNo, temp, icon_thermometer, R.color.menuRowColor);
                    dataModels.add(newData);


                    for (int i = 0; i < dataModels.size(); i++) {
                        DataModel data = dataModels.get(i);
                        String aval = data.getTemperature();
                        Double currInt = Double.parseDouble(aval);
                        maxVal = Math.max(maxVal, currInt);
                        minVal = Math.min(minVal, currInt);
                        sumVal = sumVal + currInt;
                        Log.d("MainActivity", "temp : " + currInt);
//                    if(currInt>=38) {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Red));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }else if(currInt<25)
//                    {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Blue));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.menuRowColor));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }

                    }
//                if(dataModels.size()>0) {
//                    avgVal = sumVal / dataModels.size();
//                }else{
//                    avgVal=0;
//                }

                    maxText.setText(String.format("Max : %.2f", maxVal));
                    minText.setText(String.format("Min : %.2f", minVal));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String roomNo=dataSnapshot.getKey();
                if(!roomNo.equals("FCM_Device_Tokens")) {
                    String temp = dataSnapshot.getValue(String.class);
                    DataModel changeData = new DataModel(roomNo, temp, icon_thermometer, R.color.menuRowColor);

                    Log.d("MainActivity", "onChildChanged: " + temp);
                    for (int i = 0; i < dataModels.size(); i++) {
                        if (dataModels.get(i).getRoomNo().equals(changeData.getRoomNo())) {
                            dataModels.set(i, changeData);
                            adapter.notifyDataSetChanged();
                        }
                    }


                    for (int i = 0; i < dataModels.size(); i++) {
                        DataModel data = dataModels.get(i);
                        String aval = data.getTemperature();
                        Double currInt = Double.parseDouble(aval);
                        maxVal = Math.max(maxVal, currInt);
                        minVal = Math.min(minVal, currInt);
                        sumVal = sumVal + currInt;
                        Log.d("MainActivity", "temp : " + currInt);
//                    if(currInt>=38) {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Red));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }else if(currInt<25)
//                    {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Blue));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        DataModel currData=dataModels.get(i);
//                        getViewByPosition(i,listView).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.menuRowColor));
//                        dataModels.set(i,currData);
//                        adapter.notifyDataSetChanged();
//                    }

                    }
//                if(dataModels.size()>0) {
//                    avgVal = sumVal / dataModels.size();
//                }else{
//                    avgVal=0;
//                }

                    maxText.setText(String.format("Max : %.2f", maxVal));
                    minText.setText(String.format("Min : %.2f", minVal));
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







       // String aval = dataModel.getTemperature();
        //String aval2 =  aval.substring(0,aval.length()-1);
        //Integer currtemp=Integer.parseInt(aval2);
        //

        Log.d("MainActivity", "onCreate: ");



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel= dataModels.get(position);


             //   parent.getChildAt(position).setBackgroundColor(dataModel.getcolor());

                Snackbar.make(view, dataModel.getRoomNo()+"\n"+dataModel.getTemperature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });


    }







//    class Sortbyroll implements Comparator<DataModel>
//    {
//        // Used for sorting in ascending order of
//        // roll number
//        @Override
//        public int compare(DataModel a, DataModel b)
//        {
//            String aval = a.getTemperature();
//            String aval2 =  aval.substring(0,aval.length()-1);
//            String bval = b.getTemperature();
//            String bval2 =  aval.substring(0,bval.length()-1);
//            if(Integer.parseInt(aval2) < Integer.parseInt(bval2)) {
//                return 1;
//            }else{
//                return 0;
//            }
//        }
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);

    }

}
