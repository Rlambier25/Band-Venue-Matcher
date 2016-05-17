# Band Tracker

#### Will track Bands and their tour locations, {5/15/2016}

#### By Reed Lambier

## Description

This application will allow users to enter a band of their choice. After entry is complete than can create and attach a venue to their band. This creates a list, or history, of shows for the band.  

## Setup/Installation Requirements

* Inside terminal: cd desktop
* Clone repository: https://github.com/77paradox77/Java-Week4-codeReview-.git
* Inside terminal: cd Java-Week4-codeReview-
* Install Gradle(if it isn't already installed)
* In another window of terminal launch POSTGRES and leave it running


## Setup Database and tables for creating entire DATABASE from scratch

* enter into terminal: $ psql
* #CREATE DATABASE band_tracker;
* Go back into BASH and type: "$ psql band_tracker < band_tracker.sql" to import the database
* In psql enter the following: \c band_tracker;
* #CREATE TABLE bands (id serial PRIMARY KEY, name varchar);
* #CREATE TABLE venues (id serial PRIMARY KEY, name varchar);
* #CREATE TABLE bands_venues(id serial PRIMARY KEY, band_id int, venue_id int);

## Now that our DATABASE's and tables are setup we can now make our test database and launch gradle.

* #CREATE DATABASE band_tracker_test WITH TEMPLATE band_tracker;
* enter "$ gradle run" in the terminal. EX: Java-Week4-codeReview-$ gradle run
* In your browser go to "localhost:4567"


## Known Bugs

Also, CSS construction is currently pending. Using bootstrap field to not take empty fields.

## Support and contact details

If you have any questions or concerns please feel free to contact me at: Rlambier46@gmail.com

## Technologies Used

* Java
* HTML5
* CSS3
* Spark
* Velocity Template Engine
* SQL

### License

*{Licensed under MIT}*

Copyright (c) 2016 Reed Lambier
