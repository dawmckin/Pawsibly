<?php
  include_once('db_connect.php');

  $gid = 113398289475853477443;

  $sql = "SELECT * FROM reg_user WHERE gid == '$gid';";

  $result = mysqli_query($conn, $sql);

  if (mysqli_num_row($result) > 0) {
    echo "Login Successfull!!! Welcome User.";
  } else {
    echo "Login not Successfull.";
  }
?>
