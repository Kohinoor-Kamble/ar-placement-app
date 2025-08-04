package com.example.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
//import com.google.ar.sceneform.ux.BaseArFragment;

public class ARActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private AnchorNode lastAnchorNode;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aractivity);
 //        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        arFragment=(ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        arFragment.setOnTapArPlaneListener((HitResult hitResult, Plane plane, MotionEvent motionEvent)->{
            if(plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) return;

            if(lastAnchorNode != null){
                arFragment.getArSceneView().getScene().removeChild(lastAnchorNode);
                lastAnchorNode.getAnchor().detach();
                lastAnchorNode.setParent(null);
                lastAnchorNode=null;

            }

            Anchor anchor=hitResult.createAnchor();
            lastAnchorNode=new AnchorNode(anchor);
            lastAnchorNode.setParent(arFragment.getArSceneView().getScene());

            MaterialFactory.makeOpaqueWithColor(this,new Color(android.graphics.Color.RED)).thenAccept(material -> {
                ModelRenderable cubeRenderable= ShapeFactory.makeCube(
                        new Vector3(0.1f, 0.1f,0.1f),
                        new Vector3(0f, 0.05f,0f),
                        material
                );

                TransformableNode node=new TransformableNode(arFragment.getTransformationSystem());
                node.setParent(lastAnchorNode);
                node.setRenderable(cubeRenderable);
                node.select();
            }).exceptionally(throwable -> {
                Toast.makeText(this,"Error loading model", Toast.LENGTH_SHORT).show();
                return null;
            });

        });
    }
}