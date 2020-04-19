<?php
  include_once('db_connect.php');

  $gid = $_POST['gid'];

  $sql = "UPDATE reg_users SET acct_terminated = 'Y', reported = NULL WHERE gid = $gid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Data Submitttted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
