<?php

  include_once('db_connect.php');

  $users = array();
  $pid = $_GET['pid'];
  $name = $_GET['name'];
  $rid = $_GET['rid'];
  $gid = $_GET['gid'];

  $sql = "SELECT pets.name FROM pets
  INNER JOIN connections ON pets.pid = connections.pid
  WHERE connections.rid=$rid AND connections.matched='Y';";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($name);

  while ($stmt->fetch()) {
    $temp = [
      'name'=>$name
    ];

    array_push($users, $temp);
  }

  echo json_encode($users);
?>
