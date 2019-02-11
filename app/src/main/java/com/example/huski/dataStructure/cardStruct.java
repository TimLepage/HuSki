package com.example.huski.dataStructure;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class cardStruct implements Parcelable {
    private String name;
    private UUID uuid;
    private gpsStruct gps;

    public cardStruct(String name){
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.gps = new gpsStruct(0,90,200); //5.7445043 + 10,45.1825309 ,212);
    }

    public cardStruct(String name, String uuid){
        this.name = name;
        this.uuid = UUID.fromString(uuid);
        this.gps = new gpsStruct(0,90,200); //5.7445043 + 10,45.1825309 ,212);
    }


    private cardStruct(Parcel in) {
        this.name = in.readString();
    }

    public int describeContents() {
        return 0;
    }


    public gpsStruct getGps() {
        return gps;
    }

    public void setGps(gpsStruct gps) {
        this.gps = gps;
    }
    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.name);
    }

    public static final Parcelable.Creator<cardStruct> CREATOR = new Parcelable.Creator<cardStruct>() {
        public cardStruct createFromParcel(Parcel in) {
            return new cardStruct(in);
        }
        public cardStruct[] newArray(int size) {
            return new cardStruct[size];
        }
    };
}