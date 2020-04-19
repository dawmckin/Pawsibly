<?php
  include_once('db_connect.php');

  $gid = $_POST['gid'];
  $reported_reason = $_POST['reported_reason'];

  $sql = "UPDATE reg_users SET reported_reason = $reported_reason, reported = 'Y', reported_date = CURRENT_TIMESTAMP WHERE gid = $gid;";

  if ($conn->query($sql) === TRUE) {
    echo 'Report Submitted Successfully';
    echo $sql;
  } else {
    echo "Error: " . $sql . "<br>" . $conn->error;
  }

  $conn->close();
?>
