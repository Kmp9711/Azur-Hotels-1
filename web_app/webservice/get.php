<?php
$get = json_decode(stripslashes($_POST['req']));
// Get data from object
$name = $get->name; // Get name you send
$age = $get->age; // Get age of user

 ?>
