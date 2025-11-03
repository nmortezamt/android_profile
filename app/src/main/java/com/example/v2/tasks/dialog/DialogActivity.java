package com.example.v2.tasks.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.v2.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DialogActivity extends AppCompatActivity {
    Button btnShowDialog, btnAlertDialog, btnProgressDialog, btnDatePickerDialog, btnTimePickerDialog, btnSnackBarDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        btnShowDialog = findViewById(R.id.btnUserDialog);

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnProgressDialog = findViewById(R.id.btnProgressDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        btnSnackBarDialog = findViewById(R.id.btnSnackBarDialog);

        // AlertDialog
        btnAlertDialog.setOnClickListener(v -> showAlertDialog());

        // ProgressDialog
        btnProgressDialog.setOnClickListener(v -> showProgressDialog());

        // DatePickerDialog
        btnDatePickerDialog.setOnClickListener(v -> showDatePickerDialog());

        // TimePickerDialog
        btnTimePickerDialog.setOnClickListener(v -> showTimePickerDialog());

        // Snackbar
        btnSnackBarDialog.setOnClickListener(v -> showSnackBar());

        btnShowDialog.setOnClickListener(v -> showCustomDialog());
    }

    private void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .setMessage("This is an alert dialog example.")
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .show();
    }

    // -----------------------------
    // 2️⃣ Progress Dialog (modern style)
    private void showProgressDialog() {
        ProgressBar progressBar = new ProgressBar(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Loading")
                .setView(progressBar)
                .setCancelable(true)
                .create();
        dialog.show();

        // Optional: auto dismiss after 3 sec
        new Handler(Looper.getMainLooper()).postDelayed(dialog::dismiss, 3000);
    }

    // -----------------------------
    // 3️⃣ Date Picker Dialog
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) ->
                        Toast.makeText(this, "Selected: " + dayOfMonth + "/" + (month+1) + "/" + year, Toast.LENGTH_SHORT).show(),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.show();
    }

    // -----------------------------
    // 4️⃣ Time Picker Dialog
    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePicker = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) ->
                        Toast.makeText(this, "Selected: " + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show(),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePicker.show();
    }

    // -----------------------------
    // 5️⃣ Snackbar
    private void showSnackBar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar.make(rootView, "This is a Snackbar!", Snackbar.LENGTH_SHORT)
                .setAction("Undo", v -> Toast.makeText(this, "Undo clicked!", Toast.LENGTH_SHORT).show())
                .show();
    }
    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_nested_list);

        // Increase size of dialog
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT, // width
                    WindowManager.LayoutParams.WRAP_CONTENT  // height
            );
        }

        // Close button
        Button btnClose = dialog.findViewById(R.id.btnCloseDialog);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        RecyclerView rvVertical = dialog.findViewById(R.id.rvVerticalUsers);
        rvVertical.setLayoutManager(new LinearLayoutManager(this));

        List<List<UserModel>> sampleData = createDemoData();

        UserSectionAdapter adapter = new UserSectionAdapter(sampleData, user ->
                Toast.makeText(this, "Clicked: " + user.getName(), Toast.LENGTH_SHORT).show()
        );

        rvVertical.setAdapter(adapter);
        dialog.show();
    }
    private List<List<UserModel>> createDemoData() {
        List<List<UserModel>> parent = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            List<UserModel> list = new ArrayList<>();
            list.add(new UserModel(R.drawable.profile, "John", 22));
            list.add(new UserModel(R.drawable.profile, "Emma", 25));
            list.add(new UserModel(R.drawable.profile, "Alex", 27));
            parent.add(list);
        }

        return parent;
    }
}
