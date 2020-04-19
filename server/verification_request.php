<?php
  include_once('db_connect.php');

  $requesting_verification = array();
  $gid = $_GET['gid'];
  $name = $_GET['name'];
  $email = $_GET['email'];
  $location = $_GET['location'];
  $website = $_GET['website'];
  $phone = $_GET['phone'];
  $bio = $_GET['bio'];


  $sql = "SELECT gid, name, email, location, website, phone, bio FROM shelter_breeder WHERE verify_request='Y' AND aid IS NULL;";

  $stmt = $conn->prepare($sql);

  $stmt->execute();

  $stmt->bind_result($gid, $name, $email, $location, $website, $phone, $bio);

  while ($stmt->fetch()) {
    $temp = [
      'gid'=>$gid,
      'name'=>$name,
      'email'=>$email,
      'location'=>$location,
      'website'=>$website,
      'phone'=>$phone,
      'bio'=>$bio
    ];

    array_push($requesting_verification, $temp);
  }

  echo json_encode($requesting_verification);
?>
