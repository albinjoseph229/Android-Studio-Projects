package com.example.studentdb;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText edit1,edit2,edit3;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edit1=findViewById(R.id.et1);
        edit2=findViewById(R.id.et2);
        edit3=findViewById(R.id.et3);
        dbHandler= new DBHandler(MainActivity.this);

    }
    public void newstudent(View v)
    {
        String studentName=edit1.getText().toString();
        String studentCourse=edit2.getText().toString();
        int studentSemester=Integer.parseInt(edit3.getText().toString());
        dbHandler.addNewStudent(studentName,studentCourse, String.valueOf(studentSemester));
        Toast.makeText(MainActivity.this, "Student is added ", Toast.LENGTH_LONG);
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }

    public void findStudent(View v)
    {
        String studentName =  edit1.getText().toString();
        Student student = dbHandler.searchStudent(studentName);
        if(student != null)
        {
            edit2.setText(String.valueOf(student.getCourse()));
            edit3.setText(String.valueOf(student.getSemester()));
        }
        else
        {
            Toast.makeText(MainActivity.this, "No Match Found !!!", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteStudent(View v)
    {
        String studentName =  edit1.getText().toString();
        boolean result = dbHandler.deleteStudent(studentName);
        if(result)
        {
            Toast.makeText(MainActivity.this, "Record Deleted", Toast.LENGTH_LONG).show();
            edit1.setText("");
            edit2.setText("");
            edit3.setText("");

        }
        else
        {
            Toast.makeText(MainActivity.this, "Record Not Deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void updateStudent(View v)
    {
        String studentName =  edit1.getText().toString();
        String studentCourse =  edit2.getText().toString();
        int studentSemester = Integer.parseInt(edit3.getText().toString());
        dbHandler.update(studentName,studentCourse,studentSemester);
        Toast.makeText(MainActivity.this, "Record Updated", Toast.LENGTH_LONG).show();
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
    }
}