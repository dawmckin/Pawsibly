<?php

  include_once('db_connect.php');

  $names = array();
  $googleid = $_GET['gid'];

  $sql = "SELECT rid FROM reg_users WHERE gid=$googleid;";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($gid);

  while ($stmt->fetch()) {
    $temp = $gid;

    array_push($names, $temp);
  }

  echo json_encode($names);
?>
