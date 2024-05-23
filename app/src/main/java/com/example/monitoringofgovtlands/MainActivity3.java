package com.example.monitoringofgovtlands;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity implements LocationListener {

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int LOCATION_PERMISSION_CODE = 200;
    private static final int LOCATION_REQUEST_CODE = 201;
    private LocationManager locationManager;

    private Geocoder geocoder;
    EditText District, Mandal, Village, SurveyNumber, Date, Time, NameOfEncroacher, IDProof, TypeOfEncroachment, EncroachedSince,Latitude,Longitude,Address;
    Button save, TakePicture, UserLocation;
    Button Yes, No;
    DatabaseHelper db;
    TextView IsLandEncroached;
    LinearLayout encroachedLayout;
    private Bitmap ImageBitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db = new DatabaseHelper(MainActivity3.this);


        District = findViewById(R.id.districtbutton);
        Mandal = findViewById(R.id.mandalbutton);
        Village = findViewById(R.id.villagebutton);
        SurveyNumber = findViewById(R.id.surveybutton);
        Date = findViewById(R.id.datebutton);
        Time = findViewById(R.id.dateandtime);
        save = findViewById(R.id.save);
        TakePicture = findViewById(R.id.takepicture);
        NameOfEncroacher = findViewById(R.id.name);
        IDProof = findViewById(R.id.id);
        TypeOfEncroachment = findViewById(R.id.type);
        EncroachedSince = findViewById(R.id.since);
        IsLandEncroached = findViewById(R.id.question);
        Yes = findViewById(R.id.yesbutton);
        No = findViewById(R.id.nobutton);
        Address = findViewById(R.id.address);
        geocoder = new Geocoder(this, Locale.getDefault());
        UserLocation = findViewById(R.id.userlocation);

        Longitude = findViewById(R.id.longitude);
        Latitude=findViewById(R.id.latitude);
        encroachedLayout = findViewById(R.id.encroachedLayout);
        // Set click listener for userLocationButton
        UserLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLastLocation();
            }
        });


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encroachedLayout.setVisibility(View.VISIBLE);
            }

        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encroachedLayout.setVisibility(View.GONE);
            }
        });

