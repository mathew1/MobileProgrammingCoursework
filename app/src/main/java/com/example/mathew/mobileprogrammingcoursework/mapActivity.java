package com.example.mathew.mobileprogrammingcoursework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mathew on 08/12/2015.
 */
public class mapActivity extends FragmentActivity  {

    List<databaseInfo> mapDataList;
    private Marker[] mapDataMarkerList = new Marker[10];
    private GoogleMap mapStarSigns;
   private float markerColours[] = {210.0f, 120.0f, 300.0f, 330.0f, 270.0f,
            340.0f, 350.0f, 360.0f, 370.0f, 380.0f};

    private LatLng latlangEKCentre = new LatLng(55.7591402, -4.1883331);

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.map_view);

        mapDataList = new ArrayList<databaseInfo>();
        databaseInfoDBMgr mapDB = new databaseInfoDBMgr(this, "trainstations.s3db", null,1);
        try{
            mapDB.dbCreate();
        }catch (IOException e)
        {
            e.printStackTrace();
        }

        mapDataList = mapDB.allMapData();
        SetUpMap();
        AddMarkers();
    }

    public void SetUpMap()
    {
        mapStarSigns = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        if (mapStarSigns != null){
            mapStarSigns.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangEKCentre,  12));
            mapStarSigns.setMyLocationEnabled(true);
            mapStarSigns.getUiSettings().setCompassEnabled(true);
            mapStarSigns.getUiSettings().setMyLocationButtonEnabled(true);
            mapStarSigns.getUiSettings().setRotateGesturesEnabled(true);
        }
    }

    public void AddMarkers()
    {
        MarkerOptions marker;
        databaseInfo mapData;
        String mrkTitle;
        String mrkText;

        for(int i = 0; i < mapDataList.size(); i++)
        {
            mapData = mapDataList.get(i);
            mrkTitle = mapData.getTrainName();
            mrkText = "Train Address: " + mapData.getTrainAddress();
            marker = SetMarker(mrkTitle,mrkText,new LatLng(mapData.getLatitude(), mapData.getLongitude()),markerColours[1], true);
            mapDataMarkerList[i] = mapStarSigns.addMarker(marker);
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor)
    {
        float anchorX;
        float anchorY;

        if(centreAnchor)
        {
            anchorX = 0.5f;
            anchorY = 0.5f;
        }
        else
        {
            anchorX = 0.5f;
            anchorY = 1.0f;
        }

        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);

        return marker;
    }
}


