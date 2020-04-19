<?php
  include_once('db_connect.php');

  $name = 'airbud';
  $dob = '1900-01-01';
  $gender = 'M';
  $type = 'Golden Retriever';
  $size = 'large';
  $bio = 'americas most loveable dog';
  $sbid = 1;

  $sql = "INSERT into pets (name, dob, gender, type, size, bio, sbid) VALUES ('$name', '$dob', '$gender', '$type', '$size', '$bio', '$sbid');";


  if ($conn->query($sql) === TRUE) {
    echo 'Pet Created Successfully';
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }
?>
