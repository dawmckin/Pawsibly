<?php
  include_once('db_connect.php');

  $lname = $_POST['lastname'];
  $fname = $_POST['firstname'];
  $gender = $_POST['gender'];
  $phone = $_POST['phone'];
  $bio = $_POST['bio'];
  $gid = $_POST['gid'];

  $sql = "UPDATE reg_users SET lastname = $lname, firstname = $fname, gender = $gender, phone = $phone, bio = $bio WHERE gid = $gid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submitttted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
