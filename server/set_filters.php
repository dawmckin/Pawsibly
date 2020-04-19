<?php
  include_once('db_connect.php');

  $radius = $_POST['radius'];
  $age = $_POST['age'];
  $type = $_POST['type'];
  $size = $_POST['size'];
  $gender = $_POST['gender'];
  $gid = $_POST['gid'];

  $sql_update = "UPDATE filters SET age = '$age', radius = '$radius', type = '$type', size = '$size', gender = '$gender' WHERE gid = '$gid';";

  $sql_insert = "INSERT INTO filters (age, radius, type, size, gender, gid) VALUES ('$age', '$radius', '$type', '$size', '$gender', '$gid');";

  if (mysqli_query($conn, $sql_insert)) {
    $conn->query($sql_insert);
    echo 'Data Submitttted Successfully';
    echo $sql_insert;
  } else if (mysqli_query($conn, $sql_update)) {
    $conn->query($sql_update);
    echo 'Data Submitttted Successfully';
    echo $sql_update;
  } else {
    echo "Error: " . $sql_insert . "<br>" . $conn->error;
    echo "Error: " . $sql_update . "<br>" . $conn->error;
  }


  $conn->close();
?>
