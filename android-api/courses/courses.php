<?php

require_once('./helper.php');

$query = "SELECT * FROM courses ORDER BY id DESC";
$sql = mysqli_query($db_connect, $query);

if ($sql) {
    $result = array();
    while ($row = mysqli_fetch_array($sql)) {
        array_push($result, array(
            'id' => $row['id'],
            'name' => $row['name'],
            'price' => $row['price'],
            'duration' => $row['duration'],
            'description' => $row['description'],
            'created_at' => $row['created_at'],
        ));
    }
    echo json_encode(array('courses' => $result));
}
