package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class InputDaganganActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;
    private String mStringImageuri = "";
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText input_dagangan_nama, input_dagangan_harga, input_dagangan_donasi, input_dagangan_est_point, input_dagangan_stok, input_dagangan_deskripsi;
    View input_dagangan_v_cari_gambar;
    ImageView input_dagangan_preview_gambar;
    Button input_dagangan_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_dagangan);
        //Pengaturan Action Bar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorWhite)));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.mas_jaka_logo_kecil);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Input Dagangan");

        input_dagangan_nama = findViewById(R.id.input_dagangan_nama);
        input_dagangan_harga = findViewById(R.id.input_dagangan_harga);
        input_dagangan_donasi = findViewById(R.id.input_dagangan_donasi);
        input_dagangan_est_point = findViewById(R.id.input_dagangan_est_point);
        input_dagangan_stok = findViewById(R.id.input_dagangan_stok);
        input_dagangan_deskripsi = findViewById(R.id.input_dagangan_deskripsi);
        input_dagangan_v_cari_gambar = findViewById(R.id.input_dagangan_v_cari_gambar);
        input_dagangan_preview_gambar = findViewById(R.id.input_dagangan_preview_gambar);
        input_dagangan_simpan = findViewById(R.id.input_dagangan_simpan);

        input_dagangan_v_cari_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cariFile();
            }
        });

        input_dagangan_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_dagangan_nama.getText().toString().equals("") || input_dagangan_harga.getText().toString().equals("") || input_dagangan_donasi.getText().toString().equals("")
                || input_dagangan_est_point.getText().toString().equals("") || input_dagangan_stok.getText().toString().equals("") || input_dagangan_deskripsi.getText().toString().equals("")) {
                    Toast.makeText(InputDaganganActivity.this, "Silahkan Lengkapi Data", Toast.LENGTH_SHORT).show();
                }else if(mStringImageuri.equals("")){
                    Toast.makeText(InputDaganganActivity.this, "Silahkan Upload Gambar", Toast.LENGTH_SHORT).show();
                }else {
                    uploadFile();
                }
            }
        });


    }
    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }

    private void cariFile(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            mImageUri = data.getData();
            mStringImageuri = data.getData().toString();
            Toast.makeText(this, "ZZ "+mImageUri.toString(), Toast.LENGTH_SHORT).show();
            Picasso.get().load(mImageUri).into(input_dagangan_preview_gambar);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        StorageReference storageReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
        storageReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoStringLink = uri.toString();
                                simpanDagangan(photoStringLink);
                                Log.d("TES UPLOAD FIREBASE", "ZZZZZZZZZ " + photoStringLink);
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TES UPLOAD FIREBASE", "Error :" + e.getMessage());
                    }
                });
    }

    private void simpanDagangan(String photoUrl){
        String mNama = input_dagangan_nama.getText().toString();
        String mHarga = input_dagangan_harga.getText().toString();
        String mDonasi = input_dagangan_donasi.getText().toString();
        String mPoint = input_dagangan_est_point.getText().toString();
        String mStok = input_dagangan_stok.getText().toString();
        String mDeskripsi = input_dagangan_deskripsi.getText().toString();

        Map<String, Object> dagangan = new HashMap<>();
        dagangan.put("nama_dagangan",mNama);
        dagangan.put("harga",mHarga);
        dagangan.put("bagi_hasil",mDonasi);
        dagangan.put("est_point",mPoint);
        dagangan.put("stok",mStok);
        dagangan.put("deskripsi",mDeskripsi);
        dagangan.put("dagangan_image", photoUrl);

        db.collection("merchandise")
                .add(dagangan)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Info", "Sukses Disimpan : "+documentReference.getId());
                        if(documentReference.getId().equals(null)){
                            Toast.makeText(InputDaganganActivity.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(InputDaganganActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            emptyForm();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(InputDaganganActivity.this, "Data Gagal Disimpan", Toast.LENGTH_SHORT).show();
                        Log.d("Info","Gagal Disimpan");
                    }
                });
    }
    private void emptyForm(){
        input_dagangan_nama.setText("");
        input_dagangan_harga.setText("");
        input_dagangan_donasi.setText("");
        input_dagangan_est_point.setText("");
        input_dagangan_stok.setText("");
        input_dagangan_deskripsi.setText("");

        input_dagangan_preview_gambar.invalidate();
        input_dagangan_preview_gambar.setImageBitmap(null);
        mStringImageuri = "";
    }
}
