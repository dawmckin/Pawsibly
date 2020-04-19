<?php

  include_once('db_connect.php');

  $pets = array();
  $pid = $_GET['pid'];
  $name = $_GET['name'];
  $gender = $_GET['gender'];
  $type = $_GET['type'];
  $bio = $_GET['bio'];
  $picture = $_GET['picture'];
  $gid = $_GET['gid'];

  $sql = "SELECT pets.pid, pets.name, pets.gender, pets.type, pets.bio, pets.picture, DATEDIFF(DAY, pets.dob, GETDATE())/365.25 AS age
  FROM pets, reg_users
  INNER JOIN filters ON reg_users.gid = filters.gid
  INNER JOIN connections ON reg_users.rid = connections.rid
  WHERE reg_users.gid = $gid AND pets.gender = filters.gender AND pets.type = filters.type AND pets.size = filters.size AND age <= filters.age;";

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

    array_push($pets, $temp);
  }

  echo json_encode($pets);
?>
