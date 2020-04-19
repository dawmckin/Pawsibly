<?php

  include_once('db_connect.php');

  $pets = array();
  $pid = $_GET['pid'];
  $name = $_GET['name'];
  $type = $_GET['type'];
  $sbid = $_GET['sbid'];
  $gid = $_GET['gid'];

  $sql = "SELECT pets.pid, pets.name, pets.type FROM pets
  INNER JOIN shelter_breeder ON pets.sbid = shelter_breeder.sbid
  INNER JOIN connections ON pets.pid = connections.pid
  WHERE shelter_breeder.gid = $gid AND connections.matched='Y';";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($pid, $name, $type);

  while ($stmt->fetch()) {
    $temp = [
      'pid'=>$pid,
      'name'=>$name,
      'type'=>$type
    ];

    array_push($pets, $temp);
  }

  echo json_encode($pets);
?>