//Save data to SQLite
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String districtname = District.getText().toString().trim();
                String mandalname = Mandal.getText().toString().trim();
                String villagename = Village.getText().toString().trim();
                String surveynumber = SurveyNumber.getText().toString().trim();
                String date = Date.getText().toString().trim();
                String time = Time.getText().toString().trim();


                long result = -1;
                save.setEnabled(false); // Disable the Save button
                Toast.makeText(MainActivity3.this, "Processing Data...", Toast.LENGTH_SHORT).show(); // Display the "Processing Data" toast message

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        boolean recordid = false;
                        String IsLandEncroached = "NO";
                        String Imagepath = ImageBitmap != null ? saveImageToDatabase(ImageBitmap) : null;

                        // Rest of the code for saving data to the database and displaying toast messages goes here

                        save.setEnabled(true); // Re-enable the Save button
                    }
                }, 5000); // Delay the execution of the code inside the Runnable by 5 seconds

                if (districtname.isEmpty() || mandalname.isEmpty() || villagename.isEmpty() || surveynumber.isEmpty() || date.isEmpty() || time.isEmpty()) {
                    Toast.makeText(MainActivity3.this, "Enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    boolean recordid = false;
                    String IsLandEncroached = "NO";
                    String Imagepath = ImageBitmap != null ? saveImageToDatabase(ImageBitmap) : null;


                    if (encroachedLayout.getVisibility() == View.VISIBLE) {
                        String EncroacherName = NameOfEncroacher.getText().toString().trim();
                        String IdProof = IDProof.getText().toString().trim();
                        String EncroachedSince_date = EncroachedSince.getText().toString().trim();
                        String TypeofEncroachment = TypeOfEncroachment.getText().toString().trim();

                        String latitude = Latitude.getText().toString().trim();
                        String longitude = Longitude.getText().toString().trim();

                        String address = Address.getText().toString().trim();

//                        String Imagepath = String.valueOf(ImageBitmap != null ? saveImageToDatabase(ImageBitmap) : null);

                        IsLandEncroached = "YES";

                        if (EncroacherName.isEmpty() || IdProof.isEmpty() || EncroachedSince_date.isEmpty() || TypeofEncroachment.isEmpty() ||latitude.isEmpty() || longitude.isEmpty() || address.isEmpty()|| ImageBitmap == null) {
                            Toast.makeText(MainActivity3.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                        } else {
                            Imagepath = saveImageToDatabase(ImageBitmap);
                            if (Imagepath == null) {
                                Toast.makeText(MainActivity3.this, "Failed to save data and image", Toast.LENGTH_SHORT).show();
                            }

                            recordid = db.insertData(districtname, mandalname, villagename, surveynumber, date, time, String.valueOf(IsLandEncroached), EncroacherName, IdProof, TypeofEncroachment, EncroachedSince_date, Imagepath, latitude, longitude, address);
                        }
                    } else {
                        recordid = db.insertData(districtname, mandalname, villagename, surveynumber, date, time, String.valueOf(IsLandEncroached), "-", "-", "-", "-", "-", "-", "-","-");
                    }
                    if (recordid) {
                        Toast.makeText(MainActivity3.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        District.setText("");
                        Mandal.setText("");
                        Village.setText("");
                        SurveyNumber.setText("");
                        Date.setText("");
                        Time.setText("");
                        ImageBitmap = null;
                        NameOfEncroacher.setText("");
                        IDProof.setText("");
                        TypeOfEncroachment.setText("");
                        EncroachedSince.setText("");
                        Latitude.setText("");
                        Longitude.setText("");
                        Address.setText("");
                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        locationManager.removeUpdates(MainActivity3.this);


                    } else {
                        Toast.makeText(MainActivity3.this, "Failed to Save Data", Toast.LENGTH_SHORT).show();
                    }
                    // Redirect to Activity 2 after 10 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 5000); // 5 seconds delay
                }
            }

            private String saveImageToDatabase(Bitmap ImageBitmap) {
                // Compress the image to JPEG format and save it to a file
                String filename = "image_" + System.currentTimeMillis() + ".jpg";
                File file = new File(getFilesDir(), filename);
                try (OutputStream outputStream = new FileOutputStream(file)) {
                    ImageBitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

// Store the file path in your database
                String filePath = file.getAbsolutePath();

                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity3.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL_13,filePath );

                return String.valueOf(filePath);
            }
        });






// Take Picture
        TakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity3.this, Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.CAMERA},
                            CAMERA_PERMISSION_CODE);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            }
        });
    }




    private void getLastLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(this, Locale.getDefault());
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(MainActivity3.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
    }






    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Camera permission denied
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
                // Location permission granted
                Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
            } else {
                // Location permission denied
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }

        }
    }






    // Handle camera activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ImageBitmap = (Bitmap) data.getExtras().get("data");

        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            try {
                DatabaseHelper dbHelper = new DatabaseHelper(MainActivity3.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses.size() > 0) {
                    Address addressObj = addresses.get(0);
                    String addressText = addressObj.getAddressLine(0);
                    String country = addressObj.getCountryName();
                    String state = addressObj.getAdminArea();
                    String district = addressObj.getSubAdminArea();
                    String mandal = addressObj.getLocality();
                    String village = addressObj.getSubLocality();
                    String street = addressObj.getThoroughfare();
                    String railwayStation = addressObj.getFeatureName();


                    contentValues.put(DatabaseHelper.COL_14, String.valueOf(latitude));
                    contentValues.put(DatabaseHelper.COL_15, String.valueOf(longitude));
                    String addressEditText = String.format("%s, %s, %s, %s, %s, %s, %s, %s",
                            country, state, district, mandal, village, street, railwayStation, addressObj.getPostalCode());

                    contentValues.put(DatabaseHelper.COL_16, addressEditText);

                    Latitude.setText(String.valueOf(latitude));
                    Longitude.setText(String.valueOf(longitude));
                    Address.setText(addressText);
                }
                // insert data into database using contentValues object
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}








