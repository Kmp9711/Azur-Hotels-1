<?php
/**
 * @package AzurWeb/Webservice
 */

 /**
  * Partie POST de l'API Webservice
  */
function api_post($var){
  // A faire
  switch($var){
    case ("req_login"):
      $get = json_decode(stripslashes($_POST['req_login']));
      // Get data from object
      $mail = $get->mail; // Get name you send
      $mdp = $get->mdp; // Get age of user

      // Faire la requête sql (Fake query pour le moment)
      $userResult = null;
      $tab = array();

      $tab['users'] = array(
        array("mail" => "santos@gmail.com", "mdp" => "mdp971", "nom" => "Dacuna", "prenom" => "Santos"),
        array("mail" => "laffont@gmail.com", "mdp" => "laffont971", "nom" => "Laffont", "prenom" => "Lise")
      );

      foreach ($tab['users'] as $user) {
        if ($user['mail'] == $mail && $user['mdp'] == $mdp){
          $userResult = $user;
        }
      }
      // On envoie l'utilisateur récuperé en JSON
      print(json_encode($userResult));
    break;

    case ("req_annuler_reserver"):
      $get = json_decode(stripslashes($_POST['req_annuler_reserver']));
      // Get data from object
      $num_reservation = $get->num_reservation;
      $code_access = $get->code_access;

      $tab['reservations'] = array(
        array("id_reservation" => "0", "code_access" => "12345"),
        array("id_reservation" => "1", "code_access" => "isaac971")
      );

      $result = cancelReservation($num_reservation, $code_access);

      // On envoie l'utilisateur récuperé en JSON
      print(json_encode($result));

    break;

    default:
      print("La variable appelée n'existe pas.");
  }
}
?>
