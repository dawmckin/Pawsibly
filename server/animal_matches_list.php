<?php

  include_once('db_connect.php');

  $users = array();
  $pid = $_GET['pid'];
  $lastname = $_GET['lastname'];
  $firstname = $_GET['firstname'];
  $rid = $_GET['rid'];
  $gid = $_GET['gid'];

  $sql = "SELECT reg_users.lastname, reg_users.firstname FROM reg_users INNER JOIN connections ON reg_users.rid = connections.rid WHERE connections.pid = $pid AND connections.matched='Y';";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($lastname, $firstname);

  while ($stmt->fetch()) {
    $temp = [
      'fullname'=>$firstname." ".$lastname
    ];

    array_push($users, $temp);
  }

  echo json_encode($users);
?>
