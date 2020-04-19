<?php
  include_once('db_connect.php');

  $lname = $_POST['lastname'];
  $fname = $_POST['firstname'];
  $dob = $_POST['dob'];
  $gender = $_POST['gender'];
  $email = $_POST['email'];
  $phone = $_POST['phone'];
  $gid = $_POST['gid'];

  $sql = "INSERT into reg_users (lastname, firstname, dob, gender,  email, phone, gid) VALUES ('$lname', '$fname', '$dob', '$gender', '$email', '$phone', '$gid');";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submit Successfully';
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
