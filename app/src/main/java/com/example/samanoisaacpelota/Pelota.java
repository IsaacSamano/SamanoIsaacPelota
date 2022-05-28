package com.example.samanoisaacpelota;
import android.content.Context;
import android.graphics.*;
import android.hardware.*;
import android.util.Log;
import android.view.*;
import android.widget.Toast;


public class Pelota  extends View implements SensorEventListener{
    Paint pincel = new Paint();
    int alto, ancho;
    int tamanio = 25;
    int borde = 8;
    float ejeX=0, ejeY=0, ejeZ1=0, ejeZ=0;
    String X,Y,Z;

    int conta1=-1;
    int conta2=0;

    boolean ya=false;


    public Pelota(Context context) {
        super(context);
        SensorManager smAdmin = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor sensorRotacion = smAdmin.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        smAdmin.registerListener(this, sensorRotacion, SensorManager.SENSOR_DELAY_FASTEST);
        Display pantalla = ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        ancho = pantalla.getWidth();
        alto = pantalla.getHeight();
    }

    @Override
    public void onSensorChanged(SensorEvent change) {
        ejeX-= change.values[0];
        X = Float.toString(ejeX);
        //Log.d(X,"X");
        if (ejeX < (tamanio+borde)) {
            ejeX = (tamanio+borde);
        } else if (ejeX > (ancho - (tamanio+borde))) {
            ejeX = ancho - (tamanio + borde);
        }
        ejeY+=change.values[1];
        Y = Float.toString(ejeY);
        //Log.d(Y,"Y");
        if (ejeY < (tamanio + borde)) {
            ejeY = (tamanio + borde);
            conta1++;
            Toast.makeText(getContext(), "Equipo 1: " + conta1 + " goles", Toast.LENGTH_SHORT).show();
            ejeY=700;
        } else if (ejeY > (alto - tamanio - 170)) {
            ejeY = alto - tamanio- 170;
            conta2++;
            Toast.makeText(getContext(), "Equipo 2: " + conta2 + " goles", Toast.LENGTH_SHORT).show();
            ejeY=700;
        }
        ejeZ = change.values[2];
        Z = String.format("%.2f",ejeZ);
        //Log.d(Z,"Z");
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onDraw(Canvas lienzo) {
        pincel.setColor(Color.GREEN);
        lienzo.drawCircle(ejeX,ejeY, ejeZ+tamanio, pincel);
        pincel.setColor(Color.BLUE);
    }
}
