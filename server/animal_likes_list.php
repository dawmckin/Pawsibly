<?php

  include_once('db_connect.php');

  $users = array();
  $pid = $_GET['pid'];
  $lastname = $_GET['lastname'];
  $firstname = $_GET['firstname'];
  $rid = $_GET['rid'];
  $gid = $_GET['gid'];

  $phone = $_GET['phone'];
  $gender = $_GET['gender'];
  $bio = $_GET['bio'];


  $sql = "SELECT reg_users.lastname, reg_users.firstname, reg_users.phone, reg_users.gender, reg_users.bio, reg_users.gid FROM reg_users INNER JOIN connections ON reg_users.rid = connections.rid WHERE connections.pid=$pid AND connections.result='Y';";


  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($lastname, $firstname, $phone, $gender, $bio, $gid);

  while ($stmt->fetch()) {
    $temp = [
      'fullname'=>$firstname." ".$lastname,
      'phone'=>$phone,
      'gender'=>$gender,
      'bio'=>$bio,
      'gid'=>$gid
    ];

    array_push($users, $temp);
  }

  echo json_encode($users);
?>
