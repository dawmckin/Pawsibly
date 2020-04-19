<?php

  include_once('db_connect.php');

  $pet_data = array();
  $pid = $_GET['pid'];
  $name = $_GET['name'];
  $gender = $_GET['gender'];
  $type = $_GET['type'];
  $bio = $_GET['bio'];
  $picture = $_GET['picture'];

  $sql = "SELECT pid, name, gender, type, bio, picture FROM pets";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($pid, $name, $gender, $type, $bio, $picture);

  while ($stmt->fetch()) {
    $temp = [
      'pid'=>$pid,
      'name'=>$name,
      'gender'=>$gender,
      'type'=>$type,
      'bio'=>$bio,
      'picture'=>$picture
    ];

    array_push($pet_data, $temp);
  }

  echo json_encode($pet_data);
?>
