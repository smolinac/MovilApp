package com.basededatos;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button  btnagrega;
	private Button  btnmodifica;
	private Button  btnelimina;
	private Button  btnconsulta;
	
	
	private UsuariosSQLiteHelper dbconnection;
	
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbconnection= new UsuariosSQLiteHelper(this, "dbejemplo1", null, 3);
        btnagrega = (Button)findViewById(R.id.btnagrega);
        btnmodifica = (Button)findViewById(R.id.btnmodifica);
        btnelimina = (Button)findViewById(R.id.btnelimina);
        btnconsulta = (Button)findViewById(R.id.btnconsulta);
        
        
        btnagrega.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				double n= (Math.random ()*10);
		       SQLiteDatabase db= dbconnection.getWritableDatabase();
		       db.execSQL ("INSERT INTO  usuarios(username, password) Values('" + n + "', 'toor')");
		       Log.i("db", "se agrego el usuario" + n);
		       db.close();   
				
			}
		});
        
        btnmodifica.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				SQLiteDatabase db= dbconnection.getWritableDatabase();
				  db.execSQL ("UPDATE usuarios SET password='otro'");
				  db.close();
				
				
			}
		});
        
        btnelimina.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SQLiteDatabase db= dbconnection.getWritableDatabase();
				  db.execSQL ("DELETE from usuarios where username like '8%'");
				  db.close();
				
				
			}
		});
        
        btnconsulta.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SQLiteDatabase db= dbconnection.getWritableDatabase();
				 
				Cursor cursor=db.rawQuery("SELECT * FROM usuarios", null);
				if (cursor.moveToFirst()){
					do{
						Log.i("db", String.format("usuario:  %s password: %s", cursor.getString(0), cursor.getString(1)));
						} while (cursor.moveToNext());
				}
				  db.close();
			}
		});
       
        
        
       }
    
  
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
