<?php

require_once('./helper.php');

parse_str(file_get_contents('php://input'), $value);

$id = $value['id'];
$name = $value['name'];
$price = $value['price'];
$duration = $value['duration'];
$description = $value['description'];

$query = "UPDATE courses SET name='$name', price='$price', duration='$duration', description='$description' WHERE id='$id'";
$sql = mysqli_query($db_connect, $query);

if ($sql) {
    echo json_encode(array('message' => 'Course is updated!'));
} else {
    echo json_encode(array('message' => 'Failed to update course!'));
}
