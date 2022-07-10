<?php

try {
    if (!session_start()) {
        throw new Exception("Cannot start session");
    }

    header('Content-type: application/json; charset=utf-8');

    if (!isset($_SESSION['user_id'])) {
        throw new Exception("Not logged in");
    }

    $_SESSION['last_access'] = time();

    $dbh = require 'connect.php';

    $dbh->beginTransaction();

    $dbh->exec('set time_zone = "+00:00";');

    $sth = $dbh->prepare("select creation from users where id = ?;");
    $sth->execute([$_SESSION['user_id']]);
    $creation = $sth->fetchColumn();

    if ($creation === false) {
        throw new Exception("Cannot get creation date");
    }

    $response = [
        "userId" => $_SESSION['user_id'],
        "creation" => $creation
    ];

    $json = json_encode($response, JSON_THROW_ON_ERROR);

    $dbh->commit();
}
catch (Throwable $e) {
    $json = json_encode(['error' => [
        'msg' => $e->getMessage(),
        'code' => $e->getCode()
    ]]);
}

echo "$json\n";

?>
