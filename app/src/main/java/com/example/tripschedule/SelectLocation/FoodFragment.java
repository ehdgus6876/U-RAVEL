package com.example.tripschedule.SelectLocation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripschedule.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodFragment extends Fragment implements TextWatcher {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editText;
    private ArrayList<LocationItem> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Spinner spinner;
    private ArrayList<String> arrayListSpinner;
    private ArrayAdapter<String> arrayAdapterSpinner;
    private LocationAdapter adapter;
    private int tmpcode;



    public FoodFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_food,container,false);
        recyclerView=v.findViewById(R.id.rv_food);
        editText=v.findViewById(R.id.editTextFilter);

        recyclerView.setHasFixedSize(true);
        arrayList=new ArrayList<>();
        editText.addTextChangedListener(this);
        layoutManager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        database=FirebaseDatabase.getInstance();
        arrayListSpinner=new ArrayList<>();







        arrayListSpinner.add("카페");
        arrayListSpinner.add("술집");
        arrayListSpinner.add("돈까스");
        arrayListSpinner.add("국밥");
        arrayListSpinner.add("고깃집");
        arrayListSpinner.add("국수");
        arrayListSpinner.add("일식");
        arrayListSpinner.add("중식");
        arrayListSpinner.add("분식");
        arrayListSpinner.add("김치찌개");
        arrayListSpinner.add("치킨");
        arrayListSpinner.add("햄버거");
        arrayListSpinner.add("피자");


         spinner=v.findViewById(R.id.spinner);

         arrayAdapterSpinner=new ArrayAdapter<>(getActivity().getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayListSpinner);

         spinner.setAdapter(arrayAdapterSpinner);
         spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 if(position==0){
                     tmpcode=0;

                     databaseReference=database.getReference("food/cafe");
                     databaseReference();

                 }
                 else if(position==1){
                     tmpcode=2;

                     databaseReference=database.getReference("food/Sul");
                     databaseReference();

                 }
                 else if(position==2){
                     tmpcode=1;

                     databaseReference=database.getReference("food/Dongas");
                     databaseReference();

                 }
                 else if(position==3){
                     tmpcode=1;

                     databaseReference=database.getReference("food/gook");
                     databaseReference();
                 }
                 else if(position==4){
                     tmpcode=1;

                     databaseReference=database.getReference("food/meat");
                     databaseReference();

                 }
                 else if(position==5){
                     tmpcode=1;

                     databaseReference=database.getReference("food/noodle");
                     databaseReference();

                 }
                 else if(position==6){
                     tmpcode=1;

                     databaseReference=database.getReference("food/sashimi");
                     databaseReference();

                 }
                 else if(position==7){
                     tmpcode=1;

                     databaseReference=database.getReference("food/china");
                     databaseReference();


                 }
                 else if(position==8){
                     tmpcode=1;

                     databaseReference=database.getReference("food/Dduck");
                     databaseReference();

                 }

                 else if(position==9){
                     tmpcode=1;

                     databaseReference=database.getReference("food/kimchi");
                     databaseReference();
                 }
                 else if(position==10){
                     tmpcode=1;

                     databaseReference=database.getReference("food/Chicken");
                     databaseReference();
                 }
                 else if(position==11){
                     tmpcode=1;

                     databaseReference=database.getReference("food/Hamburger");
                     databaseReference();
                 }
                 else if(position==12){
                     tmpcode=1;

                     databaseReference=database.getReference("food/pizza");
                     databaseReference();
                 }

             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });


        return v;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
    void databaseReference(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                int i=0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    LocationItem locationItem =snapshot.getValue(LocationItem.class);
                    arrayList.add(locationItem);
                    arrayList.get(i).setCode(tmpcode);
                    i++;

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FoodFragment", String.valueOf(databaseError.toException()));
            }
        });
        adapter=new LocationAdapter(arrayList,getActivity().getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
