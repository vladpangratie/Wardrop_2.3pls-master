package ro.scoaladevalori.cegeka.wardrop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import ro.scoaladevalori.cegeka.wardrop.db.Memory;
import ro.scoaladevalori.cegeka.wardrop.db.MemoryDbHelper;


public class AddClothesActivity extends AppCompatActivity {

    String colourItem;
    String categoryItem;
    String styleItem;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_REQUEST_CODE = 200;
    private ImageView selectedImageView;
    private EditText colorEditText;
    private EditText categoryEditText;
    private EditText styleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);


        Button PictureBtn = findViewById(R.id.TakePicture_Btn);
        PictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        final Spinner spinner_colours= (Spinner)findViewById(R.id.select_color_spinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_colours, android.R.layout.simple_spinner_item);
        spinner_colours.setAdapter(adapter);
        spinner_colours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colourItem = spinner_colours.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
          selectedImageView = findViewById(R.id.picture);
        final Spinner spinner_categories = findViewById(R.id.select_category_spinner);
        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner_categories, android.R.layout.simple_spinner_item);
        spinner_categories.setAdapter(adapter2);
        spinner_categories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryItem = spinner_categories.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner spinner_style = (Spinner)findViewById(R.id.select_style);
        ArrayAdapter <CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.spinner_style , android.R.layout.simple_spinner_item);
        spinner_style.setAdapter(adapter3);
        spinner_style.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                styleItem = spinner_style.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button AddBtn = findViewById(R.id.Add_Btn);
        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap image = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();
                new MemoryDbHelper (AddClothesActivity.this).addMemory(new Memory(colourItem, categoryItem, styleItem,image));
                finish();
            }
        });

    }
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedImageView.setImageBitmap(imageBitmap);
        }
    }



}
