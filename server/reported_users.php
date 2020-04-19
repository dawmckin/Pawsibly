<?php
  include_once('db_connect.php');

  $reported_users = array();
  $gid = $_GET['gid'];
  $firstname = $_GET['firstname'];
  $lastname = $_GET['lastname'];
  $reported = $_GET['reported'];
  $reported_date = $_GET['reported_date'];
  $reported_reason = $_GET['reported_reason'];


  $sql = "SELECT gid, firstname, lastname, reported_date, reported_reason FROM reg_users WHERE reported='Y';";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($gid, $firstname, $lastname, $reported_date, $reported_reason);

  while ($stmt->fetch()) {
    $temp = [
      'gid'=>$gid,
      'name'=>$firstname. " " .$lastname,
      'reported_date'=>$reported_date,
      'reported_reason'=>$reported_reason
    ];

    array_push($reported_users, $temp);
  }

  echo json_encode($reported_users);
?>
