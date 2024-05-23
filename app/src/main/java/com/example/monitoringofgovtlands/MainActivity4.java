package com.example.monitoringofgovtlands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    Button Back;
    private TableLayout tableLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Back = findViewById(R.id.mybutton);
        tableLayout = findViewById(R.id.tableLayout);



// Retrieve data from SQLite database
        DatabaseHelper db = new DatabaseHelper(this);
        Cursor cursor = db.getAllData();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity2.class);
                startActivity(intent);
            }
        });



// Add data to the table
        if (cursor.moveToFirst()) {
            do {


                TableRow newrow = new TableRow(this);
                newrow.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                TextView idTextView = new TextView(this);
                idTextView.setText(cursor.getString(0));
                idTextView.setTextSize(24);
                newrow.addView(idTextView);
                idTextView.setTextColor(Color.parseColor("#1A1A1A"));
                idTextView.setGravity(Gravity.CENTER);
                idTextView.setPadding(16, 16, 16, 16);


                TextView districtTextView = new TextView(this);
                districtTextView.setText(cursor.getString(1));
                districtTextView.setTextSize(24);
                newrow.addView(districtTextView);
                districtTextView.setTextColor(Color.parseColor("#1A1A1A"));
                districtTextView.setGravity(Gravity.CENTER);
                districtTextView.setPadding(16, 16, 16, 16);

                TextView mandalTextView = new TextView(this);
                mandalTextView.setText(cursor.getString(2));
                mandalTextView.setTextSize(24);
                newrow.addView(mandalTextView);
                mandalTextView.setTextColor(Color.parseColor("#1A1A1A"));
                mandalTextView.setGravity(Gravity.CENTER);
                mandalTextView.setPadding(16, 16, 16, 16);

                TextView villageTextView = new TextView(this);
                villageTextView.setText(cursor.getString(3));
                villageTextView.setTextSize(24);
                newrow.addView(villageTextView);
                villageTextView.setTextColor(Color.parseColor("#1A1A1A"));
                villageTextView.setGravity(Gravity.CENTER);
                villageTextView.setPadding(16, 16, 16, 16);

                TextView surveyTextView = new TextView(this);
                surveyTextView.setText(cursor.getString(4));
                surveyTextView.setTextSize(24);
                newrow.addView(surveyTextView);
                surveyTextView.setTextColor(Color.parseColor("#1A1A1A"));
                surveyTextView.setGravity(Gravity.CENTER);
                surveyTextView.setPadding(16, 16, 16, 16);

                TextView dateTextView = new TextView(this);
                dateTextView.setText(cursor.getString(5));
                dateTextView.setTextSize(24);
                newrow.addView(dateTextView);
                dateTextView.setTextColor(Color.parseColor("#1A1A1A"));
                dateTextView.setGravity(Gravity.CENTER);
                dateTextView.setPadding(16, 16, 16, 16);

                TextView dateandtimeTextView = new TextView(this);
                dateandtimeTextView.setText(cursor.getString(6));
                dateandtimeTextView.setTextSize(24);
                newrow.addView(dateandtimeTextView);
                dateandtimeTextView.setTextColor(Color.parseColor("#1A1A1A"));
                dateandtimeTextView.setGravity(Gravity.CENTER);
                dateandtimeTextView.setPadding(16, 16, 16, 16);
                TextView questionTextView = new TextView(this);
                questionTextView.setText(cursor.getString(7));
                questionTextView.setTextSize(24);
                newrow.addView(questionTextView);
                questionTextView.setTextColor(Color.parseColor("#1A1A1A"));
                questionTextView.setGravity(Gravity.CENTER);
                questionTextView.setPadding(16, 16, 16, 16);


                TextView nameTextView = new TextView(this);
                nameTextView.setText(cursor.getString(8));
                nameTextView.setTextSize(24);
                newrow.addView(nameTextView);
                nameTextView.setTextColor(Color.parseColor("#1A1A1A"));
                nameTextView.setGravity(Gravity.CENTER);
                nameTextView.setPadding(16, 16, 16, 16);

                TextView IdTextView = new TextView(this);
                IdTextView.setText(cursor.getString(9));
                IdTextView.setTextSize(24);
                newrow.addView(IdTextView);
                IdTextView.setTextColor(Color.parseColor("#1A1A1A"));
                IdTextView.setGravity(Gravity.CENTER);
                IdTextView.setPadding(16, 16, 16, 16);

                TextView typeTextView = new TextView(this);
                typeTextView.setText(cursor.getString(10));
                typeTextView.setTextSize(24);
                newrow.addView(typeTextView);
                typeTextView.setTextColor(Color.parseColor("#1A1A1A"));
                typeTextView.setGravity(Gravity.CENTER);
                typeTextView.setPadding(16, 16, 16, 16);

                TextView sinceTextView = new TextView(this);
                sinceTextView.setText(cursor.getString(11));
                sinceTextView.setTextSize(24);
                newrow.addView(sinceTextView);
                sinceTextView.setTextColor(Color.parseColor("#1A1A1A"));
                sinceTextView.setGravity(Gravity.CENTER);
                sinceTextView.setPadding(16, 16, 16, 16);

                final String imageLink = cursor.getString(12);
                TextView imageTextView = new TextView(this);
                imageTextView.setText(cursor.getString(12));
                imageTextView.setText(imageLink);
                imageTextView.setTextSize(24);
                newrow.addView(imageTextView);
                imageTextView.setPadding(16, 16, 16, 16);
                imageTextView.setTextColor(Color.parseColor("#1A1A1A"));
                imageTextView.setGravity(Gravity.CENTER);
                imageTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Create a new Intent to launch the NewActivity
                        Intent intent = new Intent(MainActivity4.this, MainActivity5.class);

                        // Pass the jpeg image link to the NewActivity as an extra
                        intent.putExtra("imageLink", imageLink);

                        // Launch the NewActivity
                        startActivity(intent);
                    }
                });






                TextView latitudeTextView = new TextView(this);
                latitudeTextView.setText(cursor.getString(13));
                latitudeTextView.setTextSize(24);
                newrow.addView(latitudeTextView);
                latitudeTextView.setTextColor(Color.parseColor("#1A1A1A"));
                latitudeTextView.setGravity(Gravity.CENTER);
                latitudeTextView.setPadding(16, 16, 16, 16);

                TextView longitudeTextView = new TextView(this);
                longitudeTextView.setText(cursor.getString(14));
                longitudeTextView.setTextSize(24);
                newrow.addView(longitudeTextView);
                longitudeTextView.setTextColor(Color.parseColor("#1A1A1A"));
                longitudeTextView.setGravity(Gravity.CENTER);
                longitudeTextView.setPadding(16, 16, 16, 16);

                TextView addressTextView = new TextView(this);
                addressTextView.setText(cursor.getString(15));
                addressTextView.setTextSize(24);
                newrow.addView(addressTextView);
                addressTextView.setTextColor(Color.parseColor("#1A1A1A"));
                addressTextView.setGravity(Gravity.CENTER);
                addressTextView.setPadding(16, 16, 16, 16);


                tableLayout.addView(newrow);
