<?php

require_once('./helper.php');

$name = $_POST['name'];
$price = $_POST['price'];
$duration = $_POST['duration'];
$description = $_POST['description'];

$query = "INSERT INTO courses(name, price, duration, description) VALUES('$name', '$price', '$duration', '$description')";
$sql = mysqli_query($db_connect, $query);

if ($sql) {
    echo json_encode(array('message' => 'Course is created!'));
} else {
    echo json_encode(array('message' => 'Failed to create course!'));
}
