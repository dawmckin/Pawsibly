<?php
$host = db.sice.indiana.edu
$user = i494f19_team53
$password = my+sql=i494f19_team53
$db = i494f19_team53
// Connection attempt
$con = mysqli_connect($host,$user,$password,$db);
// Check connection
if(!$con) {
	die("Connection failed: " . mysqli_connect_error());
}

