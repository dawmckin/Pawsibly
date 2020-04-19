<?php
  include_once('db_connect.php');

  $reported_sb = array();
  $gid = $_GET['gid'];
  $name = $_GET['name'];
  $phone = $_GET['phone'];
  $reported = $_GET['reported'];
  $reported_date = $_GET['reported_date'];
  $reported_reason = $_GET['reported_reason'];


  $sql = "SELECT gid, name, phone, reported_date, reported_reason FROM shelter_breeder WHERE reported='Y';";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($gid, $name, $phone, $reported_date, $reported_reason);

  while ($stmt->fetch()) {
    $temp = [
      'gid'=>$gid,
      'name'=>$name,
      'phone'=>$phone,
      'reported_date'=>$reported_date,
      'reported_reason'=>$reported_reason
    ];

    array_push($reported_sb, $temp);
  }

  echo json_encode($reported_sb);
?>
