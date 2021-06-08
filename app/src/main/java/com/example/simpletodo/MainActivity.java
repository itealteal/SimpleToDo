package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etTask;
    Button btnAdd;
    Button btnRemove;
    Button btnClear;
    Spinner spinner;
    ListView lvTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTask=findViewById(R.id.etTask);
        btnAdd=findViewById(R.id.btnAdd);
        btnRemove=findViewById(R.id.btnRemove);
        btnClear=findViewById(R.id.btnClear);
        spinner=findViewById(R.id.spinner);
        lvTask=findViewById(R.id.lvTask);
        ArrayList<String> taskArray= new ArrayList<String>();
        ArrayAdapter aaTask = new ArrayAdapter(this,android.R.layout.simple_list_item_1,taskArray);
        String [] array = getResources().getStringArray(R.array.taskArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.spinner_item, array);
        spinner.setAdapter(adapter);
        lvTask.setAdapter(aaTask);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    btnAdd.setEnabled(true);
                    btnRemove.setEnabled(false);
                    etTask.setHint(R.string.type_in_a_new_task);
                    etTask.setInputType(InputType.TYPE_CLASS_TEXT);
                    etTask.setText("");
                }else if(position==1){
                    btnRemove.setEnabled(true);
                    btnAdd.setEnabled(false);
                    etTask.setHint(R.string.type_index_of_task);
                    etTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etTask.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etTask.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please enter something",Toast.LENGTH_LONG).show();
                }else{
                    taskArray.add(etTask.getText().toString());
                    etTask.setText("");
                }
                aaTask.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskArray.size()==0) {
                    Toast.makeText(MainActivity.this, "No task to remove", Toast.LENGTH_LONG).show();
                }else if(etTask.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(etTask.getText().toString())>=taskArray.size()){
                    Toast.makeText(MainActivity.this, "Index does not exist", Toast.LENGTH_LONG).show();
                }else{
                    taskArray.remove(Integer.parseInt(etTask.getText().toString()));
                    etTask.setText("");
                }
                aaTask.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taskArray.size()==0) {
                    Toast.makeText(MainActivity.this, "No task to clear", Toast.LENGTH_LONG).show();
                }else {
                    taskArray.clear();
                    aaTask.notifyDataSetChanged();
                }
            }
        });

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(btnRemove.isEnabled()){
                    etTask.setText(String.valueOf(position));
                }
            }
        });
    }
}