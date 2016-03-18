<?php
/**
 * @package AzurWeb/Webservice
 */

// Requires
require_once('../fonction.php');
require_once('get.php');
require_once('post.php');

if ((isset($_GET["action"]))&&(isset($_GET["var"]))) {
  switch ($_GET["action"]) {

    case ("get") :
      api_get($_GET["var"]);
    break;

    case ("post") :
      api_post($_GET["var"]);
    break;

    default: print("L'action appelée n'existe pas.");
  }
} else {
  print("Erreur lors de l'utilisation de l'API");
}
?>
