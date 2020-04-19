<?php

  include_once('db_connect.php');

  $names = array();
  $googleid = $_GET['gid'];

  $sql = "SELECT gid FROM shelter_breeder WHERE gid=$googleid;";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($gid);

  while ($stmt->fetch()) {
    $temp = $gid;

    array_push($names, $temp);
  }

  echo json_encode($names);
?>
