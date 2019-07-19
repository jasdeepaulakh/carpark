
This is a SpringBoot App, just run the CarparkApplication and it should run.
All storage is in memory - just a HashMap.
Assuming only one car park for now, so GET calls don't have a carparkID


Getting all car park info [GET]
===============================
curl -X GET http://localhost:8080/

Getting space by floor number [GET] (not passing in carpark id, just assuming there's one for now)
===================================
curl -X GET http://localhost:8080/floor/<floornumber>

Park in a space [POST]
======================
curl -X POST http://localhost:8080/park/<floor>/<space>


Free up space
==============
To do.

Other Stats / logic
===================
TO DO (i.e. cars on the move, free spaces and likelihood of getting one ...)