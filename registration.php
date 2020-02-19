<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
$firstname = $_POST['firstname'];
$lastname = $_POST['lastname'];
$password = $_POST['password'];
$phone = $_POST['phone'];
$email = $_POST['email'];
if($firstname == '' || $lastname == '' || $password == '' || $phone == '' || $email == ''){
echo 'please fill all values';
}else{
require_once('db_connect.php);
$sql = "SELECT * FROM reg_users WHERE email='$email' OR phone='$phone'";
$check = mysqli_fetch_array(mysqli_query($con,$sql));
if(isset($check)){
echo 'Phone or email already exist';
}else{
$sql = "INSERT INTO reg_users(firstname,lastname,password,email,phone) VALUES('$firstname','lastname','password','email','phone')";
if(mysqli_query($con,$sql)){
echo 'Successfully registerd';
}else{
echo 'Please make sure all information is filled out correctly';
}
}
mysqli_close($con);
}
}else{
echo 'error';
}
