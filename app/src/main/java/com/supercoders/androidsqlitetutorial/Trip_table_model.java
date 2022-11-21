package com.supercoders.androidsqlitetutorial;

public class Trip_table_model {
    String id="";
    String trip_name="";
    String trip_destination="";
    String trip_date="";
    String trip_require="";
    String trip_description="";
    String trip_created_at="";

    public Trip_table_model(String id, String trip_name, String trip_destination, String trip_date, String trip_require, String trip_description, String trip_created_at) {
        this.id = id;
        this.trip_name = trip_name;
        this.trip_destination = trip_destination;
        this.trip_date = trip_date;
        this.trip_require = trip_require;
        this.trip_description = trip_description;
        this.trip_created_at = trip_created_at;
    }

    public Trip_table_model() {

    }

    public String getTripId() {
        return id;
    }

    public void updateTripId(String id) {
        this.id = id;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void updateTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getTrip_destination() {
        return trip_destination;
    }
    public void updateTrip_destination(String trip_destination) {
        this.trip_destination = trip_destination;
    }

    public String getTrip_date() {
        return trip_date;
    }

    public void updateTrip_date(String trip_date) {
        this.trip_date = trip_date;
    }

    public String getTrip_require() {
        return trip_require;
    }

    public void UpdateTrip_require(String trip_require) {
        this.trip_require = trip_require;
    }


    public String getTrip_description() {
        return trip_description;
    }

    public void updateTrip_description(String trip_description) {
        this.trip_description = trip_description;
    }

    public String getTrip_created_at() {
        return trip_created_at;
    }

    public void updateTrip_created_at(String trip_created_at) {
        this.trip_created_at = trip_created_at;
    }
}
