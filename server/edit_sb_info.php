<?php
  include_once('db_connect.php');

  $name = $_POST['name'];
  $manager = $_POST['manager'];
  $location = $_POST['location'];
  $website = $_POST['website'];
  $phone = $_POST['phone'];
  $bio = $_POST['bio'];
  $gid = $_POST['gid'];

  $sql = "UPDATE shelter_breeder SET name = $name, manager = $manager, location = $location, website = $website, phone = $phone, bio = $bio WHERE gid = $gid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submitttted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
