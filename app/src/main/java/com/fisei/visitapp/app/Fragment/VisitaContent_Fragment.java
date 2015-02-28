package com.fisei.visitapp.app.Fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.fisei.visitapp.app.R;
import com.fisei.visitapp.app.adapter.AdapterEstudiantePracticas;
import com.fisei.visitapp.app.database.DatabaseManager;
import com.fisei.visitapp.app.entity.EstudianteInformacion;
import com.fisei.visitapp.app.entity.PasantiaPracticas;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;

/**
 * Created by diegoztc on 06/02/15.
 */
public class VisitaContent_Fragment extends Fragment {

    public static final String CODECC = "CCEstudiante";
    String codeCC="0";

    ImageView imageView;
    Intent intent;
    Bitmap image;
    byte[] imageData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    // TODO Auto-generated method stub
    // Comprobamos si se recupera de un estado anterior
        if (savedInstanceState != null){
            codeCC = savedInstanceState.getString("CCEstudiante");
            String carrera="";
            carrera=DatabaseManager.getInstance().getEstudianteInformacion(codeCC).getCarrera();
          if(carrera.equals("Sistemas"))
              return inflater.inflate(R.layout.activity_visita_sistemas, container, false);
            if(carrera.contains("Electr"))
                return inflater.inflate(R.layout.activity_visita_sistemas, container, false);
                if(carrera.equals("Industrial"))
                    return inflater.inflate(R.layout.activity_visita_sistemas, container, false);


        }


        return inflater.inflate(R.layout.activity_visita, container, false);
    }
    @Override
    public void onStart() {
    // TODO Auto-generated method stub
        super.onStart();
        DatabaseManager.init(getActivity());
    
    // Comprobamos si tenemos argumentos
        Bundle args = getArguments();
        if (args != null){
    
    // Si tenemos argumentos, establecemos la posicion segun el codigo
            updateContent(args.getString(CODECC));
        }else if(!codeCC.equals("0")){
    
    // Si la variable de instancia es diferente a -1
    // quiere decir que nos hemos recuperado de un estado anterior
    // y actualizamos el contenido
            updateContent(codeCC);
        }
    }


    public void insertPicture(byte[] data)
    {

        ContentValues values = new ContentValues(1);
        values.put("photo", data);
    }

    public void updateContent(String codeCC){


        try {
            // Instanciamos el TextView y establecemos el contenido
            EstudianteInformacion est = DatabaseManager.getInstance().getEstudianteInformacion(codeCC);

            PasantiaPracticas pst = DatabaseManager.getInstance().getLastPasantiaPracticasByEstudiante(est.getCodEstudiante());

            TextView txtVNombre = (TextView) getActivity().findViewById(R.id.txtVNombre);
            TextView txtVCedula = (TextView) getActivity().findViewById(R.id.txtVCedula);
            TextView txtVCarrera = (TextView) getActivity().findViewById(R.id.txtVCarrera);

            TextView txtVEntidad = (TextView) getActivity().findViewById(R.id.txtVEmail);
            TextView txtVHoras = (TextView) getActivity().findViewById(R.id.txtVHoras);



            final TextView txtVFecha = (TextView) getActivity().findViewById(R.id.txtVFecha);




            Button btnVFecha = (Button)getActivity().findViewById(R.id.btnVFecha);
            Button btnVAceptar = (Button)getActivity().findViewById(R.id.btnVAceptar);
            Button btnVCancelar = (Button)getActivity().findViewById(R.id.btnVCancelar);

            ImageButton btnVTakePhoto = (ImageButton) getActivity().findViewById(R.id.btnVTakePhoto);


            imageView=(ImageView) getActivity().findViewById(R.id.imageView);

            txtVNombre.setText(est.getNombres());
            txtVCedula.setText(codeCC);
            txtVEntidad.setText(pst.getEntidad());
            txtVCarrera.setText(est.getCarrera());
            txtVHoras.setText(pst.getHorasPracticas());


            btnVFecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DialogFragment newFragment = new DatePickerFragment() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            txtVFecha.setText("" + year + "-" + month +  "-" + day);
                        }
                    };
                    newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
                }
            });

            btnVTakePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI.getPath());
                    startActivityForResult(intent, 0);
                }
            });

            btnVAceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   Log.e("Image",imageData.toString()+" "+image.getConfig().toString()+" "+image.getRowBytes());

                }
            });




        }catch (Exception ex)
        {
            ex.printStackTrace();

        }
        // Guardamos el codigo del elemento que estamos consultando
        this.codeCC=codeCC;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
        {
            Bundle ext = data.getExtras();
            image=(Bitmap)ext.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            imageView.setImageBitmap(image);

            imageData = stream.toByteArray();

            Log.e("Info", intent.getStringExtra(MediaStore.EXTRA_OUTPUT));



        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
    // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    // Guardamos el estado de la posicion del elemento
    // que estábamos consultando
        outState.putString(CODECC,codeCC);
    }

}
