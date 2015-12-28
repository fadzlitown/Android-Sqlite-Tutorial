package my.fadzlirazali.sqlite_crud_training;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import my.fadzlirazali.sqlite_crud_training.helper.TableControllerStudent;
import my.fadzlirazali.sqlite_crud_training.model.Student;

/* CRUD Sqlite database Tutorial
*
* 1.0 Create a record in Android Sqlite DB
*
* */

public class MainActivity extends AppCompatActivity {

    public int recordCount;
    public Button mCreateBtn;
    public TextView mCount;
    public LinearLayout linearLayoutRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateBtn = (Button)findViewById(R.id.create_btn);
        mCount = (TextView) findViewById(R.id.count);
        linearLayoutRecord = (LinearLayout)findViewById(R.id.llRecord);

        countStudent();     //Check total student from db
        readStudent();      //query all student from db
        /*  set up the OnClickListener class listener */
        mCreateBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Context context = view.getContext();      // get this app context, then inflate it to form xml layout
                /* Inflate a new form view inside parent view which's not attached on parent root */
                final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View formView = inflater.inflate(R.layout.form,null, false);

                /* Initialize the dialogbox form first */
                final EditText editFname = (EditText) formView.findViewById(R.id.firstName);
                final EditText editEmail = (EditText) formView.findViewById(R.id.emailName);


                /* Dialog box displayed */
                new AlertDialog.Builder(context)
                        .setView(formView)
                        .setTitle("Create a student")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                /* After add btn is being pressed, getting a text from edittext & set the input values as a student object */
                                final Student student = new Student();
                                student.setFirstname(editFname.getText().toString());
                                student.setEmail(editEmail.getText().toString());

                                boolean createStatus = new TableControllerStudent(context).insert(student);
                                if (createStatus) {
                                    Toast.makeText(context, "Save!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "Unable to save!", Toast.LENGTH_LONG).show();
                                }
                                countStudent();
                                readStudent();
                                dialog.cancel();
                            }
                        }).show();
            }
        });
    }

    public void countStudent(){
        /* Get the return count of the total student */
        recordCount  = new TableControllerStudent(this).count();

        if(recordCount>1)
            mCount.setText(recordCount+" records");
        else
            mCount.setText(recordCount+" record");
    }

    public void readStudent(){
        /* Clear all the view first*/
        linearLayoutRecord.removeAllViews();
        List<Student> students = new TableControllerStudent(this).read();

        if(students.size()>0){
            for(Student student: students){

                int id = student.getId();
                String name = student.getFirstname();
                String email = student.getEmail();

                String viewContents = name+" - "+email;
                TextView item = new TextView(this);
                item.setPadding(2,15,2,5);
                item.setText(viewContents);
                item.setTag(Integer.toString(id));
                linearLayoutRecord.addView(item);
            }
        }
        else {
            TextView item = new TextView(this);
            item.setPadding(10,10,10,10);
            item.setText("No Records are found");
            linearLayoutRecord.addView(item);
        }
    }

}
