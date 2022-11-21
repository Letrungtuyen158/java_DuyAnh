package com.supercoders.androidsqlitetutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
public class Main_trip extends AppCompatActivity {
    DatabaseConfig db_config;
    TextView all_trip;
    TextView count_trip;
    @SuppressLint("Miss Data input")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_trip);
        db_config=new DatabaseConfig(Main_trip.this);
        Button btn_dl_trip=findViewById(R.id.dl_trip);
        Button btn_add_trip=findViewById(R.id.add_trip);
        Button btn_update_trip=findViewById(R.id.put_trip);
        Button btn_read_trip=findViewById(R.id.get_trip);
        all_trip=findViewById(R.id.Trip_All);
        count_trip=findViewById(R.id.Trip_Count);
        btn_read_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_Trip();
            }
        });
        btn_add_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupAddTrip();
            }
        });
        btn_update_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupSearchTrip();
            }
        });
        btn_dl_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupDelete();
            }
        });
    }
    private void get_Trip() {
        List<Trip_table_model> tripModelList=db_config.getAllTrips();
        all_trip.setText("");
        for(Trip_table_model tripModel:tripModelList){
            all_trip.append("ID : "+tripModel.getTripId()+" | Name : "+tripModel.getTrip_name()+" | Destination : "+tripModel.getTrip_destination()+" | Date : "+tripModel.getTrip_date()+ " | Require : "+tripModel.getTrip_require()+" | Date : "+tripModel.getTrip_date()+" \" \n\n");
        }
    }
    private void PopupDelete() {
        AlertDialog.Builder al=new AlertDialog.Builder(Main_trip.this);
        View view=getLayoutInflater().inflate(R.layout.delete_trip,null);
        al.setView(view);
        final EditText delete_bt=view.findViewById(R.id.Id_delete_Trip);
        Button dl=view.findViewById(R.id.Delete_Trip);
        final AlertDialog alertDialog=al.show();
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_config.deleteTrip(delete_bt.getText().toString());
                alertDialog.dismiss();
                get_Trip();
            }
        });
    }
    private void PopupSearchTrip() {
        AlertDialog.Builder al=new AlertDialog.Builder(Main_trip.this);
        View view=getLayoutInflater().inflate(R.layout.search_id_trip,null);
        al.setView(view);
        final EditText Id_Trip=view.findViewById(R.id.Id_Trip);
        Button search=view.findViewById(R.id.button_Search_Trip);
        final AlertDialog alertDialog=al.show();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpView(Id_Trip.getText().toString());
                alertDialog.dismiss();
                get_Trip();
            }
        });
    }
    private void PopUpView(final String id) {
        Trip_table_model tripModel=db_config.getTrip(id);
        AlertDialog.Builder al=new AlertDialog.Builder(Main_trip.this);
        View view=getLayoutInflater().inflate(R.layout.update_trip,null);
        final EditText Name_Trip=view.findViewById(R.id.Name_Trip);
        final EditText Destination_Trip=view.findViewById(R.id.Destination_Trip);
        final EditText Date_Trip=view.findViewById(R.id.Date_Trip);
        final EditText Require_Trip=view.findViewById(R.id.Require_Trip);
        final EditText Description_Trip=view.findViewById(R.id.Description_Trip);
        Button update_btn=view.findViewById(R.id.Update_Trip);
        al.setView(view);
        Name_Trip.setText(tripModel.getTrip_name());
        Destination_Trip.setText(tripModel.getTrip_destination());
        Date_Trip.setText(tripModel.getTrip_date());
        Require_Trip.setText(tripModel.getTrip_require());
        Description_Trip.setText(tripModel.getTrip_description());
        final AlertDialog alertDialog=al.show();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trip_table_model Trip_table_model=new Trip_table_model();
                Trip_table_model.updateTrip_name(Name_Trip.getText().toString());
                Trip_table_model.updateTripId(id);
                Trip_table_model.updateTrip_destination(Destination_Trip.getText().toString());
                Trip_table_model.updateTrip_date(Date_Trip.getText().toString());
                Trip_table_model.UpdateTrip_require(Require_Trip.getText().toString());
                Trip_table_model.updateTrip_description(Description_Trip.getText().toString());
                db_config.updateTrip(Trip_table_model);
                alertDialog.dismiss();
                get_Trip();
            }
        });
    }
    private void PopupAddTrip() {
        AlertDialog.Builder al=new AlertDialog.Builder(Main_trip.this);
        View view=getLayoutInflater().inflate(R.layout.add_trip,null);
        final EditText Name_Trip=view.findViewById(R.id.Name_Trip);
        final EditText Destination_Trip=view.findViewById(R.id.Destination_Trip);
        final EditText Date_Trip=view.findViewById(R.id.Date_trip);
        final EditText Require_Trip=view.findViewById(R.id.Require_Trip);
        final EditText Description_Trip=view.findViewById(R.id.Description_Trip);
        Button ADD_TRIP=view.findViewById(R.id.button_Add);
        al.setView(view);
        final AlertDialog alertDialog=al.show();
        ADD_TRIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trip_table_model Trip_Table_Modal=new Trip_table_model();
                Trip_Table_Modal.updateTrip_name(Name_Trip.getText().toString());
                Trip_Table_Modal.updateTrip_destination(Destination_Trip.getText().toString());
                Trip_Table_Modal.updateTrip_date(Date_Trip.getText().toString());
                Trip_Table_Modal.UpdateTrip_require(Require_Trip.getText().toString());
                Trip_Table_Modal.updateTrip_description(Description_Trip.getText().toString());
                Date date=new Date();
                Trip_Table_Modal.updateTrip_created_at(""+date.getTime());
                db_config.AddTrip(Trip_Table_Modal);
                alertDialog.dismiss();
                get_Trip();
            }
        });
    }
}