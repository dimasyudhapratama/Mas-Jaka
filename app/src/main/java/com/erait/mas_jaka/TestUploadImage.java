package com.erait.mas_jaka;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class TestUploadImage extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private StorageReference mStorageRef;

    Button btn_cari_gambar, upload_gambar;
    ImageView img_gambar;
    private Uri mImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_upload_image);
        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        btn_cari_gambar = findViewById(R.id.btn_cari_gambar);
        img_gambar = findViewById(R.id.img_gambar);
        upload_gambar = findViewById(R.id.upload_gambar);

        btn_cari_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        upload_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });
    }

    private void openFileChooser(){
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
            Toast.makeText(this, "ZZ "+mImageUri.toString(), Toast.LENGTH_SHORT).show();
            Picasso.get().load(mImageUri).into(img_gambar);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){
        StorageReference storageReference = mStorageRef.child(System.currentTimeMillis()+"."+ getFileExtension(mImageUri));
        storageReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String photoStringLink = uri.toString();
                                uploadData(photoStringLink);
                                Log.d("TES UPLOAD FIREBASE", "ZZZZZZZZZ "+ photoStringLink);
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TES UPLOAD FIREBASE","Error :"+e.getMessage() );
                    }
                });
    }
    private void uploadData(String data){

        Toast.makeText(TestUploadImage.this, "ZZZ "+data, Toast.LENGTH_SHORT).show();
    }
}
