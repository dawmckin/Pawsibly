<?php
  include_once('db_connect.php');

  $admin_id = array();
  $gid = $_GET['gid'];
  $aid = $_GET['aid'];

  $sql = "SELECT sbid FROM shelter_breeder WHERE gid=$gid;";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($aid);

  while ($stmt->fetch()) {
    $temp = $aid;

    array_push($admin_id, $temp);
  }

  echo json_encode($admin_id);
?>
