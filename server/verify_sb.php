<?php
  include_once('db_connect.php');

  $gid = $_POST['gid'];
  $aid = $_POST['aid'];

  $sql = "UPDATE shelter_breeder SET aid = $aid WHERE gid = $gid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submitttted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
