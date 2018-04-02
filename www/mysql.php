<?php
 
/*
* Database Constants
* Make sure you are putting the values according to your database here 
*/
define('DB_HOST','localhost');
define('DB_USERNAME','');
define('DB_PASSWORD','');
define('DB_NAME', '');
 
//Connecting to the database
$conn = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
 
//checking the successful connection
if($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
 
//making an array to store the response
$response = array(); 
 
//if there is a post request move ahead 
if($_SERVER['REQUEST_METHOD']=='POST'){
 
 //getting the parameters from request 
 $DATE = $_POST['DATE']; 
 $DURATION = $_POST['DURATION']; 
 $PAINTINTENSITY = $_POST['PAINTINTENSITY']; 
 $TYPE = $_POST['TYPE']; 
 $PRESYMPTOMS = $_POST['PRESYMPTOMS']; 
 $PAINLOCATION = $_POST['PAINLOCATION']; 
 $OTHERSYMPTOMS = $_POST['OTHERSYMPTOMS']; 
 $MEDICINETAKEN = $_POST['MEDICINETAKEN']; 
 $LOCATION = $_POST['LOCATION']; 
 $TRIGGERS = $_POST['TRIGGERS']; 
 $MENSTRUATION = $_POST['MENSTRUATION']; 

 
 //creating a statement to insert to database 
 $stmt = $conn->prepare("INSERT INTO migreeni (DATE, DURATION, PAINTINTENSITY, TYPE, PRESYMPTOMS, PAINLOCATION, OTHERSYMPTOMS, MEDICINETAKEN, LOCATION, TRIGGERS, MENSTRUATION) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
 
 //binding the parameter to statement 
 $stmt->bind_param("sssssssssss", $DATE, $DURATION,  $PAINTINTENSITY, $TYPE, $PRESYMPTOMS, $PAINLOCATION, $OTHERSYMPTOMS, $MEDICINETAKEN, $LOCATION, $TRIGGERS, $MENSTRUATION );
 
 //if data inserts successfully
 if($stmt->execute()){
 //making success response 
 $response['error'] = false; 
 $response['message'] = 'Migraine saved successfully'; 
 }else{
 //if not making failure response 
 $response['error'] = true; 
 $response['message'] = 'Please try later';
 }
 
}else{
 $response['error'] = true; 
 $response['message'] = "Invalid request"; 
}
 
//displaying the data in json format 
echo json_encode($response);