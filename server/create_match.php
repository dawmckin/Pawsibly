<?php
  include_once('db_connect.php');

  $pid = $_POST['pid'];
  $rid = $_POST['rid'];

  $sql = "UPDATE connections SET matched = 'Y' WHERE pid = $pid AND rid = $rid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submitttted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