// Add borders to the row
                GradientDrawable rowBorder = new GradientDrawable();
                rowBorder.setColor(Color.TRANSPARENT);
                rowBorder.setStroke(6, Color.DKGRAY);
                newrow.setBackground(rowBorder);

// Add borders to the columns
                GradientDrawable colBorder = new GradientDrawable();
                colBorder.setColor(Color.TRANSPARENT);
                colBorder.setStroke(6, Color.DKGRAY);

                districtTextView.setBackground(colBorder);
                mandalTextView.setBackground(colBorder);
                villageTextView.setBackground(colBorder);
                surveyTextView.setBackground(colBorder);
                dateTextView.setBackground(colBorder);
                dateandtimeTextView.setBackground(colBorder);
                imageTextView.setBackground(colBorder);
                nameTextView.setBackground(colBorder);
                idTextView.setBackground(colBorder);
                typeTextView.setBackground(colBorder);
                sinceTextView.setBackground(colBorder);
                questionTextView.setBackground(colBorder);
                latitudeTextView.setBackground(colBorder);
                longitudeTextView.setBackground(colBorder);
                addressTextView.setBackground(colBorder);


// Add the new row to the existing table


            } while (cursor.moveToNext());
        }

// Close the cursor to release resources
        cursor.close();
        db.close();
    }


    }






