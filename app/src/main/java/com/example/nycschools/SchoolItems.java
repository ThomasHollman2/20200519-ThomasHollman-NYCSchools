package com.example.nycschools;

import android.os.Parcel;
import android.os.Parcelable;

public class SchoolItems implements Parcelable {

    String school_name = "?";
    String num_of_sat_test_takers = "?";
    String sat_critical_reading_avg_score = "?";
    String sat_math_avg_score = "?";
    String sat_writing_avg_score = "?";

    SchoolItems(){

    }

    protected SchoolItems(Parcel in) {
        school_name = in.readString();
        num_of_sat_test_takers = in.readString();
        sat_critical_reading_avg_score = in.readString();
        sat_math_avg_score = in.readString();
        sat_writing_avg_score = in.readString();
    }

    public static final Creator<SchoolItems> CREATOR = new Creator<SchoolItems>() {
        @Override
        public SchoolItems createFromParcel(Parcel in) {
            return new SchoolItems(in);
        }

        @Override
        public SchoolItems[] newArray(int size) {
            return new SchoolItems[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(school_name);
        dest.writeString(num_of_sat_test_takers);
        dest.writeString(sat_critical_reading_avg_score);
        dest.writeString(sat_math_avg_score);
        dest.writeString(sat_writing_avg_score);
    }


}
