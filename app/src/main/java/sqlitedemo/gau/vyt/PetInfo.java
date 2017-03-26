package sqlitedemo.gau.vyt.pettrackerv1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PetInfo extends AppCompatActivity {

    public static Pet selectedPet;
    DataBaseHelper myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        TextView txt_petName = (TextView)findViewById(R.id.textView_petName);
        TextView txt_birthDate = (TextView)findViewById(R.id.textView_birthDate);
        TextView txt_collarPhone = (TextView)findViewById(R.id.textView_collarPhone);

        txt_petName.setText(selectedPet.getName());
        txt_birthDate.setText(selectedPet.getYear() + "-" + (selectedPet.getMonth() + 1) + "-" + selectedPet.getDay());
        txt_collarPhone.setText(selectedPet.getTelNo());

        Button button_remove = (Button)findViewById(R.id.button_remove);
        Button button_locate = (Button)findViewById(R.id.button_locate);
        Button button_edit = (Button)findViewById(R.id.button_edit);

        button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });

        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity_EditPetInfo();
            }
        });
    }

    private void remove() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to delete this record?");
        builder1.setCancelable(true);
        myDataBase = new DataBaseHelper(this);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myDataBase.deletePet(selectedPet);
                        launchActivity_MainActivity();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void launchActivity_MainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        launchActivity_MainActivity();
    }

    private void launchActivity_EditPetInfo() {
        Intent intent = new Intent(this, EditPetInfo.class);
        startActivity(intent);
    }
}
