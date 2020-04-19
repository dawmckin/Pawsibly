<?php
  include_once('db_connect.php');

  $name = $_POST['name'];
  $dob = $_POST['dob'];
  $gender = $_POST['gender'];
  $type = $_POST['type'];
  $size = $_POST['size'];
  $bio = $_POST['bio'];
  $picture = $_POST['picture'];
  $sbid = $_POST['sbid'];

  $sql = "INSERT into pets (name, dob, gender, type, size, bio, picture, sbid)  VALUES ('$name', '$dob', '$gender', '$type', '$size', '$bio', '$picture', '$s', '$sbid');";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submit Successfully';
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
