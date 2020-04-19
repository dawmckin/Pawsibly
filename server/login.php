<?php

  include_once('db_connect.php');

  $names = array();
  $googleid = $_GET['gid'];

  $sql = "SELECT gid FROM reg_users WHERE gid=$googleid;";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($googleemail);

  while ($stmt->fetch()) {
    $temp = $googleid;

    array_push($names, $temp);
    }

  echo json_encode($names);
  ?>
